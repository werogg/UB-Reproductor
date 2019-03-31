package edu.ub.prog2.VinagreJorgeOteroJoel.model;

/**
 *
 * @author wero
 */
public abstract class FitxerReproduible extends FitxerMultimedia {
    private String codec;
    private float durada;
    private Reproductor reproductor;

    protected FitxerReproduible(String cami, String nom, String codec, float durada, Reproductor r) {
        super(cami);
        super.setDescripcio(nom);
        this.codec = codec;
        this.durada = durada;
        this.reproductor = r;
    }
    
    
    
    protected abstract void reproduir();
    
}
