/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.agentes;

import br.unesp.rc.integratesys.atuadores.Atuador;
import java.util.Objects;

/**
 *
 * @author Helton
 */
public class Acao {

    public enum TipoAcao {
        LIGAR,
        DESLIGAR
    };
    
    private final TipoAcao tipoAcao;
    private final Atuador atuador;
    
    public Acao(TipoAcao tipoAcao, Atuador atuador) {
        this.tipoAcao = tipoAcao;
        this.atuador = atuador;
    }

    /**
     * @return the tipoAcao
     */
    public TipoAcao getTipoAcao() {
        return tipoAcao;
    }

    /**
     * @return the atuador
     */
    public Atuador getAtuador() {
        return atuador;
    }
        
    public void executar() {
        getAtuador().setLigado(getTipoAcao() == TipoAcao.LIGAR);
    }
    
    @Override
    public boolean equals(Object outro) {
        if (outro == null) {
            return false;
        }
        if (outro == this) {
            return true;
        }        
        if (!(outro instanceof Acao)) {
            return false;
        }
        Acao acao = (Acao)outro;            
        return ((tipoAcao == acao.getTipoAcao()) && (atuador == acao.getAtuador()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.getTipoAcao());
        hash = 53 * hash + Objects.hashCode(this.getAtuador());
        return hash;
    }
    
}
