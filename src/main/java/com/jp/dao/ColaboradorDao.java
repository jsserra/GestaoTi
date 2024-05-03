/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.dao;

import com.jp.model.Colaborador;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class ColaboradorDao implements Serializable {
    
   private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "gestaotiPU")
    EntityManager em;

    private List<Colaborador> listaObjetos;

    private String mensagem;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Colaborador> getListaObjetos() {
        String jpql = "from Colaborador";
        return em.createQuery(jpql).getResultList();
    }

    public void setListaObjetos(List<Colaborador> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<Colaborador> getAll() {
        Query query = em.createQuery("from Colaborador c");

        try{
            return query.getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<Colaborador>();
        }
    }

    public Colaborador findById(Integer id) {
        Colaborador obj = (Colaborador) em.find(Colaborador.class, id);
        return obj;
    }

    public void persist(Colaborador object) throws Exception{
        em.persist(object);
        mensagem = "Salvo com sucesso!";
    }

    public void merge(Colaborador object) throws Exception{
        em.merge(object);
        mensagem = "Editado com sucesso!";
    }

    public void remove(Colaborador object) throws Exception{
        object = em.merge(object);
        em.remove(object);
    }

    public String getMensagem() {
        return mensagem;
    }
    
}
