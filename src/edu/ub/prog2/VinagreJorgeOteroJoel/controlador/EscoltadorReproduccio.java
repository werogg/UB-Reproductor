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

    public EscoltadorReproduccio(CarpetaFitxers llista_reproduint, boolean[] llistaCtrl, boolean reproduccioCiclica, boolean reproduccioAleatoria, Reproductor player) {
        this.llistaReproduint = llista_reproduint;
        this.llistaCtrl = llistaCtrl;
        this.reproduccioCiclica = reproduccioCiclica;
        this.reproduccioAleatoria = reproduccioAleatoria;
    }
    
    public EscoltadorReproduccio() {
        reproduccioCiclica = false;
        reproduccioAleatoria = false;
        llistaCtrl = null;
        playing = null;
        playing_index = 0;
    }

    public void setReproduccioCiclica(boolean reproduccioCiclica) {
        this.reproduccioCiclica = reproduccioCiclica;
    }

    public void setReproduccioAleatoria(boolean reproduccioAleatoria) {
        this.reproduccioAleatoria = reproduccioAleatoria;
    }

    public boolean isReproduccioCiclica() {
        return reproduccioCiclica;
    }

    public boolean isReproduccioAleatoria() {
        return reproduccioAleatoria;
    }
    
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
    
    @Override
    protected void next() {
        boolean exception_caught = false;
        int i;
        if (hasNext() && isReproduccioAleatoria()) {
            i = 0;
            playing_index = (int) Math.round(Math.random() * llistaReproduint.getSize());
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
        } else {
            if (isReproduccioCiclica()) {
                try {
                    iniciadorReproduccio(llistaReproduint, controlador);
                } catch (AplicacioException ex) {
                    Logger.getLogger(EscoltadorReproduccio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                controlador.aturaReproduccio();
                } catch (AplicacioException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

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
