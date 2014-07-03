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
    private int temperaturaIdeal =  25;
    private int umidadeIdeal = 70;
    private int luminosidadeIdeal = 60;

    //Condições mínimas e máximas
    private int temperaturaMinima = 22;
    private int temperaturaMaxima = 30;
    private int umidadeMinima = 55;
    private int umidadeMaxima = 80;
    private int luminosidadeMinima = 50;
    private int luminosidadeMaxima = 95;

    public Parametros() {
        temperaturaInicial = 25;
        umidadeInicial = 65;
        luminosidadeInicial = 65;
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
     * @return the temperaturaMinima
     */
    public int getTemperaturaMinima() {
        return temperaturaMinima;
    }

    /**
     * @return the temperaturaMaxima
     */
    public int getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    /**
     * @return the umidadeMinima
     */
    public int getUmidadeMinima() {
        return umidadeMinima;
    }

    /**
     * @return the umidadeMaxima
     */
    public int getUmidadeMaxima() {
        return umidadeMaxima;
    }

    /**
     * @return the luminosidadeMinima
     */
    public int getLuminosidadeMinima() {
        return luminosidadeMinima;
    }

    /**
     * @return the luminosidadeMaxima
     */
    public int getLuminosidadeMaxima() {
        return luminosidadeMaxima;
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
     * @param temperaturaMinima the temperaturaMinima to set
     */
    public void setTemperaturaMinima(int temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    /**
     * @param temperaturaMaxima the temperaturaMaxima to set
     */
    public void setTemperaturaMaxima(int temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    /**
     * @param umidadeMinima the umidadeMinima to set
     */
    public void setUmidadeMinima(int umidadeMinima) {
        this.umidadeMinima = umidadeMinima;
    }

    /**
     * @param umidadeMaxima the umidadeMaxima to set
     */
    public void setUmidadeMaxima(int umidadeMaxima) {
        this.umidadeMaxima = umidadeMaxima;
    }

    /**
     * @param luminosidadeMinima the luminosidadeMinima to set
     */
    public void setLuminosidadeMinima(int luminosidadeMinima) {
        this.luminosidadeMinima = luminosidadeMinima;
    }

    /**
     * @param luminosidadeMaxima the luminosidadeMaxima to set
     */
    public void setLuminosidadeMaxima(int luminosidadeMaxima) {
        this.luminosidadeMaxima = luminosidadeMaxima;
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
