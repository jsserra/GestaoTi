/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.dao;

import com.jp.model.Colaborador;
import com.jp.model.Filial;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.validation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateful
public class ColaboradorDao implements Serializable {
    
   private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "gestaotiPU")
    EntityManager em;

    private List<Colaborador> listaObjetos;

    private String mensagem;
    private Validator validator;

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
        try {
            validate(object);
            em.persist(object);
            mensagem = "Salvo com sucesso!";
        } catch (ValidationException e){
            throw e;
        } catch (PersistenceException e){
            Throwable cause = e.getCause();

            if(cause instanceof ConstraintViolationException){
                throw new Exception("Erro: Violação de restrição de integridade única.", e);
            } else{
                throw e;
            }
        }
    }

    public void merge(Colaborador object) throws Exception{
        try {
            validate(object);
            em.merge(object);
            mensagem = "Editado com sucesso!";
        } catch (ValidationException e){
            throw e;
        } catch (PersistenceException e){
            Throwable cause = e.getCause();
            if(cause instanceof ConstraintViolationException)
                throw new Exception("Erro: Violação de restrição de integridade única.\", e");
            else
                throw e;
        }
    }

    public void remove(Colaborador object) throws Exception{
        object = em.merge(object);
        em.remove(object);
    }

    public String getMensagem() {
        return mensagem;
    }

    private void initValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private void validate(Colaborador colaborador) {
        if (validator == null)
            initValidator();

        Set<ConstraintViolation<Colaborador>> constraintViolations = validator.validate(colaborador);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation violation : constraintViolations) {
                throw new ValidationException(violation.getMessage());
            }
        }
    }
}
