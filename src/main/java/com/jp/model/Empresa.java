/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.model;

import com.jp.model.enums.TipoEmpresa;
import jakarta.persistence.*;

import java.io.Serializable;



/**
 *
 * @author julianos
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_empresa")
@Table(name = "empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "cnpj")
    private String cnpj;
    
    @Column(name = "ie")
    private String ie;

    @Column(name = "razao")
    private String razao;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "celular")
    private String celular;

    @Column(name = "whatsapp")
    private String whatsapp;

    @Column(name = "email")
    private String email;
    
    @Column(name = "site")
    private String site;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "senha")
    private String senha;
    
    @Column(name = "codigo_cliente")
    private String codigoCliente;
//
//    @Column(name = "tipo_empresa")
//    private TipoEmpresa tipoEmpresa;

    @Lob
    @Column(name = "logotipo")
    private String logotipo;

    @Column(name = "info")
    private String info;

    @Column(name = "ativo")
    private Boolean ativo;

    @OneToOne
    @JoinColumn(name = "id_end", referencedColumnName = "id", nullable = true)
    private Endereco endereco;

    public Empresa() {
    }

    public Empresa(Integer id) {
        this.id = id;
    }

    public Empresa(Integer id, String razao, String nome) {
        this.id = id;
        this.razao = razao;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

//    public TipoEmpresa getTipoEmpresa() {
//        return tipoEmpresa;
//    }
//
//    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
//        this.tipoEmpresa = tipoEmpresa;
//    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jp.model.Empresa[ id=" + id + " ]";
    }
    
}
