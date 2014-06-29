/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.atuadores.Aquecedor;
import br.unesp.rc.integratesys.atuadores.Atuadores;
import br.unesp.rc.integratesys.atuadores.Lampada;
import br.unesp.rc.integratesys.atuadores.Umidificador;
import br.unesp.rc.integratesys.atuadores.Ventilador;
import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.Sensores;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TesteAtuadores {

    private Sensores sensores;
    private Atuadores atuadores;
    private AgendadorTarefas agendadorTarefas;
    
    @Before
    public void setUp() {
        agendadorTarefas = new AgendadorTarefas();
        sensores = new Sensores();
        atuadores = new Atuadores(sensores, agendadorTarefas);
        IntegrateSysLibraryLoader.getLibrary().setTemperatura(30);
        IntegrateSysLibraryLoader.getLibrary().setUmidade(60);
        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(50);        
    }

    @Test
    public void testeVentilador() {
        Ventilador ventilador = atuadores.getVentilador();
        
        ventilador.setLigado(true);        
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(29, sensores.getSensorTemperatura().getTemperatura());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(28, sensores.getSensorTemperatura().getTemperatura());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(27, sensores.getSensorTemperatura().getTemperatura());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(26, sensores.getSensorTemperatura().getTemperatura());

        ventilador.setLigado(false);
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(27, sensores.getSensorTemperatura().getTemperatura());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(28, sensores.getSensorTemperatura().getTemperatura());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(29, sensores.getSensorTemperatura().getTemperatura());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(30, sensores.getSensorTemperatura().getTemperatura());                
    }
    
    @Test
    public void testeAquecedor() {
        Aquecedor aquecedor = atuadores.getAquecedor();
        
        aquecedor.setLigado(true);
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(32, sensores.getSensorTemperatura().getTemperatura());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(34, sensores.getSensorTemperatura().getTemperatura());        
        
        aquecedor.setLigado(false);
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(32, sensores.getSensorTemperatura().getTemperatura());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(30, sensores.getSensorTemperatura().getTemperatura());        
    }
    
    @Test
    public void testeUmidificador() {
        Umidificador umidificador = atuadores.getUmidificador();
        
        umidificador.setLigado(true);
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(70, sensores.getSensorUmidade().getUmidade());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(80, sensores.getSensorUmidade().getUmidade());        
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(90, sensores.getSensorUmidade().getUmidade());        
        
        umidificador.setLigado(false);
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(80, sensores.getSensorUmidade().getUmidade());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(70, sensores.getSensorUmidade().getUmidade());        
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(60, sensores.getSensorUmidade().getUmidade());        
    }    
    
    @Test
    public void testeLampada() {
        Lampada lampada = atuadores.getLampada();
        
        lampada.setLigado(true);
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(60, sensores.getSensorLuminosidade().getLuminosidade());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(70, sensores.getSensorLuminosidade().getLuminosidade());        
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(80, sensores.getSensorLuminosidade().getLuminosidade());        
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(85, sensores.getSensorLuminosidade().getLuminosidade());        
        
        lampada.setLigado(false);
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(75, sensores.getSensorLuminosidade().getLuminosidade());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(65, sensores.getSensorLuminosidade().getLuminosidade());        
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(55, sensores.getSensorLuminosidade().getLuminosidade());
        agendadorTarefas.executarProximoCiclo();
        Assert.assertEquals(50, sensores.getSensorLuminosidade().getLuminosidade());
    }     
    
}
