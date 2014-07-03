/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.app;

import br.unesp.rc.integratesys.view.FormAmbiente;
import br.unesp.rc.integratesys.view.FormPrincipal;
import java.awt.EventQueue;

/**
 *
 * @author Helton
 */
public class App {
    
    private static void definirLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }        
    }
    
    public static void main(String[] args) {
        definirLookAndFeel();        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormAmbiente().exibir();
            }
        });
    }    
    
}
