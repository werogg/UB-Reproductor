package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import edu.ub.prog2.VinagreJorgeOteroJoel.model.CarpetaFitxers;
import edu.ub.prog2.utils.Menu;
import java.util.Scanner;

/**
 * Exemple d'utilitzaciÃ³ de la classe MenÃº. Aquest exemple requereix la llibreria
 * UtilsProg2.jar.
 * @author Xavier BarÃ³
 */

// TODO: ELIMINAR CLASE
public class ControlMenu {
    private final CarpetaFitxers carpeta; 
    private final AplicacioUB1 app;

    ControlMenu(AplicacioUB1 app) {
        this.carpeta = app.getCarpeta();
        this.app = app;
    }

    // Declarem les opcions per a referir-se a les opcions del menÃº.
    static private enum OpcionsMenuPrincipal {PRINT_FOLDER,ADD_FILE,DEL_FILE,EXIT};
    static private enum OpcionsSubmenu1 {MENU_S1_OPCIO1,MENU_S1_OPCIO2,MENU_S1_SORTIR};
    
    // Declarem descripcions personalitzades per a les opcions del menÃº principal
    static private String[] descMenuPrincipal={"Mostrar carpeta",
                                               "Afegir arxiu",
                                               "Eliminar arxiu",
                                               "Sortir"};

    // Declarem descripcions personalitzades per a les opcions del menÃº secundari
    /*static private String[] descMenu2={"Primera OpciÃ³ del menÃº secundari",
                                                "Segona OpciÃ³ del menÃº secundari",
                                                "MenÃº anterior"};*/


    /**
     * MenÃº principal del reproductor d'audio
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
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
                    app.addFileOption();
                    break;
                case DEL_FILE:
                    app.removeFileOption();
                    break;
                case EXIT:
                    System.out.println("Fins aviat!");
                    break;
            }

        } while(opcio!=OpcionsMenuPrincipal.EXIT);
    }

    /**
     * MenÃº secundari
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */
    /*
    private void gestioMenuSecundari(Scanner sc) {

        // Creem l'objecte per al menÃº. Li passem com a primer parÃ metre el nom del menÃº
        Menu<OpcionsSubmenu1> menu=new Menu<OpcionsSubmenu1>("Menu Secundari",OpcionsSubmenu1.values());

        // Assignem la descripciÃ³ de les opcions
        menu.setDescripcions(descMenu2);

        // Obtenim una opciÃ³ des del menÃº i fem les accions pertinents
        OpcionsSubmenu1 opcio = null;
        do {
            // Mostrem les opcions del menÃº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃ ries
            switch(opcio) {
                case MENU_S1_OPCIO1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃ³
                    System.out.println("Has triat la opciÃ³ 1");
                    break;
                case MENU_S1_OPCIO2:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃ³
                    System.out.println("Has triat la opciÃ³ 2");
                    break;
                case MENU_S1_SORTIR:
                    System.out.println("Fins aviat!");
                    break;
            }

        } while(opcio!=OpcionsSubmenu1.MENU_S1_SORTIR);
    }*/

}
