package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.Dades;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerMultimedia;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InControlador;
import java.util.List;

public class Controlador implements InControlador {
    private Dades dades;
    private final EscoltadorReproduccio eplayer;
    private final Reproductor player;
    
    /**
     * The Controlador class constructor
     */
    public Controlador() {
        dades = new Dades();
        eplayer = new EscoltadorReproduccio();
        player = new Reproductor(eplayer);
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
     * Load data from disk
     * @param camiOrigen Path to the data file
     * @throws AplicacioException when the file couldn't be found
     */
    @Override
    public void carregarDadesDisc(String camiOrigen) throws AplicacioException {
        dades = Dades.carregarDadesDisc(camiOrigen);
        dades.resetReproductors(player);
    }
    
    /**
     * Show albums
     * @return Albums info
     */
    public List<String> mostrarAlbums() {
        return dades.mostrarAlbums();
    }

    /**
     * Play a file
     * @param i Library file id to play
     * @throws AplicacioException if failed to play
     */
    @Override
    public void reproduirFitxer(int i) throws AplicacioException {
        CarpetaFitxers cf = new CarpetaFitxers();
        cf.addFitxer(dades.reproduirFitxer(i));
        eplayer.iniciadorReproduccio(cf, this);
    }

    /**
     * Add new album
     * @param string Album name
     * @throws AplicacioException if error creating the new album
     */
    @Override
    public void afegirAlbum(String string) throws AplicacioException {
        dades.afegirAlbum(string);
    }

    /**
     * Show albums listed
     * @return numered list of albums
     */
    @Override
    public List<String> mostrarLlistatAlbums() {
        return dades.mostrarLlistatAlbums();
    }

    /**
     * Remove an album
     * @param string Name of the album to be removed
     * @throws AplicacioException if error removing the album
     */
    @Override
    public void esborrarAlbum(String string) throws AplicacioException {
        dades.esborrarAlbum(string);
    }

    /**
     * Check if an album exists
     * @param string The name of the album to be checked
     * @return true if exists
     */
    @Override
    public boolean existeixAlbum(String string) {
        return dades.existeixAlbum(string);
    }

    /**
     * Add file to an album
     * @param album_name The name of the album to add the file
     * @param selected_file The id of the file (on the library) to be added
     * @throws AplicacioException if error adding the file to the album
     */
    @Override
    public void afegirFitxer(String album_name, int selected_file) throws AplicacioException {
        dades.afegirFitxer(album_name, selected_file);
    }
    
    /**
     * Remove a file from an album
     * @param string The name of the album to remove the file
     * @param i The id of the file (on the library) to be removed
     * @throws AplicacioException if error removing the file
     */
    @Override
    public void esborrarFitxer(String string, int i) throws AplicacioException {
        dades.esborrarFitxer(string, i);
    }

    /**
     * Show an album
     * @param string Name of the album to be showed
     * @return Album info
     * @throws AplicacioException if error getting the album info
     */
    @Override
    public List<String> mostrarAlbum(String string) throws AplicacioException {
        return dades.mostrarAlbum(string);
    }

    /**
     * Open the player
     */
    @Override
    public void obrirFinestraReproductor() {
        this.player.open();
    }

    /**
     * Close the player
     * @throws AplicacioException if error closing the player
     */
    @Override
    public void tancarFinestraReproductor() throws AplicacioException {
        this.player.close();
    }

    /**
     * Play the whole library
     * @throws AplicacioException if error while trying to play the library
     */
    @Override
    public void reproduirCarpeta() throws AplicacioException {
        eplayer.iniciadorReproduccio(dades.reproduirCarpeta(), this);
    }

    /**
     * Play a folder/album
     * @param string Name of the album/folder to be played
     * @throws AplicacioException if error while trying to play the folder
     */
    @Override
    public void reproduirCarpeta(String string) throws AplicacioException {
        eplayer.iniciadorReproduccio(dades.reproduirCarpeta(string), this);
    }
    
    /**
     * Resume playing
     * @throws AplicacioException if error on resuming
     */
    @Override
    public void reemprenReproduccio() throws AplicacioException {
        this.player.resume();
    }

    /**
     * Pause playing
     * @throws AplicacioException if error on trying to pause the playing
     */
    @Override
    public void pausaReproduccio() throws AplicacioException {
        this.player.pause();
    }

    /**
     * Stop playing
     * @throws AplicacioException if error on trying to stop the playing
     */
    @Override
    public void aturaReproduccio() throws AplicacioException {
        this.player.stop();
    }

    /**
     * Skip playing
     * @throws AplicacioException if error while trying to skip the playing
     */
    @Override
    public void saltaReproduccio() throws AplicacioException {
        eplayer.next();
    }
    
    /**
     * Enable/Disable cyclic playing
     */
    public void setReproduccioCiclica() {
        dades.setReproduccioCiclica(eplayer);
    }

    /**
     * Enable/Disable random playing
     */
    public void setReproduccioAleatoria() {
        dades.setReproduccioAleatoria(eplayer);
    }
    
    /**
     * Getter of cyclic playing
     * @return true if cyclic playing is enabled
     */
    public boolean isReproduccioCiclica() {
        return dades.isReproduccioCiclica();
    }

    /**
     * Getter of random playing
     * @return true if random playing is enabled
     */
    public boolean isReproduccioAleatoria() {
        return dades.isReproduccioAleatoria();
    }
}