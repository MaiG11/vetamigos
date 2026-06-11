package com.clinica.veterinaria.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especie;
    private String raca;
    private String sexo;
    private LocalDate dataDeNascimento;
    private Double peso;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "Não pode ser negativo")
    private Integer quantidadeConsultas; // Equivale à "quantidade" do Produto

    @NotNull(message = "O mínimo é obrigatório")
    @Min(value = 0, message = "Não pode ser negativo")
    private Integer quantidadeMinima; // Equivale ao "estoqueMinimo" do Produto

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getQuantidadeConsultas() {
        return quantidadeConsultas;
    }

    public void setQuantidadeConsultas(Integer quantidadeConsultas) {
        this.quantidadeConsultas = quantidadeConsultas;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }
}