package com.jp.view.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class ExceptionsUtil {
    public static String getExceptionMessage(Exception ex){
        while(ex.getCause() != null){
            ex = (Exception) ex.getCause();
        }
        String retorno = ex.getMessage();
        if(retorno.contains("foreign key")){
            retorno = "Registro não pode ser excluído por possuir referência no sistema";
        }

        return retorno;
    }
}
