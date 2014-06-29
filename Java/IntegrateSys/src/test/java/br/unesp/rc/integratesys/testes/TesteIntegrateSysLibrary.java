/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.library.IntegrateSysLibrary;
import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TesteIntegrateSysLibrary {   
    
    @Test
    public void testeLoadLibrary() {
        IntegrateSysLibrary library = IntegrateSysLibraryLoader.getLibrary();        
        Assert.assertNotNull(library);
    }   

    @Test
    public void testeGetAndSetTemperatura() {
        IntegrateSysLibraryLoader.getLibrary().setTemperatura(99);
        Assert.assertEquals(99, IntegrateSysLibraryLoader.getLibrary().getTemperatura());

        IntegrateSysLibraryLoader.getLibrary().setTemperatura(100);
        Assert.assertEquals(100, IntegrateSysLibraryLoader.getLibrary().getTemperatura());        
    }
    
    @Test
    public void testeGetAndSetUmidade() {
        IntegrateSysLibraryLoader.getLibrary().setUmidade(99);
        Assert.assertEquals(99, IntegrateSysLibraryLoader.getLibrary().getUmidade());

        IntegrateSysLibraryLoader.getLibrary().setUmidade(100);
        Assert.assertEquals(100, IntegrateSysLibraryLoader.getLibrary().getUmidade());
    }    
        
    @Test
    public void testeGetAndSetLuminosidade() {
        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(99);
        Assert.assertEquals(99, IntegrateSysLibraryLoader.getLibrary().getLuminosidade());

        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(100);
        Assert.assertEquals(100, IntegrateSysLibraryLoader.getLibrary().getLuminosidade());
    }  
    
    @Test
    public void testeHasAndSetAgua() {
        IntegrateSysLibraryLoader.getLibrary().setAgua(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().hasAgua());

        IntegrateSysLibraryLoader.getLibrary().setAgua(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().hasAgua());
    }     
    
    @Test
    public void testeHasAndSetEnergia() {
        IntegrateSysLibraryLoader.getLibrary().setEnergia(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().hasEnergia());

        IntegrateSysLibraryLoader.getLibrary().setEnergia(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().hasEnergia());
    }  
    
    @Test
    public void testeStatusAndSetAlarme() {
        IntegrateSysLibraryLoader.getLibrary().setAlarme(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusAlarme());

        IntegrateSysLibraryLoader.getLibrary().setAlarme(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusAlarme());
    }     
    
    @Test
    public void testeStatusAndSetAquecedores() {
        IntegrateSysLibraryLoader.getLibrary().setAquecedor(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusAquecedor());

        IntegrateSysLibraryLoader.getLibrary().setAquecedor(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusAquecedor());
    }     
    
    @Test
    public void testeStatusAndSetLampadas() {
        IntegrateSysLibraryLoader.getLibrary().setLampada(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusLampada());

        IntegrateSysLibraryLoader.getLibrary().setLampada(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusLampada());
    }  
    
    @Test
    public void testeStatusAndSetUmedecedores() {
        IntegrateSysLibraryLoader.getLibrary().setUmedecedor(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusUmedecedor());

        IntegrateSysLibraryLoader.getLibrary().setUmedecedor(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusUmedecedor());
    }    
    
    @Test
    public void testStatusAndSetVentiladores() {
        IntegrateSysLibraryLoader.getLibrary().setVentilador(false);
        Assert.assertFalse(IntegrateSysLibraryLoader.getLibrary().statusVentilador());

        IntegrateSysLibraryLoader.getLibrary().setVentilador(true);
        Assert.assertTrue(IntegrateSysLibraryLoader.getLibrary().statusVentilador());
    }      
    
}
