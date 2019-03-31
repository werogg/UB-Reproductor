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
    
     public void guardarDadesDisc(String camiDesti) throws FileNotFoundException, IOException {
        File file = new File(camiDesti);
        
        FileOutputStream fout = new FileOutputStream(new File(camiDesti));
        ObjectOutputStream oos = new ObjectOutputStream(fout);

        oos.writeObject(dades);

        oos.close();
        fout.close();
    }
    
    public void carregarDadesDisc(String camiOrigen) throws AplicacioException, IOException, ClassNotFoundException {
        
        try (FileInputStream fin = new FileInputStream(new File(camiOrigen))) {
            ObjectInputStream ois = new ObjectInputStream(fin);
            
            dades = (Dades) ois.readObject();
            
            ois.close();
            fin.close();
        }
        
    } 
}
