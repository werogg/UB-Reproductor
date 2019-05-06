package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.AlbumFitxersMultimedia;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EscoltadorReproduccio extends EscoltadorReproduccioBasic {

    private CarpetaFitxers llistaReproduint;
    private boolean [] llistaCtrl;
    private boolean reproduccioCiclica, reproduccioAleatoria;
    private FitxerReproduible playing;
    private int playing_index;
    private Controlador controlador;

    /**
     * EscoltadorReproduccio constructor with data
     * @param llista_reproduint Folder to be played
     * @param llistaCtrl Boolean control list
     * @param reproduccioCiclica Cyclic mode boolean
     * @param reproduccioAleatoria Random mode noolean
     * @param player The player
     */
    public EscoltadorReproduccio(CarpetaFitxers llista_reproduint, boolean[] llistaCtrl, boolean reproduccioCiclica, boolean reproduccioAleatoria, Reproductor player) {
        this.llistaReproduint = llista_reproduint;
        this.llistaCtrl = llistaCtrl;
        this.reproduccioCiclica = reproduccioCiclica;
        this.reproduccioAleatoria = reproduccioAleatoria;
    }
    
    /**
     * EscoltadorReproduccio constructor without data
     */
    public EscoltadorReproduccio() {
        reproduccioCiclica = false;
        reproduccioAleatoria = false;
        llistaCtrl = null;
        playing = null;
        playing_index = 0;
    }

    /**
     * Cyclic playing setter
     * @param reproduccioCiclica
     */
    public void setReproduccioCiclica(boolean reproduccioCiclica) {
        this.reproduccioCiclica = reproduccioCiclica;
    }

    /**
     * Random playing setter
     * @param reproduccioAleatoria
     */
    public void setReproduccioAleatoria(boolean reproduccioAleatoria) {
        this.reproduccioAleatoria = reproduccioAleatoria;
    }

    /**
     * Cyclic playing getter
     * @return true if enabled
     */
    public boolean isReproduccioCiclica() {
        return reproduccioCiclica;
    }

    /**
     * Random playing getter
     * @return true if enabled
     */
    public boolean isReproduccioAleatoria() {
        return reproduccioAleatoria;
    }
    
    /**
     * Start the playing
     * @param llistaReproduint Folder to be played
     * @param controlador Controller
     * @throws AplicacioException 
     */
    public void iniciadorReproduccio(CarpetaFitxers llistaReproduint, Controlador controlador) throws AplicacioException{
        this.llistaReproduint = llistaReproduint;
        this.controlador = controlador;
        this.playing_index = 0;
        
        if (llistaReproduint instanceof AlbumFitxersMultimedia) {
            AlbumFitxersMultimedia afm = (AlbumFitxersMultimedia) llistaReproduint;
            this.llistaCtrl = new boolean[afm.getSize()];
        } else this.llistaCtrl = new boolean[llistaReproduint.getSize()];
        next();
    }
    
    /**
     * Method to execute when a playing file ends
     */
    @Override
    protected void onEndFile() {
        if (this.reproduccioCiclica && !hasNext()) {
            if (llistaReproduint instanceof AlbumFitxersMultimedia) {
            AlbumFitxersMultimedia afm = (AlbumFitxersMultimedia) llistaReproduint;
            this.llistaCtrl = new boolean[afm.getSize()];
            } else this.llistaCtrl = new boolean[llistaReproduint.getSize()];
        }
        next();
    }
    
    /**
     * Next file to play method
     */
    @Override
    protected void next() {
        boolean exception_caught = false; // Boolean to catch exception
        int i;
        
        if (hasNext() && isReproduccioAleatoria()) {
            i = 0;
            playing_index = (int) Math.round(Math.random() * (llistaReproduint.getSize() - 1));
            while (llistaCtrl[playing_index] && i < llistaCtrl.length) playing_index = (( playing_index + 1 ) % llistaCtrl.length);
        } else if (hasNext() && !isReproduccioAleatoria()) {
            i = 0;
            while (llistaCtrl[playing_index] && i < llistaCtrl.length) {
                playing_index = (playing_index + 1) % llistaCtrl.length;
            }
        }
        
        if (hasNext()) {
            try {
                if (llistaReproduint instanceof AlbumFitxersMultimedia) {
                    AlbumFitxersMultimedia afm = (AlbumFitxersMultimedia) llistaReproduint;
                    playing = (FitxerReproduible) afm.getAt(playing_index);
                    playing.reproduir();
                } else {
                    playing = (FitxerReproduible) llistaReproduint.getAt(playing_index);
                    playing.reproduir();
                }
            } catch (AplicacioException ex) {
                System.out.println(ex.getMessage());
            }

            if (!exception_caught) llistaCtrl[playing_index] = true;
            
        } else if (!hasNext() && isReproduccioCiclica()) {
            try {
                iniciadorReproduccio(llistaReproduint, controlador);
            } catch (AplicacioException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            try {
                controlador.aturaReproduccio();
            } catch (AplicacioException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    /**
     * Check if there's any file left to be played
     * @return true if there's a next file
     */
    @Override
    protected boolean hasNext() {
        int i = 0;
        while (i < llistaCtrl.length) {
            if (!llistaCtrl[i]) return true;
            i++;
        }
        return false;
    }  
}
