package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.controlador.Controlador;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.Menu;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AplicacioUB3 {
    
    private final Controlador controlador;
    
    // Declarem les opcions per a referir-se a les opcions del menÃº.
    static private enum OpcionsMenuPrincipal {LIBRARY_MANAGER, ALBUM_MANAGER, PLAYER_MANAGER, SAVE_DATA, RECOVER_DATA, EXIT};
    
    static private enum OpcionsMenuBiblioteca {ADD_MEDIA, SHOW_LIBRARY, DEL_MEDIA, BACK};
    static private enum OpcionsMenuAfegir {ADD_VIDEO, ADD_AUDIO, BACK};
    
    static private enum OpcionsMenuAlbum {ADD_ALBUM, SHOW_ALBUMS, DEL_ALBUM, MANAGE_ALBUM, BACK};
    static private enum OpcionsMenuAlbumManager {ADD_MEDIA, SHOW_ALBUM, DEL_MEDIA, BACK};
    static private enum OpcionsMenuPlayerManager {PLAY_MEDIA, PLAY_LIBRARY, PLAY_ALBUM, CONTINUE_PLAY, RANDOM_PLAY, PLAYING_MANAGER , BACK};
    static private enum OpcionsMenuPlayingManager {RESUME, PAUSE, STOP, JUMP, BACK}
    
    // Declarem descripcions personalitzades per a les opcions del menÃº principal
    private static final String[] MENU_PRINCIPAL_DESC = {
        "Library Management",
        "Album Management",
        "Player Management",
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
    
    private static final String[] MENU_ALBUM_DESC = {
        "Add new album",
        "Show albums",
        "Remove album",
        "Manage album",
        "Go back"
    };
    
    private static final String[] MENU_ALBUM_MANAGER_DESC = {
        "Add media to the album",
        "Show album",
        "Remove media from the album",
        "Go back"
    };
    
    private static final String[] MENU_PLAYER_DESC = {
        "Play media",
        "Play full library",
        "Play album",
        "Activate/Deactivate continue play",
        "Activate/Deactivate random play",
        "Playing now manager",
        "Go back"
    };
    
    private static final String[] MENU_PLAYING_DESC = {
        "Resume",
        "Pause",
        "Stop",
        "Jump",
        "Go back"
    };
    
    /**
    * Constructor for class AplicacioUB1
    */
    AplicacioUB3() {
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
        
        Menu<OpcionsMenuAlbum> album_menu = new Menu<>("Album menu", OpcionsMenuAlbum.values());
        Menu<OpcionsMenuAlbumManager> album_manager_menu = new Menu<>("Album manager menu", OpcionsMenuAlbumManager.values());
        Menu<OpcionsMenuPlayerManager> player_manager_menu = new Menu<>("Album player manager menu", OpcionsMenuPlayerManager.values());
        Menu<OpcionsMenuPlayingManager> playing_manager_menu = new Menu<>("Album playing manager menu", OpcionsMenuPlayingManager.values());
        

        menu.setDescripcions(MENU_PRINCIPAL_DESC);
        library_menu.setDescripcions(MENU_LIBRARY_DESC);
        add_menu.setDescripcions(MENU_ADD_MEDIA_DESC);
        
        album_menu.setDescripcions(MENU_ALBUM_DESC);
        album_manager_menu.setDescripcions(MENU_ALBUM_MANAGER_DESC);
        player_manager_menu.setDescripcions(MENU_PLAYER_DESC);
        playing_manager_menu.setDescripcions(MENU_PLAYING_DESC);
        

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
                case ALBUM_MANAGER:
                    albumManager(sc, album_menu, album_manager_menu);
                    break;
                case PLAYER_MANAGER:
                    playerManager(sc, player_manager_menu, playing_manager_menu);
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
    
    private void albumManager(Scanner sc, Menu<OpcionsMenuAlbum> album_menu, Menu<OpcionsMenuAlbumManager> album_menu_manager) {
        OpcionsMenuAlbum opcio;
        
        do {
            // Prints the menu
            album_menu.mostrarMenu();
            
            // Get the selected option
            opcio = album_menu.getOpcio(sc);
            
            // Switch between options
            switch (opcio) {
                case ADD_ALBUM:
                    createAlbumOpt();
                    break;
                case SHOW_ALBUMS:
                    showAlbumsSimplified();
                    break;
                case DEL_ALBUM:
                    removeAlbumOpt();
                    break;
                case MANAGE_ALBUM:
                    manageAlbumOpt(sc, album_menu_manager);
                    break;
                case BACK:
                    break;
            }
        } while (opcio != OpcionsMenuAlbum.BACK);
    }
    
    private void manageAlbumManager(Scanner sc, Menu<OpcionsMenuAlbumManager> album_menu, String album_name) throws AplicacioException {
        OpcionsMenuAlbumManager opcio;
        
        do {
            if (!controlador.existeixAlbum(album_name)) throw new AplicacioException("Album index out of bounds!"); 
            
            // Prints the menu
            album_menu.mostrarMenu();
            
            // Get the selected option
            opcio = album_menu.getOpcio(sc);
            
            // Switch between options
            switch (opcio) {
                case ADD_MEDIA:
                    addMediaToAlbumOpt(album_name);
                    break;
                case SHOW_ALBUM:
                    showAlbumOpt(album_name);
                    break;
                case DEL_MEDIA:
                    removeMediaFromAlbumOpt(album_name);
                    break;
                case BACK:
                    break;
            }
        } while (opcio != OpcionsMenuAlbumManager.BACK);
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
    
    private void playerManager(Scanner sc, Menu<OpcionsMenuPlayerManager> player_menu, Menu<OpcionsMenuPlayingManager> playing_menu) {
        OpcionsMenuPlayerManager opcio;
        
        do {
            // Prints the menu
            player_menu.mostrarMenu();
            
            // Get the selected option
            opcio = player_menu.getOpcio(sc);
            
            // Switch between options
            switch (opcio) {
                case PLAY_MEDIA:
                    playMediaOpt();
                    break;
                case PLAY_LIBRARY:
                    playLibraryOpt();
                    break;
                case PLAY_ALBUM:
                    playAlbumOpt();
                    break;
                case CONTINUE_PLAY:
                    enableDisableCiclicPlayingOpt();
                    break;
                case RANDOM_PLAY:
                    enableDisableRandomPlayingOpt();
                    break;
                case PLAYING_MANAGER:
                    playingManager(sc, playing_menu);
                    break;
                case BACK:
                    break;
            }
        } while (opcio != OpcionsMenuPlayerManager.BACK);
    }
    
    private void playingManager(Scanner sc, Menu<OpcionsMenuPlayingManager> playing_menu) {
        OpcionsMenuPlayingManager opcio;
        
        do {
            // Prints the menu
            playing_menu.mostrarMenu();
            
            // Get the selected option
            opcio = playing_menu.getOpcio(sc);
            
            // Switch between options
            switch (opcio) {
                case RESUME:
                    resumePlayingOpt();
                    break;
                case PAUSE:
                    pausePlayingOpt();
                    break;
                case STOP:
                    stopPlayingOpt();
                    break;
                case JUMP:
                    jumpPlayingOpt();
                    break;
                case BACK:
                    break;
            }
        } while (opcio != OpcionsMenuPlayingManager.BACK);
    }

    private void manageAlbumOpt(Scanner sc, Menu<OpcionsMenuAlbumManager> album_menu) {
        String album_name;
        showAlbumsSimplified();
        System.out.println("Select an album: (by name)");
        album_name = sc.nextLine();
        
        try {
            manageAlbumManager(sc, album_menu, album_name);
        } catch (AplicacioException ex) {
            System.out.println(ex.getMessage());
        }
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
        path = sc.nextLine();
        
        
        System.out.println("Introduce the video name:");
        nomVideo = sc.nextLine();
        
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
        } catch (AplicacioException e) {
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
        String cami = sc.nextLine();
        
        System.out.println("Itroduce the path to the audio's image:");
        String camiImatge = sc.nextLine();
        
        System.out.println("Introduce the audio name:");
        String nomAudio = sc.nextLine();
        
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
        } catch (AplicacioException e) {
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
        
        showLibrarySimplified();
        
        System.out.println("Which file do you want to remove?");
        int index_arxiu_sel = sc.nextInt();
        --index_arxiu_sel;
        
        try {
            controlador.esborrarFitxer(index_arxiu_sel);
        } catch (AplicacioException e) {
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
    
    private void showLibrarySimplified() {
        controlador.mostrarBibliotecaSimplified().forEach((info) -> {
            System.out.println(info);
        });
    }
    
    private void showAlbumsSimplified() {
        controlador.mostrarLlistatAlbums().forEach((info) -> {
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
        } catch (AplicacioException ex) {
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
        } catch (AplicacioException e) {
            System.err.println(e.getCause());
            exception_caught = true;
        } 
        
        if (!exception_caught) System.out.println("Data succefully loaded!");
    }
    
    private void createAlbumOpt() {
        boolean exception_caught = false;
        Scanner sc = new Scanner(System.in);
        String titol;
        int tamany = 0;
        
        System.out.println("Introduce the album name");
        titol = sc.nextLine();

        try {
            controlador.afegirAlbum(titol);
        } catch (AplicacioException e) {
            exception_caught = true;
        }
        
        if (!exception_caught) {
            System.out.println("The album was created succesfully!");
        }
    }
    
    private void addMediaToAlbumOpt(String album_name) {
        boolean exception_caught = false;
        Scanner sc = new Scanner(System.in);
        int selected_file;
        
        showLibrarySimplified();
        System.out.println("Select a file:");
        selected_file = sc.nextInt();
        selected_file--;
        
        try {
            controlador.afegirFitxer(album_name, selected_file);
        } catch (AplicacioException ex) {
            System.err.println(ex.getMessage());
        }
        
        if (!exception_caught) {
            System.out.println("The file was added succesfully!");
        }
        
    }
    
    private void showAlbumOpt(String album_name) {
        try {
            System.out.println(controlador.mostrarAlbum(album_name));
        } catch (AplicacioException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private void removeMediaFromAlbumOpt(String album_name) {
        boolean exception_caught = false;
        Scanner sc = new Scanner(System.in);
        showAlbumOpt(album_name);
        System.out.println("Select the file to be removed:");
        int selected_file = sc.nextInt();
        selected_file--;
        
        try {
            controlador.esborrarFitxer(album_name, selected_file);
        } catch (AplicacioException ex) {
            exception_caught = true;
            System.err.println(ex.getMessage());
        }
        
        if (!exception_caught) {
            System.out.println("The file was succefully removed from the album!");
        }
    }
    
    private void removeAlbumOpt() {
        boolean exception_caught = false;
        Scanner sc = new Scanner(System.in);
        showAlbumsSimplified();
        System.out.println("Select the album to be removed:");
        String selected_album = sc.nextLine();
        
        try {
            controlador.esborrarAlbum(selected_album);
        } catch (AplicacioException ex) {
            exception_caught = true;
            System.err.println(ex.getMessage());
        }
        
        if (!exception_caught) {
            System.out.println("The album was succefully removed!");
        }
    }
    
    private void enableDisableCiclicPlayingOpt() {
        controlador.setReproduccioCiclica();
        
        if(controlador.isReproduccioCiclica()) System.out.println("CiclicPlaying is now enabled!");
        else System.out.println("CiclicPlaying is now disabled!");
    }
    
    private void enableDisableRandomPlayingOpt(){
        controlador.setReproduccioAleatoria();
        
        if(controlador.isReproduccioAleatoria()) System.out.println("RandomPlaying is now enabled");
        else System.out.println("RandomPlaying is now disabled!");
    }
    
    private void resumePlayingOpt() {
        boolean exception_caught = false;
        
        try {
            controlador.reemprenReproduccio();
        } catch (AplicacioException ex) {
            System.err.println(ex.getMessage());
            exception_caught = true;
        }
        
        if (!exception_caught) System.out.println("Playing resumed!");
    }
    
    private void pausePlayingOpt() {
        boolean exception_caught = false;
        
        try {
            controlador.pausaReproduccio();
        } catch (AplicacioException ex) {
            System.err.println(ex.getMessage());
            exception_caught = true;
        }
        
        if (!exception_caught) System.out.println("Playing paused!");
    }
    
    private void stopPlayingOpt() {
        boolean exception_caught = false;
        try {
            controlador.aturaReproduccio();
        } catch (AplicacioException ex) {
            System.err.println(ex.getMessage());
            exception_caught = true;
        }
        
        if (!exception_caught) System.out.println("Playing stopped!");
    }
    
    private void jumpPlayingOpt() {
        boolean exception_caught = false;
        try {
            controlador.saltaReproduccio();
        } catch (AplicacioException ex) {
            System.err.println(ex.getMessage());
            exception_caught = true;
        }
        
        if (!exception_caught) System.out.println("File playing skipped!");
    }
    
    private void playMediaOpt() {
        boolean exception_caught = false;
        Scanner sc = new Scanner(System.in);
        int play_id;
        
        showLibrarySimplified();
        System.out.println("Quin fitxer vols reproduïr? (Index)");
        play_id = sc.nextInt();
        --play_id;
        
        controlador.obrirFinestraReproductor();
        try {
            controlador.reproduirFitxer(play_id);
        } catch (AplicacioException ex) {
            System.err.println(ex.getMessage());
            exception_caught = true;
        }
        
        if (!exception_caught) System.out.println("Iniciant reproductor...");
    }
    
    private void playLibraryOpt() {
        boolean exception_caught = false;
        controlador.obrirFinestraReproductor();
        try {
            controlador.reproduirCarpeta();
        } catch (AplicacioException ex) {
            System.err.println(ex.getMessage());
        }
        
        if (!exception_caught) System.out.println("Inciant reproductor...");
    }
    
    private void playAlbumOpt() {
        boolean exception_caught = false;
        Scanner sc = new Scanner(System.in);
        showAlbumsSimplified();
        System.out.println("Which album do you want to play? (By name)");
        String play_album = sc.nextLine();
        controlador.obrirFinestraReproductor();
        
        try {
            controlador.reproduirCarpeta(play_album);
        } catch (AplicacioException ex) {
            System.err.println(ex.getMessage());
        }
        
        if (!exception_caught) System.out.println("Inciant reproductor...");
    }
}