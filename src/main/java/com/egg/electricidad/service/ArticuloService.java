package com.egg.electricidad.service;

import com.egg.electricidad.api.dto.CrearArticuloDTO;
import com.egg.electricidad.domain.entity.Articulo;
import com.egg.electricidad.domain.repository.ArticuloRepository;
import com.egg.electricidad.domain.repository.FabricaRepository;
import com.egg.electricidad.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticuloService {

    private final ArticuloRepository articuloRepository;
    private final FabricaRepository fabricaRepository;

    @Transactional(readOnly = true)
    public List<Articulo> listarArticulos() {
        return articuloRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Articulo buscarArticuloPorNro(Integer nroArticulo) {
        return articuloRepository.findByNroArticulo(nroArticulo);
    }

    @Transactional
    public synchronized void crearArticulo(CrearArticuloDTO request) {
        Articulo articuloNuevo = generateFromDTO(request);
        Integer proximoNroArticulo =
                articuloRepository.count() == 0
                        ? 1
                        : articuloRepository.buscarUltimoNumero() + 1;
        articuloNuevo.setNroArticulo(proximoNroArticulo);
        articuloRepository.save(articuloNuevo);
    }

    @Transactional
    public void modificarArticulo(Integer nroArticulo, CrearArticuloDTO request) {
        Articulo articulo = articuloRepository.findByNroArticulo(nroArticulo);
        if (null != request.nombre()) {
            articulo.setNombre(request.nombre());
        }
        if (null != request.descripcion()) {
            articulo.setDescripcion(request.descripcion());
        }
        if (null != request.fabricaId()) {
            articulo.setFabrica(fabricaRepository
                    .findById(request.fabricaId())
                    .orElseThrow(RecordNotFoundException::new));
        }
        articuloRepository.save(articulo);
    }

    @Transactional
    public void eliminarArticulo(Integer nroArticulo) {
        Articulo articulo = articuloRepository.findByNroArticulo(nroArticulo);
        articuloRepository.delete(articulo);
    }

    private Articulo generateFromDTO(CrearArticuloDTO request) {
        Articulo articulo = new Articulo();
        articulo.setNombre(request.nombre());
        articulo.setDescripcion(request.descripcion());
        articulo.setFabrica(fabricaRepository
                .findById(request.fabricaId())
                .orElseThrow(RecordNotFoundException::new));
        return articulo;
    }

}
