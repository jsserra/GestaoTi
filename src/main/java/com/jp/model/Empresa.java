/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.model;

import com.jp.model.enums.TipoEmpresa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;



/**
 *
 * @author julianos
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "tipo_empresa")
@Table(name = "empresa")
public class Empresa implements Serializable {

    protected static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq_empresa", sequenceName = "seq_empresa_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_empresa", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Integer id;
    
    @Column(name = "cnpj", length = 14, unique = true, nullable = true)
    protected String cnpj;
    
    @Column(name = "ie", length = 10, unique = true, nullable = true)
    protected String ie;

    @Column(name = "razao", length = 45, nullable = false)
    @NotBlank
    protected String razao;

    @Column(name = "nome", length = 45, nullable = false)
    protected String nome;

    @Column(name = "telefone", length = 11)
    protected String telefone;
    
    @Column(name = "celular", length = 11)
    protected String celular;

    @Column(name = "whatsapp", length = 11)
    protected String whatsapp;

    @Column(name = "email", length = 32)
    protected String email;
    
    @Column(name = "site", length = 54)
    protected String site;
    
    @Column(name = "login", length = 32)
    protected String login;
    
    @Column(name = "senha", length = 24)
    protected String senha;
    
    @Column(name = "codigo_cliente", length = 24)
    protected String codigoCliente;

    @Column(name = "tipo_empresa")
    @Enumerated(EnumType.STRING)
    protected TipoEmpresa tipoEmpresa;

    @Lob
    @Column(name = "logotipo", length=100000)
    protected byte[] logotipo;

    @Column(name = "info")
    protected String info;

    @Column(name = "ativo")
    protected Boolean ativo = true;

    @OneToOne
    @JoinColumn(name = "id_end", referencedColumnName = "id", nullable = true)
    protected Endereco endereco;

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

    public byte[] getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(byte[] logotipo) {
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
