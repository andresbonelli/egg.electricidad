package com.egg.electricidad.api.controller;

import com.egg.electricidad.api.dto.UserRegisterDTO;
import com.egg.electricidad.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo ) {
        if (null != error) {
            modelo.put("error", "Usuario o Contraseña inválidos!");
        }
        return "login";
    }

    @RequestMapping("/registro")
    public String registro() {
        return "registrousuario";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute UserRegisterDTO usuario, ModelMap model) {
        usuarioService.registro(usuario);
        model.put("exito", "Usuario registrado con éxito");
        return "redirect:login";
    }
}
