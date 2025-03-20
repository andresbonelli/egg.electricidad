package com.egg.electricidad.api.controller;

import com.egg.electricidad.service.FabricaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/crear")
    public String formularioCrearFabrica() {
        return "crearfabrica";
    }

    @PostMapping("/modificar")
    public String modificarFabrica() {
        return "modificarfabrica";
    }

    @PostMapping("/cargarnuevo")
    public String crearFabrica(String nombre) {
        fabricaService.crearFabrica(nombre);
        return "redirect:/fabrica/lista";
    }
}
