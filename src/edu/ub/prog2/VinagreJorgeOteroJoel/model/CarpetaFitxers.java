package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.naming.LimitExceededException;

public class CarpetaFitxers implements Serializable {
    
    protected final int _max_size;
    protected ArrayList<FitxerMultimedia> tauFitxers;
    
    /**
     * Constructor of CarpetaFitxer class
     */
    public CarpetaFitxers() {
        _max_size = 100;
        tauFitxers = new ArrayList<>(_max_size);
    }
    
    /**
     * Getter of folder size
     * @return Folder size
     */
    public int getSize() {
        return tauFitxers.size();
    }
    
    /**
     * Add a file to the folder
     * @param file File to be added to the folder
     * @throws javax.naming.LimitExceededException
     * @throws edu.ub.prog2.utils.AplicacioException
     * @throws java.io.FileNotFoundException
     */
    public void addFitxer(File file) throws LimitExceededException, AplicacioException, FileNotFoundException {
        if (isFull())
            throw new LimitExceededException("Exception: The folder is full.");
        else
            tauFitxers.add((FitxerMultimedia) file);
    }
    
    /**
     * Remove a file from the folder
     * @param file File to be removed from the folder
     * @throws java.io.FileNotFoundException
     */
    public void removeFitxer(File file) throws FileNotFoundException {
        if (file instanceof FitxerMultimedia) {
            FitxerMultimedia fm = (FitxerMultimedia) file;
            if (tauFitxers.contains(fm))
                tauFitxers.remove(fm);
            else
                throw new FileNotFoundException("Exception: File not found");
        }
    }
    
    /**
     * Get file at position
     * @param position Index of the file
     * @return File in the index
     * @throws java.io.FileNotFoundException
     * 
     */
    public File getAt(int position) throws FileNotFoundException {
        if (position >= tauFitxers.size())
            throw new FileNotFoundException("Exception: File not found");
        else
            return tauFitxers.get(position);
    }
    
    /**
     * Clear the folder
     */
    public void clear() {
        tauFitxers.clear();
    }
    
    /**
     * Check if the folder is full
     * @return Folder is full
     */
    public boolean isFull() {
        return getSize() == _max_size;
    }
    
    /**
     * Get full info about a folder.
     * @return Full folder info
     */
    @Override
    public String toString() {
        String to_print = "";
        int cnt = 1;
        
        to_print += "File Folder:\n";
        to_print += "================\n";
        
        for (FitxerMultimedia fitxer : tauFitxers) {
            if (fitxer != null) {
                to_print += "[" + cnt + "] Description = " 
                    + fitxer.getDescripcio() 
                    + ", Date = " + fitxer.getUltimaModificacio() 
                    + ", Name = " + fitxer.getNomFitxer()
                    + ", Ext = " + fitxer.getExtensio()
                    + ", Absolute Path = " + fitxer.getAbsolutePath()
                    + "\n";
                cnt++;
            }
        }
        
        return to_print;
    }
    
}
