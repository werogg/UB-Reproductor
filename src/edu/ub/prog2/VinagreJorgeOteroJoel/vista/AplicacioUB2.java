package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Controlador;
import edu.ub.prog2.utils.Menu;
import java.util.Scanner;

public class AplicacioUB2 {
    
    private final Controlador controlador;
    
    // Declarem les opcions per a referir-se a les opcions del menÃº.
    static private enum OpcionsMenuPrincipal {PRINT_FOLDER,ADD_FILE,DEL_FILE,EXIT};
    static private enum OpcionsSubmenu1 {MENU_S1_OPCIO1,MENU_S1_OPCIO2,MENU_S1_SORTIR};
    // Declarem descripcions personalitzades per a les opcions del menÃº principal
    
    private static final String[] MENU_DESC = {
        "Mostrar carpeta",
        "Afegir arxiu",
        "Eliminar arxiu",
        "Sortir"
    };
    
    /**
    * Constructor for class AplicacioUB1
    */
    AplicacioUB2() {
        controlador = new Controlador();
    }
    
    /**
    * The app's menu manager
    * @param sc A Scanner of System.in
    */
    public void manager(Scanner sc) {

        // Creem l'objecte per al menÃº. Li passem com a primer parÃ metre el nom del menÃº
        Menu<OpcionsMenuPrincipal> menu=new Menu<>("Menu Principal",OpcionsMenuPrincipal.values());

        // Assignem la descripciÃ³ de les opcions
        menu.setDescripcions(MENU_DESC);

        // Obtenim una opciÃ³ des del menÃº i fem les accions pertinents
        OpcionsMenuPrincipal opcio;
        
        do {
            // Mostrem les opcions del menÃº
            menu.mostrarMenu();
            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃ ries
            switch(opcio) {
                case PRINT_FOLDER:
                    System.out.println(controlador.getFolder());
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
    * Option to add a new file to the folder
    */
    private void addFileOption() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el camí al teu fitxer:");
        String nou_cami = sc.next();
        
        sc.nextLine();
        
        System.out.println("Introdueix la descripció del teu fitxer:");
        String nou_desc = sc.nextLine();
        
        controlador.FolderAddFile(nou_cami, nou_desc);
    }
    
    /**
    * Option to remove a file by index
    */
    private void removeFileOption() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Quin arxiu vols eliminar? [Index]");
        int index_arxiu_sel = sc.nextInt();
        
        controlador.FolderRemoveFile(index_arxiu_sel);
    }
}