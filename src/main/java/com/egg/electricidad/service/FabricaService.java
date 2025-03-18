package com.egg.electricidad.service;

import com.egg.electricidad.domain.entity.Fabrica;
import com.egg.electricidad.domain.repository.FabricaRepository;
import com.egg.electricidad.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FabricaService {

    private final FabricaRepository fabricaRepository;

    public List<Fabrica> listarFabricas() {
        return fabricaRepository.findAll();
    }

    public Fabrica buscarFabricaPorNombre(String nombre) {
        return fabricaRepository.findByNombre(nombre);
    }

    public void crearFabrica(String nombre) {
        Fabrica fabrica = new Fabrica();
        fabrica.setNombre(nombre);
        fabricaRepository.save(fabrica);
    }

    public void modificarFabrica(UUID id, String nombre) {
        Fabrica fabrica = fabricaRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        fabrica.setNombre(nombre);
        fabricaRepository.save(fabrica);
    }

    public void eliminarFabrica(UUID id) {
        fabricaRepository.deleteById(id);
    }
}
