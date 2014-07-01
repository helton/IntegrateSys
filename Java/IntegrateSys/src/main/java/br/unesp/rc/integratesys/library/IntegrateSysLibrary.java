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
    int getLuminosidade();
    int getTemperatura();
    int getUmidade();

    // sets - sensores
    void setLuminosidade(int Luminosidade);
    void setTemperatura(int Temperatura);
    void setUmidade(int Umidade);

    // gets - atuadores
    int getNivelAquecedor();
    int getNivelLampada();
    int getNivelUmidificador();
    int getNivelVentilador();

    // sets - atuadores
    void setNivelAquecedor(int nivelAquecedor);
    void setNivelLampada(int nivelLampada);
    void setNivelUmidificador(int nivelUmidificador);
    void setNivelVentilador(int nivelVentilador);  
        
}
