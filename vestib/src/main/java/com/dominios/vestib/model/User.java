package com.dominios.vestib.model;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class User {
    @RequestMapping("/login.html")
    public String login() {
        return "login.html";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}
