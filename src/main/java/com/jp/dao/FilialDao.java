/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.dao;

import com.jp.model.Filial;
import com.jp.model.util.UtilMessages;
import jakarta.ejb.Stateless;
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

@Stateless
public class FilialDao implements Serializable {
    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "gestaotiPU")
    private EntityManager em;
    private List<Filial> listaObjetos;

    private String mensagem;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Filial> getListaObjetos() {
        String jpql = "from Filial";
        return em.createQuery(jpql).getResultList();
    }

    public void setListaObjetos(List<Filial> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
//    public List<Filial> getFilial(){
//       // Query q = em.createQuery("select h from FilialHeranca h");
//       // return q.getResultList();
//        List<Filial> filiais = new ArrayList<>();
//        Query q = em.createQuery("select f from Filial f");
//
//        try{
//        filiais = q.getResultList();
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//        return filiais;
//    }


    public List<Filial> getAll() {
        Query query = em.createQuery("from Filial f");

        try{
            return query.getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<Filial>();
        }
    }

    public Filial findById(Integer id) {
        //rollback(); //em algumas situações ao tentar editar, algo pode ser mantido na seção de forma errônea
        Filial obj = (Filial) em.find(Filial.class, id);
        return obj;
    }

    public void persist(Filial object) throws Exception{
//        try{
            //em.getTransaction().begin();
            em.persist(object);
           // em.getTransaction().commit();
//            mensagem = "Objeto persistido com sucesso!";
//            return true;
//        }catch (Exception ex){
//            //rollback();
//            mensagem="Erro ao persistir: " + UtilMessages.getExceptionMessage(ex);
//            return false;
//        }
    }

    public void merge(Filial object) throws Exception{
//        try{
            //em.getTransaction().begin();
            em.merge(object);
            //em.getTransaction().commit();
//            mensagem = "Objeto persistido com sucesso!";
//            return true;
//        }catch (Exception ex){
            //rollback();
//            mensagem="Erro ao persistir: " + UtilMessages.getExceptionMessage(ex);
//            return false;
//        }
    }

//    public void rollback() {
//        if (em.getTransaction().isActive()==false){
//            em.getTransaction().begin();
//        }
//
//        em.getTransaction().rollback();
//    }

    public void remove(Filial object) throws Exception{
//        try{
            //em.getTransaction().begin();
            object = em.merge(object);
            em.remove(object);
            //em.getTransaction().commit();
//            mensagem = "Objeto removido com sucesso!";
//            return true;
//        }catch (Exception ex){
            //rollback();
//            mensagem="Erro ao remover: " + UtilMessages.getExceptionMessage(ex);
//            return false;
//    }
    }

    public String getMensagem() {
        return mensagem;
    }
}
