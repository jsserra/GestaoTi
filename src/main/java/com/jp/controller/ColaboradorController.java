package com.jp.controller;

import com.jp.dao.ColaboradorDao;
import com.jp.dao.SetorDao;
import com.jp.model.Colaborador;
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
public class ColaboradorController implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private ColaboradorDao daoColaborador;
    @EJB
    private SetorDao daoSetor;
    private List<Colaborador> colaboradores;

    private Colaborador selectedColaborador;

    private List<Colaborador> selectedColaboradores;

    private boolean viewFormMode = true;

    public String acessar(){
        return "/colaboradores/listagem?faces-redirect=true";
    }

    public List<Colaborador> getColaboradores() {
        return daoColaborador.getAll();
    }

    public Colaborador getSelectedColaborador() { return selectedColaborador; }

    public void setSelectedColaborador(Colaborador selectedColaborador) {
        this.selectedColaborador = selectedColaborador;
    }

    public List<Colaborador> getSelectedColaboradores() { return selectedColaboradores; }

    public void setSelectedColaboradores(List<Colaborador> selectedColaboradores) {
        this.selectedColaboradores = selectedColaboradores;
    }

    public ColaboradorDao getDaoColaborador() {
        return daoColaborador;
    }

    public SetorDao getDaoSetor() {
        return daoSetor;
    }

    public void openNew() {
        this.selectedColaborador = new Colaborador();
        this.callEditDialog();
    }

    public void saveColaborador() {
        try {
            if (this.selectedColaborador.getId() == null) {
                daoColaborador.persist(this.selectedColaborador);
                MessagesUtil.infoMessage("Colaborador criado", daoColaborador.getMensagem());
            } else {
                daoColaborador.merge(this.selectedColaborador);
                MessagesUtil.infoMessage("Colaborador atualizado", daoColaborador.getMensagem());
            }

            PrimeFaces.current().ajax().update("colaboradores-form:messages", "colaboradores-form:dt-colaboradores");
        }catch(Exception e){
            PrimeFaces.current().ajax().addCallbackParam("exceptionOccurred", true);
            MessagesUtil.errorMessage("Erro ao gravar", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public void deleteColaborador() {
        try {
            daoColaborador.remove(this.selectedColaborador);
            this.selectedColaborador = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Colaborador excluído"));
            PrimeFaces.current().ajax().update("colaboradores-form:messages", "colaboradores-form:dt-colaboradores");
        }catch (Exception e) {
            MessagesUtil.errorMessage("Erro ao gravar", ExceptionsUtil.getExceptionMessage(e));
        }
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedColaboradores()) {
            int size = this.selectedColaboradores.size();
            return size > 1 ? size + " colaboradores selecionados " : "1 colaborador selecionado";
        }

        return "Excluir";
    }

    public boolean hasSelectedColaboradores() {
        return this.selectedColaboradores != null && !this.selectedColaboradores.isEmpty();
    }

    public void deleteSelectedColaboradores() {
        try {
            for (Colaborador e : this.selectedColaboradores) {
                daoColaborador.remove(e);
            }
            this.selectedColaboradores = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Colaboradores excluídos"));
            PrimeFaces.current().ajax().update("colaboradores-form:messages", "colaboradores-form:dt-colaboradores");
            PrimeFaces.current().executeScript("PF('dtColaboradores').clearFilters()");
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

}
