package com.jp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "filial")
public class Filial extends Empresa implements Serializable {
    public Filial() {
        super();
    }
}
