package com.egg.electricidad.api.controller;

import com.egg.electricidad.service.FabricaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/fabrica")
public class FabricaController {

    private final FabricaService fabricaService;

    @GetMapping("/lista")
    public String listaFabricas(ModelMap model) {
        model.addAttribute("fabricas", fabricaService.listarFabricas());
        return "listafabrica";
    }
}
