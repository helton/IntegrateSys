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
    private static int temperaturaMinimaCritica = 22;
    private static int temperaturaMaximaCritica = 30;

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

    public static int getTemperaturaMinimaCritica() {
        return temperaturaMinimaCritica;
    }

    public static void setTemperaturaMinimaCritica(int temperaturaMinimaCritica) {
        ParametrosArmazenados.temperaturaMinimaCritica = temperaturaMinimaCritica;
    }

    public static int getTemperaturaMaximaCritica() {
        return temperaturaMaximaCritica;
    }

    public static void setTemperaturaMaximaCritica(int temperaturaMaximaCritica) {
        ParametrosArmazenados.temperaturaMaximaCritica = temperaturaMaximaCritica;
    }

    public static int getUmidadeMinimaCritica() {
        return umidadeMinimaCritica;
    }

    public static void setUmidadeMinimaCritica(int umidadeMinimaCritica) {
        ParametrosArmazenados.umidadeMinimaCritica = umidadeMinimaCritica;
    }

    public static int getUmidadeMaximaCritica() {
        return umidadeMaximaCritica;
    }

    public static void setUmidadeMaximaCritica(int umidadeMaximaCritica) {
        ParametrosArmazenados.umidadeMaximaCritica = umidadeMaximaCritica;
    }

    public static int getLuminosidadeMinimaCritica() {
        return luminosidadeMinimaCritica;
    }

    public static void setLuminosidadeMinimaCritica(int luminosidadeMinimaCritica) {
        ParametrosArmazenados.luminosidadeMinimaCritica = luminosidadeMinimaCritica;
    }

    public static int getLuminosidadeMaximaCritica() {
        return luminosidadeMaximaCritica;
    }

    public static void setLuminosidadeMaximaCritica(int luminosidadeMaximaCritica) {
        ParametrosArmazenados.luminosidadeMaximaCritica = luminosidadeMaximaCritica;
    }
    private static int umidadeMinimaCritica = 60;
    private static int umidadeMaximaCritica = 80;
    private static int luminosidadeMinimaCritica = 50;
    private static int luminosidadeMaximaCritica = 95;

}
