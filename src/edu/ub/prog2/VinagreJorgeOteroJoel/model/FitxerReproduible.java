package edu.ub.prog2.VinagreJorgeOteroJoel.model;

public abstract class FitxerReproduible extends FitxerMultimedia {
    private final String codec;
    private final float durada;
    private transient Reproductor reproductor;

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
    
    
    /**
     * Abstract method to play the file
     */
    protected abstract void reproduir();
    
}
