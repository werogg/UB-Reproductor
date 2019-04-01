 package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import java.io.File;

public class Audio extends FitxerReproduible {
    private int kbps;

    public Audio(String cami, File fitxerImatge, String nom, String codec, float durada,
            int kbps, Reproductor r) {
        super(cami, nom, codec, durada, r);
        this.kbps = kbps;
    }
    
    @Override
    protected void reproduir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
