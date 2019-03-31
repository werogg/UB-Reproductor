package edu.ub.prog2.VinagreJorgeOteroJoel.model;

/**
 *
 * @author wero
 */
public class Audio extends FitxerReproduible {

    public Audio(String cami, String nom, String codec, float durada, Reproductor r) {
        super(cami, nom, codec, durada, r);
    }
    
    @Override
    protected void reproduir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
