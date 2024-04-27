package com.jp.model;

import com.jp.model.enums.TipoEmpresa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "filial")
public class Filial extends Empresa implements Serializable {
    public Filial() {
        super();
        tipoEmpresa = TipoEmpresa.Filial;
    }
}
