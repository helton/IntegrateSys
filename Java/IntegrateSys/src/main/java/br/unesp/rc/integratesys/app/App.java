/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.app;

import br.unesp.rc.integratesys.view.FormAmbiente;
import java.awt.EventQueue;

/**
 *
 * @author Helton
 */
public class App {
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormAmbiente().exibir();
            }
        });
    }    
    
}
