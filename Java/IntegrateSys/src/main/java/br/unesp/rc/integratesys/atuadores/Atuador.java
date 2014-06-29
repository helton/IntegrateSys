/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.utils.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public abstract class Atuador {
    
    private boolean ligado;  
    private final AgendadorTarefas agendadorTarefas;
    
    public Atuador(AgendadorTarefas agendadorTarefas) {
        this.agendadorTarefas = agendadorTarefas;
    }
    
    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }
    
    public boolean isLigado() {
        return ligado;
    }     

    /**
     * @return the agendadorTarefas
     */
    public AgendadorTarefas getAgendadorTarefas() {
        return agendadorTarefas;
    }
    
}
