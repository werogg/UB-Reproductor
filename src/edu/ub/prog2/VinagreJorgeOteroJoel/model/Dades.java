package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dades implements Serializable {
    
    private BibliotecaFitxersMultimedia biblioteca;
    private ArrayList<AlbumFitxersMultimedia> album_list;
    
    /**
     * Dades class constructor
     */
    public Dades() {
        biblioteca = new BibliotecaFitxersMultimedia();
        album_list = new ArrayList<>();
    }
    
    /**
     * Add video to the library
     * @param path Path to the video
    public void eliminarAlbum(int i) {
     * @param nomVideo Video name
     * @param codec Video codec
     * @param durada Video duration
     * @param alcada Video high
     * @param amplada Video width
     * @param fps Video frames per second
     * @param reproductor Reproductor object for playing
     * @throws AplicacioException If the file is already in the library, library is full or file is not media
     */
    public void afegirVideo(String path, String nomVideo, String codec, float durada, int alcada, int amplada, float fps, Reproductor reproductor) throws AplicacioException {
        
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
     * @param reproductor Reproductor object for playing
     * @throws AplicacioException If the file is already in the library, library is full or file is not media
     */
    public void afegirAudio(String cami, String camiImatge ,String nomAudio, String codec, float durada, int kbps, Reproductor reproductor) throws AplicacioException {
        FitxerMultimedia fm = new FitxerMultimedia(camiImatge);
        
        if (!fm.exists()) throw new AplicacioException("The audio image doesn't exists!");
        
        Audio audio = new Audio(cami, fm, nomAudio, codec, durada, kbps, reproductor);
        
        biblioteca.addFitxer(audio);
    }

    /**
     * Show the current library
     * @return A string list with info of every file in library
     */
    public List<String> mostrarBiblioteca() { 
        List<String> info = new ArrayList<>();
        
        int cnt = 1;
        for (FitxerMultimedia fm : biblioteca.tauFitxers) {
            info.add("-----------------------------");
            info.add("File Index: " + cnt);
            info.add(fm.toString());
            cnt++;
        }
        
        return info;
    }
    
    public List<String> mostrarBibliotecaSimplified() {
        List<String> info = new ArrayList<>();
        
        int cnt = 1;
        
        info.add("-----------------------------");
        for (FitxerMultimedia fm : biblioteca.tauFitxers) {
            info.add("[" + cnt + "] " + fm.getNomFitxer());
            cnt++;
        }
        info.add("-----------------------------");
        
        return info;
    }

    /**
     * Remove a file from the library
     * @param id Id of the file to be removed
     * @throws AplicacioException when the file can't be found
     */
    public void esborrarFitxer(int id) throws AplicacioException {
       FitxerMultimedia fm = (FitxerMultimedia) biblioteca.getAt(id);
       
       for (AlbumFitxersMultimedia afm : album_list) {
           afm.removeAllFitxers(fm);
       }
       
       biblioteca.removeFitxer(fm);
    }
    
    public void esborrarFitxer(String album_name, int id) throws AplicacioException {
        boolean trobat = false;
        Iterator it = album_list.iterator();
        AlbumFitxersMultimedia afm;
        int i = 0;
        
        while(it.hasNext()) {
            afm = (AlbumFitxersMultimedia) it.next();
            
            if(afm.getTitol().equals(album_name)) {
                trobat = true;
                break;
            }
            i++;
        }
        
        if (trobat) afm = album_list.get(i);
        else throw new AplicacioException("The album doesn't exists!");
        
        FitxerMultimedia fm = (FitxerMultimedia) biblioteca.getAt(id);
        
        afm.removeFitxer(fm);
    }
    
    public void esborrarAlbum(String titol) throws AplicacioException {
        boolean trobat = false;
        AlbumFitxersMultimedia afm;
        Iterator it = album_list.iterator();
        
        int i = 0;
        while (it.hasNext()) {
            afm = (AlbumFitxersMultimedia) it.next();
            
            if(afm.getTitol().equals(titol)) {
                trobat = true;
                break;
            }
            i++;
        }
        
        if(trobat) album_list.remove(i);
        else throw new AplicacioException("Album not found!");
    }
    
    public void afegirAlbum(String titol) throws AplicacioException {
        AlbumFitxersMultimedia afm = new AlbumFitxersMultimedia(titol);
        AlbumFitxersMultimedia next_afm;
        boolean same_titol_existing = false;
        
        Iterator it = album_list.iterator();
        
        while (it.hasNext()) {
            next_afm = (AlbumFitxersMultimedia) it.next();
            if (afm.getTitol().equals(next_afm.getTitol())) {
                same_titol_existing = true;
                break;
            }
        }
        
        if (!same_titol_existing) {
            album_list.add(afm);
        } else
            throw new AplicacioException("Album already existing!");
    }
    
    public List<String> mostrarLlistatAlbums() {
        List<String> info = new ArrayList<>();
        
        int cnt = 1;
        info.add("-----------------------------");
        for (AlbumFitxersMultimedia afm : album_list) {
            info.add("[" + cnt + "] " + afm.getTitol());
            cnt++;
        }
        info.add("-----------------------------");
        
        return info;
    }
    
    public List<String> mostrarAlbums() {
        List<String> info = new ArrayList<>();
        
        int cnt = 1;
        info.add("-----------------------------");
        album_list.forEach((afm) -> {
            info.add(afm.toString());
        });
        info.add("-----------------------------");
        
        return info;
    }
    
    public List<String> mostrarAlbum(String album_name) throws AplicacioException {
        List<String> info = new ArrayList<>();
        boolean trobat = false;
        
        
        Iterator it = album_list.iterator();
        AlbumFitxersMultimedia afm;
        
        while(it.hasNext()) {
            afm = (AlbumFitxersMultimedia) it.next();
            
            if(afm.getTitol().equals(album_name)) {
                info.add(afm.toString());
                trobat = true;
                break;
            }
        }
        
        if (!trobat) throw new AplicacioException("The album doesn't exists!");
        
        return info;
    }
    
    public boolean existeixAlbum(String album_titol) {
        
        Iterator it = album_list.iterator();
        AlbumFitxersMultimedia afm;
        
        while(it.hasNext()) {
            afm = (AlbumFitxersMultimedia) it.next();
            
            if(afm.getTitol().equals(album_titol)) return true;
        }
        
        return false;
    }
    
    public void guardarDadesDisc(String camiDesti) throws AplicacioException { 
        try (FileOutputStream fout = new FileOutputStream(new File(camiDesti)); ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(this);
            fout.close();
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Dades carregarDadesDisc(String camiOrigen) throws AplicacioException {
        Dades dades_temp;
        try (FileInputStream fin = new FileInputStream(new File(camiOrigen)); ObjectInputStream ois = new ObjectInputStream(fin)) {
            dades_temp = (Dades) ois.readObject();
            fin.close();
            ois.close();
        } catch (FileNotFoundException ex) {
            throw new AplicacioException("Data file not found!");
        } catch (IOException ex) {
            throw new AplicacioException("IOException caught!");
        } catch (ClassNotFoundException ex) {
            throw new AplicacioException("Class not found in data file!");
        }
        
        return dades_temp;
    }
    
    public void afegirFitxer(String album_name, int selected_file) throws AplicacioException {
        boolean trobat = false;
        Iterator it = album_list.iterator();
        AlbumFitxersMultimedia afm;
        int i = 0;
        
        while(it.hasNext()) {
            afm = (AlbumFitxersMultimedia) it.next();
            
            if(afm.getTitol().equals(album_name)) {
                trobat = true;
                break;
            }
            i++;
        }
        
        if (trobat) afm = album_list.get(i);
        else throw new AplicacioException("The album doesn't exists!");
        
        FitxerMultimedia fm = (FitxerMultimedia) biblioteca.getAt(selected_file);
        
        if (!afm.isFull())
            afm.addFitxer(fm);
        else
            throw new AplicacioException("The album is full!");
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
        
        return to_print;
    }
}