/**
* The AlicacioUB implements a media player application
* that plays different type of media.
*
* @author  Jorge Vinagre Triguero, Joel Otero Martín
* @version 1.0
* @since   2019-03-13 
*/
package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

public class IniciadorAplicacioUB {
    
    private static AplicacioUB2 aplicacio;
    
    /**
    * This is the main method where we call and instance an AplicacioUB1 object.
    * @param args Unused.
    */
    public static void main(String [] args) {
        aplicacio = new AplicacioUB2();
        
        aplicacio.gestioAplicacioUB();
    }
    
}
