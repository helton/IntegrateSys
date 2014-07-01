/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.sensores;

import java.util.ArrayList;

/**
 *
 * @author Helton
 */
public class Sensores extends ArrayList<Sensor> {
    
    private final SensorTemperatura sensorTemperatura;
    private final SensorLuminosidade sensorLuminosidade;
    private final SensorUmidade sensorUmidade;   
    
    public Sensores() {
        sensorTemperatura = new SensorTemperatura();
        sensorLuminosidade = new SensorLuminosidade();
        sensorUmidade = new SensorUmidade();
    }

    /**
     * @return the sensorTemperatura
     */
    public SensorTemperatura getSensorTemperatura() {
        return sensorTemperatura;
    }

    /**
     * @return the sensorLuminosidade
     */
    public SensorLuminosidade getSensorLuminosidade() {
        return sensorLuminosidade;
    }

    /**
     * @return the sensorUmidade
     */
    public SensorUmidade getSensorUmidade() {
        return sensorUmidade;
    }
    
}
