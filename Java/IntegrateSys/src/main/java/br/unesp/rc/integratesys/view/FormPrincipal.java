/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.view;

import br.unesp.rc.integratesys.ambiente.Ambiente;
import br.unesp.rc.integratesys.atuadores.Nivel;
import br.unesp.rc.integratesys.simulacao.CondicaoTempo;
import br.unesp.rc.integratesys.simulacao.EstadoAmbiente;
import br.unesp.rc.integratesys.simulacao.ExecutorTarefas;
import br.unesp.rc.integratesys.simulacao.SimuladorCondicoesMeteorologicas;
import br.unesp.rc.integratesys.simulacao.Tarefa;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Helton
 */
public class FormPrincipal extends FormBase {

    private final Ambiente ambiente;
    private final ExecutorTarefas atualizadorAmbienteInterno;
    private final ExecutorTarefas atualizadorAmbienteExterno;
    private final ExecutorTarefas atualizadorTempoDecorrido;

    private static final int INTERVALO_ATUALIZACAO_AMBIENTE_INTERNO = 3;
    private static final int INTERVALO_ATUALIZACAO_AMBIENTE_EXTERNO = 12;
    
    private static final int TOLERANCIA_VARIACAO_TEMPERATURA = 3;
    private static final int TOLERANCIA_VARIACAO_UMIDADE = 8;    
    private static final int TOLERANCIA_VARIACAO_LUMINOSIDADE = 8;

    private void configurarSliders() {
        Hashtable<Integer, Component> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("desligado"));
        labelTable.put(1, new JLabel("baixo"));
        labelTable.put(2, new JLabel("médio"));
        labelTable.put(3, new JLabel("alto"));

        sliUmidificador.setLabelTable(labelTable);
        sliUmidificador.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ambiente.getAtuadores().getUmidificador().setNivel(Nivel.fromInt(((JSlider) e.getSource()).getValue()));
            }
        });

        sliLampada.setLabelTable(labelTable);
        sliLampada.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ambiente.getAtuadores().getLampada().setNivel(Nivel.fromInt(((JSlider) e.getSource()).getValue()));
            }
        });

        sliVentilador.setLabelTable(labelTable);
        sliVentilador.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ambiente.getAtuadores().getVentilador().setNivel(Nivel.fromInt(((JSlider) e.getSource()).getValue()));
            }
        });

        sliAquecedor.setLabelTable(labelTable);
        sliAquecedor.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ambiente.getAtuadores().getAquecedor().setNivel(Nivel.fromInt(((JSlider) e.getSource()).getValue()));
            }
        });
    }

    private void definirImagemInicialAtuadores() {
        lblAquecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/aquecedor.png")));
        lblVentilador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/ventilador.png")));
        lblUmidificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/umidificador.png")));
        lblLampada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/lampada.png")));
    }
    
    private void definirImagemInicialSensores() {
        lblSensorTemperatura.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/temperatura.png")));
        lblSensorUmidade.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/umidade.png")));
        lblSensorLuminosidade.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/luminosidade.png")));        
    }

    private void atualizarPrevisaoTempo() {
        SimuladorCondicoesMeteorologicas simulador = ambiente.getSimuladorCondicoesMeteorologicas();
        EstadoAmbiente previsaoTempo = simulador.getPrevisaoTempo();
        CondicaoTempo condicaoTempo = simulador.getCondicaoTempo();
        lblPrevisaoTempoImagem.setIcon(condicaoTempo.getImagem());
        lblPrevisaoTempoTemperatura.setText(previsaoTempo.getTemperatura().toString());
        lblPrevisaoTempoUmidade.setText(previsaoTempo.getUmidade().toString());
        lblPrevisaoTempoLuminosidade.setText(previsaoTempo.getLuminosidade().toString());
    }

    private void atualizarAmbienteExterno() {
        SimuladorCondicoesMeteorologicas simulador = ambiente.getSimuladorCondicoesMeteorologicas();
        EstadoAmbiente ambienteExterno = simulador.getNovoAmbienteExterno();
        lblAmbienteExternoTemperatura.setText(ambienteExterno.getTemperatura().toString());
        lblAmbienteExternoUmidade.setText(ambienteExterno.getUmidade().toString());
        lblAmbienteExternoLuminosidade.setText(ambienteExterno.getLuminosidade().toString());
    }

    private void atualizarAmbienteInterno() {
        EstadoAmbiente ambienteInterno = ambiente.getAmbienteInterno();
        lblAmbienteInternoTemperatura.setText(ambienteInterno.getTemperatura().toString());
        lblAmbienteInternoUmidade.setText(ambienteInterno.getUmidade().toString());
        lblAmbienteInternoLuminosidade.setText(ambienteInterno.getLuminosidade().toString());
        definirImagemAtuadores();
        atualizarStatusAmbienteInterno();
    }
    
    private void atualizarStatusAmbienteInterno() {
        int temperaturaAtual = ambiente.getSensores().getSensorTemperatura().getTemperatura();
        int temperaturaIdeal = ambiente.getParametros().getTemperaturaIdeal();
        int temperaturaMinima = ambiente.getParametros().getTemperaturaMinima();
        int temperaturaMaxima = ambiente.getParametros().getTemperaturaMinima();        
        
        if (Math.abs(temperaturaAtual - temperaturaIdeal) <= TOLERANCIA_VARIACAO_TEMPERATURA) {
            lblSensorTemperatura.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/temperatura.png")));
            lblSensorTemperatura.setText("temperatura ideal");
        }
        else if (temperaturaAtual < temperaturaMinima) {
            lblSensorTemperatura.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/temperatura_baixa.png")));
            lblSensorTemperatura.setText("temperatura baixa");            
        }
        else if (temperaturaAtual > temperaturaMaxima) {
            lblSensorTemperatura.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/temperatura_alta.png")));
            lblSensorTemperatura.setText("temperatura alta");            
        }
        
        int umidadeAtual = ambiente.getSensores().getSensorUmidade().getUmidade();
        int umidadeIdeal = ambiente.getParametros().getUmidadeIdeal();
        int umidadeMinima = ambiente.getParametros().getUmidadeMinima();
        int umidadeMaxima = ambiente.getParametros().getUmidadeMinima();        
        
        if (Math.abs(umidadeAtual - umidadeIdeal) <= TOLERANCIA_VARIACAO_UMIDADE) {
            lblSensorUmidade.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/umidade.png")));
            lblSensorUmidade.setText("umidade ideal");
        }
        else if (umidadeAtual < umidadeMinima) {
            lblSensorUmidade.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/umidade_baixa.png")));
            lblSensorUmidade.setText("umidade baixa");
        }
        else if (umidadeAtual > umidadeMaxima) {
            lblSensorUmidade.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/umidade_alta.png")));
            lblSensorUmidade.setText("umidade alta");            
        }
        
        int luminosidadeAtual = ambiente.getSensores().getSensorLuminosidade().getLuminosidade();
        int luminosidadeIdeal = ambiente.getParametros().getLuminosidadeIdeal();
        int luminosidadeMinima = ambiente.getParametros().getLuminosidadeMinima();
        int luminosidadeMaxima = ambiente.getParametros().getLuminosidadeMinima();        
        
        if (Math.abs(luminosidadeAtual - luminosidadeIdeal) <= TOLERANCIA_VARIACAO_LUMINOSIDADE) {
            lblSensorLuminosidade.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/luminosidade.png")));
            lblSensorLuminosidade.setText("luminosidade ideal");
        }
        else if (luminosidadeAtual < luminosidadeMinima) {
            lblSensorLuminosidade.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/luminosidade_baixa.png")));
            lblSensorLuminosidade.setText("luminosidade baixa");
        }
        else if (luminosidadeAtual > luminosidadeMaxima) {
            lblSensorLuminosidade.setIcon(new ImageIcon(getClass().getResource("/imagens/sensores/luminosidade_alta.png")));
            lblSensorLuminosidade.setText("luminosidade alta");
        }         
    }

    private void definirImagemAtuadores() {
        if (ambiente.getAtuadores().getVentilador().getNivel() == Nivel.DESLIGADO) {
            lblVentilador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/ventilador.png")));
            sliAquecedor.setEnabled(true);
        } else {
            lblVentilador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/ventilador_ligado.png")));
            sliAquecedor.setEnabled(false);
        }

        if (ambiente.getAtuadores().getAquecedor().getNivel() == Nivel.DESLIGADO) {
            lblAquecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/aquecedor.png")));
            sliVentilador.setEnabled(true);
        } else {
            lblAquecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/aquecedor_ligado.png")));
            sliVentilador.setEnabled(false);
        }

        //resolvendo deadlock
        if (!sliVentilador.isEnabled() && !sliAquecedor.isEnabled()) {
            sliVentilador.setEnabled(true);
        }

        if (ambiente.getAtuadores().getUmidificador().getNivel() == Nivel.DESLIGADO) {
            lblUmidificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/umidificador.png")));
        } else {
            lblUmidificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/umidificador_ligado.png")));
        }

        if (ambiente.getAtuadores().getLampada().getNivel() == Nivel.DESLIGADO) {
            lblLampada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/lampada.png")));
        } else {
            lblLampada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/lampada_ligada.png")));
        }

    }

    /**
     * Creates new form FormPrincipal
     */
    public FormPrincipal() {
        initComponents();
        configurarSliders();
        definirImagemInicialAtuadores();
        definirImagemInicialSensores();
        ambiente = new Ambiente();

        atualizadorTempoDecorrido = new ExecutorTarefas(1, new Tarefa() {
            private String formatarTempoDecorrido(long diferencaEmMilisegundos) {

                long segundosEmMilisegundos = 1000;
                long minutosEmMilisegundos = segundosEmMilisegundos * 60;
                long horasEmMilisegundos = minutosEmMilisegundos * 60;

                long horasDecorridas = diferencaEmMilisegundos / horasEmMilisegundos;
                diferencaEmMilisegundos = diferencaEmMilisegundos % horasEmMilisegundos;

                long minutosDecorridos = diferencaEmMilisegundos / minutosEmMilisegundos;
                diferencaEmMilisegundos = diferencaEmMilisegundos % minutosEmMilisegundos;

                long segundosDecorridos = diferencaEmMilisegundos / segundosEmMilisegundos;

                return String.format("%02d:%02d:%02d",
                        horasDecorridas, minutosDecorridos, segundosDecorridos);

            }

            @Override
            public void executar() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        lblTempoAtual.setText(formatarTempoDecorrido(ambiente.getTempoDecorridoSimulacaoAtual()));
                        lblTempoAcumulado.setText(formatarTempoDecorrido(ambiente.getTempoTotalSimulacaoDecorrido()));
                    }
                });
            }

        });

        atualizadorAmbienteExterno = new ExecutorTarefas(INTERVALO_ATUALIZACAO_AMBIENTE_EXTERNO, new Tarefa() {
            @Override
            public void executar() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        atualizarAmbienteExterno();
                        ambiente.atualizarAmbienteExterno();
                    }
                });
            }
        });

        atualizadorAmbienteInterno = new ExecutorTarefas(INTERVALO_ATUALIZACAO_AMBIENTE_INTERNO, new Tarefa() {
            @Override
            public void executar() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        atualizarAmbienteInterno();
                        ambiente.atualizarAmbienteInterno();
                    }
                });
            }
        });
        btnIniciarSimulacao.setIcon(new ImageIcon(getClass().getResource("/imagens/simulacao/iniciar.png")));
        btnPausarSimulacao.setIcon(new ImageIcon(getClass().getResource("/imagens/simulacao/pausar.png")));        
        btnParametros.setIcon(new ImageIcon(getClass().getResource("/imagens/simulacao/configuracoes.png")));        
    }

    private void iniciarSimulacao() {
        btnIniciarSimulacao.setEnabled(false);
        btnPausarSimulacao.setEnabled(true);
        btnParametros.setEnabled(false);

        sliAquecedor.setEnabled(true);
        sliVentilador.setEnabled(true);
        sliUmidificador.setEnabled(true);
        sliLampada.setEnabled(true);

        atualizarPrevisaoTempo();
        ambiente.iniciarSimulacao();
        atualizadorTempoDecorrido.iniciar();
        atualizadorAmbienteExterno.iniciar();
        atualizadorAmbienteInterno.iniciar();
    }

    private void pausarSimulacao() {
        btnIniciarSimulacao.setEnabled(true);
        btnPausarSimulacao.setEnabled(false);
        btnParametros.setEnabled(true);

        sliAquecedor.setEnabled(false);
        sliVentilador.setEnabled(false);
        sliUmidificador.setEnabled(false);
        sliLampada.setEnabled(false);

        ambiente.pausarSimulacao();
        atualizadorTempoDecorrido.pausar();
        atualizadorAmbienteExterno.pausar();
        atualizadorAmbienteInterno.pausar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrevisaoTempo = new javax.swing.JPanel();
        lblPrevisaoTempoTituloUmidade = new javax.swing.JLabel();
        lblPrevisaoTempoTituloTemperatura = new javax.swing.JLabel();
        lblPrevisaoTempoTituloLuminosidade = new javax.swing.JLabel();
        lblPrevisaoTempoImagem = new javax.swing.JLabel();
        lblPrevisaoTempoTemperatura = new javax.swing.JLabel();
        lblPrevisaoTempoUmidade = new javax.swing.JLabel();
        lblPrevisaoTempoLuminosidade = new javax.swing.JLabel();
        pnlAmbienteInterno = new javax.swing.JPanel();
        lblAmbienteInternoTituloUmidade = new javax.swing.JLabel();
        lblAmbienteInternoTituloTemperatura = new javax.swing.JLabel();
        lblAmbienteInternoTituloLuminosidade = new javax.swing.JLabel();
        lblAmbienteInternoTemperatura = new javax.swing.JLabel();
        lblAmbienteInternoUmidade = new javax.swing.JLabel();
        lblAmbienteInternoLuminosidade = new javax.swing.JLabel();
        pnlAmbienteExterno = new javax.swing.JPanel();
        lblAmbienteExternoTituloUmidade = new javax.swing.JLabel();
        lblAmbienteExternoTituloTemperatura = new javax.swing.JLabel();
        lblAmbienteExternoTituloLuminosidade = new javax.swing.JLabel();
        lblAmbienteExternoTemperatura = new javax.swing.JLabel();
        lblAmbienteExternoUmidade = new javax.swing.JLabel();
        lblAmbienteExternoLuminosidade = new javax.swing.JLabel();
        sliUmidificador = new javax.swing.JSlider();
        sliLampada = new javax.swing.JSlider();
        sliAquecedor = new javax.swing.JSlider();
        sliVentilador = new javax.swing.JSlider();
        pnlControleSimulacao = new javax.swing.JPanel();
        lblTituloTempoAcumulado = new javax.swing.JLabel();
        lblTempoAcumulado = new javax.swing.JLabel();
        lblTituloTempoAtual = new javax.swing.JLabel();
        lblTempoAtual = new javax.swing.JLabel();
        btnIniciarSimulacao = new javax.swing.JButton();
        btnPausarSimulacao = new javax.swing.JButton();
        btnParametros = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IntegrateSys 1.0");

        pnlPrevisaoTempo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Previsão do tempo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        lblPrevisaoTempoTituloUmidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblPrevisaoTempoTituloUmidade.setText("Umidade:");

        lblPrevisaoTempoTituloTemperatura.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblPrevisaoTempoTituloTemperatura.setText("Temperatura:");

        lblPrevisaoTempoTituloLuminosidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblPrevisaoTempoTituloLuminosidade.setText("Luminosidade:");

        lblPrevisaoTempoTemperatura.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblPrevisaoTempoTemperatura.setText("0º C");

        lblPrevisaoTempoUmidade.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblPrevisaoTempoUmidade.setText("0 %");

        lblPrevisaoTempoLuminosidade.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblPrevisaoTempoLuminosidade.setText("0 %");

        javax.swing.GroupLayout pnlPrevisaoTempoLayout = new javax.swing.GroupLayout(pnlPrevisaoTempo);
        pnlPrevisaoTempo.setLayout(pnlPrevisaoTempoLayout);
        pnlPrevisaoTempoLayout.setHorizontalGroup(
            pnlPrevisaoTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrevisaoTempoLayout.createSequentialGroup()
                .addGroup(pnlPrevisaoTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrevisaoTempoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPrevisaoTempoImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(pnlPrevisaoTempoLayout.createSequentialGroup()
                        .addGroup(pnlPrevisaoTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrevisaoTempoTituloUmidade)
                            .addComponent(lblPrevisaoTempoTituloTemperatura)
                            .addComponent(lblPrevisaoTempoTituloLuminosidade))
                        .addGap(29, 29, 29)
                        .addGroup(pnlPrevisaoTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrevisaoTempoLuminosidade, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(lblPrevisaoTempoTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPrevisaoTempoUmidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlPrevisaoTempoLayout.setVerticalGroup(
            pnlPrevisaoTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrevisaoTempoLayout.createSequentialGroup()
                .addGroup(pnlPrevisaoTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrevisaoTempoTemperatura, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPrevisaoTempoTituloTemperatura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrevisaoTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrevisaoTempoTituloUmidade)
                    .addComponent(lblPrevisaoTempoUmidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrevisaoTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrevisaoTempoTituloLuminosidade)
                    .addComponent(lblPrevisaoTempoLuminosidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrevisaoTempoImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlAmbienteInterno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ambiente da granja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        lblAmbienteInternoTituloUmidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAmbienteInternoTituloUmidade.setText("Umidade:");

        lblAmbienteInternoTituloTemperatura.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAmbienteInternoTituloTemperatura.setText("Temperatura:");

        lblAmbienteInternoTituloLuminosidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAmbienteInternoTituloLuminosidade.setText("Luminosidade:");

        lblAmbienteInternoTemperatura.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAmbienteInternoTemperatura.setText("0º C");

        lblAmbienteInternoUmidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAmbienteInternoUmidade.setText("0 %");

        lblAmbienteInternoLuminosidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAmbienteInternoLuminosidade.setText("0 %");

        javax.swing.GroupLayout pnlAmbienteInternoLayout = new javax.swing.GroupLayout(pnlAmbienteInterno);
        pnlAmbienteInterno.setLayout(pnlAmbienteInternoLayout);
        pnlAmbienteInternoLayout.setHorizontalGroup(
            pnlAmbienteInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAmbienteInternoLayout.createSequentialGroup()
                .addGroup(pnlAmbienteInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAmbienteInternoTituloUmidade)
                    .addComponent(lblAmbienteInternoTituloTemperatura)
                    .addComponent(lblAmbienteInternoTituloLuminosidade))
                .addGap(29, 29, 29)
                .addGroup(pnlAmbienteInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAmbienteInternoLuminosidade, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(lblAmbienteInternoTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAmbienteInternoUmidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAmbienteInternoLayout.setVerticalGroup(
            pnlAmbienteInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAmbienteInternoLayout.createSequentialGroup()
                .addGroup(pnlAmbienteInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAmbienteInternoTemperatura, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAmbienteInternoTituloTemperatura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAmbienteInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmbienteInternoTituloUmidade)
                    .addComponent(lblAmbienteInternoUmidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAmbienteInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmbienteInternoTituloLuminosidade)
                    .addComponent(lblAmbienteInternoLuminosidade)))
        );

        pnlAmbienteExterno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ambiente externo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        lblAmbienteExternoTituloUmidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAmbienteExternoTituloUmidade.setText("Umidade:");

        lblAmbienteExternoTituloTemperatura.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAmbienteExternoTituloTemperatura.setText("Temperatura:");

        lblAmbienteExternoTituloLuminosidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAmbienteExternoTituloLuminosidade.setText("Luminosidade:");

        lblAmbienteExternoTemperatura.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblAmbienteExternoTemperatura.setText("0º C");

        lblAmbienteExternoUmidade.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblAmbienteExternoUmidade.setText("0 %");

        lblAmbienteExternoLuminosidade.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblAmbienteExternoLuminosidade.setText("0 %");

        javax.swing.GroupLayout pnlAmbienteExternoLayout = new javax.swing.GroupLayout(pnlAmbienteExterno);
        pnlAmbienteExterno.setLayout(pnlAmbienteExternoLayout);
        pnlAmbienteExternoLayout.setHorizontalGroup(
            pnlAmbienteExternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAmbienteExternoLayout.createSequentialGroup()
                .addGroup(pnlAmbienteExternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAmbienteExternoTituloUmidade)
                    .addComponent(lblAmbienteExternoTituloTemperatura)
                    .addComponent(lblAmbienteExternoTituloLuminosidade))
                .addGap(29, 29, 29)
                .addGroup(pnlAmbienteExternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAmbienteExternoLuminosidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAmbienteExternoTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(lblAmbienteExternoUmidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAmbienteExternoLayout.setVerticalGroup(
            pnlAmbienteExternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAmbienteExternoLayout.createSequentialGroup()
                .addGroup(pnlAmbienteExternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAmbienteExternoTemperatura, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAmbienteExternoTituloTemperatura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAmbienteExternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmbienteExternoTituloUmidade)
                    .addComponent(lblAmbienteExternoUmidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAmbienteExternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmbienteExternoTituloLuminosidade)
                    .addComponent(lblAmbienteExternoLuminosidade)))
        );

        pnlAtuadorUmidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atuadores (granja)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        lblUmidificador.setText("umidificador");
        lblUmidificador.setBorder(new javax.swing.border.MatteBorder(null));
        lblUmidificador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUmidificador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliUmidificador.setMaximum(3);
        sliUmidificador.setPaintLabels(true);
        sliUmidificador.setSnapToTicks(true);
        sliUmidificador.setValue(0);
        sliUmidificador.setEnabled(false);

        lblLampada.setText("lâmpada");
        lblLampada.setBorder(new javax.swing.border.MatteBorder(null));
        lblLampada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLampada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliLampada.setMaximum(3);
        sliLampada.setPaintLabels(true);
        sliLampada.setSnapToTicks(true);
        sliLampada.setValue(0);
        sliLampada.setEnabled(false);

        lblAquecedor.setText("aquecedor");
        lblAquecedor.setBorder(new javax.swing.border.MatteBorder(null));
        lblAquecedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAquecedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliAquecedor.setMaximum(3);
        sliAquecedor.setPaintLabels(true);
        sliAquecedor.setSnapToTicks(true);
        sliAquecedor.setValue(0);
        sliAquecedor.setEnabled(false);

        lblVentilador.setText("ventilador");
        lblVentilador.setBorder(new javax.swing.border.MatteBorder(null));
        lblVentilador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblVentilador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliVentilador.setMaximum(3);
        sliVentilador.setPaintLabels(true);
        sliVentilador.setSnapToTicks(true);
        sliVentilador.setValue(0);
        sliVentilador.setEnabled(false);

        javax.swing.GroupLayout pnlAtuadorUmidadeLayout = new javax.swing.GroupLayout(pnlAtuadorUmidade);
        pnlAtuadorUmidade.setLayout(pnlAtuadorUmidadeLayout);
        pnlAtuadorUmidadeLayout.setHorizontalGroup(
            pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                .addGroup(pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblAquecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(lblVentilador, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                        .addComponent(sliAquecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sliVentilador, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(sliUmidificador, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sliLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(lblUmidificador, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(lblLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAtuadorUmidadeLayout.setVerticalGroup(
            pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAtuadorUmidadeLayout.createSequentialGroup()
                .addGroup(pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUmidificador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliUmidificador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAtuadorUmidadeLayout.createSequentialGroup()
                .addGroup(pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAquecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVentilador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliAquecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliVentilador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlControleSimulacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Controle de simulação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        lblTituloTempoAcumulado.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTituloTempoAcumulado.setText("Tempo acumulado:");

        lblTempoAcumulado.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblTempoAcumulado.setText("00:00:00");

        lblTituloTempoAtual.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTituloTempoAtual.setText("Tempo atual:");

        lblTempoAtual.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblTempoAtual.setText("00:00:00");

        btnIniciarSimulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSimulacaoActionPerformed(evt);
            }
        });

        btnPausarSimulacao.setEnabled(false);
        btnPausarSimulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausarSimulacaoActionPerformed(evt);
            }
        });

        btnParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParametrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControleSimulacaoLayout = new javax.swing.GroupLayout(pnlControleSimulacao);
        pnlControleSimulacao.setLayout(pnlControleSimulacaoLayout);
        pnlControleSimulacaoLayout.setHorizontalGroup(
            pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControleSimulacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlControleSimulacaoLayout.createSequentialGroup()
                            .addComponent(lblTituloTempoAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(27, 27, 27)
                            .addComponent(lblTempoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlControleSimulacaoLayout.createSequentialGroup()
                            .addComponent(lblTituloTempoAcumulado)
                            .addGap(27, 27, 27)
                            .addComponent(lblTempoAcumulado, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlControleSimulacaoLayout.createSequentialGroup()
                        .addComponent(btnParametros, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIniciarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPausarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlControleSimulacaoLayout.setVerticalGroup(
            pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControleSimulacaoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnIniciarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPausarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnParametros, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTituloTempoAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTempoAtual))
                .addGap(3, 3, 3)
                .addGroup(pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTituloTempoAcumulado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTempoAcumulado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sensores (granja)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblSensorLuminosidade.setText("sensor de luminosidade");
        lblSensorLuminosidade.setBorder(new javax.swing.border.MatteBorder(null));
        lblSensorLuminosidade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSensorLuminosidade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblSensorTemperatura.setText("sensor de temperatura");
        lblSensorTemperatura.setToolTipText("");
        lblSensorTemperatura.setBorder(new javax.swing.border.MatteBorder(null));
        lblSensorTemperatura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSensorTemperatura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblSensorUmidade.setText("sensor de umidade");
        lblSensorUmidade.setBorder(new javax.swing.border.MatteBorder(null));
        lblSensorUmidade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSensorUmidade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblSensorTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSensorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSensorLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSensorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSensorTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSensorLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPrevisaoTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAmbienteExterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAmbienteInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlControleSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlAtuadorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPrevisaoTempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlAmbienteExterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlAmbienteInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlControleSimulacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAtuadorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSimulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSimulacaoActionPerformed
        iniciarSimulacao();
    }//GEN-LAST:event_btnIniciarSimulacaoActionPerformed

    private void btnPausarSimulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausarSimulacaoActionPerformed
        pausarSimulacao();
    }//GEN-LAST:event_btnPausarSimulacaoActionPerformed

    private void btnParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametrosActionPerformed
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormParametros(ambiente.getParametros()).exibir();
            }
        });
    }//GEN-LAST:event_btnParametrosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSimulacao;
    private javax.swing.JButton btnParametros;
    private javax.swing.JButton btnPausarSimulacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAmbienteExternoLuminosidade;
    private javax.swing.JLabel lblAmbienteExternoTemperatura;
    private javax.swing.JLabel lblAmbienteExternoTituloLuminosidade;
    private javax.swing.JLabel lblAmbienteExternoTituloTemperatura;
    private javax.swing.JLabel lblAmbienteExternoTituloUmidade;
    private javax.swing.JLabel lblAmbienteExternoUmidade;
    private javax.swing.JLabel lblAmbienteInternoLuminosidade;
    private javax.swing.JLabel lblAmbienteInternoTemperatura;
    private javax.swing.JLabel lblAmbienteInternoTituloLuminosidade;
    private javax.swing.JLabel lblAmbienteInternoTituloTemperatura;
    private javax.swing.JLabel lblAmbienteInternoTituloUmidade;
    private javax.swing.JLabel lblAmbienteInternoUmidade;
    private final javax.swing.JLabel lblAquecedor = new javax.swing.JLabel();
    private final javax.swing.JLabel lblLampada = new javax.swing.JLabel();
    private javax.swing.JLabel lblPrevisaoTempoImagem;
    private javax.swing.JLabel lblPrevisaoTempoLuminosidade;
    private javax.swing.JLabel lblPrevisaoTempoTemperatura;
    private javax.swing.JLabel lblPrevisaoTempoTituloLuminosidade;
    private javax.swing.JLabel lblPrevisaoTempoTituloTemperatura;
    private javax.swing.JLabel lblPrevisaoTempoTituloUmidade;
    private javax.swing.JLabel lblPrevisaoTempoUmidade;
    private final javax.swing.JLabel lblSensorLuminosidade = new javax.swing.JLabel();
    private final javax.swing.JLabel lblSensorTemperatura = new javax.swing.JLabel();
    private final javax.swing.JLabel lblSensorUmidade = new javax.swing.JLabel();
    private javax.swing.JLabel lblTempoAcumulado;
    private javax.swing.JLabel lblTempoAtual;
    private javax.swing.JLabel lblTituloTempoAcumulado;
    private javax.swing.JLabel lblTituloTempoAtual;
    private final javax.swing.JLabel lblUmidificador = new javax.swing.JLabel();
    private final javax.swing.JLabel lblVentilador = new javax.swing.JLabel();
    private javax.swing.JPanel pnlAmbienteExterno;
    private javax.swing.JPanel pnlAmbienteInterno;
    private final javax.swing.JPanel pnlAtuadorUmidade = new javax.swing.JPanel();
    private javax.swing.JPanel pnlControleSimulacao;
    private javax.swing.JPanel pnlPrevisaoTempo;
    private javax.swing.JSlider sliAquecedor;
    private javax.swing.JSlider sliLampada;
    private javax.swing.JSlider sliUmidificador;
    private javax.swing.JSlider sliVentilador;
    // End of variables declaration//GEN-END:variables
}
