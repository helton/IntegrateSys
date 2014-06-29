/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.utils.DistribuidorValores;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TesteDistribuidorValores {
       
    @Test
    public void testeDistribuicaoVariacaoMenorQueIncremento_ValoresPositivos() {
        List<Integer> valores = DistribuidorValores.distribuir(3, 4);
        Assert.assertEquals(1, valores.size());
        Assert.assertEquals(3, valores.get(0).intValue());
    }
    
       
    @Test
    public void testeDistribuicaoVariacaoMaiorQueIncremento_ValoresNegativos() {
        List<Integer> valores = DistribuidorValores.distribuir(-3, 4);
        Assert.assertEquals(1, valores.size());
        Assert.assertEquals(-3, valores.get(0).intValue());
    }    
    
    @Test
    public void testeDistribuicaoVariacaoMaiorQueIncremento_VariacaoMultiplaDoIncremento_ValoresPositivos() {
        List<Integer> valores = DistribuidorValores.distribuir(12, 4);
        Assert.assertEquals(3, valores.size());
        Assert.assertEquals(4, valores.get(0).intValue());
        Assert.assertEquals(4, valores.get(1).intValue());
        Assert.assertEquals(4, valores.get(2).intValue());        
    }    
    
    @Test
    public void testeDistribuicaoVariacaoMenorQueIncremento_VariacaoMultiplaDoIncremento_ValoresNegativos() {
        List<Integer> valores = DistribuidorValores.distribuir(-12, 4);
        Assert.assertEquals(3, valores.size());
        Assert.assertEquals(-4, valores.get(0).intValue());
        Assert.assertEquals(-4, valores.get(1).intValue());
        Assert.assertEquals(-4, valores.get(2).intValue());        
    }        
    
    @Test
    public void testeDistribuicaoVariacaoMaiorQueIncremento_VariacaoNaoMultiplaDoIncremento_ValoresPositivos() {
        List<Integer> valores = DistribuidorValores.distribuir(11, 4);
        Assert.assertEquals(3, valores.size());
        Assert.assertEquals(4, valores.get(0).intValue());
        Assert.assertEquals(4, valores.get(1).intValue());
        Assert.assertEquals(3, valores.get(2).intValue());        
    }

    @Test
    public void testeDistribuicaoVariacaoMenorQueIncremento_VariacaoNaoMultiplaDoIncremento_ValoresNegativos() {
        List<Integer> valores = DistribuidorValores.distribuir(-11, 4);
        Assert.assertEquals(3, valores.size());
        Assert.assertEquals(-4, valores.get(0).intValue());
        Assert.assertEquals(-4, valores.get(1).intValue());
        Assert.assertEquals(-3, valores.get(2).intValue());        
    }    
    
}
