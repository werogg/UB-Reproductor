package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author jvinagtr42.alumnes
 */
public class AlbumFitxersMultimedia extends BibliotecaFitxersMultimedia {
    
    protected int n = 10;
    private final String titol;
    protected ArrayList<FitxerMultimedia> alb_fitxers;

    public AlbumFitxersMultimedia(int n, String titol, ArrayList<FitxerMultimedia> alb_fitxers) {
        this.n = n;
        this.titol = titol;
        this.alb_fitxers = alb_fitxers;
    }
    
    AlbumFitxersMultimedia album = new AlbumFitxersMultimedia(10,"a", ArrayList<FitxerMultimedia> pep);
    
    
    public void addFixter(){
        
    }
    
    
    
}
