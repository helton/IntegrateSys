/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.library;

import com.sun.jna.Native;

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
                System.err.println("Dll " + IntegrateSysLibrary.LIBRARY_NAME 
                        + " não pode ser carregada. Certifique-se de possuir esse "
                        + "arquivo no diretório da aplicação e que ele seja compatível "
                        + "com a arquitetura de seu sistema operacional (32 ou 64 bits).");
                System.err.println("Erro: " + ex.getMessage());
            }        
        }
        return IntegrateSysLibraryLoader.library;
    }
    
}
