/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.atuadores;

import br.unesp.rc.integratesys.sensores.Sensores;
import br.unesp.rc.integratesys.utils.AgendadorTarefas;
import java.util.ArrayList;

/**
 *
 * @author Helton
 */
public class Atuadores extends ArrayList<Atuador> {
    
    private final Alarme alarme;
    private final Aquecedor aquecedor;
    private final Lampada lampada;
    private final Umedecedor umedecedor;
    private final Ventilador ventilador;
    
    public Atuadores(Sensores sensores, AgendadorTarefas agendadorTarefas) {
        alarme = new Alarme(agendadorTarefas, sensores.getSensorAgua(), 
                sensores.getSensorEnergia(), sensores.getSensorIncendio());
        aquecedor = new Aquecedor(agendadorTarefas, sensores.getSensorTemperatura());
        lampada = new Lampada(agendadorTarefas, sensores.getSensorLuminosidade());        
        umedecedor = new Umedecedor(agendadorTarefas, sensores.getSensorUmidade());
        ventilador = new Ventilador(agendadorTarefas, sensores.getSensorTemperatura());
    }
    
    /**
     * @return the ventilador
     */
    public Ventilador getVentilador() {
        return ventilador;
    }

    /**
     * @return the umedecedor
     */
    public Umedecedor getUmedecedor() {
        return umedecedor;
    }

    /**
     * @return the lampada
     */
    public Lampada getLampada() {
        return lampada;
    }

    /**
     * @return the aquecedor
     */
    public Aquecedor getAquecedor() {
        return aquecedor;
    }

    /**
     * @return the alarme
     */
    public Alarme getAlarme() {
        return alarme;
    }
    
}
