package com.egg.electricidad.domain.repository;

import com.egg.electricidad.domain.entity.Fabrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FabricaRepository extends JpaRepository<Fabrica, UUID> {
    Fabrica findByNombre(String nombre);
}
