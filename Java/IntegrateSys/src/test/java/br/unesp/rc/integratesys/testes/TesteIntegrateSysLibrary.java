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
    public void testGetAndSetNivelAquecedor() {
        IntegrateSysLibraryLoader.getLibrary().setNivelAquecedor(0);
        Assert.assertEquals(0, IntegrateSysLibraryLoader.getLibrary().getNivelAquecedor());

        IntegrateSysLibraryLoader.getLibrary().setNivelAquecedor(1);
        Assert.assertEquals(1, IntegrateSysLibraryLoader.getLibrary().getNivelAquecedor());
    }     
    
    @Test
    public void testGetAndSetNivelLampada() {
        IntegrateSysLibraryLoader.getLibrary().setNivelLampada(0);
        Assert.assertEquals(0, IntegrateSysLibraryLoader.getLibrary().getNivelLampada());

        IntegrateSysLibraryLoader.getLibrary().setNivelLampada(1);
        Assert.assertEquals(1, IntegrateSysLibraryLoader.getLibrary().getNivelLampada());
    }  
    
    @Test
    public void testGetAndSetNivelUmidificador() {
        IntegrateSysLibraryLoader.getLibrary().setNivelUmidificador(0);
        Assert.assertEquals(0, IntegrateSysLibraryLoader.getLibrary().getNivelUmidificador());

        IntegrateSysLibraryLoader.getLibrary().setNivelUmidificador(1);
        Assert.assertEquals(1, IntegrateSysLibraryLoader.getLibrary().getNivelUmidificador());
    }    
    
    @Test
    public void testGetAndSetNivelVentilador() {
        IntegrateSysLibraryLoader.getLibrary().setNivelVentilador(0);
        Assert.assertEquals(0, IntegrateSysLibraryLoader.getLibrary().getNivelVentilador());

        IntegrateSysLibraryLoader.getLibrary().setNivelVentilador(1);
        Assert.assertEquals(1, IntegrateSysLibraryLoader.getLibrary().getNivelVentilador());
    }      
    
}
