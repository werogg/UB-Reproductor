/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerMultimedia;
import edu.ub.prog2.utils.Menu;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.naming.LimitExceededException;

/**
 *
 * @author wero
 */
public class AplicacioUB1 {
    Scanner input = new Scanner(System.in);
    CarpetaFitxers carpeta = new CarpetaFitxers(); 
    
    AplicacioUB1() {
        
    }
    
    public void gestioAplicacioUB() {
        
        int seleccio;
        
        Menu menu = new Menu("Opcions", OpcionsMenu.values());
        
        menu.mostrarMenu();
        
        do {
            seleccio = input.nextInt();
            FitxerMultimedia fm;
            
            switch (seleccio) {
                case 1:
                    break;
                case 2:
                    fm = addNewFile();
                    fm.setUltimaModificacio();
                    
                    try {
                        carpeta.addFitxer(fm);
                    } catch (LimitExceededException e) {
                        System.err.println(e.getExplanation());
                    }
                    break;
                case 3:
                    int index_arxiu_sel = -1;
                    System.out.println("Quin arxiu vols eliminar? [Index]");
                    try {
                        index_arxiu_sel = input.nextInt();
                    } catch (InputMismatchException e) {
                        
                    }
                    
                    
                    try {
                        
                        fm = (FitxerMultimedia) carpeta.getAt(--index_arxiu_sel);
                        carpeta.removeFitxer(fm);
                    } catch (FileNotFoundException | IndexOutOfBoundsException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println(carpeta);
                    break;
            }
            
        } while (seleccio != 1);
        
    }
    
    void verifyIndex() {
        
    }
    
    public FitxerMultimedia addNewFile() {
        
        System.out.println("Introdueix el camí al teu fitxer:");
        String nou_cami = input.next();
        
        FitxerMultimedia arxiu = new FitxerMultimedia(nou_cami);
        
        return arxiu;
    }
    
}