package com.egg.electricidad.api.dto;

public record CrearArticuloDTO (
        String nombre,
        String descripcion,
        String fabricaId
) {
}
