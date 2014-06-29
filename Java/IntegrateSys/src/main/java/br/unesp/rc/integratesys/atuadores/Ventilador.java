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

    private final int MAXIMO_INCREMENTO_TEMPERATURA_POR_CICLO = 2;
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
//                int diferenca = 30/* substituir por parametros.getTemperaturaIdeal()*/ - sensorTemperatura.getTemperatura();
//                int quantidadeCiclos = Math.round(diferenca / MAXIMO_INCREMENTO_TEMPERATURA_POR_CICLO);
//                for (int ciclo = 1; ciclo <= quantidadeCiclos; ciclo++) {
//                    getAgendadorTarefas().agendarTarefa(new Tarefa() {
//                        @Override
//                        public void executar() {
//                            IntegrateSysLibraryLoader.getLibrary().setTemperatura(sensorTemperatura.getTemperatura() + MAXIMO_INCREMENTO_TEMPERATURA_POR_CICLO);
//                        }
//                    });
//                }
            }
        }
    }
}