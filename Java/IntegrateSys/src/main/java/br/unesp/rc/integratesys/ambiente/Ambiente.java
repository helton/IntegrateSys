/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.ambiente;

import br.unesp.rc.integratesys.atuadores.Alarme;
import br.unesp.rc.integratesys.atuadores.Aquecedor;
import br.unesp.rc.integratesys.atuadores.Lampada;
import br.unesp.rc.integratesys.atuadores.Umedecedor;
import br.unesp.rc.integratesys.atuadores.Ventilador;

/**
 *
 * @author Helton
 */
public class Ambiente {

    public enum Controle {
        AUTOMATICO,
        MANUAL
    }
    
    private int temperatura;
    private int umidade;
    private int luminosidade;
    private boolean agua;
    private boolean energia;
    private boolean incendio;
    
    private Alarme alarme;
    private Aquecedor aquecedor;
    private Lampada lampada;
    private Umedecedor umedecedor;
    private Ventilador ventilador;
    
    private Controle controle;
    
    public Ambiente() {
        temperatura = 0;
        umidade = 0;
        luminosidade = 0;
        agua = false;
        energia = false;
        incendio = false;
        alarme = new Alarme(false);
        aquecedor = new Aquecedor(false);
        lampada = new Lampada(false);        
        umedecedor = new Umedecedor(false);
        ventilador = new Ventilador(false);
        controle = Controle.AUTOMATICO;
    }

    /**
     * @return the temperatura
     */
    public int getTemperatura() {
        return temperatura;
    }

    /**
     * @return the umidade
     */
    public int getUmidade() {
        return umidade;
    }

    /**
     * @return the luminosidade
     */
    public int getLuminosidade() {
        return luminosidade;
    }

    /**
     * @return the agua
     */
    public boolean hasAgua() {
        return agua;
    }

    /**
     * @return the energia
     */
    public boolean hasEnergia() {
        return energia;
    }

    /**
     * @return the incendio
     */
    public boolean inIncendio() {
        return incendio;
    }

    /**
     * @return the ventilador
     */
    public Ventilador getVentilador() {
        return ventilador;
    }

    /**
     * @return the umedecedor
     */
    public Umedecedor getUmedecedor() {
        return umedecedor;
    }

    /**
     * @return the lampada
     */
    public Lampada getLampada() {
        return lampada;
    }

    /**
     * @return the aquecedor
     */
    public Aquecedor getAquecedor() {
        return aquecedor;
    }

    /**
     * @return the alarme
     */
    public Alarme getAlarme() {
        return alarme;
    }
    
    /**
     * @return the controle
     */
    public Controle getControle() {
        return controle;
    }    
    
}