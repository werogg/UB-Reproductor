package edu.ub.prog2.VinagreJorgeOteroJoel.model;

/**
 *
 * @author JORGEC
 */
public class Video extends FitxerReproduible {
    private float fps;
    private int alcada, amplada;

    /**
     * Video class constructor
     * @param cami Path to the video
     * @param nom Filename
     * @param codec File codec
     * @param durada File duration
     * @param alcada File high
     * @param amplada File width
     * @param fps Frames per second of the file
     * @param r File player
     */
    public Video(String cami, String nom, String codec, float durada,
            int alcada, int amplada, float fps, Reproductor r) {
        super(cami, nom, codec, durada, r);
        this.fps = fps;
        this.alcada = alcada;
        this.amplada = amplada;
    }

    /**
     * Play file method
     */
    @Override
    protected void reproduir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
