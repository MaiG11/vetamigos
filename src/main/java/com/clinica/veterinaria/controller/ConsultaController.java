package com.clinica.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clinica.veterinaria.model.Animal;
import com.clinica.veterinaria.model.Consulta;
import com.clinica.veterinaria.model.Veterinario;
import com.clinica.veterinaria.repository.AnimalRepository;
import com.clinica.veterinaria.repository.ConsultaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ConsultaController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @GetMapping("/consulta")
    public String carregaConsulta(HttpSession sessao, Model model) {
        if (sessao.getAttribute("veterinario") == null)
            return "redirect:/login";

        // Checklist: Lista aparece em ordem alfabética
        model.addAttribute("animais", animalRepository.findAllByOrderByNomeAsc());
        model.addAttribute("consulta", new Consulta());

        return "consulta/gestao";
    }

    @PostMapping("/consulta/salvar")
    public String cadastrarConsulta(
            @ModelAttribute Consulta consulta,
            HttpSession sessao,
            RedirectAttributes redirectAttributes) {

        if (sessao.getAttribute("veterinario") == null)
            return "redirect:/login";

        Veterinario veterinario = (Veterinario) sessao.getAttribute("veterinario");
        consulta.setVeterinario(veterinario);

        Animal animal = animalRepository.findById(consulta.getAnimal().getId()).orElseThrow();

        if (consulta.getTipo().equals("ENTRADA")) {
            // Quantidade atualiza após entrada
            animal.setQuantidadeConsultas(animal.getQuantidadeConsultas() + consulta.getQuantidade());
        } else {
            // Validação para não deixar negativo (lógica correta)
            if (animal.getQuantidadeConsultas() < consulta.getQuantidade()) {
                redirectAttributes.addFlashAttribute("alerta", 
                    "Erro: O animal " + animal.getNome() + " não tem consultas suficientes!");
                return "redirect:/consulta";
            }
            //  Quantidade atualiza após saída
            animal.setQuantidadeConsultas(animal.getQuantidadeConsultas() - consulta.getQuantidade());
            
            //  Alerta aparece SOMENTE em saída e usa RedirectAttributes
            if (animal.getQuantidadeConsultas() < animal.getQuantidadeMinima()) {
                redirectAttributes.addFlashAttribute("alerta", 
                    "ATENÇÃO: O animal " + animal.getNome() + 
                    " está abaixo da quantidade mínima! Atual: " + animal.getQuantidadeConsultas());
            }
        }

        consultaRepository.save(consulta);
        animalRepository.save(animal);

        return "redirect:/consulta";
    }
}