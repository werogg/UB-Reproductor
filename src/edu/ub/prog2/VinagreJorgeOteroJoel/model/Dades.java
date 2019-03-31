package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Dades {
    
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

    public void guardarDadesDisc(String camiDesti) throws AplicacioException, FileNotFoundException, IOException {
        
        File fitxer_disc = new File("pepe.dat");
        
        if(fitxer_disc.canWrite()){
            
        }
        
        FileInputStream	fin = new FileInputStream(fitxer_disc); //Leer de un fichero
        FileOutputStream fout =	new FileOutputStream(fitxer_disc); //Escribir en un fichero
        
        ObjectOutputStream oos = new ObjectOutputStream(fout); //Para escribir un objeto.
        ObjectInputStream ois =	new ObjectInputStream(fin); //Para leer un objeto.

    }
    
    public void carregarDadesDisc(String camiOrigen) throws AplicacioException{


    }
}
