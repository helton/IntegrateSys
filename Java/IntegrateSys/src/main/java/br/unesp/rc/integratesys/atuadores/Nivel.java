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
    private static final Map<Integer, Nivel> mapaConversaoIntParaEnum = new HashMap<>();
    
    private Nivel(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }  
    
    public static Nivel fromInt(int i) {
        Nivel tipo = mapaConversaoIntParaEnum.get(Integer.valueOf(i));
        if (tipo == null) 
            return Nivel.DESLIGADO;
        return tipo;
    }    
    
    static {
        for (Nivel tipo: Nivel.values()) {
            mapaConversaoIntParaEnum.put(tipo.valor, tipo);
        }
    }
    
}
