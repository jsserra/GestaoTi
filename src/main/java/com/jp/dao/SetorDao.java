package com.jp.dao;

import com.jp.model.Endereco;
import com.jp.model.Setor;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SetorDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "gestaotiPU")
    EntityManager em;

    public List<Setor> getAll()  {
        List<Setor> setores = new ArrayList<>();
        Query q = em.createQuery("select s from Setor s");

        try{
            setores = q.getResultList();
        }catch(Exception e){
            System.out.println(e);
        }
        return setores;
    }
}
