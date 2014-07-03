/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.simulacao.AgendadorTarefas;
import br.unesp.rc.integratesys.simulacao.Agendavel;

/**
 *
 * @author Helton
 */
public abstract class Atuador implements Agendavel {
    
    private final AgendadorTarefas agendadorTarefas;
    private Nivel nivel;  
    
    public Atuador(AgendadorTarefas agendadorTarefas) {
        this.nivel = Nivel.DESLIGADO;
        this.agendadorTarefas = agendadorTarefas;
    }
    
    public void setNivel(Nivel nivel) {
        if (this.nivel != nivel) {
            Nivel nivelAntigo = this.nivel;
            Nivel nivelNovo = nivel;
            this.nivel = nivel;
            int variacao = (nivelNovo.getValor() - nivelAntigo.getValor()) * getVariacaoPorNivel();
            agendadorTarefas.alterarEstado(this, variacao);
        }
    }  
    
    public Nivel getNivel() {
        return nivel;
    }     

}
