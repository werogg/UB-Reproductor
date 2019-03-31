package edu.ub.prog2.VinagreJorgeOteroJoel.model;

/**
 *
 * @author wero
 */
public abstract class FitxerReproduible {
    
    protected FitxerReproduible(String cami, String nom, String codec, float durada, Reproductor r) {
        
    }
    
    protected abstract void reproduir();
    
}
