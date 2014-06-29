/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.SensorTemperatura;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;
import br.unesp.rc.integratesys.utils.Tarefa;

/**
 *
 * @author Helton
 */
public class Ventilador extends Atuador {

    private final SensorTemperatura sensorTemperatura;
    
    public Ventilador(AgendadorTarefas agendadorTarefas, SensorTemperatura sensorTemperatura) {
        super(agendadorTarefas);
        this.sensorTemperatura = sensorTemperatura;
    }    

    @Override
    public void setLigado(boolean ligado) {
        if (isLigado() != ligado) {
            super.setLigado(ligado);
            if (isLigado()) {
                getAgendadorTarefas().agendarTarefa(new Tarefa() {
                    @Override
                    public void executar() {
                        IntegrateSysLibraryLoader.getLibrary().setTemperatura(sensorTemperatura.getTemperatura() + 1);
                    }
                });
            }
        }
    }
}
