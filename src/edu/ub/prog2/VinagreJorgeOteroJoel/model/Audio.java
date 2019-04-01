 package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import java.io.File;

/**
 *
 * @author JORGEC
 */
public class Audio extends FitxerReproduible {
    private int kbps; // Audio kbps attribute

    /**
     * Audio class contructor
     * @param cami Path to the audio file
     * @param fitxerImatge Path to the audio's image file
     * @param nom Audio name
     * @param codec Audio codec
     * @param durada Durada del audio
     * @param kbps Kbps(Qualitat) del audio
     * @param r Reproductor del audio
     */
    public Audio(String cami, File fitxerImatge, String nom, String codec, float durada,
            int kbps, Reproductor r) {
        super(cami, nom, codec, durada, r);
        this.kbps = kbps;
    }
    
    /**
     * Play audio method
     */
    @Override
    protected void reproduir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
