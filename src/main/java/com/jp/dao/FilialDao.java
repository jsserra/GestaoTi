/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.dao;

import com.jp.model.Filial;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
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
public class FilialDao implements Serializable{
    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "gestaotiPU")
    private EntityManager em;
    private List<Filial> listaObjetos;

    private String mensagem;
    private Validator validator;

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
        try{
            //em.getTransaction().begin();
            validate(object);
            em.persist(object);
            mensagem = "Salvo com sucesso!";
           // em.getTransaction().commit();
//            return true;
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

    public void merge(Filial object) throws Exception{
//        try{
            //em.getTransaction().begin();
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
            //em.getTransaction().commit();
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

    private void initValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private void validate(Filial filial){
        if(validator == null)
            initValidator();

        Set<ConstraintViolation<Filial>> constraintViolations = validator.validate(filial);
        if(!constraintViolations.isEmpty()){
            for(ConstraintViolation violation : constraintViolations) {
                if(violation.getMessage().contains("Duplicate entry")) {
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
