package com.clinica.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clinica.veterinaria.model.Animal;
import com.clinica.veterinaria.repository.AnimalRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/animais/editar/{id}")
    public String editarAnimal(
        HttpSession sessao,
        @PathVariable Long id, 
        Model model) {
        
        if (sessao.getAttribute("veterinario") == null)
            return "redirect:/login";

        Animal animal = animalRepository.findById(id).orElseThrow();
        model.addAttribute("animal", animal);

        return "animais/editar";
    }

    @GetMapping("/animais")
    public String listarAnimais(
        HttpSession sessao, 
        Model model, 
        @RequestParam(required = false) String buscaAnimal) {
            
        if (sessao.getAttribute("veterinario") == null)
            return "redirect:/login";

        List<Animal> animais;
        if (buscaAnimal != null && !buscaAnimal.isBlank())
            animais = animalRepository.findByNomeContainingIgnoreCase(buscaAnimal);
        else
            animais = animalRepository.findAll();

        model.addAttribute("animais", animais);

        return "animais/lista";
    }

    @PostMapping("/animais/salvar")
    public String salvarAnimal(
        HttpSession sessao, 
        @ModelAttribute Animal animal) {
            
        if (sessao.getAttribute("veterinario") == null)
            return "redirect:/login";
        
        animalRepository.save(animal);

        return "redirect:/animais";
    }

    @PostMapping("/animais/deletar/{id}")
    public String deletarAnimal(HttpSession sessao, @PathVariable Long id) {

        if (sessao.getAttribute("veterinario") == null)
            return "redirect:/login";
        
        animalRepository.deleteById(id);

        return "redirect:/animais";
    }

    
}
