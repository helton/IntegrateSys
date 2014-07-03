/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.ambiente;

/**
 *
 * @author Helton
 */
public class Parametros {

    public Parametros() {                
    }

    public int getTemperaturaInicial() {
        return ParametrosArmazenados.getTemperaturaInicial();
    }

    public int getUmidadeInicial() {
        return ParametrosArmazenados.getUmidadeInicial();
    }

    public int getLuminosidadeInicial() {
        return ParametrosArmazenados.getLuminosidadeInicial();
    }

    public int getTemperaturaIdeal() {
        return ParametrosArmazenados.getTemperaturaIdeal();
    }

    public int getUmidadeIdeal() {
        return ParametrosArmazenados.getUmidadeIdeal();
    }

    public int getLuminosidadeIdeal() {
        return ParametrosArmazenados.getLuminosidadeIdeal();
    }

    public int getTemperaturaMinimaCritica() {
        return ParametrosArmazenados.getTemperaturaMinimaCritica();
    }

    public int getTemperaturaMaximaCritica() {
        return ParametrosArmazenados.getTemperaturaMaximaCritica();
    }

    public int getUmidadeMinimaCritica() {
        return ParametrosArmazenados.getUmidadeMinimaCritica();
    }

    public int getUmidadeMaximaCritica() {
        return ParametrosArmazenados.getUmidadeMaximaCritica();
    }

    public int getLuminosidadeMinimaCritica() {
        return ParametrosArmazenados.getLuminosidadeMinimaCritica();
    }

    public int getLuminosidadeMaximaCritica() {
        return ParametrosArmazenados.getLuminosidadeMaximaCritica();
    }    
}
