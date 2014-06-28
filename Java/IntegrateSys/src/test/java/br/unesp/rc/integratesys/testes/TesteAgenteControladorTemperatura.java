/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.testes;

import br.unesp.rc.integratesys.agentes.Acao;
import br.unesp.rc.integratesys.agentes.AgenteControladorTemperatura;
import br.unesp.rc.integratesys.ambiente.Parametros;
import br.unesp.rc.integratesys.atuadores.Aquecedor;
import br.unesp.rc.integratesys.atuadores.Ventilador;
import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Helton
 */
public class TesteAgenteControladorTemperatura {

    private Parametros parametros;
    private AgenteControladorTemperatura agenteControladorTemperatura;
    
    private final int TEMPERATURA_IDEAL = 30;

    @Before
    public void setUp() {
        parametros = new Parametros();
        parametros.setTemperaturaIdeal(TEMPERATURA_IDEAL);
        agenteControladorTemperatura = new AgenteControladorTemperatura(parametros, new Aquecedor(false), new Ventilador(false));
    }

    @Test
    public void testAcaoTemperaturaMaiorQueIdeal() {
        IntegrateSysLibraryLoader.getLibrary().setTemperatura(25);
        
        List<Acao> acoes = agenteControladorTemperatura.agir();
        Assert.assertEquals(2, acoes.size());
        Assert.assertEquals(acoes.get(0), new Acao(Acao.TipoAcao.LIGAR,    agenteControladorTemperatura.getAquecedor()));
        Assert.assertEquals(acoes.get(1), new Acao(Acao.TipoAcao.DESLIGAR, agenteControladorTemperatura.getVentilador()));        
    }
    
    @Test
    public void testAcaoTemperaturaMenorQueIdeal() {
        IntegrateSysLibraryLoader.getLibrary().setTemperatura(32);
        
        List<Acao> acoes = agenteControladorTemperatura.agir();
        Assert.assertEquals(2, acoes.size());
        Assert.assertEquals(acoes.get(0), new Acao(Acao.TipoAcao.DESLIGAR, agenteControladorTemperatura.getAquecedor()));
        Assert.assertEquals(acoes.get(1), new Acao(Acao.TipoAcao.LIGAR,    agenteControladorTemperatura.getVentilador()));        
    }
    
    @Test
    public void testAcaoTemperaturaIdeal() {
        IntegrateSysLibraryLoader.getLibrary().setTemperatura(TEMPERATURA_IDEAL);
        
        List<Acao> acoes = agenteControladorTemperatura.agir();
        Assert.assertEquals(0, acoes.size());
    }        
    
}
