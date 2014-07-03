/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.utils;

import br.unesp.rc.integratesys.ambiente.Parametros;
import br.unesp.rc.integratesys.library.IntegrateSysLibraryLoader;
import java.util.Random;

/**
 *
 * @author Helton
 */
public class SimuladorCondicoesMeteorologicas {

    private boolean ligado;
    private final AgendadorTarefas agendadorTarefas;
    private final Parametros parametros;
    private final Random random;
    private PrevisaoTempo previsaoTempo;

    public SimuladorCondicoesMeteorologicas(AgendadorTarefas agendadorTarefas, Parametros parametros) {
        this.ligado = false;
        this.agendadorTarefas = agendadorTarefas;
        this.parametros = parametros;
        this.random = new Random();
    }

    private CondicaoTempo gerarCondicaoTempo() {
        int valorAleatorio = random.nextInt(CondicaoTempo.values().length);
        return CondicaoTempo.fromInt(valorAleatorio);
    }

    public int getNumeroAleatorioComFaixa(int minimo, int maximo) {
        return random.nextInt(maximo - minimo + 1) + minimo;
    }

    public PrevisaoTempo getPrevisaoTempo() {
        if (previsaoTempo == null) {
            previsaoTempo = new PrevisaoTempo(gerarCondicaoTempo());
            switch (previsaoTempo.getCondicaoTempo()) {
                case DIA_CHUVOSO:
                    previsaoTempo.setTemperatura(getNumeroAleatorioComFaixa(10, 20));
                    previsaoTempo.setUmidade(getNumeroAleatorioComFaixa(70, 90));
                    previsaoTempo.setLuminosidade(getNumeroAleatorioComFaixa(10, 40));
                case DIA_NUBLADO:
                    previsaoTempo.setTemperatura(getNumeroAleatorioComFaixa(10, 20));
                    previsaoTempo.setUmidade(getNumeroAleatorioComFaixa(50, 65));
                    previsaoTempo.setLuminosidade(getNumeroAleatorioComFaixa(10, 40));
                case DIA_ENSOLARADO:
                    previsaoTempo.setTemperatura(getNumeroAleatorioComFaixa(25, 35));
                    previsaoTempo.setUmidade(getNumeroAleatorioComFaixa(40, 60));
                    previsaoTempo.setLuminosidade(getNumeroAleatorioComFaixa(60, 90));
                
                System.out.println("Temperatura = " + Integer.toString(previsaoTempo.getTemperatura()) + " ºC");
                System.out.println("Umidade = " + Integer.toString(previsaoTempo.getUmidade()) + " %");
                System.out.println("Luminosidade = " + Integer.toString(previsaoTempo.getLuminosidade()) + " %");                                        
            }
        }
        return previsaoTempo;
    }
    
   /* private void agendarAlteracao(final int ciclo, final int incremento) {
        agendadorTarefas.agendarTarefa(new Tarefa() {
            @Override
            public void executar() {
                setValor(getValorSensor() + incremento);
            }
        }, ciclo);
    }        
    
    private void alterarEstado(int variacao) {
        List<Integer> incrementos = DistribuidorValores.distribuir(variacao, getIncrementoPorCiclo());
        for (int ciclo = 0; ciclo < incrementos.size(); ciclo++) {
            agendarAlteracao(ciclo, incrementos.get(ciclo));            
        }
    }  */     

    public void simular() {
        if (isLigado()) {
            agendadorTarefas.agendarTarefa(new Tarefa() {
                @Override
                public void executar() {
                    int novaVariacaoTemperatura = parametros.getTemperaturaInicial() - getPrevisaoTempo().getTemperatura();
                    System.out.println("Variação da temperatura = " + Integer.toString(novaVariacaoTemperatura));
                    IntegrateSysLibraryLoader.getLibrary().setTemperatura(IntegrateSysLibraryLoader.getLibrary().getTemperatura() + novaVariacaoTemperatura);
                    
                    int novaVariacaoUmidade = parametros.getUmidadeInicial() - getPrevisaoTempo().getTemperatura();
                    System.out.println("Variação da umidade = " + Integer.toString(novaVariacaoUmidade));
                    IntegrateSysLibraryLoader.getLibrary().setUmidade(IntegrateSysLibraryLoader.getLibrary().getUmidade() + novaVariacaoUmidade);

                    int novaVariacaoLuminosidade = parametros.getLuminosidadeInicial() - getPrevisaoTempo().getTemperatura();
                    System.out.println("Variação da luminosidade = " + Integer.toString(novaVariacaoLuminosidade));                    
                    IntegrateSysLibraryLoader.getLibrary().setLuminosidade(IntegrateSysLibraryLoader.getLibrary().getLuminosidade() + novaVariacaoLuminosidade);
                }
            }, 2);
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