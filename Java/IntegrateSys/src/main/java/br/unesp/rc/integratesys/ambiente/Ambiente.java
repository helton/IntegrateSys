/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.ambiente;

import br.unesp.rc.integratesys.atuadores.Atuadores;
import br.unesp.rc.integratesys.atuadores.Nivel;
import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.Sensores;
import br.unesp.rc.integratesys.simulacao.AgendadorTarefas;
import br.unesp.rc.integratesys.simulacao.EstadoAmbiente;
import br.unesp.rc.integratesys.simulacao.SimuladorCondicoesMeteorologicas;

/**
 * Classe que gera o ambiente de simulação da granja, fazendo as configurações 
 * iniciais, inicializando parâmetros, tarefas, atuadores e sensores e iniciando
 * a simulação das condições metereológicas
 * 
 * @author Raquel
 */
public class Ambiente {

    private final Parametros parametros;
    private final AgendadorTarefas agendadorTarefas;
    private final Sensores sensores;
    private final Atuadores atuadores;
    private final SimuladorCondicoesMeteorologicas simulador;
    private long tempoDecorridoUltimaSimulacao;
    private long tempoInicioSimulacaoAtual;

    public Ambiente() {
        parametros = new Parametros();
        agendadorTarefas = new AgendadorTarefas();
        sensores = new Sensores();
        atuadores = new Atuadores(sensores, agendadorTarefas);
        simulador = new SimuladorCondicoesMeteorologicas(sensores, agendadorTarefas, parametros);
        tempoInicioSimulacaoAtual = 0;
        tempoDecorridoUltimaSimulacao = 0;
        configurarCondicoesIniciais();
    }
    
    /**
     * Configura as condições iniciais da granja, utilizando os valores iniciais passados pelos sensores
     */
    private void configurarCondicoesIniciais() {
        //apenas os valores iniciais dos sensores são disponibilizados
        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(simulador.getPrevisaoTempo().getLuminosidade().toInteger());
        IntegrateSysLibraryLoader.getLibrary().setTemperatura(simulador.getPrevisaoTempo().getTemperatura().toInteger());
        IntegrateSysLibraryLoader.getLibrary().setUmidade(simulador.getPrevisaoTempo().getUmidade().toInteger());
        //todos os atuadores ficam desligados por padrão
        IntegrateSysLibraryLoader.getLibrary().setNivelAquecedor(Nivel.DESLIGADO.getValor());
        IntegrateSysLibraryLoader.getLibrary().setNivelLampada(Nivel.DESLIGADO.getValor());
        IntegrateSysLibraryLoader.getLibrary().setNivelUmidificador(Nivel.DESLIGADO.getValor());
        IntegrateSysLibraryLoader.getLibrary().setNivelVentilador(Nivel.DESLIGADO.getValor());
    }
    
    /**
     * Retorna o estado do ambiente atual da granja, incluindo os valores de luminosidade, temperatura e umidade
     * 
     * @return ambienteInterno Retorna o objeto com as informações do estado do ambiente
     */
    public EstadoAmbiente getAmbienteInterno() {
        EstadoAmbiente ambienteInterno = new EstadoAmbiente();
        ambienteInterno.setLuminosidade(new Luminosidade(IntegrateSysLibraryLoader.getLibrary().getLuminosidade()));
        ambienteInterno.setTemperatura(new Temperatura(IntegrateSysLibraryLoader.getLibrary().getTemperatura()));
        ambienteInterno.setUmidade(new Umidade(IntegrateSysLibraryLoader.getLibrary().getUmidade()));
        return ambienteInterno;
    }
    
    /**
     * Atualiza o ambiente interno de acordo com os parâmetros atuais do ciclo, gerando um novo ciclo
     */
    public void atualizarAmbienteInterno() {
        agendadorTarefas.executarProximoCiclo();
    }
    
    /**
     * Atualiza o ambiente externo de acordo com os parâmetros atuais do ciclo, gerando um novo ciclo
     */
    public void atualizarAmbienteExterno() {
        simulador.atualizarAmbienteExterno();
    }

    /**
     * Método get para obter os atuadores da granja
     * 
     * @return the atuadores
     */
    public Atuadores getAtuadores() {
        return atuadores;
    }

    /**
     * Método get para obter os sensores da granja
     * 
     * @return the sensores
     */
    public Sensores getSensores() {
        return sensores;
    }

    /**
     * Método get para obter os parametros da granja
     * 
     * @return the parametros
     */
    public Parametros getParametros() {
        return parametros;
    }

    /**
     * Método get para obter o agendador de tarefas atual da granja
     * 
     * @return the agendadorTarefas
     */
    public AgendadorTarefas getAgendadorTarefas() {
        return agendadorTarefas;
    }

    /**
     * Método get para obter o simulador da granja
     * 
     * @return the simulador
     */
    public SimuladorCondicoesMeteorologicas getSimuladorCondicoesMeteorologicas() {
        return simulador;
    }

    public void iniciarSimulacao() {
        tempoInicioSimulacaoAtual = System.nanoTime();
    }

    public void pausarSimulacao() {
        tempoDecorridoUltimaSimulacao = getTempoDecorridoSimulacaoAtual();
    }

    public long getTempoDecorridoSimulacaoAtual() {
        return (((System.nanoTime()) - tempoInicioSimulacaoAtual) / 1000000);
    }

    public long getTempoTotalSimulacaoDecorrido() {
        return tempoDecorridoUltimaSimulacao + getTempoDecorridoSimulacaoAtual();
    }

}
