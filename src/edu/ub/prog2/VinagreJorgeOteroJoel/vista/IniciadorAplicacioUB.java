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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            // if there's an error setting the gui look and feel we will show this exception trought the console logger
            Logger.getLogger(IniciadorAplicacioUB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AplicacioUB4 aplicacio = new AplicacioUB4();
        aplicacio.setVisible(true);
    }
}
