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
public class Aquecedor extends Atuador {

    private final SensorTemperatura sensorTemperatura;
    
    public Aquecedor(AgendadorTarefas agendadorTarefas, SensorTemperatura sensorTemperatura) {
        super(agendadorTarefas);
        this.sensorTemperatura = sensorTemperatura;
    }

}
