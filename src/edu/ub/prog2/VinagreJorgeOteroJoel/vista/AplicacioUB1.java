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
    
    private final CarpetaFitxers carpeta; 
    private FitxerMultimedia fm;
    
    // Declarem les opcions per a referir-se a les opcions del menÃº.
    static private enum OpcionsMenuPrincipal {PRINT_FOLDER,ADD_FILE,DEL_FILE,EXIT};
    static private enum OpcionsSubmenu1 {MENU_S1_OPCIO1,MENU_S1_OPCIO2,MENU_S1_SORTIR};
    // Declarem descripcions personalitzades per a les opcions del menÃº principal
    static private String[] descMenuPrincipal={"Mostrar carpeta",
                                               "Afegir arxiu",
                                               "Eliminar arxiu",
                                               "Sortir"};
    
    AplicacioUB1() {
        carpeta = new CarpetaFitxers();
    }
    
    public void manager(Scanner sc) {

        // Creem l'objecte per al menÃº. Li passem com a primer parÃ metre el nom del menÃº
        Menu<OpcionsMenuPrincipal> menu=new Menu<OpcionsMenuPrincipal>("Menu Principal",OpcionsMenuPrincipal.values());

        // Assignem la descripciÃ³ de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opciÃ³ des del menÃº i fem les accions pertinents
        OpcionsMenuPrincipal opcio = null;
        do {
            // Mostrem les opcions del menÃº
            menu.mostrarMenu();
            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃ ries
            switch(opcio) {
                case PRINT_FOLDER:
                    System.out.println(carpeta);
                    break;
                case ADD_FILE:
                    this.addFileOption();
                    break;
                case DEL_FILE:
                    this.removeFileOption();
                    break;
                case EXIT:
                    System.out.println("Fins aviat!");
                    break;
            }

        } while(opcio!=OpcionsMenuPrincipal.EXIT);
    }
    
    public void gestioAplicacioUB() {
        Scanner input = new Scanner(System.in);
        
        manager(input);
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