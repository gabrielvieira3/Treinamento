package com.gt.trainee.dto;

import com.gt.trainee.models.Receita;

import java.io.Serializable;

public class ReceitaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String descricao;
    private String tempoDePreparo;
    private Integer rendimento;
    private Boolean favorita = false;

    public ReceitaDTO(){
    }

    public ReceitaDTO(Long id, String nome, String descricao, String tempoDePreparo, Integer rendimento, Boolean favorita){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tempoDePreparo = tempoDePreparo;
        this.rendimento = rendimento;
        this.favorita = favorita;
    }

    public ReceitaDTO(Receita receita) {
        this.id = receita.getId();
        this.nome = receita.getNome();
        this.descricao = receita.getDescricao();
        this.tempoDePreparo = receita.getTempoDePreparo();
        this.rendimento = receita.getRendimento();
        this.favorita = receita.getFavorita();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTempoDePreparo() {
        return tempoDePreparo;
    }

    public void setTempoDePreparo(String tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
    }

    public Integer getRendimento() {
        return rendimento;
    }

    public void setRendimento(Integer rendimento) {
        this.rendimento = rendimento;
    }

    public Boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceitaDTO that = (ReceitaDTO) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
