/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.utils;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Helton
 */
public class AgendadorTarefas {
   
    private Queue<Tarefa> tarefas;
    
    public AgendadorTarefas() {
        tarefas = new PriorityQueue<>();
    }
    
    public void agendarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }
    
    public void processar() {
        
    }
}
