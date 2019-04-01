package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import java.io.File;
import java.util.Date;

public class FitxerMultimedia extends File {
    
    private Date ultima_modificacio;
    private String descripcio;
    
    /**
     * The constructor of FitxerMultimedia's class
     * @param cami The file path
     */
    public FitxerMultimedia(String cami) {
        super(cami);
        ultima_modificacio = new Date();
    }
    
    /**
     * Getter of last file modification
     * @return Last modification of the file
     */
    public Date getUltimaModificacio() { return ultima_modificacio; }
    
    /**
     * Getter of file name
     * @return Filename
     */
    public String getNomFitxer() { return getName(); }
    
    /**
     * Getter of file description
     * @return File description
     */
    public String getDescripcio() { return descripcio; }
    
    /**
     * Getter of file extension
     * @return File extension
     */
    public String getExtensio() { 
        String fileName = getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    /**
     * Getter of Absolute path
     * @return Absolute filepath
     */
    public String getCamiAbsolut() {
        String abs_path = getAbsolutePath().replace(getName(), "");
        return abs_path;
    }
    
    /**
     * Setter of last modification (to now)
     */
    public void setUltimaModificacio() {
        ultima_modificacio = new Date();
    }
    
    /**
     * Setter of file description
     * @param desc
     */
    public void setDescripcio(String desc) {
        descripcio = desc;
    }
    
    
    /**
     * Check if 2 fitxerMultimedia are the same object
     * @param fitxerMultimedia File to be compared
     * @return File is the same
     */
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
    
    /**
     * Get full info about a file.
     * @return Full file info
     */
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
