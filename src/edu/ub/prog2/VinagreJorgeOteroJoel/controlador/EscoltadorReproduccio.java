package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;

public class EscoltadorReproduccio extends EscoltadorReproduccioBasic {

    private CarpetaFitxers llistaReproduint;
    private boolean [] llistaCtrl;
    private boolean reproduccioCiclica, reproduccioAleatoria;
    private FitxerReproduible playing;

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
        
        next();
    }
    
    
    @Override
    protected void onEndFile() {
        if (this.reproduccioCiclica && !hasNext()) {
            llistaCtrl = new boolean[llistaReproduint.getSize()];
        }
        next();
    }
    
    @Override
    protected void next() {
        
        if(hasNext()){
            if(isReproduccioAleatoria())  {
                int i = 0;
                int cont = 0;
                while(llistaCtrl.length > i){
                    if(!llistaCtrl[i])
                        cont++;
                    i++;    
                }
                int pos =(int) Math.round(Math.random()*(cont));
                int cnt = 0;
                while(llistaCtrl[cnt] && pos != 0) {
                    if (!llistaCtrl[cnt]) pos--;
                    cnt++;
                }
                try {
                    playing = (FitxerReproduible) llistaReproduint.getAt(pos);
                    playing.reproduir();
                } catch (AplicacioException ex) {
                    System.err.println(ex.getMessage());
                }
            }else{
                int i = 0;
                while(llistaCtrl.length > i){
                    if(!llistaCtrl[i]) {
                        try {
                            playing = (FitxerReproduible) llistaReproduint.getAt(i);
                            playing.reproduir();
                        } catch (AplicacioException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }
                    i++;
                }   
            }
        }
    }

    @Override
    protected boolean hasNext() {
        int i = 0;
        while (llistaCtrl.length > i) {
            if (!llistaCtrl[i]) return true;
            i++;
        }
        return false;
    }
    
    
    
}
