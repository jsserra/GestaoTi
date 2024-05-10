/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.controller;

import com.jp.dao.FornecedorDao;
import com.jp.dao.GenericDao;
import com.jp.model.Fornecedor;
import com.jp.model.Fornecedor;
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
public class FornecedorController implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    FornecedorDao daoFornecedor;
    private List<Fornecedor> fornecedores;

    private Fornecedor selectedFornecedor;

    private List<Fornecedor> selectedFornecedores;

    private boolean viewFormMode = true;

    private transient UploadedFile file;

    public String acessar(){
        return "/fornecedores/listagem?faces-redirect=true";
    }

    public List<Fornecedor> getFornecedores() {
        return daoFornecedor.getAll();
    }

    public Fornecedor getSelectedFornecedor() { return selectedFornecedor; }

    public void setSelectedFornecedor(Fornecedor selectedFornecedor) {
        this.selectedFornecedor = selectedFornecedor;
    }

    public List<Fornecedor> getSelectedFornecedores() { return selectedFornecedores; }

    public void setSelectedFornecedores(List<Fornecedor> selectedFornecedores) {
        this.selectedFornecedores = selectedFornecedores;
    }

    public void openNew() {
        this.selectedFornecedor = new Fornecedor();
        this.callEditDialog();
    }

    public void saveFornecedor() {
        try {
            if (this.selectedFornecedor.getId() == null) {
                daoFornecedor.persist(this.selectedFornecedor);
                MessagesUtil.infoMessage("Fornecedor criado", daoFornecedor.getMensagem());
            } else {
                daoFornecedor.merge(this.selectedFornecedor);
                MessagesUtil.infoMessage("Fornecedor Atualizado", daoFornecedor.getMensagem());
            }

            PrimeFaces.current().executeScript("PF('manageFornecedorDialog').hide()");
            PrimeFaces.current().ajax().update("fornecedores-form:messages", "fornecedores-form:dt-fornecedores");
        }catch(Exception e){
            MessagesUtil.errorMessage("Erro ao gravar", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public void deleteFornecedor() {
        try {
            daoFornecedor.remove(this.selectedFornecedor);
            this.selectedFornecedor = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fornecedor Excluído"));
            PrimeFaces.current().ajax().update("fornecedores-form:messages", "fornecedores-form:dt-fornecedores");
        }catch (Exception e) {
            MessagesUtil.errorMessage("Erro ao gravar", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedFornecedores()) {
            int size = this.selectedFornecedores.size();
            return size > 1 ? size + " fornecedores selecionados " : "1 fornecedor selecionado";
        }
        return "Excluir";
    }

    public boolean hasSelectedFornecedores() {
        return this.selectedFornecedores != null && !this.selectedFornecedores.isEmpty();
    }

    public void deleteSelectedFornecedores() {
        try {
            for (Fornecedor e : this.selectedFornecedores) {
                daoFornecedor.remove(e);
            }
            this.selectedFornecedores = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fornecedores Excluídos"));
            PrimeFaces.current().ajax().update("fornecedores-form:messages", "fornecedores-form:dt-fornecedores");
            PrimeFaces.current().executeScript("PF('dtFornecedores').clearFilters()");
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
            selectedFornecedor.setLogotipo(event.getFile().getContent());
            String nomeArquivo = file.getFileName();
            MessagesUtil.infoMessage("Arquivo " + nomeArquivo + " carregado com sucesso");
        }catch (Exception e){
            MessagesUtil.errorMessage("Erro ao enviar foto", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public void removeLogo(){
        this.selectedFornecedor.setLogotipo(null);
    }

    public UploadedFile getFile() {
        return file;
    }

    public StreamedContent getFornecedorLogo(){
        FacesContext fc = FacesContext.getCurrentInstance();

        if(fc.getRenderResponse() || fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE || this.selectedFornecedor == null){
            return new DefaultStreamedContent();
        }

        if(selectedFornecedor.getLogotipo() != null){
            return DefaultStreamedContent.builder().contentType("image/png").name("logo").stream(() -> new ByteArrayInputStream(selectedFornecedor.getLogotipo())).build();
        } else{
            return new DefaultStreamedContent();
        }
    }
}