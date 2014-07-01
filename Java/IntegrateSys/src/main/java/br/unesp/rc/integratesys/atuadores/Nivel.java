/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Helton
 */
public enum Nivel {
    DESLIGADO(0),
    BAIXO(1),
    MEDIO(2),
    ALTO(3);
    
    private final int valor;
    private static final Map<Integer, Nivel> intToTypeMap = new HashMap<>();
    
    private Nivel(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }  
    
    public static Nivel fromInt(int i) {
        Nivel type = intToTypeMap.get(Integer.valueOf(i));
        if (type == null) 
            return Nivel.DESLIGADO;
        return type;
    }    
    
    static {
        for (Nivel type: Nivel.values()) {
            intToTypeMap.put(type.valor, type);
        }
    }
    
}
