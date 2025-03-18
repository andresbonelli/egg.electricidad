package com.egg.electricidad.service;

import com.egg.electricidad.api.dto.CrearArticuloDTO;
import com.egg.electricidad.domain.entity.Articulo;
import com.egg.electricidad.domain.repository.ArticuloRepository;
import com.egg.electricidad.domain.repository.FabricaRepository;
import com.egg.electricidad.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticuloService {

    private final ArticuloRepository articuloRepository;
    private final FabricaRepository fabricaRepository;

    public List<Articulo> listarArticulos() {
        return articuloRepository.findAll();
    }

    public Articulo buscarArticuloPorNro(Integer nroArticulo) {
        return articuloRepository.findByNroArticulo(nroArticulo);
    }

    public void crearArticulo(CrearArticuloDTO request) {
        Integer proximoNroArticulo =
                articuloRepository.count() == 0
                        ? 1
                        : articuloRepository.buscarUltimoNumero() + 1;
        Articulo articuloNuevo = new Articulo();
        articuloNuevo.setNroArticulo(proximoNroArticulo);
        articuloNuevo.setNombre(request.nombre());
        articuloNuevo.setDescripcion(request.descripcion());
        articuloNuevo.setFabrica(fabricaRepository
                .findById(UUID.fromString(request.fabricaId()))
                .orElseThrow(RecordNotFoundException::new));
        articuloRepository.save(articuloNuevo);
    }

    public void eliminarArticulo(Integer nroArticulo) {
        Articulo articulo = articuloRepository.findByNroArticulo(nroArticulo);
        articuloRepository.delete(articulo);
    }

    private void validar(Integer nroArticulo) {
        if (articuloRepository.findByNroArticulo(nroArticulo) != null) {
            throw new IllegalArgumentException("El articulo ya existe");
        }
    }

}
