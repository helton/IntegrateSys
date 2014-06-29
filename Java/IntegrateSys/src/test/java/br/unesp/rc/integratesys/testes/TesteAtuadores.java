/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.atuadores.Atuadores;
import br.unesp.rc.integratesys.atuadores.Ventilador;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TesteAtuadores {

    private Atuadores atuadores;
    
    @Before
    public void setUp() {
        //atuadores = new Atuadores();
    }

    @Test
    @Ignore
    public void testeVentilador() {
        Ventilador ventilador = atuadores.getVentilador();
        ventilador.setLigado(true);
        
    }
}
