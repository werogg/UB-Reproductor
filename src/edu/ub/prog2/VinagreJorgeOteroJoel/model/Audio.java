package edu.ub.prog2.VinagreJorgeOteroJoel.model;

/**
 *
 * @author wero
 */
public class Audio extends FitxerReproduible {
    private int kbps;

    public Audio(String cami, String nom, String codec, float durada,
            int kbps, Reproductor r) {
        super(cami, nom, codec, durada, r);
        this.kbps = kbps;
    }
    
    @Override
    protected void reproduir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
