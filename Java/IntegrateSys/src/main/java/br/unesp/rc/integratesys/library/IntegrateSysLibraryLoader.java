/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.library;

import com.sun.jna.Native;
import javax.swing.JOptionPane;

/**
 *
 * @author Helton
 */
public class IntegrateSysLibraryLoader {

    private static IntegrateSysLibrary library;    
    
    public static IntegrateSysLibrary getLibrary() {
        if (IntegrateSysLibraryLoader.library == null) {
            try {
                library = (IntegrateSysLibrary) Native.loadLibrary(IntegrateSysLibrary.LIBRARY_NAME, IntegrateSysLibrary.class);
            } catch(UnsatisfiedLinkError ex) {
                JOptionPane.showMessageDialog(null, "Arquivo \"" + IntegrateSysLibrary.LIBRARY_NAME 
                        + ".dll\" não pode ser carregado.\nCertifique-se de possuir esse "
                        + "arquivo no diretório da aplicação e que ele seja compatível "
                        + "com seu sistema operacional (32 ou 64 bits).", "IntegrateSys - Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }        
        }
        return IntegrateSysLibraryLoader.library;
    }
    
}
