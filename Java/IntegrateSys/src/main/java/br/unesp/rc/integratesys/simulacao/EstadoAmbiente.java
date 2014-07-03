/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.simulacao;

import br.unesp.rc.integratesys.ambiente.Luminosidade;
import br.unesp.rc.integratesys.ambiente.Temperatura;
import br.unesp.rc.integratesys.ambiente.Umidade;

/**
 *
 * @author Helton
 */
public class EstadoAmbiente {

    private Temperatura temperatura;
    private Umidade umidade;
    private Luminosidade luminosidade;

    /**
     * @return the temperatura
     */
    public Temperatura getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the umidade
     */
    public Umidade getUmidade() {
        return umidade;
    }

    /**
     * @param umidade the umidade to set
     */
    public void setUmidade(Umidade umidade) {
        this.umidade = umidade;
    }

    /**
     * @return the luminosidade
     */
    public Luminosidade getLuminosidade() {
        return luminosidade;
    }

    /**
     * @param luminosidade the luminosidade to set
     */
    public void setLuminosidade(Luminosidade luminosidade) {
        this.luminosidade = luminosidade;
    }

}
