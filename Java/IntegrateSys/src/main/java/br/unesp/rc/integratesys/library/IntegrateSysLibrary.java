/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.library;

import com.sun.jna.Library;

/**
 *
 * @author Helton
 */
public interface IntegrateSysLibrary extends Library {
    
    static final String LIBRARY_NAME = "IntegrateSysLib";

    // gets - sensores
    int getTemperatura();
    int getUmidade();
    int getLuminosidade();
    boolean hasAgua();
    boolean hasEnergia();
    boolean inIncendio();
    
    // gets - atuadores
    boolean statusAlarme();
    boolean statusAquecedor();
    boolean statusLampada();
    boolean statusUmidificador();
    boolean statusVentilador();
    
    // sets - sensores
    void setAgua(boolean agua);
    void setEnergia(boolean energia);
    void setIncendio(boolean incendio);
    void setLuminosidade(int luminosidade);
    void setTemperatura(int temperatura);
    void setUmidade(int umidade);

    // sets - atuadores
    void setAlarme(boolean alarme);
    void setAquecedor(boolean aquecedor);
    void setLampada(boolean lampada);
    void setUmidificador(boolean umidificador);
    void setVentilador(boolean ventilador);    
        
}
