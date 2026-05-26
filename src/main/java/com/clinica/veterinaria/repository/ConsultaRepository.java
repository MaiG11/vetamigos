package com.clinica.veterinaria.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.veterinaria.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    
}
