/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.ambiente;

/**
 *
 * @author Waldeilson
 */
public class ParametrosArmazenados {
    private static int temperaturaInicial = 30;
    private static int umidadeInicial = 60;
    private static int luminosidadeInicial = 60;
    private static int temperaturaIdeal =  25;
    private static int umidadeIdeal = 70;
    private static int luminosidadeIdeal = 60;    

    public static int getTemperaturaInicial() {
        return temperaturaInicial;
    }

    public static void setTemperaturaInicial(int temperaturaInicial) {
        ParametrosArmazenados.temperaturaInicial = temperaturaInicial;
    }

    public static int getUmidadeInicial() {
        return umidadeInicial;
    }

    public static void setUmidadeInicial(int umidadeInicial) {
        ParametrosArmazenados.umidadeInicial = umidadeInicial;
    }

    public static int getLuminosidadeInicial() {
        return luminosidadeInicial;
    }

    public static void setLuminosidadeInicial(int luminosidadeInicial) {
        ParametrosArmazenados.luminosidadeInicial = luminosidadeInicial;
    }

    public static int getTemperaturaIdeal() {
        return temperaturaIdeal;
    }

    public static void setTemperaturaIdeal(int temperaturaIdeal) {
        ParametrosArmazenados.temperaturaIdeal = temperaturaIdeal;
    }

    public static int getUmidadeIdeal() {
        return umidadeIdeal;
    }

    public static void setUmidadeIdeal(int umidadeIdeal) {
        ParametrosArmazenados.umidadeIdeal = umidadeIdeal;
    }

    public static int getLuminosidadeIdeal() {
        return luminosidadeIdeal;
    }

    public static void setLuminosidadeIdeal(int luminosidadeIdeal) {
        ParametrosArmazenados.luminosidadeIdeal = luminosidadeIdeal;
    }

    public static int getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public static void setTemperaturaMinima(int temperaturaMinima) {
        ParametrosArmazenados.temperaturaMinima = temperaturaMinima;
    }

    public static int getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public static void setTemperaturaMaxima(int temperaturaMaxima) {
        ParametrosArmazenados.temperaturaMaxima = temperaturaMaxima;
    }

    public static int getUmidadeMinima() {
        return umidadeMinima;
    }

    public static void setUmidadeMinima(int umidadeMinima) {
        ParametrosArmazenados.umidadeMinima = umidadeMinima;
    }

    public static int getUmidadeMaxima() {
        return umidadeMaxima;
    }

    public static void setUmidadeMaxima(int umidadeMaxima) {
        ParametrosArmazenados.umidadeMaxima = umidadeMaxima;
    }

    public static int getLuminosidadeMinima() {
        return luminosidadeMinima;
    }

    public static void setLuminosidadeMinima(int luminosidadeMinima) {
        ParametrosArmazenados.luminosidadeMinima = luminosidadeMinima;
    }

    public static int getLuminosidadeMaxima() {
        return luminosidadeMaxima;
    }

    public static void setLuminosidadeMaxima(int luminosidadeMaxima) {
        ParametrosArmazenados.luminosidadeMaxima = luminosidadeMaxima;
    }
    
    private static int umidadeMinima = 60;
    private static int umidadeMaxima = 80;
    private static int luminosidadeMinima = 50;
    private static int luminosidadeMaxima = 95;
    private static int temperaturaMinima = 22;
    private static int temperaturaMaxima = 30;

}
