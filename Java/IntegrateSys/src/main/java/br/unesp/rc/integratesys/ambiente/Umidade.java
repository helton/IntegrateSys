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
public class Umidade {
    
    int umidade;
    
    public Umidade(int umidade) {
        this.umidade = umidade;
    }
    
    public int toInteger() {
        return umidade;
    }
   
    @Override
    public String toString() {
        return umidade + " %";
    }

    public static Umidade fromInteger(int umidade) {
        return new Umidade(umidade);
    }
    
    public static String toString(int umidade) {
        return fromInteger(umidade).toString();
    }    
    
}