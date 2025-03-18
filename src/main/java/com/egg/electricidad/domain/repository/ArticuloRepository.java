package com.egg.electricidad.domain.repository;

import com.egg.electricidad.domain.entity.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, UUID> {
    Articulo findByNroArticulo(Integer nroArticulo);

    @Query(value = "SELECT MAX(a.nro_articulo) FROM t_articulo a", nativeQuery = true)
    Integer buscarUltimoNumero();
}
