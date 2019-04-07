package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import java.util.ArrayList;

/**
 *
 * @author jvinagtr42.alumnes
 */
public class AlbumFitxersMultimedia extends BibliotecaFitxersMultimedia {
    
    private final int max;
    private final String titol;
    private ArrayList<FitxerMultimedia> album_fitxers;

    public AlbumFitxersMultimedia(int max, String titol) {
        this.max = max;
        this.titol = titol;
        this.album_fitxers = new ArrayList<>(max);
    }
    
    public AlbumFitxersMultimedia(String titol) {
        this.max = 10;
        this.titol = titol;
        this.album_fitxers = new ArrayList<>(max);
    }
    
    public String getTitol() {
        return titol;
    }
    
    public void addFitxer(){
        
    }
    
    
    
}
