package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import edu.ub.prog2.utils.ReproductorBasic;
import java.io.File;

public class Reproductor extends ReproductorBasic {

    /**
     * Reproductor constructor (Player)
     * @param controlador The controller
     */
    public Reproductor(EscoltadorReproduccioBasic controlador) {
        super(controlador);
    }
    
    /**
     * Play a file
     * @param fr FitxerReproduible object
     * @throws AplicacioException 
     */
    public void reprodueix(FitxerReproduible fr) throws AplicacioException {
        this.play(fr);
    }
    
    /**
     * Play an audio
     * @param fr FitxerReproduible object (Audio)
     * @param file File object (Image)
     * @throws AplicacioException 
     */
    public void reprodueix(FitxerReproduible fr, File file) throws AplicacioException {
        this.play(fr, file);
    }
}
