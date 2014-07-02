/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.utils;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author Helton
 */
public enum CondicaoTempo {

    DIA_CHUVOSO(0, "dia chuvoso"),
    DIA_NUBLADO(1, "dia nublado"),
    DIA_ENSOLARADO(2, "dia ensolarado");

    private static final String PATH_IMAGENS_CLIMA = "/imagens/clima/";
    private static final Map<Integer, CondicaoTempo> mapaConversaoIntParaEnum = new HashMap<>();
    private final int indice;
    private final String descricao;
    private ImageIcon imagem;

    private CondicaoTempo(int indice, String descricao) {
        this.indice = indice;
        this.descricao = descricao;
    }

    public ImageIcon getImagem() {
        if (imagem == null) {
            imagem = new ImageIcon(getClass().getResource(String.format("%s/%s.png", PATH_IMAGENS_CLIMA, descricao.replace(" ", "_"))));
        }
        return imagem;
    }
    
    /**
     * @return the descricao
     */    
    public String getDescricao() {
        return descricao;
    }

    public static CondicaoTempo fromInt(int i) {
        CondicaoTempo tipo = mapaConversaoIntParaEnum.get(Integer.valueOf(i));
        if (tipo == null) {
            return CondicaoTempo.DIA_ENSOLARADO;
        }
        return tipo;
    }

    static {
        for (CondicaoTempo tipo : CondicaoTempo.values()) {
            mapaConversaoIntParaEnum.put(tipo.indice, tipo);
        }
    }

}

