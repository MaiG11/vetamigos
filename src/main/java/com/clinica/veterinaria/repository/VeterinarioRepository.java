package com.clinica.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.veterinaria.model.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{


        Veterinario findByLogin(String login);

}

    

