package com.gt.trainee.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_receitas")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String descricao;
    @NotNull
    private String tempoDePreparo;
    @NotNull
    @Min(1)
    private Integer rendimento;

    @Valid
    @ManyToMany
    @JoinTable(name = "rel_categoria_receita",
            joinColumns = @JoinColumn(name = "id_receita", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id"))
    private List<Category> categorias;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receita")
    private List<ModoPreparo> modoPreparo = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receita")
    private List<Ingrediente> ingredientes;

    @JsonIgnore
    @ManyToMany(mappedBy="receita")
    private Set<Pessoa> pessoa = new HashSet<>();

    private Boolean favorita = false;

    public Receita(){
    }

    public Receita(Long id, String nome, String descricao, String tempoDePreparo, Integer rendimento, Boolean favorita) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tempoDePreparo = tempoDePreparo;
        this.rendimento = rendimento;
        this.favorita = favorita;
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

    public Set<Pessoa> getPessoa() {
        return pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receita receita = (Receita) o;

        return id.equals(receita.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Receita{" + "nome='" + nome + '\'' + ", descricao='" + descricao + '\'' + ", tempoDePreparo='"
                + tempoDePreparo + '\'' + ", rendimento=" + rendimento + '}';
    }
}
