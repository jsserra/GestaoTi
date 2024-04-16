/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.dao;

import com.jp.model.Endereco;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julianos
 */
@Stateless
public class EnderecoDao implements Serializable {
    
       private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "gestaotiPU")
    EntityManager em;

    public List<Endereco> getEnderecos()  {
        List<Endereco> enderecos = new ArrayList<>();
        Query q = em.createQuery("select e from Endereco e");
        
        try{
        enderecos = q.getResultList();
        }catch(Exception e){
            System.out.println(e);
        }
        return enderecos;
    } 
    
}
