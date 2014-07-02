/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Helton
 */
public class Varredor {
    
    public final int INTERVALO_POR_CICLO = 3;
    private final Runnable tarefaPeriodica;
    private ScheduledExecutorService executorTarefa;
    
    public Varredor(final AgendadorTarefas agendadorTarefas, final Tarefa callback) {
        tarefaPeriodica = new Runnable() {
            @Override
            public void run() {
                agendadorTarefas.executarProximoCiclo();
                if (callback != null) {
                    callback.executar();
                }
            }
        };        
    }
    
    public void iniciar() {
        if (executorTarefa != null) {
            executorTarefa.shutdown();
        }
        executorTarefa = Executors.newSingleThreadScheduledExecutor();
        executorTarefa.scheduleAtFixedRate(tarefaPeriodica, 0, INTERVALO_POR_CICLO, TimeUnit.SECONDS);        
    }
    
    public void pausar() {
        executorTarefa.shutdown();
        executorTarefa = null;
    }
    
}
