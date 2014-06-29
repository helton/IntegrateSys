/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.sensores.SensorAgua;
import br.unesp.rc.integratesys.sensores.SensorEnergia;
import br.unesp.rc.integratesys.sensores.SensorIncendio;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public class Alarme extends Atuador {

    private final SensorAgua sensorAgua;
    private final SensorEnergia sensorEnergia;
    private final SensorIncendio sensorIncendio;
    
    public Alarme(AgendadorTarefas agendadorTarefas, SensorAgua sensorAgua,
            SensorEnergia sensorEnergia, SensorIncendio sensorIncendio) {
        super(agendadorTarefas);
        this.sensorAgua = sensorAgua;
        this.sensorEnergia = sensorEnergia;
        this.sensorIncendio = sensorIncendio;
    }
    
}
