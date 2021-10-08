package com.gt.trainee.dto;

import com.gt.trainee.models.Ingrediente;

import java.io.Serializable;

public class IngredienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Integer quantidade;


    public IngredienteDTO(){
    }

    public IngredienteDTO(Long id, String nome, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public IngredienteDTO(Ingrediente ingrediente) {
        this.id = ingrediente.getId();
        this.nome = ingrediente.getNome();
        this.quantidade = ingrediente.getQuantidade();
    }

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredienteDTO that = (IngredienteDTO) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
