package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;

public abstract class FitxerReproduible extends FitxerMultimedia {
    private final String codec;
    private final float durada;
    protected transient Reproductor reproductor;

    /**
     * FitxerReproduible class constructor
     * @param cami Path to the file
     * @param nom Filename
     * @param codec File codec
     * @param durada File duration
     * @param r Player
     */
    protected FitxerReproduible(String cami, String nom, String codec, float durada, Reproductor r) {
        super(cami);
        super.setDescripcio(nom);
        
        this.codec = codec;
        this.durada = durada;
        this.reproductor = r;
    }

    public float getDurada() {
        return durada;
    }
    
    
    /**
     * Abstract method to play the file
     * @throws AplicacioException if error happens playing a file
     */
    protected abstract void reproduir() throws AplicacioException;
    
}
