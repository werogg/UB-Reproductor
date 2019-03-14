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
    
    public BibliotecaFitxersMultimedia(){
        tauFitxers= new ArrayList<>();
    }
    
    @Override
    public void	addFitxer(File fitxer) throws LimitExceededException, AplicacioException {
        if (isFull())
            throw new LimitExceededException("Exception: The folder is full.");
        else if (!fitxer.exists()){
            throw new AplicacioException("Exception: File not exists");
        }
        else if (fitxer.exists() && !tauFitxers.contains(fitxer)) {
            tauFitxers.add((FitxerMultimedia) fitxer);
        }             
        
    }
}
