/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.ambiente;

/**
 * Classe utilizada para gerenciar os parametros da simulação da granja, estabelecendo
 * valores para as consições ideais e críticas da temperatura, umidade e luminosidade
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

    /**
     * Contrutor do método que inicializa os parâmetros de temperatura, umidade e luminosidade iniciais
     */
    public Parametros() {
        temperaturaInicial = 25;
        umidadeInicial = 65;
        luminosidadeInicial = 65;
    }

    /**
     * Metodo get para retornar a temperatura ideal da granja
     * @return a temperatura ideal
     */
    public int getTemperaturaIdeal() {
        return temperaturaIdeal;
    }

    /**
     * Metodo get para retornar a umidade ideal da granja
     * @return a umidade ideal
     */
    public int getUmidadeIdeal() {
        return umidadeIdeal;
    }

    /**
     * Metodo get para retornar a luminosidade ideal da granja
     * @return a luminosidade ideal
     */
    public int getLuminosidadeIdeal() {
        return luminosidadeIdeal;
    }

    /**
     * Metodo get para retornar a temperatura mínima aceitável para a granja
     * @return a temperatura Minima Critica
     */
    public int getTemperaturaMinima() {
        return temperaturaMinima;
    }

    /**
     * Metodo get para retornar a temperatura máxima aceitável para a granja
     * @return a temperatura Maxima Critica
     */
    public int getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    /**
     * Metodo get para retornar a umidade mínima aceitável para a granja
     * @return a umidade Minima Critica
     */
    public int getUmidadeMinima() {
        return umidadeMinima;
    }

    /**
     * Metodo get para retornar a umidade máxima aceitável para a granja
     * @return the umidade Maxima Critica
     */
    public int getUmidadeMaxima() {
        return umidadeMaxima;
    }

    /**
     * Metodo get para retornar a luminosidade mínima aceitável para a granja
     * @return A luminosidade Minima Critica
     */
    public int getLuminosidadeMinima() {
        return luminosidadeMinima;
    }

    /**
     * Metodo get para retornar a luminosidade máxima aceitável para a granja
     * @return a luminosidade Maxima Critica
     */
    public int getLuminosidadeMaxima() {
        return luminosidadeMaxima;
    }

    /**
     * Metodo set para mudar a temperatura ideal da granja
     * @param temperaturaIdeal a temperatura ideal a alterar
     */
    public void setTemperaturaIdeal(int temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }

    /**
     * Metodo set para mudar a umidade ideal da granja
     * @param umidadeIdeal a umidade ideal a alterar
     */
    public void setUmidadeIdeal(int umidadeIdeal) {
        this.umidadeIdeal = umidadeIdeal;
    }

    /**
     * Metodo set para mudar a luminosidade ideal da granja
     * @param luminosidadeIdeal the luminosidadeIdeal to set
     */
    public void setLuminosidadeIdeal(int luminosidadeIdeal) {
        this.luminosidadeIdeal = luminosidadeIdeal;
    }

    /**
     * Metodo set para mudar a temperatura mínima aceitável para a granja
     * @param temperaturaMinimaCritica a temperatura mínima crítica para setar
     */
    public void setTemperaturaMinima(int temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    /**
     * Metodo set para mudar a temperatura máxima aceitável para a granja
     * @param temperaturaMaximaCritica a temperatura máxima crítica para setar
     */
    public void setTemperaturaMaxima(int temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    /**
     * Metodo set para mudar a umidade mínima aceitável para a granja
     * @param umidadeMinimaCritica A umidade minima critica a alterar
     */
    public void setUmidadeMinima(int umidadeMinima) {
        this.umidadeMinima = umidadeMinima;
    }

    /**
     * Metodo set para mudar a umidade máxima aceitável para a granja
     * @param umidadeMaximaCritica A umidade máxima critica a alterar
     */
    public void setUmidadeMaxima(int umidadeMaxima) {
        this.umidadeMaxima = umidadeMaxima;
    }

    /**
     * Metodo set para mudar a luminosidade máínima aceitável para a granja
     * @param luminosidadeMinimaCritica A luminosidade Mínima Critica a alterar
     */
    public void setLuminosidadeMinima(int luminosidadeMinima) {
        this.luminosidadeMinima = luminosidadeMinima;
    }

    /**
     * Metodo set para mudar a luminosidade máxima aceitável para a granja
     * @param luminosidadeMaximaCritica A luminosidade Máxima Critica a alterar
     */
    public void setLuminosidadeMaxima(int luminosidadeMaxima) {
        this.luminosidadeMaxima = luminosidadeMaxima;
    }

    /**
     * Método get que retorna a temperatura inicial da granja
     * @return a temperatura Inicial
     */
    public int getTemperaturaInicial() {
        return temperaturaInicial;
    }

    /**
     * Método get que retorna a umidade inicial da granja
     * @return a umidade inicial
     */
    public int getUmidadeInicial() {
        return umidadeInicial;
    }

    /**
     * Metodo get que retorna a luminosidade inicial da granja
     * @return a luminosidade inicial
     */
    public int getLuminosidadeInicial() {
        return luminosidadeInicial;
    }

}
