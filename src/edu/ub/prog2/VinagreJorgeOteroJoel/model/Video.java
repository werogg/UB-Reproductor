package edu.ub.prog2.VinagreJorgeOteroJoel.model;

/**
 *
 * @author JORGEC
 */
public class Video extends FitxerReproduible {
    private float fps;
    private int alcada, amplada;

    /**
     *
     * @param cami
     * @param nom
     * @param codec
     * @param durada
     * @param alcada
     * @param amplada
     * @param fps
     * @param r
     */
    public Video(String cami, String nom, String codec, float durada,
            int alcada, int amplada, float fps, Reproductor r) {
        super(cami, nom, codec, durada, r);
        this.fps = fps;
        this.alcada = alcada;
        this.amplada = amplada;
    }

    /**
     *
     */
    @Override
    protected void reproduir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
