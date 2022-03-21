package com.dominios.vestib.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }
}
