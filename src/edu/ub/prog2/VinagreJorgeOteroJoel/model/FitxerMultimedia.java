/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import java.io.File;
import java.util.Date;

public class FitxerMultimedia extends File {
    
    private Date ultima_modificacio;
    private String descripcio;
    
    public FitxerMultimedia(String cami) {
        super(cami);
        setUltimaModificacio();
        setDescripcio("Sense descripció");
    }
    
    public Date getUltimaModificacio() { return ultima_modificacio; }
    public String getNomFitxer() { return getName(); }
    public String getDescripcio() { return descripcio; }
    public String getExtensio() { 
        String fileName = getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    public String getCamiAbsolut() {
        String abs_path = getAbsolutePath().replace(getName(), "");
        return abs_path;
    }
    
    public void setUltimaModificacio() {
        ultima_modificacio = new Date();
    }
    
    public void setDescripcio(String desc) {
        descripcio = desc;
    }
    
    @Override
    public boolean equals(Object fitxerMultimedia) {
        if (fitxerMultimedia instanceof FitxerMultimedia) {
            FitxerMultimedia fm = (FitxerMultimedia) fitxerMultimedia;
            return this.getNomFitxer().equals(fm.getNomFitxer()) 
                    && this.getCamiAbsolut().equals(fm.getCamiAbsolut())
                    && this.getExtensio().equals(fm.getExtensio());
        }
        return false;
    }
    
    @Override
    public String toString() {
        String to_print = "";
        to_print += "-----------------------------\n";
        to_print += "|Nom: " + getNomFitxer() + "\n";
        to_print += "|Extensio: " + getExtensio() + "\n";
        to_print += "|Loc: " + getCamiAbsolut() + "\n";
        to_print += "|Desc: " + getDescripcio() + "\n";
        to_print += "|Última Mod: " + getUltimaModificacio() + "\n";
        to_print += "-----------------------------\n";
        return to_print;
    }
}
