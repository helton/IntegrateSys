/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.utils;

import br.unesp.rc.integratesys.ambiente.Luminosidade;
import br.unesp.rc.integratesys.ambiente.Parametros;
import br.unesp.rc.integratesys.ambiente.Temperatura;
import br.unesp.rc.integratesys.ambiente.Umidade;
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
    private EstadoAmbiente previsaoTempo;
    private EstadoAmbiente ambienteExterno;
    private CondicaoTempo condicaoTempo;

    private static final int VARIACAO_TEMPERATURA_PREVISAO_TEMPO = 2;
    private static final int VARIACAO_UMIDADE_PREVISAO_TEMPO = 5;
    private static final int VARIACAO_LUMINOSIDADE_PREVISAO_TEMPO = 5;

    public SimuladorCondicoesMeteorologicas(AgendadorTarefas agendadorTarefas, Parametros parametros) {
        this.ligado = false;
        this.agendadorTarefas = agendadorTarefas;
        this.random = new Random();
    }

    public int getNumeroAleatorioComFaixa(int minimo, int maximo) {
        return random.nextInt(maximo - minimo + 1) + minimo;
    }

    public EstadoAmbiente getPrevisaoTempo() {
        if (previsaoTempo == null) {
            previsaoTempo = new EstadoAmbiente();
            switch (getCondicaoTempo()) {
                case DIA_CHUVOSO:
                    previsaoTempo.setTemperatura(new Temperatura(getNumeroAleatorioComFaixa(10, 20)));
                    previsaoTempo.setUmidade(new Umidade(getNumeroAleatorioComFaixa(70, 90)));
                    previsaoTempo.setLuminosidade(new Luminosidade(getNumeroAleatorioComFaixa(10, 40)));
                case DIA_NUBLADO:
                    previsaoTempo.setTemperatura(new Temperatura(getNumeroAleatorioComFaixa(10, 20)));
                    previsaoTempo.setUmidade(new Umidade(getNumeroAleatorioComFaixa(50, 65)));
                    previsaoTempo.setLuminosidade(new Luminosidade(getNumeroAleatorioComFaixa(10, 40)));
                case DIA_ENSOLARADO:
                    previsaoTempo.setTemperatura(new Temperatura(getNumeroAleatorioComFaixa(25, 35)));
                    previsaoTempo.setUmidade(new Umidade(getNumeroAleatorioComFaixa(40, 60)));
                    previsaoTempo.setLuminosidade(new Luminosidade(getNumeroAleatorioComFaixa(60, 90)));
            }
        }
        return previsaoTempo;
    }

    public EstadoAmbiente getNovoAmbienteExterno() {
        ambienteExterno = new EstadoAmbiente();

        Temperatura temperatura = new Temperatura(previsaoTempo.getTemperatura().toInteger()
                + getNumeroAleatorioComFaixa(-VARIACAO_TEMPERATURA_PREVISAO_TEMPO, VARIACAO_TEMPERATURA_PREVISAO_TEMPO));
        ambienteExterno.setTemperatura(temperatura);

        Umidade umidade = new Umidade(previsaoTempo.getUmidade().toInteger()
                + getNumeroAleatorioComFaixa(-VARIACAO_UMIDADE_PREVISAO_TEMPO, VARIACAO_UMIDADE_PREVISAO_TEMPO));
        ambienteExterno.setUmidade(umidade);

        Luminosidade luminosidade = new Luminosidade(previsaoTempo.getLuminosidade().toInteger()
                + getNumeroAleatorioComFaixa(-VARIACAO_LUMINOSIDADE_PREVISAO_TEMPO, VARIACAO_LUMINOSIDADE_PREVISAO_TEMPO));
        ambienteExterno.setLuminosidade(luminosidade);

        return ambienteExterno;
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

    /**
     * @return the condicaoTempo
     */
    public CondicaoTempo getCondicaoTempo() {
        if (condicaoTempo == null) {
            int valorAleatorio = random.nextInt(CondicaoTempo.values().length);
            condicaoTempo = CondicaoTempo.fromInt(valorAleatorio);
        }
        return condicaoTempo;
    }


    private void agendaAlteracaoTemperaturaAmbienteExterno() {
        agendadorTarefas.alterarEstado(new Agendavel() {

            @Override
            public int getVariacaoPorNivel() {
                return 1;
            }

            @Override
            public int getIncrementoPorCiclo() {
                return 1;                
            }

            @Override
            public int getValor() {
                return IntegrateSysLibraryLoader.getLibrary().getTemperatura();
            }

            @Override
            public void setValor(int valor) {
                IntegrateSysLibraryLoader.getLibrary().setTemperatura(valor);
            }
        }, previsaoTempo.getTemperatura().toInteger() - ambienteExterno.getTemperatura().toInteger());        
    }
    
    private void agendaAlteracaoUmidadeAmbienteExterno() {
        agendadorTarefas.alterarEstado(new Agendavel() {

            @Override
            public int getVariacaoPorNivel() {
                return 1;
            }

            @Override
            public int getIncrementoPorCiclo() {
                return 1;                
            }

            @Override
            public int getValor() {
                return IntegrateSysLibraryLoader.getLibrary().getUmidade();
            }

            @Override
            public void setValor(int valor) {
                IntegrateSysLibraryLoader.getLibrary().setUmidade(valor);
            }
        }, previsaoTempo.getUmidade().toInteger() - ambienteExterno.getUmidade().toInteger());        
    }
    
    private void agendaAlteracaoLuminosidadeAmbienteExterno() {
        agendadorTarefas.alterarEstado(new Agendavel() {

            @Override
            public int getVariacaoPorNivel() {
                return 1;
            }

            @Override
            public int getIncrementoPorCiclo() {
                return 1;                
            }

            @Override
            public int getValor() {
                return IntegrateSysLibraryLoader.getLibrary().getLuminosidade();
            }

            @Override
            public void setValor(int valor) {
                IntegrateSysLibraryLoader.getLibrary().setLuminosidade(valor);
            }
        }, previsaoTempo.getLuminosidade().toInteger() - ambienteExterno.getLuminosidade().toInteger());        
    }            
    
    public void atualizarAmbienteExterno() {
        agendaAlteracaoTemperaturaAmbienteExterno();
        agendaAlteracaoUmidadeAmbienteExterno();
        agendaAlteracaoLuminosidadeAmbienteExterno();
    }

}
