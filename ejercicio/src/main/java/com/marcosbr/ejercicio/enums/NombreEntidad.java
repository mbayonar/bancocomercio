package com.marcosbr.ejercicio.enums;

/**
 *
 * @author Marcos
 */
public enum NombreEntidad {
    
    PERSONA("Cliente"),
    USUARIO("Usuario");
    
    private String valor;

    private NombreEntidad(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}