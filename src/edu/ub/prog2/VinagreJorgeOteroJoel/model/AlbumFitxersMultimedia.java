package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.utils.AplicacioException;
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
    
    @Override
    public boolean isFull() {
        return album_fitxers.size() == max;
    }
    
    public void addFitxer(FitxerMultimedia fm) throws AplicacioException {
        if (isFull()) throw new AplicacioException("The album is full!");
        album_fitxers.add(fm);
    }
    
    public void removeFitxer(int index) throws AplicacioException {
        if (index < 0 || index >= album_fitxers.size()) throw new AplicacioException("Index out of bounds!");
        album_fitxers.remove(index);
    }
    
    public void removeAllFitxers(FitxerMultimedia fm) {
        while (album_fitxers.contains(fm)) {
            album_fitxers.remove(fm);
        }
    }

    @Override
    public String toString() {
        return "AlbumFitxersMultimedia{" + "max=" + max + ", titol=" + titol + ", album_fitxers=" + album_fitxers + '}';
    }
    
}
