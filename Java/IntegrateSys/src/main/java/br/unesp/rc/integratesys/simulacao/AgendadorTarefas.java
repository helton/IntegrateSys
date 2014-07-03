/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.simulacao;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Helton
 */
public class AgendadorTarefas {
   
    private int indiceProximoCiclo;
    private final Map<Integer, Ciclo> ciclos;
    
    public AgendadorTarefas() {
    ciclos = new HashMap<>();
        indiceProximoCiclo = 0;
    }
    
    public void agendarTarefa(Tarefa tarefa, int numeroCiclosEspera) {
        int indice = getIndiceProximoCiclo() + numeroCiclosEspera;
        Ciclo ciclo = ciclos.get(indice);
        if (ciclo == null) {
            ciclo = new Ciclo();
            ciclos.put(indice, ciclo);
        }
        ciclo.agendarTarefa(tarefa);
    }
    
    public void executarProximoCiclo() {
        Ciclo ciclo = ciclos.get(indiceProximoCiclo);
        if (ciclo != null) {
            ciclo.processarTarefas();
            ciclos.remove(indiceProximoCiclo);
        }
        indiceProximoCiclo++;
    }

    /**
     * @return the indiceProximoCiclo
     */
    public int getIndiceProximoCiclo() {
        return indiceProximoCiclo;
    }

    private void agendarAlteracao(final Agendavel agendavel, final int ciclo, final int incremento) {
        agendarTarefa(new Tarefa() {
            @Override
            public void executar() {
                agendavel.setValor(agendavel.getValor() + incremento);
            }
        }, ciclo);
    }    
    
    public void alterarEstado(Agendavel agendavel, int variacao) {
        List<Integer> incrementos = DistribuidorValores.distribuir(variacao, agendavel.getIncrementoPorCiclo());
        for (int ciclo = 0; ciclo < incrementos.size(); ciclo++) {
            agendarAlteracao(agendavel, ciclo, incrementos.get(ciclo));            
        }
    }       
    
}

class Ciclo {

    private final Queue<Tarefa> tarefas;
    
    public Ciclo() {
        tarefas = new ArrayDeque<>();
    }
    
    public void agendarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }
    
    public void processarTarefas() {
        while (!tarefas.isEmpty()) {
            tarefas.poll().executar();
        }
    }
    
}
