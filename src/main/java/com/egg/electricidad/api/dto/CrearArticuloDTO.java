package com.egg.electricidad.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CrearArticuloDTO (
        @NotBlank
        @Size(min = 6, message = "El nombre de articulo debe tener al menos 6 caracteres")
        String nombre,
        @NotBlank
        String descripcion,
        UUID fabricaId
) {
}
