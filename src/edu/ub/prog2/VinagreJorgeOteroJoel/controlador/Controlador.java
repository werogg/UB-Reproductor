package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerMultimedia;
import java.io.FileNotFoundException;

public class Controlador {
    private final CarpetaFitxers carpeta;
    private FitxerMultimedia fm;
    
    public Controlador() {
        carpeta = new CarpetaFitxers();
    }
    
    public void removeByIndex(int index) {
        fm = (FitxerMultimedia) carpeta.getAt(--index);
        
        try {
            
        }
        carpeta.removeFitxer(fm);
        
        
        
    }
    
}
