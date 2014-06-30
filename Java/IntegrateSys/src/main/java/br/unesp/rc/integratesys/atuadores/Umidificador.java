/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.SensorUmidade;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public class Umidificador extends AtuadorSimples {

    private final int VARIACAO_UMIDADE   = 30;
    private final int INCREMENTO_UMIDADE = 10;

    private final SensorUmidade sensorUmidade;
    
    public Umidificador(AgendadorTarefas agendadorTarefas, SensorUmidade sensorUmidade) {
        super(agendadorTarefas);
        this.sensorUmidade = sensorUmidade;
    }

    @Override
    public int getVariacaoAoLigar() {
        return VARIACAO_UMIDADE;
    }
    
    @Override
    public int getVariacaoAoDesligar() {
        return - VARIACAO_UMIDADE;        
    }
    
    @Override
    public int getIncremento() {
        return INCREMENTO_UMIDADE;        
    }      
    
    @Override
    public int getValorSensor() {
        return sensorUmidade.getUmidade();
    }
    
    @Override
    public void setValor(int valor) {
        IntegrateSysLibraryLoader.getLibrary().setUmidade(valor);
    }
    
    @Override
    public void setLigado(boolean ligado) {
        super.setLigado(ligado);        
        IntegrateSysLibraryLoader.getLibrary().setUmidificador(ligado);
    }        
    
}
