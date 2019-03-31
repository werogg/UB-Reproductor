package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Controlador;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.Menu;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AplicacioUB2 {
    
    private final Controlador controlador;
    
    // Declarem les opcions per a referir-se a les opcions del menÃº.
    static private enum OpcionsMenuPrincipal {LIBRARY_MANAGER,SAVE_DATA,RECOVER_DATA,EXIT};
    static private enum OpcionsMenuBiblioteca {ADD_MEDIA,SHOW_LIBRARY,DEL_MEDIA, BACK};
    static private enum OpcionsMenuAfegir {ADD_VIDEO,ADD_AUDIO,BACK};
    // Declarem descripcions personalitzades per a les opcions del menÃº principal
    
    private static final String[] MENU_PRINCIPAL_DESC = {
        "Gestió Biblioteca",
        "Guardar Dades",
        "Recuperar Dades",
        "Sortir"
    };
    
    private static final String[] MENU_LIBRARY_DESC = {
        "Afegir fitxer multimèdia a la biblioteca",
        "Mostrar biblioteca",
        "Eliminar fitxer multimèdia",
        "Menú anterior"
    };
    
    private static final String[] MENU_ADD_MEDIA_DESC = {
        "Afegir vídeo",
        "Afegir àudio",
        "Menú anterior"
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
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menu Principal", OpcionsMenuPrincipal.values());
        Menu<OpcionsMenuBiblioteca> library_menu = new Menu<>("Gestió de la Biblioteca", OpcionsMenuBiblioteca.values());
        Menu<OpcionsMenuAfegir> add_menu = new Menu<>("Afegir media a la Biblioteca", OpcionsMenuAfegir.values());

        // Assignem la descripciÃ³ de les opcions
        menu.setDescripcions(MENU_PRINCIPAL_DESC);
        library_menu.setDescripcions(MENU_LIBRARY_DESC);
        add_menu.setDescripcions(MENU_ADD_MEDIA_DESC);

        // Obtenim una opciÃ³ des del menÃº i fem les accions pertinents
        OpcionsMenuPrincipal opcio;
        
        do {
            // Mostrem les opcions del menÃº
            menu.mostrarMenu();
            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃ ries
            switch(opcio) {
                case LIBRARY_MANAGER:
                    libManager(sc, library_menu, add_menu);
                    break;
                case SAVE_DATA:
                    break;
                case RECOVER_DATA:
                    break;
                case EXIT:
                    System.out.println("Fins aviat!");
                    break;
            }

        } while(opcio!=OpcionsMenuPrincipal.EXIT);
    }
    
    /**
     * Library Manager menu
     * @param sc Scanner (input)
     * @param library_menu The library Menu object
     * @param add_menu The add media Menu object
     */
    private void libManager(Scanner sc, Menu<OpcionsMenuBiblioteca> library_menu, Menu<OpcionsMenuAfegir> add_menu) {
        OpcionsMenuBiblioteca opcio;
        
        do {
            // Prints the menu
            library_menu.mostrarMenu();
            
            // Get the selected option
            opcio = library_menu.getOpcio(sc);
            
            // Switch between options
            switch (opcio) {
                case ADD_MEDIA:
                    addMediaManager(sc, add_menu);
                    break;
                case SHOW_LIBRARY:
                    
                    break;
                case DEL_MEDIA:
                    removeFileOption();
                    break;
                case BACK:
                    break;
            }
        } while (opcio != OpcionsMenuBiblioteca.BACK);
    }
    
    /**
     * Add media manager menu
     * @param sc Scanner (input)
     * @param add_menu The add media Menu object
     */
    private void addMediaManager(Scanner sc, Menu<OpcionsMenuAfegir> add_menu) {
        OpcionsMenuAfegir opcio;
        
        do {
            // Prints the menu
            add_menu.mostrarMenu();
            
            // Get the selected option
            opcio = add_menu.getOpcio(sc);
            
            // Switch between options
            switch (opcio) {
                case ADD_VIDEO:
                    addVideoFileOption();
                    break;
                case ADD_AUDIO:
                    break;
                case BACK:
                    break;
            }
        } while (opcio != OpcionsMenuAfegir.BACK);
    }
    
    /**
    * Starts the manager
    */
    public void gestioAplicacioUB() {
        Scanner input = new Scanner(System.in);
        
        manager(input);
    }
    
    private void addVideoFileOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el camí al teu fitxer de video:");
        String path = sc.next();
        
        System.out.println("Introdueix el nom del teu video:");
        String nomVideo = sc.next();
        
        System.out.println("Introdueix el codec del teu video:");
        String codec = sc.next();
        
        System.out.println("Introdueix la durada del teu video:");
        float durada = sc.nextFloat();
        
        System.out.println("Introdueix la alcada del teu video:");
        int alcada = sc.nextInt();
        
        System.out.println("Introdueix la amplada del teu video:");
        int amplada = sc.nextInt();
        
        System.out.println("Introdueix els fps del teu video:");
        float fps = sc.nextFloat();
        
        try {
            controlador.afegirVideo(path, nomVideo, codec, durada, alcada, amplada, fps);
        } catch (AplicacioException | FileNotFoundException e) {
            System.err.println(e.getCause());
        }
    }
    
    
    /**
    * Option to remove a file by index
    */
    private void removeFileOption() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Quin arxiu vols eliminar? [Index]");
        int index_arxiu_sel = sc.nextInt();
        
        try {
            controlador.esborrarFitxer(index_arxiu_sel);
        } catch (AplicacioException | FileNotFoundException e) {
            System.err.println(e.getCause());
        }
    }
    
    private void showLibraryOption() {
        for (String info: controlador.mostrarBiblioteca()) {
            System.out.println(info);
        }
    }
}