package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Reproductor;

public class Video extends FitxerReproduible {
    private final float fps;
    private final int alcada, amplada;

    /**
     * Video class constructor
     * @param cami Path to the video
     * @param nom Video name
     * @param codec Video codec
     * @param durada Video duration
     * @param alcada Video high
     * @param amplada Video width
     * @param fps Video frames per second
     * @param r Video player
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
