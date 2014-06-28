/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.tests;

import br.unesp.rc.integratesys.library.IntegrateSysLibrary;
import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TestIntegrateSysLibrary {   
    
    @Test
    public void testLoadLibrary() {
        IntegrateSysLibrary library = IntegrateSysLibraryLoader.getLibrary();
        Assert.assertNotNull(library);
    }   

    @Test
    public void testGetAndSetTemperatura() {
        IntegrateSysLibraryLoader.getLibrary().setTemperatura(99);
        Assert.assertEquals(99, IntegrateSysLibraryLoader.getLibrary().getTemperatura());

        IntegrateSysLibraryLoader.getLibrary().setTemperatura(100);
        Assert.assertEquals(100, IntegrateSysLibraryLoader.getLibrary().getTemperatura());        
    }
    
    @Test
    public void testGetAndSetUmidade() {
        IntegrateSysLibraryLoader.getLibrary().setUmidade(99);
        Assert.assertEquals(99, IntegrateSysLibraryLoader.getLibrary().getUmidade());

        IntegrateSysLibraryLoader.getLibrary().setUmidade(100);
        Assert.assertEquals(100, IntegrateSysLibraryLoader.getLibrary().getUmidade());
    }    
        
    @Test
    public void testHasAndSetLuminosidade() {
        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().hasLuminosidade());

        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().hasLuminosidade());
    }  
    
    @Test
    public void testHasAndSetAgua() {
        IntegrateSysLibraryLoader.getLibrary().setAgua(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().hasAgua());

        IntegrateSysLibraryLoader.getLibrary().setAgua(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().hasAgua());
    }     
    
    @Test
    public void testHasAndSetEnergia() {
        IntegrateSysLibraryLoader.getLibrary().setEnergia(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().hasEnergia());

        IntegrateSysLibraryLoader.getLibrary().setEnergia(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().hasEnergia());
    }  
    
    @Test
    public void testStatusAndSetAlarme() {
        IntegrateSysLibraryLoader.getLibrary().setAlarme(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusAlarme());

        IntegrateSysLibraryLoader.getLibrary().setAlarme(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusAlarme());
    }     
    
    @Test
    public void testStatusAndSetAquecedores() {
        IntegrateSysLibraryLoader.getLibrary().setAquecedores(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusAquecedores());

        IntegrateSysLibraryLoader.getLibrary().setAquecedores(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusAquecedores());
    }     
    
    @Test
    public void testStatusAndSetLampadas() {
        IntegrateSysLibraryLoader.getLibrary().setLampadas(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusLampadas());

        IntegrateSysLibraryLoader.getLibrary().setLampadas(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusLampadas());
    }  
    
    @Test
    public void testStatusAndSetUmedecedores() {
        IntegrateSysLibraryLoader.getLibrary().setUmedecedores(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusUmedecedores());

        IntegrateSysLibraryLoader.getLibrary().setUmedecedores(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusUmedecedores());
    }    
    
    @Test
    public void testStatusAndSetVentiladores() {
        IntegrateSysLibraryLoader.getLibrary().setVentiladores(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusVentiladores());

        IntegrateSysLibraryLoader.getLibrary().setVentiladores(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusVentiladores());
    }      
    
}
