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
public abstract class AtuadorSimples extends Atuador {

    public AtuadorSimples(AgendadorTarefas agendadorTarefas) {
        super(agendadorTarefas);
    }   
    
    abstract int getVariacaoAoLigar();
    abstract int getVariacaoAoDesligar();
    abstract int getIncremento();
    abstract int getValorSensor();
    abstract void setValor(int valor);
    
    private void agendarAlteracao(final int ciclo, final int incremento) {
        getAgendadorTarefas().agendarTarefa(new Tarefa() {
            @Override
            public void executar() {
                setValor(getValorSensor() + incremento);
            }
        }, ciclo);
    }    
    
    private void alterarEstado(int variacao, int incremento) {
        List<Integer> incrementos = DistribuidorValores.distribuir(variacao, incremento);
        for (int ciclo = 0; ciclo < incrementos.size(); ciclo++) {
            agendarAlteracao(ciclo, incrementos.get(ciclo));            
        }
    }
    
    @Override
    public void setLigado(boolean ligado) {
        if (isLigado() != ligado) {
            super.setLigado(ligado);
            int variacao;
            if (isLigado()) {
                variacao = getVariacaoAoLigar();
            }
            else {
                variacao = getVariacaoAoDesligar();
            }
            alterarEstado(variacao, getIncremento());
        }
    }    
    
}
