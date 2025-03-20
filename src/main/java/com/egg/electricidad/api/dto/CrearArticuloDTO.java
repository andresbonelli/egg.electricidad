package com.egg.electricidad.api.dto;

import java.util.UUID;

public record CrearArticuloDTO (
        String nombre,
        String descripcion,
        UUID fabricaId
) {
}
