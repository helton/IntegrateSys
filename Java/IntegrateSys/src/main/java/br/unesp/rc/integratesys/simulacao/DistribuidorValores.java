/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.simulacao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Helton
 */
public class DistribuidorValores {
    
    public static List<Integer> distribuir(int variacao, int incremento) {
        List<Integer> valores = new ArrayList<>();
        int restante = variacao;
        while (restante != 0) {
            int valorIteracao = 0;
            if (Integer.signum(variacao) == 1) {
                valorIteracao = Math.min(restante, incremento);
            }
            else if (Integer.signum(variacao) == -1) {
                valorIteracao = Math.max(restante, -incremento);
            }
            valores.add(valorIteracao);
            restante -= valorIteracao;                
        }
        return valores;
    }
    
}
