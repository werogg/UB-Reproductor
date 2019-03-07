/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerMultimedia;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.naming.LimitExceededException;

/**
 *
 * @author wero
 */
public class AplicacioUB1 {
    
    private final CarpetaFitxers carpeta; 
    private FitxerMultimedia fm;
    
    AplicacioUB1() {
        carpeta = new CarpetaFitxers();
    }
    
    public void gestioAplicacioUB() {
        Scanner input = new Scanner(System.in);
        
        ControlMenu app = new ControlMenu(this);
        
        app.manager(input);
        
        
    }
    
    public CarpetaFitxers getCarpeta() {
        return carpeta;
    }
    
    public void addFileOption() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el camí al teu fitxer:");
        String nou_cami = sc.next();
        
        fm = new FitxerMultimedia(nou_cami);
        
        try {
            carpeta.addFitxer(fm);
        } catch (LimitExceededException e) {
            System.err.println(e.getCause());
        } 
    }
    
    public void removeFileOption() {
        Scanner sc = new Scanner(System.in);
        int index_arxiu_sel = -1;
        
        System.out.println("Quin arxiu vols eliminar? [Index]");
        
        try {
            index_arxiu_sel = sc.nextInt();
        } catch (InputMismatchException e) {
            
        }
        
        try {
            fm = (FitxerMultimedia) carpeta.getAt(--index_arxiu_sel);
            carpeta.removeFitxer(fm);
        } catch (FileNotFoundException | IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }
}