package com.gt.trainee.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String sobreMim;
    @NotNull
    private String comidaFavorita;
    @NotNull
    @Min(1)
    private Integer age;

    @JsonIgnore
    @OneToOne(mappedBy = "pessoa")
    private Usuario usuario;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "rel_people_recipe", joinColumns = @JoinColumn(name = "people_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private Set<Receita> receita = new HashSet<>();

    public Pessoa() {
    }

    public Pessoa(Long id, String name, String sobreMim, String comidaFavorita, Integer age) {
        this.id = id;
        this.name = name;
        this.sobreMim = sobreMim;
        this.comidaFavorita = comidaFavorita;
        this.age = age;
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

    public Set<Receita> getReceita() {
        return receita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        return id.equals(pessoa.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Peoples{" +
                "name='" + name + '\'' +
                ", aboutMe='" + sobreMim + '\'' +
                ", favouriteFood='" + comidaFavorita + '\'' +
                ", age=" + age +
                ", receita" + receita +
                '}';
    }
}
