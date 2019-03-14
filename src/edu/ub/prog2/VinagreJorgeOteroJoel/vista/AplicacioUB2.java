package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.VinagreJorgeOteroJoel.model.FitxerMultimedia;
import edu.ub.prog2.utils.Menu;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.naming.LimitExceededException;

public class AplicacioUB2 {
    
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
    
    /**
    * Constructor for class AplicacioUB1
    */
    AplicacioUB2() {
        carpeta = new CarpetaFitxers();
    }
    
    /**
    * The app's menu manager
    * @param sc A Scanner of System.in
    */
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
    
    /**
    * Starts the manager
    */
    public void gestioAplicacioUB() {
        Scanner input = new Scanner(System.in);
        
        manager(input);
    }
    
    /**
    * @return A CarpetaFitxers object (The folder)
    */
    public CarpetaFitxers getCarpeta() {
        return carpeta;
    }
    
    /**
    * Option to add a new file to the folder
    */
    private void addFileOption() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el camí al teu fitxer:");
        String nou_cami = sc.next();
        
        sc.nextLine();
        
        System.out.println("Introdueix la descripció del teu fitxer:");
        String nou_desc = sc.nextLine();
        
        fm = new FitxerMultimedia(nou_cami);
        fm.setDescripcio(nou_desc);
        
        try {
            carpeta.addFitxer(fm);
        } catch (LimitExceededException e) {
            System.err.println(e.getCause());
        } 
    }
    
    /**
    * Option to remove a file by index
    */
    private void removeFileOption() {
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