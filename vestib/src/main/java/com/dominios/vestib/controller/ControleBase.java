package com.dominios.vestib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ControleBase {
    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String getIndex(Model model) {
        return "index";
    }

}
