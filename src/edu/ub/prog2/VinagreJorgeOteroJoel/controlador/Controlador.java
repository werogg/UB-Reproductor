package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.Dades;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.EscoltadorReproduccio;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InControlador;
import java.util.List;

public class Controlador implements InControlador {
    private Dades dades;
    EscoltadorReproduccio eplayer = new EscoltadorReproduccio();
    private final Reproductor player = new Reproductor(eplayer);
    
    /**
     * The Controlador class constructor
     */
    public Controlador() {
        dades = new Dades();
    }
    
    /**
     * Add video to the library (Delegation)
     * @param path Path to the video file
     * @param nomVideo Video name
     * @param codec Video codec
     * @param durada Video duration
     * @param alcada Video heigh
     * @param amplada Video width
     * @param fps Video frames per second
     * @throws AplicacioException If the file is already in the library, library is full or file is not media
     */
    @Override
    public void afegirVideo(String path, String nomVideo, String codec,
            float durada, int alcada, int amplada, float fps) throws AplicacioException {
        
        dades.afegirVideo(path, nomVideo, codec, durada, alcada, amplada, fps, player);
        
    }
    
    /**
     * Add audio to the library (Delegation)
     * @param cami Path to the audio
     * @param camiImatge Path to the Audio's image
     * @param nomAudio Audio name
     * @param codec Audio codec
     * @param durada Audio duration
     * @param kbps Audio kbps (Quality)
     * @throws AplicacioException If the file is already in the library, library is full or file is not media
     */
    @Override
    public void afegirAudio(String cami, String camiImatge, String nomAudio, String codec, float durada, int kbps) throws AplicacioException {
        dades.afegirAudio(cami, camiImatge, nomAudio, codec, durada, kbps, player);
    }
    
    /**
     * Remove a file from the library (Delegation)
     * @param id Id of the file to be removed
     * @throws AplicacioException when the file couldn't be found
     */
    @Override
    public void esborrarFitxer(int id) throws AplicacioException {
        dades.esborrarFitxer(id);
    }
    
    /**
     * Show the current library (Delegation)
     * @return A string list with info of every file in library
     */
    @Override
    public List<String> mostrarBiblioteca() {
        return dades.mostrarBiblioteca();
    }
    
    public List<String> mostrarBibliotecaSimplified() {
        return dades.mostrarBibliotecaSimplified();
    }
    
    /**
     * Save data to the disk
     * @param camiDesti Path to the data file
     * @throws AplicacioException for IO error
     */
    @Override
    public void guardarDadesDisc(String camiDesti) throws AplicacioException { 
        dades.guardarDadesDisc(camiDesti);
    }
    
    /**
     * Load tdata from disk
     * @param camiOrigen Path to the data file
     * @throws AplicacioException when the file couldn't be found
     */
    @Override
    public void carregarDadesDisc(String camiOrigen) throws AplicacioException {
        dades.carregarDadesDisc(camiOrigen);
        // TODO static method
    }
    
    public List<String> mostrarAlbums() {
        return dades.mostrarAlbums();
    }

    // TODO
    @Override
    public void reproduirFitxer(int i) throws AplicacioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // DONE
    @Override
    public void afegirAlbum(String string) throws AplicacioException {
        dades.afegirAlbum(string);
    }

    // DONE
    @Override
    public List<String> mostrarLlistatAlbums() {
        return dades.mostrarLlistatAlbums();
    }

    @Override
    public void esborrarAlbum(String string) throws AplicacioException {
        dades.esborrarAlbum(string);
    }

    // DONE
    @Override
    public boolean existeixAlbum(String string) {
        return dades.existeixAlbum(string);
    }

    // DONE
    @Override
    public void afegirFitxer(String album_name, int selected_file) throws AplicacioException {
        dades.afegirFitxer(album_name, selected_file);
    }
    
    // DONE
    @Override
    public void esborrarFitxer(String string, int i) throws AplicacioException {
        dades.esborrarFitxer(string, i);
    }

    // DONE
    @Override
    public List<String> mostrarAlbum(String string) throws AplicacioException {
        return dades.mostrarAlbum(string);
    }

    @Override
    public void obrirFinestraReproductor() {
        this.player.open();
    }

    @Override
    public void tancarFinestraReproductor() throws AplicacioException {
        this.player.close();
    }

    // TODO
    @Override
    public void reproduirCarpeta() throws AplicacioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // TODO
    @Override
    public void reproduirCarpeta(String string) throws AplicacioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void reemprenReproduccio() throws AplicacioException {
        this.player.resume();
    }

    @Override
    public void pausaReproduccio() throws AplicacioException {
        this.player.pause();
    }

    @Override
    public void aturaReproduccio() throws AplicacioException {
        this.player.stop();
    }

    // TODO
    @Override
    public void saltaReproduccio() throws AplicacioException {
        System.out.println("no implementado");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}