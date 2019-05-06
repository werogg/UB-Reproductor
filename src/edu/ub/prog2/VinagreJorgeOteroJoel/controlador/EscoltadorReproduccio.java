package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.AlbumFitxersMultimedia;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;

public class EscoltadorReproduccio extends EscoltadorReproduccioBasic {

    private CarpetaFitxers llistaReproduint; // File container with the media that have to be played
    private boolean [] llistaCtrl; // Control list of the played media
    private boolean reproduccioCiclica, reproduccioAleatoria; // Playing modes
    private FitxerReproduible playing; // Current file playing
    private int playing_index; // Current file index playing (in llistaReproduint)
    private Controlador controlador; // Controller object to stop the playing at the end of the folder

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
        
        // Different implementation if we get a CarpetaFitxers or an AlbumFitxersMultimedia object
        if (llistaReproduint instanceof AlbumFitxersMultimedia) {
            AlbumFitxersMultimedia afm = (AlbumFitxersMultimedia) llistaReproduint;
            this.llistaCtrl = new boolean[afm.getSize()]; // Init control list with all values to false
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
            playing_index = (int) Math.round(Math.random() * (llistaReproduint.getSize() - 1)); // We get a random file index
            
            // While iteration to avoid getting already played file index
            while (llistaCtrl[playing_index] && i < llistaCtrl.length) {
                playing_index = (( playing_index + 1 ) % llistaCtrl.length);
            }
            
            // The new index will be currentIndex + 1
        } else if (hasNext() && !isReproduccioAleatoria()) {
            i = 0;
            while (llistaCtrl[playing_index] && i < llistaCtrl.length) {
                playing_index = (playing_index + 1) % llistaCtrl.length;
            }
        }
        
        if (hasNext()) {
            
            /* We are forced to do a try/catch on the controller
                as the model method doesn't throws any excetpion */
            try {
                if (llistaReproduint instanceof AlbumFitxersMultimedia) {
                    // Cast the Folder -> AlbumFitxersMultimedia (Different implementation)
                    AlbumFitxersMultimedia afm = (AlbumFitxersMultimedia) llistaReproduint;
                    playing = (FitxerReproduible) afm.getAt(playing_index); // Get the file at given index
                    playing.reproduir(); // Call the play method
                } else {
                    // Get the file at given index
                    playing = (FitxerReproduible) llistaReproduint.getAt(playing_index);
                    playing.reproduir(); // Call the play method
                }
            } catch (AplicacioException ex) {
                System.out.println(ex.getMessage());
            }

            // Set the media as played in the control list if no exception was caught
            if (!exception_caught) llistaCtrl[playing_index] = true;
            
        } else if (!hasNext() && isReproduccioCiclica()) {
            
            /* We are forced to do a try/catch on the controller
                as the model method doesn't throws any excetpion */
            try {
                // Start again the playing
                iniciadorReproduccio(llistaReproduint, controlador);
            } catch (AplicacioException ex) {
                System.err.println(ex.getMessage());
            }
        } else { // If there's no next file to play
            
            /* We are forced to do a try/catch on the controller
                as the model method doesn't throws any excetpion */
            try {
                controlador.aturaReproduccio(); // Stop the playing
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
