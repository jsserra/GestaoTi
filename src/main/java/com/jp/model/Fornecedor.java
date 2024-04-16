/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author julianos
 */
@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "ie")
    private String ie;
    @Column(name = "fone")
    private String fone;
    @Column(name = "fone_venda")
    private String foneVenda;
    @Column(name = "fone_sac")
    private String foneSac;
    @Column(name = "whatsapp")
    private String whatsapp;
    @Column(name = "representante")
    private String representante;
    @Column(name = "representante_fone")
    private String representanteFone;
    @Column(name = "representante_ramal")
    private String representanteRamal;
    @Column(name = "representante_email")
    private String representanteEmail;
    @Column(name = "site")
    private String site;
    @Column(name = "codigo_cliente")
    private String codigoCliente;
    @Column(name = "email_cadastrado")
    private String emailCadastrado;
    @Column(name = "login")
    private String login;
    @Column(name = "senha")
    private String senha;
    @Column(name = "info")
    private String info;
    @Column(name = "fornece")
    private Boolean fornece;
    @Column(name = "ativo")
    private Boolean ativo;

    public Fornecedor() {
    }

    public Fornecedor(Integer id) {
        this.id = id;
    }

    public Fornecedor(Integer id, String nome, String razaoSocial, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

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

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getFoneVenda() {
        return foneVenda;
    }

    public void setFoneVenda(String foneVenda) {
        this.foneVenda = foneVenda;
    }

    public String getFoneSac() {
        return foneSac;
    }

    public void setFoneSac(String foneSac) {
        this.foneSac = foneSac;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getRepresentanteFone() {
        return representanteFone;
    }

    public void setRepresentanteFone(String representanteFone) {
        this.representanteFone = representanteFone;
    }

    public String getRepresentanteRamal() {
        return representanteRamal;
    }

    public void setRepresentanteRamal(String representanteRamal) {
        this.representanteRamal = representanteRamal;
    }

    public String getRepresentanteEmail() {
        return representanteEmail;
    }

    public void setRepresentanteEmail(String representanteEmail) {
        this.representanteEmail = representanteEmail;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getEmailCadastrado() {
        return emailCadastrado;
    }

    public void setEmailCadastrado(String emailCadastrado) {
        this.emailCadastrado = emailCadastrado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getFornece() {
        return fornece;
    }

    public void setFornece(Boolean fornece) {
        this.fornece = fornece;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }



    @Override
    public String toString() {
        return "com.jp.model.Fornecedor[ id=" + id + " ]";
    }
    
}
