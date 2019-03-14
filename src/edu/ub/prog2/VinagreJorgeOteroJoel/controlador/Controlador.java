package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerMultimedia;
import edu.ub.prog2.utils.AplicacioException;
import java.io.FileNotFoundException;
import javax.naming.LimitExceededException;

public class Controlador {
    private final CarpetaFitxers carpeta;
    private FitxerMultimedia fm;
    
    /**
     * The Controlador class constructor
     */
    public Controlador() {
        carpeta = new CarpetaFitxers();
    }
    
    /**
     * Get the folder
     * @return The current folder
     */
    public CarpetaFitxers getFolder() {
        return carpeta;
    }
    
    /**
     * Remove file from folder by index
     * @param index Index of the file from the folder to be removed
     */
    public void FolderRemoveFile(int index) {
        try {
            fm = (FitxerMultimedia) carpeta.getAt(--index);
            carpeta.removeFitxer(fm);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Add a new file to the folder
     * @param path Path to the file
     * @param desc Description of the file
     */
    public void FolderAddFile(String path, String desc) {
        fm = new FitxerMultimedia(path);
        fm.setDescripcio(desc);
        
        try {
            carpeta.addFitxer(fm);
        } catch (LimitExceededException e) {
            System.err.println(e.getCause());
        } catch (AplicacioException e) {
            System.err.println(e.getCause());
        } 
    }
    
}
