/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.simulacao;

import br.unesp.rc.integratesys.ambiente.Luminosidade;
import br.unesp.rc.integratesys.ambiente.Parametros;
import br.unesp.rc.integratesys.ambiente.Temperatura;
import br.unesp.rc.integratesys.ambiente.Umidade;
import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import br.unesp.rc.integratesys.sensores.Sensores;
import java.util.Random;

/**
 *
 * @author Helton
 */
public class SimuladorCondicoesMeteorologicas {

    private final AgendadorTarefas agendadorTarefas;
    private final Sensores sensores;    
    private final Parametros parametros;
    private EstadoAmbiente previsaoTempo;
    private EstadoAmbiente ambienteExterno;
    private CondicaoTempo condicaoTempo;
    private boolean ligado;
    private final Random random;

    private static final int VARIACAO_TEMPERATURA_PREVISAO_TEMPO = 1;
    private static final int VARIACAO_UMIDADE_PREVISAO_TEMPO = 2;
    private static final int VARIACAO_LUMINOSIDADE_PREVISAO_TEMPO = 2;
    
    private final int TEMPERATURA_LIMITE_MAXIMA = 100;
    private final int TEMPERATURA_LIMITE_MINIMA = 0;
    private final int UMIDADE_LIMITE_MAXIMA = 100;
    private final int UMIDADE_LIMITE_MINIMA = 0;    
    private final int LUMINOSIDADE_LIMITE_MAXIMA = 100;
    private final int LUMINOSIDADE_LIMITE_MINIMA = 0;           

    public SimuladorCondicoesMeteorologicas(Sensores sensores, AgendadorTarefas agendadorTarefas, Parametros parametros) {
        this.ligado = false;
        this.sensores = sensores;
        this.agendadorTarefas = agendadorTarefas;
        this.parametros = parametros;
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
                    previsaoTempo.setTemperatura(new Temperatura(getNumeroAleatorioComFaixa(18, 20)));
                    previsaoTempo.setUmidade(new Umidade(getNumeroAleatorioComFaixa(50, 60)));
                    previsaoTempo.setLuminosidade(new Luminosidade(getNumeroAleatorioComFaixa(30, 50)));
                    break;
                case DIA_NUBLADO:
                    previsaoTempo.setTemperatura(new Temperatura(getNumeroAleatorioComFaixa(18, 25)));
                    previsaoTempo.setUmidade(new Umidade(getNumeroAleatorioComFaixa(50, 60)));
                    previsaoTempo.setLuminosidade(new Luminosidade(getNumeroAleatorioComFaixa(50, 55)));
                    break;                    
                case DIA_ENSOLARADO:
                    previsaoTempo.setTemperatura(new Temperatura(getNumeroAleatorioComFaixa(20, 25)));
                    previsaoTempo.setUmidade(new Umidade(getNumeroAleatorioComFaixa(20, 40)));
                    previsaoTempo.setLuminosidade(new Luminosidade(getNumeroAleatorioComFaixa(50, 90)));
                    break;                    
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
        int variacaoTemperatura = previsaoTempo.getTemperatura().toInteger() - ambienteExterno.getTemperatura().toInteger();
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
                return sensores.getSensorTemperatura().getTemperatura();
            }

            @Override
            public void setValor(int valor) {
                IntegrateSysLibraryLoader.getLibrary().setTemperatura(Math.max(Math.min(valor, TEMPERATURA_LIMITE_MAXIMA), TEMPERATURA_LIMITE_MINIMA));
            }
        }, variacaoTemperatura);        
    }
    
    private void agendaAlteracaoUmidadeAmbienteExterno() {
        int variacaoUmidade = previsaoTempo.getUmidade().toInteger() - ambienteExterno.getUmidade().toInteger();
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
                return sensores.getSensorUmidade().getUmidade();
            }

            @Override
            public void setValor(int valor) {
                IntegrateSysLibraryLoader.getLibrary().setUmidade(Math.max(Math.min(valor, UMIDADE_LIMITE_MAXIMA), UMIDADE_LIMITE_MINIMA));
            }
        }, variacaoUmidade);        
    }
    
    private void agendaAlteracaoLuminosidadeAmbienteExterno() {
        int variacaoLuminosidade = previsaoTempo.getLuminosidade().toInteger() - ambienteExterno.getLuminosidade().toInteger();
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
                return sensores.getSensorLuminosidade().getLuminosidade();
            }

            @Override
            public void setValor(int valor) {
                IntegrateSysLibraryLoader.getLibrary().setLuminosidade(Math.max(Math.min(valor, LUMINOSIDADE_LIMITE_MAXIMA), LUMINOSIDADE_LIMITE_MINIMA));                
            }
        }, variacaoLuminosidade);        
    }            
    
    public void atualizarAmbienteExterno() {
        agendaAlteracaoTemperaturaAmbienteExterno();
        agendaAlteracaoUmidadeAmbienteExterno();
        agendaAlteracaoLuminosidadeAmbienteExterno();
    }

}
