package com.gt.trainee.dto;

import com.gt.trainee.models.Pessoa;

import java.io.Serializable;

public class PessoaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String sobreMim;
    private String comidaFavorita;
    private Integer age;

    public PessoaDTO() {
    }

    public PessoaDTO(Long id, String name, String sobreMim, String comidaFavorita, Integer age) {
        this.id = id;
        this.name = name;
        this.sobreMim = sobreMim;
        this.comidaFavorita = comidaFavorita;
        this.age = age;
    }

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.name = pessoa.getName();
        this.sobreMim = pessoa.getSobreMim();
        this.comidaFavorita = pessoa.getComidaFavorita();
        this.age = pessoa.getAge();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSobreMim() {
        return sobreMim;
    }

    public void setSobreMim(String sobreMim) {
        this.sobreMim = sobreMim;
    }

    public String getComidaFavorita() {
        return comidaFavorita;
    }

    public void setComidaFavorita(String comidaFavorita) {
        this.comidaFavorita = comidaFavorita;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
