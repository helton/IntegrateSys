/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.ambiente;

/**
 *
 * @author Helton
 */
public class Temperatura {
    
    int temperatura;
    
    public Temperatura(int temperatura) {
        this.temperatura = temperatura;
    }
    
    public int toInteger() {
        return temperatura;
    }
   
    @Override
    public String toString() {
        return temperatura + "º C";
    }

    public static Temperatura fromInteger(int temperatura) {
        return new Temperatura(temperatura);
    }
    
    public static String toString(int temperatura) {
        return fromInteger(temperatura).toString();
    }    
    
}
