/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.Sensores;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TesteSensores {

    private Sensores sensores;

    @Before
    public void setUp() {
        sensores = new Sensores();
    }        

    @Test
    public void testeSensorLuminosidade() {
        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(55);
        Assert.assertEquals(55, sensores.getSensorLuminosidade().getLuminosidade());

        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(12);
        Assert.assertEquals(12, sensores.getSensorLuminosidade().getLuminosidade());
    }    
    
    @Test
    public void testeSensorTemperatura() {
        IntegrateSysLibraryLoader.getLibrary().setTemperatura(28);
        Assert.assertEquals(28, sensores.getSensorTemperatura().getTemperatura());

        IntegrateSysLibraryLoader.getLibrary().setTemperatura(33);
        Assert.assertEquals(33, sensores.getSensorTemperatura().getTemperatura());
    }
    
    @Test
    public void testeSensorUmidade() {
        IntegrateSysLibraryLoader.getLibrary().setUmidade(70);
        Assert.assertEquals(70, sensores.getSensorUmidade().getUmidade());

        IntegrateSysLibraryLoader.getLibrary().setUmidade(40);
        Assert.assertEquals(40, sensores.getSensorUmidade().getUmidade());
    }    
    
    
}
