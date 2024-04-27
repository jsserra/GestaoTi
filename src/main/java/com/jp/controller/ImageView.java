package com.jp.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseId;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

@Named
@ViewScoped
public class ImageView implements Serializable {

    public ImageView(){}
    private static final long serialVersionUID = 1L;
    public StreamedContent viewFromBytes (byte[] imageBytes){
        byte[] buffer;
        FacesContext fc = FacesContext.getCurrentInstance();

        if(fc.getRenderResponse() || fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE){
            return new DefaultStreamedContent();
        }

        buffer = imageBytes;
        InputStream input = new ByteArrayInputStream(buffer);
        //StreamedContent stream = DefaultStreamedContent.builder().contentType("image/png").stream(() -> input).build();//DefaultStreamedContent(input, "image/jpeg");
        //InputStream input = new ByteArrayInputStream(buffer);
        StreamedContent stream = DefaultStreamedContent.builder()
                .contentType("image/jpeg")
                .writer((os) -> {
                    try {
                        os.write(buffer);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .build();
                //.builder().contentType("image/png").stream(() -> input).build();
        return stream;
    }

}
