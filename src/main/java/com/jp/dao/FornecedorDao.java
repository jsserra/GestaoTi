/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.dao;

import com.jp.model.Filial;
import com.jp.model.Fornecedor;
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
    private Validator validator;

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
        try {
            em.persist(object);
            mensagem = "Salvo com sucesso!";
        } catch (ValidationException e){
            throw e;
        } catch (PersistenceException e){
            Throwable cause = e.getCause();
//            //rollback();
            if(cause instanceof ConstraintViolationException){
                throw new Exception("Erro: Violação de restrição de integridade única.", e);
            } else{
                throw e;
            }
        }
    }

    public void merge(Fornecedor object) throws Exception{
        try {
            validate(object);
            em.merge(object);
            mensagem = "Editado com sucesso!";
        } catch (ValidationException e){
            throw e;
        } catch (PersistenceException e){
            Throwable cause = e.getCause();
            if(cause instanceof ConstraintViolationException)
                throw new Exception("Erro: Violação de restrição de integridade única.", e);
            else
                throw e;
        }
    }

    public void remove(Fornecedor object) throws Exception{
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

    private void validate(Fornecedor fornecedor){
        if(validator == null)
            initValidator();

        Set<ConstraintViolation<Fornecedor>> constraintViolations = validator.validate(fornecedor);
        if(!constraintViolations.isEmpty()){
            for(ConstraintViolation violation : constraintViolations) {
                if (violation.getMessage().contains("Duplicate entry")) {
                    if (violation.getMessage().contains("empresa.cnpj"))
                        throw new ValidationException("O CNPJ informado já foi cadastrado em outra empresa.");
                    if (violation.getMessage().contains("empresa.ie"))
                        throw new ValidationException("A Inscrição Estadual informada já foi cadastrado em outra empresa.");
                }
                throw new ValidationException(violation.getMessage());
            }
        }
    }
}
