/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.simulacao;

/**
 *
 * @author Helton
 */
public interface Agendavel {

    int getVariacaoPorNivel();
    int getIncrementoPorCiclo();
    int getValor();
    void setValor(int valor);     
    
}
