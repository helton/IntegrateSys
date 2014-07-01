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

    //condições iniciais
    private final int temperaturaInicial;
    private final int umidadeInicial;
    private final int luminosidadeInicial;

    //Condições ideais
    private int temperaturaIdeal;
    private int umidadeIdeal;
    private int luminosidadeIdeal;

    //Condições críticas
    private int temperaturaMinimaCritica = 22;
    private int temperaturaMaximaCritica = 30;
    private int umidadeMinimaCritica;
    private int umidadeMaximaCritica;
    private int luminosidadeMinimaCritica;
    private int luminosidadeMaximaCritica;

    public Parametros() {
        temperaturaInicial = 30;
        umidadeInicial = 60;
        luminosidadeInicial = 50;
    }

    /**
     * @return the temperaturaIdeal
     */
    public int getTemperaturaIdeal() {
        return temperaturaIdeal;
    }

    /**
     * @return the umidadeIdeal
     */
    public int getUmidadeIdeal() {
        return umidadeIdeal;
    }

    /**
     * @return the luminosidadeIdeal
     */
    public int getLuminosidadeIdeal() {
        return luminosidadeIdeal;
    }

    /**
     * @return the temperaturaMinimaCritica
     */
    public int getTemperaturaMinimaCritica() {
        return temperaturaMinimaCritica;
    }

    /**
     * @return the temperaturaMaximaCritica
     */
    public int getTemperaturaMaximaCritica() {
        return temperaturaMaximaCritica;
    }

    /**
     * @return the umidadeMinimaCritica
     */
    public int getUmidadeMinimaCritica() {
        return umidadeMinimaCritica;
    }

    /**
     * @return the umidadeMaximaCritica
     */
    public int getUmidadeMaximaCritica() {
        return umidadeMaximaCritica;
    }

    /**
     * @return the luminosidadeMinimaCritica
     */
    public int getLuminosidadeMinimaCritica() {
        return luminosidadeMinimaCritica;
    }

    /**
     * @return the luminosidadeMaximaCritica
     */
    public int getLuminosidadeMaximaCritica() {
        return luminosidadeMaximaCritica;
    }

    /**
     * @param temperaturaIdeal the temperaturaIdeal to set
     */
    public void setTemperaturaIdeal(int temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }

    /**
     * @param umidadeIdeal the umidadeIdeal to set
     */
    public void setUmidadeIdeal(int umidadeIdeal) {
        this.umidadeIdeal = umidadeIdeal;
    }

    /**
     * @param luminosidadeIdeal the luminosidadeIdeal to set
     */
    public void setLuminosidadeIdeal(int luminosidadeIdeal) {
        this.luminosidadeIdeal = luminosidadeIdeal;
    }

    /**
     * @param temperaturaMinimaCritica the temperaturaMinimaCritica to set
     */
    public void setTemperaturaMinimaCritica(int temperaturaMinimaCritica) {
        this.temperaturaMinimaCritica = temperaturaMinimaCritica;
    }

    /**
     * @param temperaturaMaximaCritica the temperaturaMaximaCritica to set
     */
    public void setTemperaturaMaximaCritica(int temperaturaMaximaCritica) {
        this.temperaturaMaximaCritica = temperaturaMaximaCritica;
    }

    /**
     * @param umidadeMinimaCritica the umidadeMinimaCritica to set
     */
    public void setUmidadeMinimaCritica(int umidadeMinimaCritica) {
        this.umidadeMinimaCritica = umidadeMinimaCritica;
    }

    /**
     * @param umidadeMaximaCritica the umidadeMaximaCritica to set
     */
    public void setUmidadeMaximaCritica(int umidadeMaximaCritica) {
        this.umidadeMaximaCritica = umidadeMaximaCritica;
    }

    /**
     * @param luminosidadeMinimaCritica the luminosidadeMinimaCritica to set
     */
    public void setLuminosidadeMinimaCritica(int luminosidadeMinimaCritica) {
        this.luminosidadeMinimaCritica = luminosidadeMinimaCritica;
    }

    /**
     * @param luminosidadeMaximaCritica the luminosidadeMaximaCritica to set
     */
    public void setLuminosidadeMaximaCritica(int luminosidadeMaximaCritica) {
        this.luminosidadeMaximaCritica = luminosidadeMaximaCritica;
    }

    /**
     * @return the temperaturaInicial
     */
    public int getTemperaturaInicial() {
        return temperaturaInicial;
    }

    /**
     * @return the umidadeInicial
     */
    public int getUmidadeInicial() {
        return umidadeInicial;
    }

    /**
     * @return the luminosidadeInicial
     */
    public int getLuminosidadeInicial() {
        return luminosidadeInicial;
    }

}
