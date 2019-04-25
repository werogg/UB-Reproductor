package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.Audio;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.Video;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;

public class EscoltadorReproduccio extends EscoltadorReproduccioBasic {

    private CarpetaFitxers llistaReproduint;
    private boolean [] llistaCtrl;
    private boolean reproduccioCiclica, reproduccioAleatoria;

    public EscoltadorReproduccio(CarpetaFitxers llista_reproduint, boolean[] llistaCtrl, boolean reproduccioCiclica, boolean reproduccioAleatoria) {
        this.llistaReproduint = llista_reproduint;
        this.llistaCtrl = llistaCtrl;
        this.reproduccioCiclica = reproduccioCiclica;
        this.reproduccioAleatoria = reproduccioAleatoria;
    }
    
    public EscoltadorReproduccio() {
        reproduccioCiclica = false;
        reproduccioAleatoria = false;
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
    
    public void iniciadorReproduccio(CarpetaFitxers llistaReproduint, boolean reproduccioCiclica) throws AplicacioException{
        this.llistaReproduint = llistaReproduint;
        this.reproduccioCiclica = reproduccioCiclica;
        this.llistaCtrl = new boolean[llistaReproduint.getSize()];
        
        if (llistaReproduint.getAt(0) instanceof Video) {
            Video video = (Video) llistaReproduint.getAt(0);
            video.reproduir();
        } else if (llistaReproduint.getAt(0) instanceof Audio) {
            Audio audio = (Audio) llistaReproduint.getAt(0);
            audio.reproduir();
        } else
            throw new AplicacioException("File not supported!");
        
        this.llistaCtrl[0] = false;
    }
    
    
    @Override
    protected void onEndFile() {
        if (this.reproduccioCiclica) {
            
        }
    }
    
    public void iniciarReproduccio(CarpetaFitxers c, boolean reproduccioCiclica) {
        
        
        
    }

    @Override
    protected void next() {
        int i = 0;
        while (llistaCtrl.length < i) {
            if (llistaCtrl[i]) break;
            i++;
        }
        
    }

    @Override
    protected boolean hasNext() {
        int i = 0;
        while (llistaCtrl.length > i) {
            if (llistaCtrl[i]) return true;
            i++;
        }
        return false;
    }
    
    
    
}
