/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.bean;

import com.jp.dao.EnderecoDao;
import com.jp.dao.FabricanteDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author julianos
 */
@Named
@RequestScoped
public class TesteBean {
    
    private LocalDateTime horaAtual;
    
    @EJB
    EnderecoDao daoEndereco;
    
    @EJB
    FabricanteDao daoFabricante;
    
    public String getHoraAtual() {
        this.horaAtual = LocalDateTime.now();
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return horaAtual.format(fomatter);
    }

    public void setHoraAtual(LocalDateTime horaAtual) {
        this.horaAtual = horaAtual;
    }

    public EnderecoDao getDaoEndereco() {
        return daoEndereco;
    }

    public void setDaoEndereco(EnderecoDao daoEndereco) {
        this.daoEndereco = daoEndereco;
    }

    public FabricanteDao getDaoFabricante() {
        return daoFabricante;
    }

    public void setDaoFabricante(FabricanteDao daoFabricante) {
        this.daoFabricante = daoFabricante;
    }




    
    
    
    
    
}