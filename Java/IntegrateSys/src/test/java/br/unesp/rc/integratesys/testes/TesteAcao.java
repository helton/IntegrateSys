/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.agentes.Acao;
import br.unesp.rc.integratesys.atuadores.Ventilador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TesteAcao {
    
    private Ventilador ventilador;
    
    @Before
    public void setUp() {
        ventilador = new Ventilador(false);
    }

    @Test
    public void testeExecutarAcao() {
        Assert.assertFalse(ventilador.isLigado());
        Acao acao = new Acao(Acao.TipoAcao.LIGAR, ventilador);
        acao.executar();
        Assert.assertTrue(ventilador.isLigado());
    }
    
}
