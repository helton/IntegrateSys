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
    boolean hasLuminosidade();
    boolean hasAgua();
    boolean hasEnergia();
    boolean hasIncendio();
    
    // gets - atuadores
    boolean statusAlarme();
    boolean statusAquecedores();
    boolean statusLampadas();
    boolean statusUmedecedores();
    boolean statusVentiladores();
    
    // sets - sensores
    void setAgua(boolean agua);
    void setEnergia(boolean energia);
    void setIncendio(boolean incendio);
    void setLuminosidade(boolean luminosidade);
    void setTemperatura(int temperatura);
    void setUmidade(int umidade);

    // sets - atuadores
    void setAlarme(boolean alarme);
    void setAquecedores(boolean aquecedores);
    void setLampadas(boolean lampadas);
    void setUmedecedores(boolean umedecedores);
    void setVentiladores(boolean ventiladores);    
    
}
