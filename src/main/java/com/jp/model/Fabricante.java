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
@Table(name = "fabricante_heranca")
public class Fabricante extends Empresa implements Serializable {

    @Column(name = "representante_comercial")
    private String representanteComercial;
    @Column(name = "representante_fone")
    private String representanteFone;
    @Column(name = "representante_ramal")
    private String representanteRamal;
    @Column(name = "suporte_fone")
    private String suporteFone;
    @Column(name = "sac_fone")
    private String sacFone;
    @Column(name = "sac_email")
    private String sacEmail;

    public Fabricante() {
    }


    public String getRepresentanteComercial() {
        return representanteComercial;
    }

    public void setRepresentanteComercial(String representanteComercial) {
        this.representanteComercial = representanteComercial;
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

    public String getSuporteFone() {
        return suporteFone;
    }

    public void setSuporteFone(String suporteFone) {
        this.suporteFone = suporteFone;
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
