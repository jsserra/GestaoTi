/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.controller;

import com.jp.dao.FilialDao;
import com.jp.model.Filial;
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
public class FilialController implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    FilialDao daoFilial;
    private List<Filial> empresas;

    private Filial selectedEmpresa;

    private List<Filial> selectedEmpresas;

    private boolean viewFormMode = true;

    private transient UploadedFile file;

    public String acessar(){
        return "/empresas/listagem?faces-redirect=true";
    }

    public List<Filial> getEmpresas() {
        return daoFilial.getAll();
    }

    public Filial getSelectedEmpresa() { return selectedEmpresa; }

    public void setSelectedEmpresa(Filial selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }

    public List<Filial> getSelectedEmpresas() { return selectedEmpresas; }

    public void setSelectedEmpresas(List<Filial> selectedEmpresas) {
        this.selectedEmpresas = selectedEmpresas;
    }

    public void openNew() {
        this.selectedEmpresa = new Filial();
        this.callEditDialog();
    }

    public void saveEmpresa() {
        try {
            if (this.selectedEmpresa.getId() == null) {
                daoFilial.persist(this.selectedEmpresa);
                MessagesUtil.infoMessage("Empresa criada", daoFilial.getMensagem());
            } else {
                daoFilial.merge(this.selectedEmpresa);
                MessagesUtil.infoMessage("Empresa Atualizada", daoFilial.getMensagem());
            }

            PrimeFaces.current().executeScript("PF('manageEmpresaDialog').hide()");
            PrimeFaces.current().ajax().update("empresas-form:messages", "empresas-form:dt-empresas");
        }catch(Exception e){
            MessagesUtil.errorMessage("Erro ao gravar", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public void deleteEmpresa() {
        try {
            daoFilial.remove(this.selectedEmpresa);
            this.selectedEmpresa = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empresa Excluída"));
            PrimeFaces.current().ajax().update("empresas-form:messages", "empresas-form:dt-empresas");
        }catch (Exception e) {
            MessagesUtil.errorMessage("Erro ao gravar", ExceptionsUtil.getExceptionMessage(e));
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
            PrimeFaces.current().ajax().update("empresas-form:messages", "empresas-form:dt-empresas");
            PrimeFaces.current().executeScript("PF('dtEmpresas').clearFilters()");
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
            selectedEmpresa.setLogotipo(event.getFile().getContent());
            String nomeArquivo = file.getFileName();
            MessagesUtil.infoMessage("Arquivo " + nomeArquivo + " carregado com sucesso");
        }catch (Exception e){
            MessagesUtil.errorMessage("Erro ao enviar foto", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public void removeLogo(){
        this.selectedEmpresa.setLogotipo(null);
    }

    public UploadedFile getFile() {
        return file;
    }

    public StreamedContent getEmpresaLogo(){
        FacesContext fc = FacesContext.getCurrentInstance();

        if(fc.getRenderResponse() || fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE || this.selectedEmpresa == null){
            return new DefaultStreamedContent();
        }

        if(selectedEmpresa.getLogotipo() != null){
            return DefaultStreamedContent.builder().contentType("image/png").name("logo").stream(() -> new ByteArrayInputStream(selectedEmpresa.getLogotipo())).build();
        } else{
            return new DefaultStreamedContent();
        }
    }
}