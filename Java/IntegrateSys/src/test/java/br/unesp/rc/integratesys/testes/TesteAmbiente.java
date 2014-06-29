/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.ambiente.Ambiente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TesteAmbiente {
    
    private Ambiente ambiente;
    
    @Before
    public void setUp() {
        ambiente = new Ambiente();
    }

    @Test
    public void testeCondicoesIniciais() {
        Assert.assertTrue(ambiente.getSensores().getSensorAgua().hasAgua());
        Assert.assertTrue(ambiente.getSensores().getSensorEnergia().hasEnergia());
        Assert.assertFalse(ambiente.getSensores().getSensorIncendio().inIncendio());        
        Assert.assertEquals(50, ambiente.getSensores().getSensorLuminosidade().getLuminosidade());
        Assert.assertEquals(30, ambiente.getSensores().getSensorTemperatura().getTemperatura());
        Assert.assertEquals(60, ambiente.getSensores().getSensorUmidade().getUmidade());
        Assert.assertFalse(ambiente.getAtuadores().getVentilador().isLigado());
        Assert.assertFalse(ambiente.getAtuadores().getUmedecedor().isLigado());
        Assert.assertFalse(ambiente.getAtuadores().getLampada().isLigado());
        Assert.assertFalse(ambiente.getAtuadores().getAquecedor().isLigado());        
        Assert.assertFalse(ambiente.getAtuadores().getAlarme().isLigado());                        
    }
}
