/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.agentes;

import br.unesp.rc.integratesys.ambiente.Parametros;
import java.util.List;

/**
 *
 * @author Helton
 */
public abstract class Agente {
    
    protected final Parametros parametros;

    public Agente(Parametros parametros) {
        this.parametros = parametros;        
    }
    
    public Parametros getParametros() {
        return parametros;
    }
    
    abstract List<Acao> agir();
    
}
