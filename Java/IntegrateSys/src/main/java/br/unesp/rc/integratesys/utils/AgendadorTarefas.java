/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.utils;

import java.util.ArrayDeque;
import java.util.HashMap;
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
        int indice = indiceProximoCiclo + numeroCiclosEspera;
        Ciclo ciclo = ciclos.get(indice);
        if (ciclo == null) {
            ciclo = new Ciclo();
            ciclos.put(indice, ciclo);
        }
        ciclo.agendarTarefa(tarefa);
    }
    
    public void executarProximoCiclo() {
        if (!ciclos.isEmpty()) {
            ciclos.remove(indiceProximoCiclo++).processarTarefas();
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
