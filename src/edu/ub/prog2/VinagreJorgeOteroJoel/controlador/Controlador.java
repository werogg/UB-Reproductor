package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.Dades;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.EscoltadorReproduccio;
import edu.ub.prog2.utils.AplicacioException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Controlador {
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
     * @throws FileNotFoundException If the file can't be found
     */
    public void afegirVideo(String path, String nomVideo, String codec,
            float durada, int alcada, int amplada, float fps) throws AplicacioException, FileNotFoundException {
        
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
     * @throws FileNotFoundException If the file can't be found
     */
    public void afegirAudio(String cami, String camiImatge, String nomAudio,
            String codec, float durada, int kbps) throws AplicacioException, FileNotFoundException {
        
        dades.afegirAudio(cami, camiImatge, nomAudio, codec, durada, kbps, player);
        
    }
    
    /**
     * Remove a file from the library (Delegation)
     * @param id Id of the file to be removed
     * @throws FileNotFoundException If the file can't be found
     */
    public void esborrarFitxer(int id) throws FileNotFoundException {
        dades.esborrarFitxer(id);
    }
    
    /**
     * Show the current library (Delegation)
     * @return A string list with info of every file in library
     */
    public List<String> mostrarBiblioteca() {
        return dades.mostrarBiblioteca();
    }
    
    public List<String> mostrarBibliotecaSimplified() {
        return dades.mostrarBibliotecaSimplified();
    }
    
    /**
     * Save data to the disk
     * @param camiDesti Path to the data file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardarDadesDisc(String camiDesti) throws FileNotFoundException, IOException { 
        dades.guardarDadesDisc(camiDesti);
    }
    
    /**
     * Load tdata from disk
     * @param camiOrigen Path to the data file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void carregarDadesDisc(String camiOrigen) throws IOException, ClassNotFoundException {
        dades.carregarDadesDisc(camiOrigen);
        // TODO static method
    }
    
    public void crearAlbum(int i, String titol) throws AplicacioException {
        dades.crearAlbum(i, titol);
    }
    public void crearAlbum(String titol) throws AplicacioException {
        dades.crearAlbum(titol);
    }
    
    public List<String> mostrarAlbums() {
        return dades.mostrarAlbums();
    }
    
    public List<String> mostrarAlbumsSimplified() {
        return dades.mostrarAlbumsSimplified();
    }
    
    public void eliminarAlbum(int i) throws AplicacioException {
        dades.eliminarAlbum(i);
    }
    
    public void afegirMediaAlbum(int selected_album, int selected_file) throws FileNotFoundException, AplicacioException {
        dades.afegirMediaAlbum(selected_album, selected_file);
    }
    
    public String mostrarAlbum(int album_index) {
        return dades.mostrarAlbum(album_index);
    }
    
    public boolean albumIndexExists(int index) {
        return dades.albumIndexExists(index);
    }
    
    public boolean albumMediaIndexExists(int selected_album, int index) {
        return dades.albumMediaIndexExists(selected_album, index);
    }
    
    public void removeMediaFromAlbum(int selected_album, int selected_file) throws AplicacioException {
        dades.removeMediaFromAlbum(selected_album, selected_file);
    }
}