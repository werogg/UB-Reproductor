package edu.ub.prog2.VinagreJorgeOteroJoel.controlador;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.Dades;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author  Jorge Vinagre Triguero, Joel Otero Martín
 * @version 1.0
 * @since   2019-03-13 
 */
public class Controlador {
    private Dades dades;
    
    /**
     * The Controlador class constructor
     */
    public Controlador() {
        dades = new Dades();
    }
    
    /**
     *
     * @param path
     * @param nomVideo
     * @param codec
     * @param durada
     * @param alcada
     * @param amplada
     * @param fps
     * @throws AplicacioException
     * @throws FileNotFoundException
     */
    public void afegirVideo(String path, String nomVideo, String codec,
            float durada, int alcada, int amplada, float fps) throws AplicacioException, FileNotFoundException {
        
        dades.afegirVideo(path, nomVideo, codec, durada, alcada, amplada, fps);
        
    }
    
    /**
     *
     * @param cami
     * @param camiImatge
     * @param nomAudio
     * @param codec
     * @param durada
     * @param kbps
     * @throws AplicacioException
     * @throws FileNotFoundException
     */
    public void afegirAudio(String cami, String camiImatge, String nomAudio,
            String codec, float durada, int kbps) throws AplicacioException, FileNotFoundException {
        
        dades.afegirAudio(cami, camiImatge, nomAudio, codec, durada, kbps);
        
    }
    
    /**
     *
     * @return
     */
    public List<String> mostrarBiblioteca() {
        return dades.mostrarBiblioteca();
    }
    
    /**
     *
     * @param id
     * @throws AplicacioException
     * @throws FileNotFoundException
     */
    public void esborrarFitxer(int id) throws AplicacioException, FileNotFoundException {
        
        dades.esborrarFitxer(id);
        
    }
    
    /**
     *
     * @param camiDesti
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardarDadesDisc(String camiDesti) throws FileNotFoundException, IOException { 
        try (FileOutputStream fout = new FileOutputStream(new File(camiDesti)); ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(dades);
        }
    }
    
    /**
     *
     * @param camiOrigen
     * @throws AplicacioException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void carregarDadesDisc(String camiOrigen) throws AplicacioException, IOException, ClassNotFoundException {
        try (FileInputStream fin = new FileInputStream(new File(camiOrigen)); ObjectInputStream ois = new ObjectInputStream(fin)) {
            dades = (Dades) ois.readObject();
        }
        
    } 
}
