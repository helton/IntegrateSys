/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.SensorTemperatura;
import br.unesp.rc.integratesys.simulacao.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public abstract class AtuadorDeTemperatura extends Atuador {
    
    private final int TEMPERATURA_MAXIMA = 100;
    private final int TEMPERATURA_MINIMA = 0;
    private final SensorTemperatura sensorTemperatura;
    
    public AtuadorDeTemperatura(AgendadorTarefas agendadorTarefas, SensorTemperatura sensorTemperatura) {
        super(agendadorTarefas);
        this.sensorTemperatura = sensorTemperatura;
    }   
    
    @Override
    public int getValor() {
        return sensorTemperatura.getTemperatura();
    }
    
    @Override
    public void setValor(int valor) {
        valor = Math.max(Math.min(valor, TEMPERATURA_MAXIMA), TEMPERATURA_MINIMA);
        IntegrateSysLibraryLoader.getLibrary().setTemperatura(valor);
    }
    
}
