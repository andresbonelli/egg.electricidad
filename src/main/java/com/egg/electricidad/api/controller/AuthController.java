package com.egg.electricidad.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/registro")
    public String registro() {
        return "registrousuario";
    }
}
