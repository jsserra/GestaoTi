package com.jp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "setor")
public class Setor implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @NotBlank
    @Column(name = "nome", length = 30, unique = true)
    private String nome;
    @Column(name = "sigla", length = 5)
    private String sigla;
    @ManyToOne
    @JoinColumn(name = "gestor", referencedColumnName = "id")
    private Colaborador gestor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Colaborador getGestor() {
        return gestor;
    }

    public void setGestor(Colaborador gestor) {
        this.gestor = gestor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setor setor = (Setor) o;
        return Objects.equals(id, setor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
