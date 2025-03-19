package com.egg.electricidad.api.controller;

import com.egg.electricidad.service.ArticuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articulo")
public class ArticuloController {

    private final ArticuloService articuloService;

    @GetMapping("/lista")
    public String listaArticulos(ModelMap model) {
        model.addAttribute("articulos", articuloService.listarArticulos());
        return "listaarticulo";
    }
}
