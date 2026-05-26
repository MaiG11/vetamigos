package com.clinica.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clinica.veterinaria.model.Veterinario;
import com.clinica.veterinaria.repository.VeterinarioRepository;


import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    
@Autowired
private VeterinarioRepository veterinarioRepository;

@GetMapping("/login")
public String exibirLogin(){
    return"login";
}
  @PostMapping("/login")
    public String realizaProcessamentoLogin(
        @RequestParam String login, 
        @RequestParam String senha, 
        HttpSession sessao, 
        Model model){


        {
            Veterinario vet = veterinarioRepository.findByLogin(login);
            if(vet != null && vet.getSenha().equals(senha)){
                sessao.setAttribute("veterinario", vet);
                return "redirect:/principal";
            }
            model.addAttribute("erro", "Login ou senha inválidos");
            return "login";
        }
    }
}