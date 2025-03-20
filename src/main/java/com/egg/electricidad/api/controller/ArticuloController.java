package com.egg.electricidad.api.controller;

import com.egg.electricidad.api.dto.CrearArticuloDTO;
import com.egg.electricidad.service.ArticuloService;
import com.egg.electricidad.service.FabricaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articulo")
public class ArticuloController {

    private final ArticuloService articuloService;
    private final FabricaService fabricaService;

    @GetMapping("/lista")
    public String listaArticulos(ModelMap model) {
        model.addAttribute("articulos", articuloService.listarArticulos());
        return "listaarticulo";
    }

    @GetMapping("/crear")
    public String formularioCrearArticulo(ModelMap model) {
        model.addAttribute("fabricas", fabricaService.listarFabricas());
        return "creararticulo";
    }

    @PostMapping("/cargarnuevo")
    public String crearArticulo(@ModelAttribute @Valid CrearArticuloDTO request) {
        articuloService.crearArticulo(request);
        return "redirect:/articulo/lista";
    }
}
