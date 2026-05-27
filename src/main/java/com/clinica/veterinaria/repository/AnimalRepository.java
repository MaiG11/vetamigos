package com.clinica.veterinaria.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.veterinaria.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{
    
    List<Animal> findByNomeContainingIgnoreCase(String nome);
}
