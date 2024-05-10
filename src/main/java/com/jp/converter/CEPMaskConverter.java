package com.jp.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("CEPMaskConverter")
public class CEPMaskConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        return string.replace("-", "");
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        final String value = (String) o;
        if (value == null || value.length() != 11 || value.isEmpty()) {
            return null;
        }
        return value.substring(0, 4) + "."
                + value.substring(5);
    }
}
