package edu.ub.prog2.VinagreJorgeOteroJoel.model;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.util.ArrayList;
import javax.naming.LimitExceededException;


/**
 *
 * @author Jorge Vinagre and Joel Otero
 */
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
     * @throws AplicacioException 
     */
    @Override
    public void	addFitxer(File file) throws AplicacioException {
        
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
            throw new AplicacioException("Exception: File does not exists");   
        
    }
}
