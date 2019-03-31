package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.Dades;
import edu.ub.prog2.utils.AplicacioException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Controlador {
    Dades dades;
    
    
    /**
     * The Controlador class constructor
     */
    public Controlador() {
        dades = new Dades();
    }
    
    public void afegirVideo(String path, String nomVideo, String codec,
            float durada, int alcada, int amplada, float fps) throws AplicacioException, FileNotFoundException {
        
        dades.afegirVideo(path, nomVideo, codec, durada, alcada, amplada, fps);
        
    }
    
    public void afegirAudio(String cami, String camiImatge, String nomAudio,
            String codec, float durada, int kbps) throws AplicacioException, FileNotFoundException {
        
        dades.afegirAudio(cami, camiImatge, nomAudio, codec, durada, kbps);
        
    }
    
    public List<String> mostrarBiblioteca() {
        return dades.mostrarBiblioteca();
    }
    
    public void esborrarFitxer(int id) throws AplicacioException, FileNotFoundException {
        
        dades.esborrarFitxer(id);
        
    }
    
    public void guardarDadesDisc(String camiDesti) throws AplicacioException, FileNotFoundException, IOException {
        
        dades.guardarDadesDisc(camiDesti);
        
    }
    
    public void carregarDadesDisc(String camiOrigen) throws AplicacioException {
        
        dades.carregarDadesDisc(camiOrigen);
        
    } 
}
