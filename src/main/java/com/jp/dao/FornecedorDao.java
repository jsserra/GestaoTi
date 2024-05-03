/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.dao;

import com.jp.model.Fornecedor;
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
public class FornecedorDao implements Serializable {
    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "gestaotiPU")
    private EntityManager em;
    private List<Fornecedor> listaObjetos;

    private String mensagem;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Fornecedor> getListaObjetos() {
        String jpql = "from Fornecedor";
        return em.createQuery(jpql).getResultList();
    }

    public void setListaObjetos(List<Fornecedor> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<Fornecedor> getAll() {
        Query query = em.createQuery("from Fornecedor f");

        try{
            return query.getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<Fornecedor>();
        }
    }

    public Fornecedor findById(Integer id) {
        Fornecedor obj = (Fornecedor) em.find(Fornecedor.class, id);
        return obj;
    }

    public void persist(Fornecedor object) throws Exception{
        em.persist(object);
        mensagem = "Salvo com sucesso!";
    }

    public void merge(Fornecedor object) throws Exception{
            em.merge(object);
            mensagem = "Editado com sucesso!";
    }

    public void remove(Fornecedor object) throws Exception{
            object = em.merge(object);
            em.remove(object);
    }

    public String getMensagem() {
        return mensagem;
    }
}
