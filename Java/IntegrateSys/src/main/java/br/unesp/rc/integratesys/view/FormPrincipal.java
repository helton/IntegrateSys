/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.view;

import br.unesp.rc.integratesys.ambiente.Ambiente;
import br.unesp.rc.integratesys.atuadores.Nivel;
import br.unesp.rc.integratesys.utils.CondicaoTempo;
import br.unesp.rc.integratesys.utils.EstadoAmbiente;
import br.unesp.rc.integratesys.utils.ExecutorTarefas;
import br.unesp.rc.integratesys.utils.SimuladorCondicoesMeteorologicas;
import br.unesp.rc.integratesys.utils.Tarefa;
import java.awt.Component;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JSlider;
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

    private static final int INTERVALO_ATUALIZACAO_AMBIENTE_INTERNO = 3;
    private static final int INTERVALO_ATUALIZACAO_AMBIENTE_EXTERNO = 10;

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

    private void atualizarPrevisaoTempo() {
        SimuladorCondicoesMeteorologicas simulador = ambiente.getControladorSimulacao().getSimuladorCondicoesMeteorologicas();
        EstadoAmbiente previsaoTempo = simulador.getPrevisaoTempo();
        CondicaoTempo condicaoTempo = simulador.getCondicaoTempo();
        lblPrevisaoTempoImagem.setIcon(condicaoTempo.getImagem());
        lblPrevisaoTempoTemperatura.setText(previsaoTempo.getTemperatura().toString());
        lblPrevisaoTempoUmidade.setText(previsaoTempo.getUmidade().toString());
        lblPrevisaoTempoLuminosidade.setText(previsaoTempo.getLuminosidade().toString());
    }

    private void atualizarAmbienteExterno() {
        SimuladorCondicoesMeteorologicas simulador = ambiente.getControladorSimulacao().getSimuladorCondicoesMeteorologicas();
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
    }

    private void definirImagemAtuadores() {
        if (ambiente.getAtuadores().getVentilador().getNivel() == Nivel.DESLIGADO) {
            lblVentilador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/ventilador.png")));
            sliAquecedor.setEnabled(true);
        } 
        else {
            lblVentilador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/ventilador_ligado.png")));
            sliAquecedor.setEnabled(false);
        }

        if (ambiente.getAtuadores().getAquecedor().getNivel() == Nivel.DESLIGADO) {
            lblAquecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/aquecedor.png")));
            sliVentilador.setEnabled(true);
        } 
        else {
            lblAquecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/aquecedor_ligado.png")));
            sliVentilador.setEnabled(false);
        }

        //resolvendo deadlock
        if (!sliVentilador.isEnabled() && !sliAquecedor.isEnabled()) {
            sliVentilador.setEnabled(true);
        }

        if (ambiente.getAtuadores().getUmidificador().getNivel() == Nivel.DESLIGADO) {
            lblUmidificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/umidificador.png")));
        } 
        else {
            lblUmidificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/umidificador_ligado.png")));
        }

        if (ambiente.getAtuadores().getLampada().getNivel() == Nivel.DESLIGADO) {
            lblLampada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/lampada.png")));
        } 
        else {
            lblLampada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/lampada_ligada.png")));
        }

    }

    /**
     * Creates new form FormPrincipal
     */
    public FormPrincipal() {
        initComponents();
        configurarSliders();
        ambiente = new Ambiente(null);
        atualizadorAmbienteExterno = new ExecutorTarefas(INTERVALO_ATUALIZACAO_AMBIENTE_EXTERNO, new Tarefa() {
            @Override
            public void executar() {
                atualizarAmbienteExterno();
                ambiente.atualizarAmbienteExterno();
            }
        });
        atualizadorAmbienteInterno = new ExecutorTarefas(INTERVALO_ATUALIZACAO_AMBIENTE_INTERNO, new Tarefa() {
            @Override
            public void executar() {
                atualizarAmbienteInterno();
                ambiente.atualizarAmbienteInterno();                
            }
        });
    }

    private void iniciarSimulacao() {
        atualizarPrevisaoTempo();
        atualizadorAmbienteExterno.iniciar();
        atualizadorAmbienteInterno.iniciar();
    }

    private void pausarSimulacao() {
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
        btnIniciarSimulacao = new javax.swing.JButton();
        btnPausarSimulacao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IntegrateSys v1.0");

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

        pnlAmbienteInterno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ambiente interno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

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

        pnlAtuadorUmidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Atuadores"));

        lblUmidificador.setText("umidificador");
        lblUmidificador.setBorder(new javax.swing.border.MatteBorder(null));
        lblUmidificador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUmidificador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliUmidificador.setMaximum(3);
        sliUmidificador.setPaintLabels(true);
        sliUmidificador.setSnapToTicks(true);
        sliUmidificador.setValue(0);

        lblLampada.setText("lâmpada");
        lblLampada.setBorder(new javax.swing.border.MatteBorder(null));
        lblLampada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLampada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliLampada.setMaximum(3);
        sliLampada.setPaintLabels(true);
        sliLampada.setSnapToTicks(true);
        sliLampada.setValue(0);

        lblAquecedor.setText("aquecedor");
        lblAquecedor.setBorder(new javax.swing.border.MatteBorder(null));
        lblAquecedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAquecedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliAquecedor.setMaximum(3);
        sliAquecedor.setPaintLabels(true);
        sliAquecedor.setSnapToTicks(true);
        sliAquecedor.setValue(0);

        lblVentilador.setText("ventilador");
        lblVentilador.setBorder(new javax.swing.border.MatteBorder(null));
        lblVentilador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblVentilador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliVentilador.setMaximum(3);
        sliVentilador.setPaintLabels(true);
        sliVentilador.setSnapToTicks(true);
        sliVentilador.setValue(0);

        javax.swing.GroupLayout pnlAtuadorUmidadeLayout = new javax.swing.GroupLayout(pnlAtuadorUmidade);
        pnlAtuadorUmidade.setLayout(pnlAtuadorUmidadeLayout);
        pnlAtuadorUmidadeLayout.setHorizontalGroup(
            pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                .addGroup(pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblAquecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
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
                        .addComponent(sliLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(lblUmidificador, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
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

        btnIniciarSimulacao.setText("Iniciar simulação");
        btnIniciarSimulacao.setToolTipText("");
        btnIniciarSimulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSimulacaoActionPerformed(evt);
            }
        });

        btnPausarSimulacao.setText("Pausar simulação");
        btnPausarSimulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausarSimulacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControleSimulacaoLayout = new javax.swing.GroupLayout(pnlControleSimulacao);
        pnlControleSimulacao.setLayout(pnlControleSimulacaoLayout);
        pnlControleSimulacaoLayout.setHorizontalGroup(
            pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControleSimulacaoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnPausarSimulacao)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlControleSimulacaoLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(btnIniciarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(31, Short.MAX_VALUE)))
        );
        pnlControleSimulacaoLayout.setVerticalGroup(
            pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControleSimulacaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPausarSimulacao)
                .addGap(47, 47, 47))
            .addGroup(pnlControleSimulacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlControleSimulacaoLayout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addComponent(btnIniciarSimulacao)
                    .addContainerGap(87, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAtuadorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlPrevisaoTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlAmbienteExterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlAmbienteInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlControleSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlPrevisaoTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlAmbienteExterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlAmbienteInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlControleSimulacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAtuadorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSimulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSimulacaoActionPerformed
        iniciarSimulacao();
    }//GEN-LAST:event_btnIniciarSimulacaoActionPerformed

    private void btnPausarSimulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausarSimulacaoActionPerformed
        pausarSimulacao();
    }//GEN-LAST:event_btnPausarSimulacaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSimulacao;
    private javax.swing.JButton btnPausarSimulacao;
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
