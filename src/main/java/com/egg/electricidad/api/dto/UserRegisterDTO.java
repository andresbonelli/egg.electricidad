package com.egg.electricidad.api.dto;

public record UserRegisterDTO(
        String email,
        String nombre,
        String apellido,
        String password,
        String confirmPassword
) {
}
