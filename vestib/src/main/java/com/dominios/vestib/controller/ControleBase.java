package com.dominios.vestib.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ControleBase {
    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }
}
