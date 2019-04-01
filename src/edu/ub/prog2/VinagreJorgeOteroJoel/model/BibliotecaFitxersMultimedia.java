package edu.ub.prog2.VinagreJorgeOteroJoel.model;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BibliotecaFitxersMultimedia extends CarpetaFitxers {
    
    /**
     * The BibliotecaFitxersMultimeda class constructor
     */
    public BibliotecaFitxersMultimedia(){
        tauFitxers = new ArrayList<>();
    }
    
    /**
     * Add file to BibliotecaFitxersMultimedia
     * @param file File to be added
     * @throws java.io.FileNotFoundException
     * @throws AplicacioException 
     */
    @Override
    public void	addFitxer(File file) throws FileNotFoundException, AplicacioException {
        
        if (file.exists()) {
            if (file instanceof FitxerMultimedia) {
                FitxerMultimedia fm = (FitxerMultimedia) file;
                if (isFull())
                    throw new AplicacioException("Exception: The folder is full.");
                if (!tauFitxers.contains(fm))
                    tauFitxers.add(fm);
                else
                    throw new AplicacioException("Exception: File already in the library");
            } else
                throw new AplicacioException("Exception: File is not a FitxerMultimedia");
        } else
            throw new FileNotFoundException("Exception: File does not exists");
    }
}
