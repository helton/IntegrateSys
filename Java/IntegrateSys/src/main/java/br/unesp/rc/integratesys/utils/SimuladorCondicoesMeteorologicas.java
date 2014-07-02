/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unesp.rc.integratesys.utils;

import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import java.util.Random;

/**
 *
 * @author Helton
 */
public class SimuladorCondicoesMeteorologicas {
       
    private boolean ligado;
    private final AgendadorTarefas agendadorTarefas;
    private final Random random;
    
    public SimuladorCondicoesMeteorologicas(AgendadorTarefas agendadorTarefas) {
        this.ligado = false;
        this.agendadorTarefas = agendadorTarefas;                
        this.random = new Random();
    }
    
    private CondicaoTempo gerarCondicaoTempo() {
        int valorAleatorio = random.nextInt(CondicaoTempo.values().length);
        switch(valorAleatorio) {
            case 0:
                return CondicaoTempo.DIA_CHUVOSO;
            case 1:
                return CondicaoTempo.DIA_NUBLADO;
            default:
                return CondicaoTempo.DIA_ENSOLARADO;
        }
    }
    
    public int getNumeroAleatorioComFaixa(int minimo, int maximo) {
        return random.nextInt(maximo - minimo + 1) + minimo;
    }
    
    private PrevisaoTempo getPrevisaoTempo() {
        CondicaoTempo condicaoTempo = gerarCondicaoTempo();
        PrevisaoTempo previsao = new PrevisaoTempo(condicaoTempo);
        switch(condicaoTempo) {
            case DIA_CHUVOSO:
                previsao.setTemperatura(getNumeroAleatorioComFaixa(10, 20));
                previsao.setUmidade(getNumeroAleatorioComFaixa(70, 90));
                previsao.setLuminosidade(getNumeroAleatorioComFaixa(10, 40));
            case DIA_NUBLADO:
                previsao.setTemperatura(getNumeroAleatorioComFaixa(10, 20));
                previsao.setUmidade(getNumeroAleatorioComFaixa(50, 65));
                previsao.setLuminosidade(getNumeroAleatorioComFaixa(10, 40));
            case DIA_ENSOLARADO:
                previsao.setTemperatura(getNumeroAleatorioComFaixa(25, 35));
                previsao.setUmidade(getNumeroAleatorioComFaixa(40, 60));
                previsao.setLuminosidade(getNumeroAleatorioComFaixa(60, 90));
        }        
        return previsao;
    }
    
    public void simular() {
        if (isLigado()) {
            final PrevisaoTempo previsaoTempo = getPrevisaoTempo();
            agendadorTarefas.agendarTarefa(new Tarefa() {
                @Override
                public void executar() {
                    IntegrateSysLibraryLoader.getLibrary().setTemperatura(previsaoTempo.getTemperatura());
                    IntegrateSysLibraryLoader.getLibrary().setUmidade(previsaoTempo.getUmidade());
                    IntegrateSysLibraryLoader.getLibrary().setLuminosidade(previsaoTempo.getLuminosidade());                
                }
            }, 1);
        }        
    }

    /**
     * @return the ligado
     */
    public boolean isLigado() {
        return ligado;
    }

    /**
     * @param ligado the ligado to set
     */
    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }
    
}

enum CondicaoTempo {
    DIA_CHUVOSO,
    DIA_NUBLADO,
    DIA_ENSOLARADO;
}

class PrevisaoTempo {
    
    private int temperatura;
    private int umidade;
    private int luminosidade;
    private final CondicaoTempo condicaoTempo;
    
    public PrevisaoTempo(CondicaoTempo condicaoTempo) {
        this.condicaoTempo = condicaoTempo;
    }
    
    /**
     * @return the temperatura
     */
    public int getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the umidade
     */
    public int getUmidade() {
        return umidade;
    }

    /**
     * @param umidade the umidade to set
     */
    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    /**
     * @return the luminosidade
     */
    public int getLuminosidade() {
        return luminosidade;
    }

    /**
     * @param luminosidade the luminosidade to set
     */
    public void setLuminosidade(int luminosidade) {
        this.luminosidade = luminosidade;
    }

    /**
     * @return the condicaoTempo
     */
    public CondicaoTempo getCondicaoTempo() {
        return condicaoTempo;
    }
    
}