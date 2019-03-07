/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VinagreJorgeOteroJoel.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.naming.LimitExceededException;

public class CarpetaFitxers {
    
    private int _max_size;
    private ArrayList<FitxerMultimedia> tauFitxers;
    
    public CarpetaFitxers() {
        _max_size = 100;
        tauFitxers = new ArrayList<>(_max_size);
    }
    
    public int getSize() {
        return tauFitxers.size();
    }
    
    public void addFitxer(File file) throws LimitExceededException {
        if (isFull())
            throw new LimitExceededException("Exception: The folder is full.");
        else
            tauFitxers.add((FitxerMultimedia) file);
    }
    
    public void removeFitxer(File file) throws FileNotFoundException {
        if (file instanceof FitxerMultimedia) {
            FitxerMultimedia fm = (FitxerMultimedia) file;
            if (tauFitxers.contains(fm))
            tauFitxers.remove(fm);
            else
                throw new FileNotFoundException("File not found");
        }
    }
    
    public File getAt(int position) throws FileNotFoundException {
        if (tauFitxers.get(position) == null)
            throw new FileNotFoundException("File not found");
        else
            return tauFitxers.get(position);
    }
    
    public void clear() {
        tauFitxers.clear();
    }
    
    public boolean isFull() {
        return getSize() == _max_size;
    }
    
    @Override
    public String toString() {
        String to_print = "";
        int cnt = 1;
        
        to_print += "Carpeta fitxers:\n";
        to_print += "================\n";
        
        for (FitxerMultimedia fitxer : tauFitxers) {
            if (fitxer != null) {
                to_print += "[" + cnt + "] Descripció = " 
                    + fitxer.getDescripcio() 
                    + ", Data = " + fitxer.getUltimaModificacio() 
                    + ", Nom = " + fitxer.getNomFitxer()
                    + ", Ext = " + fitxer.getExtensio()
                    + ", Cami Absolut = " + fitxer.getAbsolutePath()
                    + "\n";
                cnt++;
            }
        }
        
        return to_print;
    }
    
}
