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
public class ExecutorTarefas {
    
    public final int intervaloExecucao;
    private final Runnable tarefaPeriodica;
    private ScheduledExecutorService executorTarefa;
    
    public ExecutorTarefas(int intervaloExecucao, final Tarefa tarefa) {
        this.intervaloExecucao = intervaloExecucao;
        this.tarefaPeriodica = new Runnable() {
            @Override
            public void run() {
               tarefa.executar();
            }
        };        
    }
    
    public void iniciar() {
        if (executorTarefa != null) {
            executorTarefa.shutdown();
        }
        executorTarefa = Executors.newSingleThreadScheduledExecutor();
        executorTarefa.scheduleAtFixedRate(tarefaPeriodica, 0, intervaloExecucao, TimeUnit.SECONDS);        
    }
    
    public void pausar() {
        executorTarefa.shutdown();
        executorTarefa = null;
    }

}
