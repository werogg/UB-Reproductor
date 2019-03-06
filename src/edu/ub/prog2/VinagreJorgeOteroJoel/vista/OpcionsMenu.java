/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

/**
 *
 * @author wero
 */
public enum OpcionsMenu {
    
    SORTIR("Sortir"),
    CREAR_FITXER("Crear fitxer"),
    ELIMINAR_FITXER("Eliminar fitxer"),
    MOSTRAR_CARPETA("Mostrar carpeta");
    
    private final String text;
    
    OpcionsMenu(final String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
    
}
