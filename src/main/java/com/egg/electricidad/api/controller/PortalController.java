package com.egg.electricidad.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PortalController {

    @GetMapping
    public String index() {
        return "inicio";
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }


}
