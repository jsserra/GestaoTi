/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.controller;

import com.jp.dao.FabricanteDao;
import com.jp.model.Fabricante;
import com.jp.view.util.ExceptionsUtil;
import com.jp.view.util.MessagesUtil;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseId;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class FabricanteController implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    FabricanteDao daoFabricante;
    private List<Fabricante> fabricantes;

    private Fabricante selectedFabricante;

    private List<Fabricante> selectedFabricantes;

    private boolean viewFormMode = true;

    private transient UploadedFile file;

    public String acessar(){
        return "/fabricantes/listagem?faces-redirect=true";
    }

    public List<Fabricante> getFabricantes() {
        return daoFabricante.getAll();
    }

    public Fabricante getSelectedFabricante() { return selectedFabricante; }

    public void setSelectedFabricante(Fabricante selectedFabricante) {
        this.selectedFabricante = selectedFabricante;
    }

    public List<Fabricante> getSelectedFabricantes() { return selectedFabricantes; }

    public void setSelectedFabricantes(List<Fabricante> selectedFabricantes) {
        this.selectedFabricantes = selectedFabricantes;
    }

    public void openNew() {
        this.selectedFabricante = new Fabricante();
        this.callEditDialog();
    }

    public void saveFabricante() {
        try {
            if (this.selectedFabricante.getId() == null) {
                daoFabricante.persist(this.selectedFabricante);
                MessagesUtil.infoMessage("Fabricante criada", daoFabricante.getMensagem());
            } else {
                daoFabricante.merge(this.selectedFabricante);
                MessagesUtil.infoMessage("Fabricante Atualizada", daoFabricante.getMensagem());
            }

            PrimeFaces.current().executeScript("PF('manageFabricanteDialog').hide()");
            PrimeFaces.current().ajax().update("fabricantes-form:messages", "fabricantes-form:dt-products");
        }catch(Exception e){
            MessagesUtil.errorMessage("Erro ao gravar", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public void deleteFabricante() {
        try {
            daoFabricante.remove(this.selectedFabricante);
            this.selectedFabricante = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fabricante Excluída"));
            PrimeFaces.current().ajax().update("fabricantes-form:messages", "fabricantes-form:dt-fabricantes");
        }catch (Exception e) {
            MessagesUtil.errorMessage("Erro ao gravar", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedFabricantes()) {
            int size = this.selectedFabricantes.size();
            return size > 1 ? size + " fabricantes selecionadas " : "1 fabricante selecionada";
        }
        return "Excluir";
    }

    public boolean hasSelectedFabricantes() {
        return this.selectedFabricantes != null && !this.selectedFabricantes.isEmpty();
    }

    public void deleteSelectedFabricantes() {
        try {
            for (Fabricante e : this.selectedFabricantes) {
                daoFabricante.remove(e);
            }
            this.selectedFabricantes = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fabricantes Excluídas"));
            PrimeFaces.current().ajax().update("fabricantes-form:messages", "fabricantes-form:dt-fabricantes");
            PrimeFaces.current().executeScript("PF('dtFabricantes').clearFilters()");
        }catch (Exception e){
            MessagesUtil.errorMessage("Erro ao gravar", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public void callViewDialog(){
        viewFormMode = true;
    }

    public void callEditDialog(){
        viewFormMode = false;
    }

    public boolean getViewFormMode(){
        return viewFormMode;
    }

    public void logoUpload(FileUploadEvent event){
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            selectedFabricante.setLogotipo(event.getFile().getContent());
            String nomeArquivo = file.getFileName();
            MessagesUtil.infoMessage("Arquivo " + nomeArquivo + " carregado com sucesso");
        }catch (Exception e){
            MessagesUtil.errorMessage("Erro ao enviar foto", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public void removeLogo(){
        this.selectedFabricante.setLogotipo(null);
    }

    public UploadedFile getFile() {
        return file;
    }

    public StreamedContent getFabricanteLogo(){
        FacesContext fc = FacesContext.getCurrentInstance();

        if(fc.getRenderResponse() || fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE || this.selectedFabricante == null){
            return new DefaultStreamedContent();
        }

        if(selectedFabricante.getLogotipo() != null){
            return DefaultStreamedContent.builder().contentType("image/png").name("logo").stream(() -> new ByteArrayInputStream(selectedFabricante.getLogotipo())).build();
        } else{
            return new DefaultStreamedContent();
        }
    }
}