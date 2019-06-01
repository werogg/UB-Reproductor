/**
* The AlicacioUB implements a media player application
* that plays different type of media.
*
* @author  Jorge Vinagre Triguero, Joel Otero Martín
* @version 2.0
* @since   2019-03-13 
*/
package edu.ub.prog2.VinagreJorgeOteroJoel.vista;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class IniciadorAplicacioUB {
    
    
    /**
    * This is the main method where we call and instance an AplicacioUB4 object.
    * @param args Unused.
    */
    public static void main(String [] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            AplicacioUB4Remastered aplicacio = new AplicacioUB4Remastered();
            aplicacio.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(IniciadorAplicacioUB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
