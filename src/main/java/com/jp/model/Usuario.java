package com.jp.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    @Column (name = "login", length = 45)
    @NotNull
    @NotBlank
    private String login;
    @NotNull
    @NotBlank
    @Column (name = "nome")
    private String nome;
    @Column (name = "senha")
    private String senha;
    @Column (name = "ativo")
    private Boolean ativo;
}
