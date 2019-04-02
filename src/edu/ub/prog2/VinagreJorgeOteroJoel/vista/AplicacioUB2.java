package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Controlador;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.Menu;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AplicacioUB2 {
    
    private final Controlador controlador;
    
    // Declarem les opcions per a referir-se a les opcions del menÃº.
    static private enum OpcionsMenuPrincipal {LIBRARY_MANAGER,SAVE_DATA,RECOVER_DATA,EXIT};
    static private enum OpcionsMenuBiblioteca {ADD_MEDIA,SHOW_LIBRARY,DEL_MEDIA, BACK};
    static private enum OpcionsMenuAfegir {ADD_VIDEO,ADD_AUDIO,BACK};
    // Declarem descripcions personalitzades per a les opcions del menÃº principal
    
    private static final String[] MENU_PRINCIPAL_DESC = {
        "Library Management",
        "Save Data",
        "Recover Data",
        "Exit"
    };
    
    private static final String[] MENU_LIBRARY_DESC = {
        "Add media file to the library",
        "Show library",
        "Remove media file",
        "Go back"
    };
    
    private static final String[] MENU_ADD_MEDIA_DESC = {
        "Add video",
        "Add audio",
        "Go back"
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
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Main Menu", OpcionsMenuPrincipal.values());
        Menu<OpcionsMenuBiblioteca> library_menu = new Menu<>("Library Management", OpcionsMenuBiblioteca.values());
        Menu<OpcionsMenuAfegir> add_menu = new Menu<>("Add media to the library", OpcionsMenuAfegir.values());

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
                    saveDataOption();
                    break;
                case RECOVER_DATA:
                    loadDataOption();
                    break;
                case EXIT:
                    System.out.println("See you later!");
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
                    showLibraryOption();
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
                    addAudioFileOption();
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
    
    /**
     * Option to add a video
     */
    private void addVideoFileOption() {
        String path, nomVideo, codec;
        float durada = 0f, fps = 0f;
        int alcada = 0, amplada = 0;
        boolean exception_caught = false;
        
        Scanner sc = new Scanner(System.in);
        
        
        System.out.println("Introduce the path to the video file:");
        path = sc.next();
        
        
        System.out.println("Introduce the video name:");
        nomVideo = sc.next();
        
        System.out.println("Introduce the video codec:");
        codec = sc.next();
        
        do {
            System.out.println("Introduce the video duration:");
            
            try {
                durada = sc.nextFloat();
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
            }
            
            sc.nextLine();
            
        } while (durada <= 0);
        
        do {
            System.out.println("Introduce the video high:");
            
            try {
                alcada = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
            }
            
            sc.nextLine();
            
        } while (alcada <= 0);
        
        do {
            System.out.println("Introduce the video width:");
            
            try {
                amplada = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
            }
            
            sc.nextLine();
            
        } while (amplada <= 0);
        
        do {
            System.out.println("Introduce the video FPS:");
            
            try {
                fps = sc.nextFloat();
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
            }
            
            sc.nextLine();
            
        } while (fps <= 0);
        
        
        try {
            controlador.afegirVideo(path, nomVideo, codec, durada, alcada, amplada, fps);
        } catch (AplicacioException | FileNotFoundException e) {
            System.err.println(e.getMessage());
            exception_caught = true;
        }
        
        if (!exception_caught) System.out.println("Video added to the library!");
    }
    
    /**
     * Option to add an audio
     */
    private void addAudioFileOption() {
        boolean exception_caught = false;
        float durada = 0f;
        int kbps = 0;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce the path to the audio file:");
        String cami = sc.next();
        
        System.out.println("Itroduce the path to the audio's image:");
        String camiImatge = sc.next();
        
        System.out.println("Introduce the audio name:");
        String nomAudio = sc.next();
        
        System.out.println("Introduce the codec:");
        String codec = sc.next();
        
        
        do {
            System.out.println("Introduce the audio duration:");
            
            try {
                durada = sc.nextFloat();
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
            }
            
            sc.nextLine(); // Clean the buffer
            
        } while (durada <= 0);
        
        do {
            System.out.println("Introduce the audio kbps:");
            
            try {
                kbps = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
            }
            
            sc.nextLine(); // Clean the buffer
            
        } while (kbps <= 0);
        
        try {
            controlador.afegirAudio(cami, camiImatge, nomAudio, codec, durada, kbps);
        } catch (AplicacioException | FileNotFoundException e) {
            System.err.println(e.getMessage());
            exception_caught = true;
        }
        
        if (!exception_caught) System.out.println("Audio file added to the library!");
    }
    
    
    /**
    * Option to remove a file by index
    */
    private void removeFileOption() {
        boolean exception_caught = false;
        Scanner sc = new Scanner(System.in);
        
        showLibraryOption();
        
        System.out.println("Which file do you want to remove? [Index]");
        int index_arxiu_sel = sc.nextInt();
        
        try {
            controlador.esborrarFitxer(index_arxiu_sel);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            exception_caught = true;
        }
        
        if (!exception_caught) System.out.println("The file was succefully removed!");
    }
    
    /**
     * Option to show the current library
     */
    private void showLibraryOption() {
        controlador.mostrarBiblioteca().forEach((info) -> {
            System.out.println(info);
        });
    }
    
    /**
     * Option to save the data
     */
    private void saveDataOption() {
        boolean exception_caught = false;
        Scanner sc = new Scanner(System.in);
        String camiDesti;
        
        System.out.println("Path to the Data File to be saved?");
        camiDesti = sc.next();
        
        try {
            controlador.guardarDadesDisc(camiDesti);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            exception_caught = true;
        }
        
        if (!exception_caught) System.out.println("Data succefully saved!");
    }
    
    /**
     * Option to load the data
     */
    private void loadDataOption() {
        boolean exception_caught = false;
        Scanner sc = new Scanner(System.in);
        String camiOrigen;
        
        System.out.println("Path to the Data File to be loaded?");
        camiOrigen = sc.next();
        
        try {
            controlador.carregarDadesDisc(camiOrigen);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getCause());
            exception_caught = true;
        } 
        
        if (!exception_caught) System.out.println("Data succefully loaded!");
    }
}