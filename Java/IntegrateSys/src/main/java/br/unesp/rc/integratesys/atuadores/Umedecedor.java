/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.sensores.SensorUmidade;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public class Umedecedor extends Atuador {

    private final SensorUmidade sensorUmidade;
    
    public Umedecedor(AgendadorTarefas agendadorTarefas, SensorUmidade sensorUmidade) {
        super(agendadorTarefas);
        this.sensorUmidade = sensorUmidade;
    }
    
}
