package com.jp.view.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class MessagesUtil {
    public static void infoMessage(String title){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, "");
        facesContext.addMessage(null, message);
    }

    public static void infoMessage(String title, String detail){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail);
        facesContext.addMessage(null, message);
    }

    public static void errorMessage(String title){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, "");
        facesContext.addMessage(null, message);
    }

    public static void errorMessage(String title, String detail){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, detail);
        facesContext.addMessage(null, message);
    }
}
