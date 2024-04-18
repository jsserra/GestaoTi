/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.controller;

import com.jp.dao.EnderecoDao;
import com.jp.dao.FilialDao;
import com.jp.model.Filial;
import jakarta.annotation.ManagedBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class FilialController implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    FilialDao daoFilial;
    private List<Filial> empresas;

    private Filial selectedEmpresa;

    private List<Filial> selectedEmpresas;

    public String acessar(){
        return "/empresas/listagem?faces-redirect=true";
    }

    public List<Filial> getEmpresas() {
        return daoFilial.getAll();
    }

    public Filial getSelectedEmpresa() throws InterruptedException { return selectedEmpresa; }

    public void setSelectedEmpresa(Filial selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }

    public List<Filial> getSelectedEmpresas() { return selectedEmpresas; }

    public void setSelectedEmpresas(List<Filial> selectedEmpresas) {
        this.selectedEmpresas = selectedEmpresas;
    }

    public void openNew() {
        this.selectedEmpresa = new Filial();
    }

    public void saveFilial() {
        try {
            if (this.selectedEmpresa.getId() == null) {
                daoFilial.persist(this.selectedEmpresa);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empresa Adicionada"));
            } else {
                daoFilial.merge(this.selectedEmpresa);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empresa Atualizada"));
            }

            PrimeFaces.current().executeScript("PF('manageEmpresaDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void deleteEmpresa() {
        try {
            daoFilial.remove(this.selectedEmpresa);
            this.selectedEmpresa = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empresa Excluída"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedEmpresas()) {
            int size = this.selectedEmpresas.size();
            return size > 1 ? size + " empresas selecionadas " : "1 empresa selecionada";
        }

        return "Excluir";
    }

    public boolean hasSelectedEmpresas() {
        return this.selectedEmpresas != null && !this.selectedEmpresas.isEmpty();
    }

    public void deleteSelectedEmpresas() {
        try {
            for (Filial e : this.selectedEmpresas) {
                daoFilial.remove(e);
            }
            this.selectedEmpresas = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empresas Excluídas"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
            PrimeFaces.current().executeScript("PF('dtEmpresas').clearFilters()");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void callViewDialog(){
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("disableModalFormInputs();");
    }

}