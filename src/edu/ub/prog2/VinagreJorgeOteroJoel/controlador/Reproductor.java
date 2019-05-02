package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.Audio;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import edu.ub.prog2.utils.ReproductorBasic;
import java.io.File;

public class Reproductor extends ReproductorBasic {

    public Reproductor(EscoltadorReproduccioBasic controlador) {
        super(controlador);
    }
    
    public void reprodueix(FitxerReproduible fr) throws AplicacioException {
        this.play(fr);
    }
    
    public void reprodueix(FitxerReproduible fr, File file) throws AplicacioException {
        this.play(fr, file);
    }
}
