/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.sensores.SensorLuminosidade;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public class Lampada extends Atuador {

    private final SensorLuminosidade sensorLuminosidade;
    
    public Lampada(AgendadorTarefas agendadorTarefas, SensorLuminosidade sensorLuminosidade) {
        super(agendadorTarefas);
        this.sensorLuminosidade = sensorLuminosidade;
    }
    
}
