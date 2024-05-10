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
public class Fornecedor extends Empresa implements Serializable {

    @Column(name = "venda_fone", length = 11)
    private String vendaFone;
    @Column(name = "venda_email", length = 32)
    private String vendaEmail;
    @Column(name = "representante", length = 32)
    private String representante;
    @Column(name = "representante_fone", length = 11)
    private String representanteFone;
    @Column(name = "representante_ramal", length = 8)
    private String representanteRamal;
    @Column(name = "representante_email", length = 32)
    private String representanteEmail;

    public Fornecedor() {
    }

    public String getVendaFone() {
        return vendaFone;
    }

    public void setVendaFone(String vendaFone) {
        this.vendaFone = vendaFone;
    }

    public String getVendaEmail() {
        return vendaEmail;
    }

    public void setVendaEmail(String vendaEmail) {
        this.vendaEmail = vendaEmail;
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
