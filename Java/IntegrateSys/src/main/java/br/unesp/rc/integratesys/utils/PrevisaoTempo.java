/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.utils;

/**
 *
 * @author Helton
 */
public class PrevisaoTempo {

    private int temperatura;
    private int umidade;
    private int luminosidade;
    private final CondicaoTempo condicaoTempo;

    public PrevisaoTempo(CondicaoTempo condicaoTempo) {
        this.condicaoTempo = condicaoTempo;
    }

    /**
     * @return the temperatura
     */
    public int getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the umidade
     */
    public int getUmidade() {
        return umidade;
    }

    /**
     * @param umidade the umidade to set
     */
    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    /**
     * @return the luminosidade
     */
    public int getLuminosidade() {
        return luminosidade;
    }

    /**
     * @param luminosidade the luminosidade to set
     */
    public void setLuminosidade(int luminosidade) {
        this.luminosidade = luminosidade;
    }

    /**
     * @return the condicaoTempo
     */
    public CondicaoTempo getCondicaoTempo() {
        return condicaoTempo;
    }

}
