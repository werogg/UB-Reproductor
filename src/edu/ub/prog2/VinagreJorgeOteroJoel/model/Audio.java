 package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;

public class Audio extends FitxerReproduible {
    private final int kbps; // Audio kbps attribute
    private final File fitxerImatge;

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
        this.fitxerImatge = fitxerImatge;
    }
    
    /**
     * Play audio method
     * @throws AplicacioException if error happens playing the audio
     */
    @Override
    public void reproduir() throws AplicacioException {
        reproductor.reprodueix(this, fitxerImatge);
    }
}
