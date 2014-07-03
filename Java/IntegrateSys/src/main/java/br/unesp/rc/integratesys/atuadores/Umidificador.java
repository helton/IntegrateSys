/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.SensorUmidade;
import br.unesp.rc.integratesys.simulacao.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public class Umidificador extends Atuador {

    private final int UMIDADE_LIMITE_MAXIMA = 100;
    private final int UMIDADE_LIMITE_MINIMA = 0;    
    private final int VARIACAO_UMIDADE_POR_NIVEL   = 10;
    private final int INCREMENTO_UMIDADE_POR_CICLO = 5;

    private final SensorUmidade sensorUmidade;
    
    public Umidificador(AgendadorTarefas agendadorTarefas, SensorUmidade sensorUmidade) {
        super(agendadorTarefas);
        this.sensorUmidade = sensorUmidade;
    }

    @Override
    public int getVariacaoPorNivel() {
        return VARIACAO_UMIDADE_POR_NIVEL;
    }
    
    @Override
    public int getIncrementoPorCiclo() {
        return INCREMENTO_UMIDADE_POR_CICLO;        
    }      
    
    @Override
    public int getValor() {
        return sensorUmidade.getUmidade();
    }
    
    @Override
    public void setValor(int valor) {
        valor = Math.max(Math.min(valor, UMIDADE_LIMITE_MAXIMA), UMIDADE_LIMITE_MINIMA);
        IntegrateSysLibraryLoader.getLibrary().setUmidade(valor);
    }
    
    @Override
    public void setNivel(Nivel nivel) {
        super.setNivel(nivel);        
        IntegrateSysLibraryLoader.getLibrary().setNivelUmidificador(getNivel().getValor());
    }     
    
}
