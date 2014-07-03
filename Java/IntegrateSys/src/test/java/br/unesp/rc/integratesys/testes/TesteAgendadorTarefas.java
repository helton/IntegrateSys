/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.simulacao.AgendadorTarefas;
import br.unesp.rc.integratesys.simulacao.Tarefa;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TesteAgendadorTarefas {

    private AgendadorTarefas agendadorTarefas;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Before
    public void setUp() {
        agendadorTarefas = new AgendadorTarefas();
    }

    private void enfileirarTarefaTeste(final int ciclo, final int tarefa) {
        agendadorTarefas.agendarTarefa(new Tarefa() {
            @Override
            public void executar() {
                System.out.print(String.format("Ciclo %d, Tarefa %d |", ciclo, tarefa));
            }
        }, ciclo - 1);
    }
    
    private void compararResultados() {
        agendadorTarefas.executarProximoCiclo();
        agendadorTarefas.executarProximoCiclo();
        agendadorTarefas.executarProximoCiclo();

        String saidaConsoleEsperada = "Ciclo 1, Tarefa 1 |"
                + "Ciclo 1, Tarefa 2 |"
                + "Ciclo 2, Tarefa 1 |"
                + "Ciclo 2, Tarefa 2 |"
                + "Ciclo 2, Tarefa 3 |"
                + "Ciclo 3, Tarefa 1 |";
        Assert.assertEquals(saidaConsoleEsperada.trim(), outContent.toString().trim());
    }
    
    @Test
    public void testeAgendarTarefaOrdemCiclosCorreta() {
        enfileirarTarefaTeste(1, 1);
        enfileirarTarefaTeste(1, 2);        
        enfileirarTarefaTeste(2, 1);
        enfileirarTarefaTeste(2, 2);
        enfileirarTarefaTeste(2, 3);
        enfileirarTarefaTeste(3, 1);
        compararResultados();
    }
    
    @Test
    public void testeAgendarTarefaOrdemCiclosNaoSequencial1() {
        enfileirarTarefaTeste(3, 1);
        enfileirarTarefaTeste(2, 1);
        enfileirarTarefaTeste(2, 2);
        enfileirarTarefaTeste(2, 3);
        enfileirarTarefaTeste(1, 1);
        enfileirarTarefaTeste(1, 2);        
        compararResultados();
    }
    
    @Test
    public void testeAgendarTarefaOrdemCiclosNaoSequencial2() {
        enfileirarTarefaTeste(2, 1);
        enfileirarTarefaTeste(1, 1);
        enfileirarTarefaTeste(2, 2);
        enfileirarTarefaTeste(1, 2);        
        enfileirarTarefaTeste(2, 3);
        enfileirarTarefaTeste(3, 1);
        compararResultados();
    }    
    
}
