/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.jp.util;

import com.jp.dao.FilialDao;
import com.jp.model.Fabricante;
import com.jp.model.Filial;
import jakarta.ejb.EJB;


/**
 *
 * @author julianos
 */
public class MainTeste {

    @EJB
    public static FilialDao dao;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       Filial filial = new Filial();

       filial.setNome("Testani Supermercados");
       filial.setRazao("Testani Comercio de Alimentos");
       filial.setCnpj("08123456");
       filial.setIe("29823784");

       try {
           dao.persist(filial);
       }catch (Exception e) {
           System.out.println(e);
       }
    }
    
}
