package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dades implements Serializable {
    
    BibliotecaFitxersMultimedia biblioteca;
    Reproductor reproductor;
    
    public Dades() {
        biblioteca = new BibliotecaFitxersMultimedia();
        reproductor = new Reproductor();
    }
    
    public void afegirVideo(String path, String nomVideo, String codec, float durada, int alcada, int amplada, float fps) throws AplicacioException, FileNotFoundException {
        
        Video video = new Video(path, nomVideo, codec, durada, alcada, amplada, fps, reproductor);
        
        biblioteca.addFitxer(video);
    }
    
    public void afegirAudio(String cami, String camiImatge ,String nomAudio, String codec, float durada, int kbps) throws AplicacioException, FileNotFoundException {
        FitxerMultimedia fm = new FitxerMultimedia(camiImatge);
        
        Audio audio = new Audio (cami, fm, nomAudio, codec, durada, kbps, reproductor);
        
        biblioteca.addFitxer(audio);
    }

    public List<String> mostrarBiblioteca() { 
        List<String> info = new ArrayList<>();
        
        for (FitxerMultimedia fm : biblioteca.tauFitxers) {
            info.add(fm.toString());
        }
        
        return info;
    }

    public void esborrarFitxer(int id) throws AplicacioException, FileNotFoundException {
       FitxerMultimedia fm = (FitxerMultimedia) biblioteca.getAt(id);
       
       biblioteca.removeFitxer(fm);
    }
}
