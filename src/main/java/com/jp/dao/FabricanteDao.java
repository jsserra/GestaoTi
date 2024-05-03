/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.dao;

import com.jp.model.Fabricante;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author everson
 */

@Stateful
public class FabricanteDao implements Serializable {
    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "gestaotiPU")
    private EntityManager em;
    private List<Fabricante> listaObjetos;

    private String mensagem;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Fabricante> getListaObjetos() {
        String jpql = "from Fabricante";
        return em.createQuery(jpql).getResultList();
    }

    public void setListaObjetos(List<Fabricante> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }


    public List<Fabricante> getAll() {
        Query query = em.createQuery("from Fabricante f");

        try{
            return query.getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<Fabricante>();
        }
    }

    public Fabricante findById(Integer id) {
        Fabricante obj = (Fabricante) em.find(Fabricante.class, id);
        return obj;
    }

    public void persist(Fabricante object) throws Exception{
        em.persist(object);
        mensagem = "Salvo com sucesso!";
    }

    public void merge(Fabricante object) throws Exception{
        em.merge(object);
        mensagem = "Editado com sucesso!";
    }

    public void remove(Fabricante object) throws Exception{
        object = em.merge(object);
        em.remove(object);
    }

    public String getMensagem() {
        return mensagem;
    }
}
