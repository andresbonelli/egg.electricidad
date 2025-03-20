package com.egg.electricidad.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        @Email
        @NotBlank
        String email,
        String nombre,
        String apellido,
        @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).*$",
                message = "La contraseña debe contener al menos una letra y un número")
        String password,
        String confirmPassword
) {
}
