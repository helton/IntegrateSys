/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.SensorLuminosidade;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public class Lampada extends AtuadorSimples {

    private final int VARIACAO_LUMINOSIDADE   = 35;
    private final int INCREMENTO_LUMINOSIDADE = 10;

    private final SensorLuminosidade sensorLuminosidade;
    
    public Lampada(AgendadorTarefas agendadorTarefas, SensorLuminosidade sensorLuminosidade) {
        super(agendadorTarefas);
        this.sensorLuminosidade = sensorLuminosidade;
    }

    @Override
    public int getVariacaoAoLigar() {
        return VARIACAO_LUMINOSIDADE;
    }
    
    @Override
    public int getVariacaoAoDesligar() {
        return - VARIACAO_LUMINOSIDADE;        
    }
    
    @Override
    public int getIncremento() {
        return INCREMENTO_LUMINOSIDADE;        
    }      
    
    @Override
    public int getValorSensor() {
        return sensorLuminosidade.getLuminosidade();
    }
    
    @Override
    public void setValor(int valor) {
        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(valor);
    }  
    
    @Override
    public void setLigado(boolean ligado) {
        IntegrateSysLibraryLoader.getLibrary().setLampada(ligado);
    }        
    
}
