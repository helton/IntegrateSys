/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.ambiente;

/**
 *
 * Classe utilizada para gerenciar o parametro de luminosidade da granja 
 * 
 * @author Helton
 */
public class Luminosidade {
    
    int luminosidade;
    
    /**
     * Contrutor para inicializar a luminosidade
     * 
     * @param luminosidade Valor inteiro que representa o estado de luminosidade da granja
     */
    public Luminosidade(int luminosidade) {
        this.luminosidade = luminosidade;
    }
    
    /**
     * Retorna o valor inteiro designado para representar o grau de luminosidade
     * 
     * @return luminosidade Valor inteiro da luminosidade
     */
    public int toInteger() {
        return luminosidade;
    }
   
    /**
     * Metodo sobrescrido para retornar a percentagem da luminosidade atual
     * 
     * @return Percentagem de luminosidade
     */
    @Override
    public String toString() {
        return luminosidade + " %";
    }

    /**
     * Cria nova instancia da luminosidade a partir de um valor inteiro
     * 
     * @param luminosidade  Valor inteiro que designa a luminosidade
     * @return              Instancia da classe luminosidade
     */
    public static Luminosidade fromInteger(int luminosidade) {
        return new Luminosidade(luminosidade);
    }
    
    public static String toString(int luminosidade) {
        return fromInteger(luminosidade).toString();
    }    
    
}