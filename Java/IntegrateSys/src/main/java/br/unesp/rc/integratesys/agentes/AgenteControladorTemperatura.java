/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.agentes;

import br.unesp.rc.integratesys.ambiente.Parametros;
import br.unesp.rc.integratesys.atuadores.Aquecedor;
import br.unesp.rc.integratesys.atuadores.Ventilador;
import br.unesp.rc.integratesys.sensores.SensorTemperatura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Helton
 */
public class AgenteControladorTemperatura extends Agente {
    
    //sensores
    private final SensorTemperatura sensorTemperatura;
    //atuadores
    private final Aquecedor aquecedor;   
    private final Ventilador ventilador;
    
    public AgenteControladorTemperatura(Parametros parametros, Aquecedor aquecedor, Ventilador ventilador) {
        super(parametros);
        sensorTemperatura = new SensorTemperatura();
        this.aquecedor = aquecedor;
        this.ventilador = ventilador;
    }
    
    @Override
    public List<Acao> agir() {
        List<Acao> acoes = new ArrayList<>();
        int temperaturaAtual = getSensorTemperatura().getValor();
        int temperaturaIdeal = parametros.getTemperaturaIdeal();
        if (temperaturaAtual != temperaturaIdeal) {
            if (temperaturaAtual < temperaturaIdeal) {            
                acoes.add(new Acao(Acao.TipoAcao.LIGAR, getAquecedor()));
                acoes.add(new Acao(Acao.TipoAcao.DESLIGAR, getVentilador()));                
            }
            else {
                acoes.add(new Acao(Acao.TipoAcao.DESLIGAR, getAquecedor()));
                acoes.add(new Acao(Acao.TipoAcao.LIGAR, getVentilador()));
            }
        }
        return acoes;
    }

    /**
     * @return the sensorTemperatura
     */
    public SensorTemperatura getSensorTemperatura() {
        return sensorTemperatura;
    }

    /**
     * @return the aquecedor
     */
    public Aquecedor getAquecedor() {
        return aquecedor;
    }

    /**
     * @return the ventilador
     */
    public Ventilador getVentilador() {
        return ventilador;
    }
    
}
