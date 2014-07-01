/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.SensorTemperatura;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;

/**
 *
 * @author Helton
 */
public class Ventilador extends AtuadorDeTemperatura {

    private final int VARIACAO_TEMPERATURA_POR_NIVEL   = 4;
    private final int INCREMENTO_TEMPERATURA_POR_CICLO = 1;

    public Ventilador(AgendadorTarefas agendadorTarefas, SensorTemperatura sensorTemperatura) {
        super(agendadorTarefas, sensorTemperatura);
    }
       
    @Override
    public int getVariacaoPorNivel() {
        return -VARIACAO_TEMPERATURA_POR_NIVEL;
    }
    
    @Override
    public int getIncrementoPorCiclo() {
        return INCREMENTO_TEMPERATURA_POR_CICLO;        
    } 
    
    @Override
    public void setNivel(Nivel nivel) {
        super.setNivel(nivel);        
        IntegrateSysLibraryLoader.getLibrary().setNivelVentilador(getNivel().getValor());
    }     

}