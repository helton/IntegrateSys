/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.utils.AgendadorTarefas;
import br.unesp.rc.integratesys.utils.DistribuidorValores;
import br.unesp.rc.integratesys.utils.Tarefa;
import java.util.List;

/**
 *
 * @author Helton
 */
public abstract class Atuador {
    
    private final AgendadorTarefas agendadorTarefas;
    private Nivel nivel;  
    
    abstract int getVariacaoPorNivel();
    abstract int getIncrementoPorCiclo();
    abstract int getValorSensor();
    abstract void setValor(int valor);    
    
    public Atuador(AgendadorTarefas agendadorTarefas) {
        this.agendadorTarefas = agendadorTarefas;
        this.nivel = Nivel.DESLIGADO;
    }
    
    public void setNivel(Nivel nivel) {
        if (this.nivel != nivel) {
            Nivel nivelAntigo = this.nivel;
            Nivel nivelNovo = nivel;
            this.nivel = nivel;
            int variacao = (nivelNovo.getValor() - nivelAntigo.getValor()) * getVariacaoPorNivel();
            alterarEstado(variacao);
        }
    }  
    
    public Nivel getNivel() {
        return nivel;
    }     

    /**
     * @return the agendadorTarefas
     */
    public AgendadorTarefas getAgendadorTarefas() {
        return agendadorTarefas;
    }
    
    private void agendarAlteracao(final int ciclo, final int incremento) {
        getAgendadorTarefas().agendarTarefa(new Tarefa() {
            @Override
            public void executar() {
                setValor(getValorSensor() + incremento);
            }
        }, ciclo);
    }    
    
    private void alterarEstado(int variacao) {
        List<Integer> incrementos = DistribuidorValores.distribuir(variacao, getIncrementoPorCiclo());
        for (int ciclo = 0; ciclo < incrementos.size(); ciclo++) {
            agendarAlteracao(ciclo, incrementos.get(ciclo));            
        }
    }    
    
}
