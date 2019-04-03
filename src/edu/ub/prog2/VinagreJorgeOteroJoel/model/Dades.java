package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dades implements Serializable {
    
    private BibliotecaFitxersMultimedia biblioteca;
    private transient Reproductor reproductor;
    
    /**
     * Dades class constructor
     */
    public Dades() {
        biblioteca = new BibliotecaFitxersMultimedia();
        reproductor = new Reproductor();
    }
    
    /**
     * Add video to the library
     * @param path Path to the video
     * @param nomVideo Video name
     * @param codec Video codec
     * @param durada Video duration
     * @param alcada Video high
     * @param amplada Video width
     * @param fps Video frames per second
     * @throws AplicacioException If the file is already in the library, library is full or file is not media
     * @throws FileNotFoundException If the file can't be found
     */
    public void afegirVideo(String path, String nomVideo, String codec, float durada, int alcada, int amplada, float fps) throws AplicacioException, FileNotFoundException {
        
        Video video = new Video(path, nomVideo, codec, durada, alcada, amplada, fps, reproductor);
        
        biblioteca.addFitxer(video);
    }
    
    /**
     * Add audio to the library
     * @param cami Path to the audio
     * @param camiImatge Path to the Audio's image
     * @param nomAudio Audio name
     * @param codec Audio codec
     * @param durada Audio duration
     * @param kbps Audio kbps (Quality)
     * @throws AplicacioException If the file is already in the library, library is full or file is not media
     * @throws FileNotFoundException If the file can't be found
     */
    public void afegirAudio(String cami, String camiImatge ,String nomAudio, String codec, float durada, int kbps) throws AplicacioException, FileNotFoundException {
        FitxerMultimedia fm = new FitxerMultimedia(camiImatge);
        
        Audio audio = new Audio (cami, fm, nomAudio, codec, durada, kbps, reproductor);
        
        biblioteca.addFitxer(audio);
    }

    /**
     * Show the current library
     * @return A string list with info of every file in library
     */
    public List<String> mostrarBiblioteca() { 
        List<String> info = new ArrayList<>();
        
        int cnt = 0;
        for (FitxerMultimedia fm : biblioteca.tauFitxers) {
            info.add("-----------------------------");
            info.add("File Index: " + cnt);
            info.add(fm.toString());
            cnt++;
        }
        
        return info;
    }

    /**
     * Remove a file from the library
     * @param id Id of the file to be removed
     * @throws FileNotFoundException If the file can't be found
     */
    public void esborrarFitxer(int id) throws FileNotFoundException {
       FitxerMultimedia fm = (FitxerMultimedia) biblioteca.getAt(id);
       
       biblioteca.removeFitxer(fm);
    }
    
    /**
     * Class toString()
     * @return Info about the current object
     */
    @Override
    public String toString() {
        String to_print = "";
        int cnt = 0;
        
        to_print += "Data: \n";
        for (FitxerMultimedia fitxer : biblioteca.tauFitxers) {
            if (fitxer != null) {
                to_print += "[" + cnt + "] Description = " 
                    + fitxer.getDescripcio() 
                    + ", Date = " + fitxer.getUltimaModificacio() 
                    + ", Name = " + fitxer.getNomFitxer()
                    + ", Ext = " + fitxer.getExtensio()
                    + ", Absolute Path = " + fitxer.getAbsolutePath()
                    + "\n";
                cnt++;
            }
        }
        to_print += "\n Player: " + reproductor;
        
        return to_print;
    }
}