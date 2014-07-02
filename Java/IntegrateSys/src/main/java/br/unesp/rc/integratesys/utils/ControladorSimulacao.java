/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.utils;

import java.util.Date;

/**
 *
 * @author Helton
 */
public class ControladorSimulacao {
    private long tempoDecorridoUltimaSimulacao;
    private long tempoInicioSimulacaoAtual;
    private final AgendadorTarefas agendadorTarefas;    
    private final SimuladorCondicoesMeteorologicas simulador;
    private final Varredor varredor;
    private boolean emSimulacao;

    public ControladorSimulacao(Tarefa tarefaExecutadoPorCiclo) {
        agendadorTarefas = new AgendadorTarefas();
        simulador = new SimuladorCondicoesMeteorologicas(agendadorTarefas);        
        varredor = new Varredor(agendadorTarefas, tarefaExecutadoPorCiclo);
        tempoDecorridoUltimaSimulacao = 0;
        tempoInicioSimulacaoAtual = 0;
        emSimulacao = false;
    }
    
    public void iniciarSimulacao() {
        emSimulacao = true;
        tempoInicioSimulacaoAtual = System.nanoTime();
        getVarredor().iniciar();
    }
    
    public void pausarSimulacao() {
        emSimulacao = false;
        tempoDecorridoUltimaSimulacao = getTempoDecorridoSimulacaoAtual();
        getVarredor().pausar();
    }
    
    public long getTempoDecorridoSimulacaoAtual() {
        return (((System.nanoTime()) - tempoInicioSimulacaoAtual)/1000000);
    }
    
    public long getTempoTotalSimulacaoDecorrido() {
        return  tempoDecorridoUltimaSimulacao + getTempoDecorridoSimulacaoAtual();
    }
    
    /**
     * @return the agendadorTarefas
     */
    public AgendadorTarefas getAgendadorTarefas() {
        return agendadorTarefas;
    }

    /**
     * @return the simulador
     */
    public SimuladorCondicoesMeteorologicas getSimuladorCondicoesMeteorologicas() {
        return simulador;
    }    

    /**
     * @return the varredor
     */
    public Varredor getVarredor() {
        return varredor;
    }

    /**
     * @return the emSimulacao
     */
    public boolean isEmSimulacao() {
        return emSimulacao;
    }
    
}