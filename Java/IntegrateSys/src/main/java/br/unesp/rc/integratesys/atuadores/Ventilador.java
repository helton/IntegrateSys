/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.sensores.SensorTemperatura;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public class Ventilador extends AtuadorDeTemperatura {

    private final int VARIACAO_TEMPERATURA   = 4;
    private final int INCREMENTO_TEMPERATURA = 1;

    public Ventilador(AgendadorTarefas agendadorTarefas, SensorTemperatura sensorTemperatura) {
        super(agendadorTarefas, sensorTemperatura);
    }
       
    @Override
    public int getVariacaoAoLigar() {
        return - VARIACAO_TEMPERATURA;
    }
    
    @Override
    public int getVariacaoAoDesligar() {
        return VARIACAO_TEMPERATURA;        
    }
    
    @Override
    public int getIncremento() {
        return INCREMENTO_TEMPERATURA;        
    }    

}