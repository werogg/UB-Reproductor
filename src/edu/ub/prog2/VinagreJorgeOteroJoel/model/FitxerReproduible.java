package edu.ub.prog2.VinagreJorgeOteroJoel.model;

/**
 *
 * @author wero
 */
public abstract class FitxerReproduible extends FitxerMultimedia {
    String codec;
    float durada;
    Reproductor reproductor;

    protected FitxerReproduible(String cami, String nom, String codec, float durada, Reproductor r) {
        super(cami);
        super.setDescripcio(nom);
        this.codec = codec;
        this.durada = durada;
        this.reproductor = r;
    }
    
    
    
    protected abstract void reproduir();
    
}
