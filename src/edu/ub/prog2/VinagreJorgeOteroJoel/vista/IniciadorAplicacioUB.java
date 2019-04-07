/**
* The AlicacioUB implements a media player application
* that plays different type of media.
*
* @author  Jorge Vinagre Triguero, Joel Otero Martín
* @version 2.0
* @since   2019-03-13 
*/
package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

public class IniciadorAplicacioUB {
    
    private static AplicacioUB3 aplicacio;
    
    /**
    * This is the main method where we call and instance an AplicacioUB3 object.
    * @param args Unused.
    */
    public static void main(String [] args) {
        aplicacio = new AplicacioUB3();
        
        aplicacio.gestioAplicacioUB();
    }
    
}
