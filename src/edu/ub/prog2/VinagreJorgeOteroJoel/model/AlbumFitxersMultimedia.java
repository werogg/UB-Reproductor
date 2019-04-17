package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.FileNotFoundException;
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
    
    public void addFitxer(FitxerMultimedia fm) throws FileNotFoundException, AplicacioException {
        album_fitxers.add(fm);
    }
    
    public void removeFitxer(int pos) {
        album_fitxers.remove(pos);
    }
    
    public void removeAllFitxers(FitxerMultimedia fm) {
        FitxerMultimedia temp_fm;
        
        for (int i = 0; i < album_fitxers.size(); i++) {
            temp_fm = (FitxerMultimedia) album_fitxers.get(i);
            if (temp_fm.equals(fm)) {
                album_fitxers.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "AlbumFitxersMultimedia{" + "max=" + max + ", titol=" + titol + ", album_fitxers=" + album_fitxers + '}';
    }
    
}
