/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.ambiente.Ambiente;
import br.unesp.rc.integratesys.atuadores.Nivel;
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
        Assert.assertEquals(50, ambiente.getSensores().getSensorLuminosidade().getLuminosidade());
        Assert.assertEquals(30, ambiente.getSensores().getSensorTemperatura().getTemperatura());
        Assert.assertEquals(60, ambiente.getSensores().getSensorUmidade().getUmidade());
        Assert.assertEquals(Nivel.DESLIGADO, ambiente.getAtuadores().getVentilador().getNivel());
        Assert.assertEquals(Nivel.DESLIGADO, ambiente.getAtuadores().getUmidificador().getNivel());
        Assert.assertEquals(Nivel.DESLIGADO, ambiente.getAtuadores().getLampada().getNivel());
        Assert.assertEquals(Nivel.DESLIGADO, ambiente.getAtuadores().getAquecedor().getNivel());                             
    }
}
