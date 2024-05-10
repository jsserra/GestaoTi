package com.jp.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("CNPJMaskConverter")
public class CNPJMaskConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        return string.replace(".", "").replace("-", "").replace("/","");
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        final String value = (String) o;
        if (value == null || value.isEmpty()) {
            return null;
        }

        //return value;
        return value.substring(0, 3) + "."
                + value.substring(3, 6) + "."
                + value.substring(6, 9) + "-"
                + value.substring(9);
    }
}
