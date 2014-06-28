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
    public void testValoresPadrao() {
        Assert.assertEquals(0, ambiente.getTemperatura());
        Assert.assertEquals(0, ambiente.getUmidade());
        Assert.assertEquals(0, ambiente.getLuminosidade());
        Assert.assertFalse(ambiente.hasAgua());
        Assert.assertFalse(ambiente.hasEnergia());
        Assert.assertFalse(ambiente.inIncendio());
        Assert.assertFalse(ambiente.getVentilador().isLigado());
        Assert.assertFalse(ambiente.getUmedecedor().isLigado());
        Assert.assertFalse(ambiente.getLampada().isLigado());
        Assert.assertFalse(ambiente.getAquecedor().isLigado());        
        Assert.assertFalse(ambiente.getAlarme().isLigado());                
        Assert.assertEquals(ambiente.getControle(), Ambiente.Controle.AUTOMATICO);                        
    }
}
