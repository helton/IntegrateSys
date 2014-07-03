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
public class Lampada extends Atuador {

    private final int LUMINOSIDADE_MAXIMA = 100;
    private final int LUMINOSIDADE_MINIMA = 0;       
    private final int VARIACAO_LUMINOSIDADE_POR_NIVEL   = 10;
    private final int INCREMENTO_LUMINOSIDADE_POR_CICLO = 5;

    private final SensorLuminosidade sensorLuminosidade;
    
    public Lampada(AgendadorTarefas agendadorTarefas, SensorLuminosidade sensorLuminosidade) {
        super(agendadorTarefas);
        this.sensorLuminosidade = sensorLuminosidade;
    }

    @Override
    public int getVariacaoPorNivel() {
        return VARIACAO_LUMINOSIDADE_POR_NIVEL;
    }
    
    @Override
    public int getIncrementoPorCiclo() {
        return INCREMENTO_LUMINOSIDADE_POR_CICLO;        
    }      
    
    @Override
    public int getValor() {
        return sensorLuminosidade.getLuminosidade();
    }
    
    @Override
    public void setValor(int valor) {
        valor = Math.max(Math.min(valor, LUMINOSIDADE_MAXIMA), LUMINOSIDADE_MINIMA);        
        IntegrateSysLibraryLoader.getLibrary().setLuminosidade(valor);
    }  
    
    @Override
    public void setNivel(Nivel nivel) {
        super.setNivel(nivel);        
        IntegrateSysLibraryLoader.getLibrary().setNivelLampada(getNivel().getValor());
    }        
    
}
