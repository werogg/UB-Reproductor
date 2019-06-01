package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AlbumFitxersMultimedia extends BibliotecaFitxersMultimedia {
    
    private final int max;
    private final String titol;

    /**
     * AlbumFitxersMultimedia constructor
     * @param max Max capacity of the folder
     * @param titol Album's title
     */
    public AlbumFitxersMultimedia(int max, String titol) {
        this.max = max;
        this.titol = titol;
        tauFitxers = new ArrayList<>(max);
    }
    
    /**
     * AlbumFitxersMultimedia constructor
     * @param titol Album's title
     */
    public AlbumFitxersMultimedia(String titol) {
        this.max = 10;
        this.titol = titol;
        tauFitxers = new ArrayList<>(max);
    }
    
    /**
     * Getter of the album's title
     * @return Album's title
     */
    public String getTitol() {
        return titol;
    }
    
    /**
     * Album's size getter
     * @return Album size
     */
    @Override
    public int getSize() {
        return tauFitxers.size();
    }
    
    /**
     * Get file at given index
     * @param position Index of the file
     * @return File object
     * @throws AplicacioException Internal app exception
     */
    @Override
    public File getAt(int position) throws AplicacioException {
        if (position >= tauFitxers.size() || position < 0)
            throw new AplicacioException("File not found");
        else
            return tauFitxers.get(position);
    }
    
    /**
     * Check if the album is full
     * @return true if album is full
     */
    @Override
    public boolean isFull() {
        return tauFitxers.size() == max;
    }
    
    /**
     * Add file to the album
     * @param fm FitxersMultimedia to add to the album
     * @throws AplicacioException Internal app exception
     */
    public void addFitxer(FitxerMultimedia fm) throws AplicacioException {
        if (isFull()) throw new AplicacioException("The album is full!");
        tauFitxers.add(fm);
    }
    
    /**
     * Remove a file from the album
     * @param index Index of the file to be removed
     * @throws AplicacioException Internal app exception
     */
    public void removeFitxer(int index) throws AplicacioException {
        if (index < 0 || index >= tauFitxers.size()) throw new AplicacioException("Index out of bounds!");
        tauFitxers.remove(index);
    }
    
    /**
     * Remove all files from the album coinciding with the given one 
     * @param fm FitxerMultimedia be removed from the album
     */
    public void removeAllFitxers(FitxerMultimedia fm) {
        while (tauFitxers.contains(fm)) {
            tauFitxers.remove(fm);
        }
    }
    
    /**
     * Show album content simplified
     * @return String list with the album content
     */
    public List<String> showContent() {
        List<String> info = new ArrayList<>();
        int cnt = 1;
        for (FitxerMultimedia fm : tauFitxers) {
            info.add("[" + cnt + "] " + fm.getNomFitxer());
            cnt++;
        }
        return info;
    }

    /**
     * Return Album's info
     * @return info about the album object
     */
    @Override
    public String toString() {
        String info = "\n";
        info += "==========================\n";
        info += titol + " - " + getSize() + "/" + max + " spaces used.\n";
        info += "==========================";
        return info;
    }
    
}
