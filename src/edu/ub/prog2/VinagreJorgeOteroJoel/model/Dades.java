package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.utils.AplicacioException;
import java.util.List;

public class Dades {
    
    BibliotecaFitxersMultimedia biblioteca;
    Reproductor reproductor;
    
    public Dades() {
        biblioteca = new BibliotecaFitxersMultimedia();
        reproductor = new Reproductor();
    }
    
    public void afegirVideo(String path, String nomVideo, String codec, float durada, int alcada, int amplada, float fps) throws AplicacioException {
        Video video = new Video(path, nomVideo, codec, durada, alcada, amplada, fps, reproductor);
        
        biblioteca.addFitxer(video);
        
    }
    
    public void afegirAudio(String cami, String camiImatge ,String nomAudio, String codec, float durada, int kbps) throws AplicacioException {
        Audio audio = new Audio (cami, camiImatge., nomAudio, codec, durada, kbps, reproductor);
        
        biblioteca.addFitxer(audio);

    }

    public List<String> mostrarBiblioteca() { 
        return null;
    }

    public void esborrarFitxer(int id) throws AplicacioException {


    }

    public void guardarDadesDisc(String camiDesti) throws AplicacioException {
        
        
        

    }
    
    public void carregarDadesDisc(String camiOrigen) throws AplicacioException{


    }
}
