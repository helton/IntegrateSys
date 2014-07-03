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
public class Luminosidade {
    
    int luminosidade;
    
    public Luminosidade(int luminosidade) {
        this.luminosidade = luminosidade;
    }
    
    public int toInteger() {
        return luminosidade;
    }
   
    @Override
    public String toString() {
        return luminosidade + " %";
    }

    public static Luminosidade fromInteger(int luminosidade) {
        return new Luminosidade(luminosidade);
    }
    
    public static String toString(int luminosidade) {
        return fromInteger(luminosidade).toString();
    }    
    
}