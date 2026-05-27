package com.clinica.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.clinica.veterinaria.model.Animal;
import com.clinica.veterinaria.repository.AnimalRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AnimalController {
    

    private final PrincipalController principalController;
    @Autowired
    private AnimalRepository animalRepository;

    AnimalController(PrincipalController principalController){
        this.principalController = principalController;
    }

    @GetMapping("/animais")
    public String listarAnimais(HttpSession sessao, Model model, @RequestParam(required = false) String buscaAnimal){
        if (sessao.getAttribute("veterinario") ==null)
            return "redirect:/login";

    List<Animal> animais;
    if (buscaAnimal != null && !buscaAnimal.isEmpty()){
        animais = animalRepository.findByNomeContainingIgnoreCase(buscaAnimal);
    } else {
        animais = animalRepository.findAll();
    }
    model.addAttribute("animais", animais);
    return "animais/lista";
   }
   
 @PostMapping("/animais/salvar")
     public String salvarAnimal(HttpSession sessao, @ModelAttribute Animal animal){
        if (sessao.getAttribute("funcionario") == null)
            return "redirect:/login";
           
        animalRepository.save(animal);

         return "redirect:/animais";
}}
     
    

