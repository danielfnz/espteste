/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danie
 */
public class Supermercado {
    
    
    private int idSupmermercado;
    private String nomeSupermercado;

    public Supermercado(int idSupmermercado, String nomeSupermercado) {
        this.nomeSupermercado = nomeSupermercado;
        this.idSupmermercado = idSupmermercado;
    }

    public String getNomeSupermercado() {
        return nomeSupermercado;
    }

    public void setNomeSupermercado(String nomeSupermercado) {
        this.nomeSupermercado = nomeSupermercado;
    }

    public int getIdSupmermercado() {
        return idSupmermercado;
    }

    public void setIdSupmermercado(int idSupmermercado) {
        this.idSupmermercado = idSupmermercado;
    }

}
