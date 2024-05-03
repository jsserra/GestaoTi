/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author julianos
 */
@Entity
@Table(name = "fabricante")
public class Fabricante extends Empresa implements Serializable {

    @Column(name = "contato", length = 54)
    private String contato;
    @Column(name = "contato_fone", length = 11)
    private String contatoFone;
    @Column(name = "contato_ramal", length = 8)
    private String contatoRamal;
    @Column(name = "contato_email", length = 32)
    private String contatoEmail;
    @Column(name = "suporte_fone", length = 11)
    private String suporteFone;
    @Column(name = "suporte_email", length = 32)
    private String suporteEmail;
    @Column(name = "sac_fone", length = 11)
    private String sacFone;
    @Column(name = "sac_email", length = 32)
    private String sacEmail;

    public Fabricante() {
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getContatoFone() {
        return contatoFone;
    }

    public void setContatoFone(String contatoFone) {
        this.contatoFone = contatoFone;
    }

    public String getContatoRamal() {
        return contatoRamal;
    }

    public void setContatoRamal(String contatoRamal) {
        this.contatoRamal = contatoRamal;
    }

    public String getContatoEmail() {
        return contatoEmail;
    }

    public void setContatoEmail(String contatoEmail) {
        this.contatoEmail = contatoEmail;
    }

    public String getSuporteFone() {
        return suporteFone;
    }

    public void setSuporteFone(String suporteFone) {
        this.suporteFone = suporteFone;
    }

    public String getSuporteEmail() {
        return suporteEmail;
    }

    public void setSuporteEmail(String suporteEmail) {
        this.suporteEmail = suporteEmail;
    }

    public String getSacFone() {
        return sacFone;
    }

    public void setSacFone(String sacFone) {
        this.sacFone = sacFone;
    }

    public String getSacEmail() {
        return sacEmail;
    }

    public void setSacEmail(String sacEmail) {
        this.sacEmail = sacEmail;
    }
}
