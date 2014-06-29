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
    
    private final SensorAgua sensorAgua;
    private final SensorEnergia sensorEnergia;
    private final SensorIncendio sensorIncendio;
    private final SensorTemperatura sensorTemperatura;
    private final SensorLuminosidade sensorLuminosidade;
    private final SensorUmidade sensorUmidade;   
    
    public Sensores() {
        sensorAgua = new SensorAgua();
        sensorEnergia = new SensorEnergia();
        sensorIncendio = new SensorIncendio();
        sensorTemperatura = new SensorTemperatura();
        sensorLuminosidade = new SensorLuminosidade();
        sensorUmidade = new SensorUmidade();
    }

    /**
     * @return the sensorAgua
     */
    public SensorAgua getSensorAgua() {
        return sensorAgua;
    }

    /**
     * @return the sensorEnergia
     */
    public SensorEnergia getSensorEnergia() {
        return sensorEnergia;
    }

    /**
     * @return the sensorIncendio
     */
    public SensorIncendio getSensorIncendio() {
        return sensorIncendio;
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
