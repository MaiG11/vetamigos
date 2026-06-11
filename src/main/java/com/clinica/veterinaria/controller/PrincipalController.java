package com.clinica.veterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class PrincipalController {

    @GetMapping("/principal")
    public String exibirPrincipal(HttpSession session) {
        if (session.getAttribute("veterinario") == null) {
            return "redirect:/login";
        }
        return "principal";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}