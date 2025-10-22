package com.application.rest.RestTecnoPc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    // Cubre la raíz "/" y todas las rutas que no tengan extensión (.css, .js, .png, etc.)
    @GetMapping(value = {"/", "/{path:[^\\.]*}"})
    public String forward() {
        return "forward:/index.html";
    }
}
