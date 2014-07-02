/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.view;

import br.unesp.rc.integratesys.ambiente.Ambiente;
import br.unesp.rc.integratesys.atuadores.Nivel;
import br.unesp.rc.integratesys.utils.CondicaoTempo;
import br.unesp.rc.integratesys.utils.Tarefa;
import java.awt.Component;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Helton
 */
public class FormAmbiente extends FormBase {

    private final Ambiente ambiente;

    /**
     * Creates new form FormPrincipal
     */
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

    private Tarefa atualizaInformacoesFormulario() {
        return new Tarefa() {

            private void verificarSituacaoCritica() {
                //tratar!
            }

            private void definirImagemSensores() {
                if (ambiente.getSensores().getSensorTemperatura().getTemperatura() >= ambiente.getParametros().getTemperaturaMaximaCritica()) {
                    lblSensorTemperatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/temperatura_alta.png")));
                } else if (ambiente.getSensores().getSensorTemperatura().getTemperatura() <= ambiente.getParametros().getTemperaturaMinimaCritica()) {
                    lblSensorTemperatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/temperatura_baixa.png")));
                } else {
                    lblSensorTemperatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/temperatura.png")));
                }

                if (ambiente.getSensores().getSensorUmidade().getUmidade() >= ambiente.getParametros().getUmidadeMaximaCritica()) {
                    lblSensorUmidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/umidade_alta.png")));
                } else if (ambiente.getSensores().getSensorUmidade().getUmidade() <= ambiente.getParametros().getUmidadeMinimaCritica()) {
                    lblSensorUmidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/umidade_baixa.png")));
                } else {
                    lblSensorUmidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/umidade.png")));
                }

                if (ambiente.getSensores().getSensorLuminosidade().getLuminosidade() <= ambiente.getParametros().getLuminosidadeMinimaCritica()) {
                    lblSensorLuminosidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/luminosidade_escuro.png")));
                } else {
                    lblSensorLuminosidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/luminosidade.png")));
                }

            }

            private void definirImagemAtuadores() {
                if (ambiente.getAtuadores().getVentilador().getNivel().getValor() == 0) {
                    lblVentilador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/ventilador.png")));
                    sliAquecedor.setEnabled(true);
                } else {
                    lblVentilador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/ventilador_ligado.png")));
                    sliAquecedor.setEnabled(false);
                }

                if (ambiente.getAtuadores().getAquecedor().getNivel().getValor() == 0) {
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

                if (ambiente.getAtuadores().getUmidificador().getNivel().getValor() == 0) {
                    lblUmidificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/umidificador.png")));
                } else {
                    lblUmidificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/umidificador_ligado.png")));
                }

                if (ambiente.getAtuadores().getLampada().getNivel().getValor() == 0) {
                    lblLampada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/lampada.png")));
                } else {
                    lblLampada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atuadores/lampada_ligada.png")));
                }

            }

            public String formatarTempoDecorrido(long diferencaEmMilisegundos) {

                long segundosEmMilisegundos = 1000;
                long minutosEmMilisegundos = segundosEmMilisegundos * 60;
                long horasEmMilisegundos = minutosEmMilisegundos * 60;

                long horasDecorridas = diferencaEmMilisegundos / horasEmMilisegundos;
                diferencaEmMilisegundos = diferencaEmMilisegundos % horasEmMilisegundos;

                long minutosDecorridos = diferencaEmMilisegundos / minutosEmMilisegundos;
                diferencaEmMilisegundos = diferencaEmMilisegundos % minutosEmMilisegundos;

                long segundosDecorridos = diferencaEmMilisegundos / segundosEmMilisegundos;

                return String.format(
                        "%02d:%02d:%02d",
                        horasDecorridas, minutosDecorridos, segundosDecorridos);

            }
            
            private void atualizarInformacoesControleSimulacao() {
                lblCiclos.setText(Integer.toString(ambiente.getControladorSimulacao().getAgendadorTarefas().getIndiceProximoCiclo() - 1));
                lblTempoAtual.setText(formatarTempoDecorrido(ambiente.getControladorSimulacao().getTempoDecorridoSimulacaoAtual()));
                lblTempoAcumulado.setText(formatarTempoDecorrido(ambiente.getControladorSimulacao().getTempoTotalSimulacaoDecorrido()));
                lblIntervaloPorCiclo.setText(ambiente.getControladorSimulacao().getVarredor().INTERVALO_POR_CICLO + " segundos");
                lblCondicaoTempo.setVisible(ambiente.getControladorSimulacao().getSimuladorCondicoesMeteorologicas().isLigado());
                if (lblCondicaoTempo.isVisible() && lblCondicaoTempo.getIcon() == null) {
                    CondicaoTempo condicaoTempo = ambiente.getControladorSimulacao().getSimuladorCondicoesMeteorologicas().getPrevisaoTempo().getCondicaoTempo();
                    lblCondicaoTempo.setIcon(condicaoTempo.getImagem());
                }
            }

            @Override
            public void executar() {
                lblTemperatura.setText(ambiente.getSensores().getSensorTemperatura().getTemperatura() + " ºC");
                lblUmidade.setText(ambiente.getSensores().getSensorUmidade().getUmidade() + " %");
                lblLuminosidade.setText(ambiente.getSensores().getSensorLuminosidade().getLuminosidade() + " %");
                atualizarInformacoesControleSimulacao();
                definirImagemSensores();
                definirImagemAtuadores();
                verificarSituacaoCritica();
                ambiente.getControladorSimulacao().getSimuladorCondicoesMeteorologicas().simular();
            }
        };
    }

    public FormAmbiente() {
        initComponents();
        configurarSliders();
        ambiente = new Ambiente(atualizaInformacoesFormulario());
        ambiente.getControladorSimulacao().iniciarSimulacao();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sliVentilador = new javax.swing.JSlider();
        sliAquecedor = new javax.swing.JSlider();
        sepAtuadores = new javax.swing.JSeparator();
        sliLampada = new javax.swing.JSlider();
        sliUmidificador = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        lblCiclos = new javax.swing.JLabel();
        lblTituloCiclos = new javax.swing.JLabel();
        lblTituloTempoAtual = new javax.swing.JLabel();
        lblTempoAtual = new javax.swing.JLabel();
        chkSimuladorCondicoesMeteorologicas = new javax.swing.JCheckBox();
        lblTituloTempoAcumulado = new javax.swing.JLabel();
        lblTempoAcumulado = new javax.swing.JLabel();
        lblTituloIntervaloPorCiclo = new javax.swing.JLabel();
        lblIntervaloPorCiclo = new javax.swing.JLabel();
        lblCondicaoTempo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IntegrateSys v1.0");

        pnlControleTemperatura.setBorder(javax.swing.BorderFactory.createTitledBorder("Controle de Temperatura"));

        pnlSensorTemperatura.setBorder(javax.swing.BorderFactory.createTitledBorder("Sensor"));

        lblSensorTemperatura.setText("sensor de temperatura");
        lblSensorTemperatura.setBorder(new javax.swing.border.MatteBorder(null));
        lblSensorTemperatura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSensorTemperatura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblTemperatura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTemperatura.setText("0º C");

        javax.swing.GroupLayout pnlSensorTemperaturaLayout = new javax.swing.GroupLayout(pnlSensorTemperatura);
        pnlSensorTemperatura.setLayout(pnlSensorTemperaturaLayout);
        pnlSensorTemperaturaLayout.setHorizontalGroup(
            pnlSensorTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorTemperaturaLayout.createSequentialGroup()
                .addGroup(pnlSensorTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSensorTemperaturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSensorTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSensorTemperaturaLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lblTemperatura)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSensorTemperaturaLayout.setVerticalGroup(
            pnlSensorTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorTemperaturaLayout.createSequentialGroup()
                .addComponent(lblSensorTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTemperatura)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAtuadoresTemperatura.setBorder(javax.swing.BorderFactory.createTitledBorder("Atuadores"));

        lblVentilador.setText("ventilador");
        lblVentilador.setBorder(new javax.swing.border.MatteBorder(null));
        lblVentilador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblVentilador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblAquecedor.setText("aquecedor");
        lblAquecedor.setBorder(new javax.swing.border.MatteBorder(null));
        lblAquecedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAquecedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliVentilador.setMaximum(3);
        sliVentilador.setPaintLabels(true);
        sliVentilador.setSnapToTicks(true);
        sliVentilador.setValue(0);

        sliAquecedor.setMaximum(3);
        sliAquecedor.setPaintLabels(true);
        sliAquecedor.setSnapToTicks(true);
        sliAquecedor.setValue(0);

        sepAtuadores.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pnlAtuadoresTemperaturaLayout = new javax.swing.GroupLayout(pnlAtuadoresTemperatura);
        pnlAtuadoresTemperatura.setLayout(pnlAtuadoresTemperaturaLayout);
        pnlAtuadoresTemperaturaLayout.setHorizontalGroup(
            pnlAtuadoresTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtuadoresTemperaturaLayout.createSequentialGroup()
                .addGroup(pnlAtuadoresTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtuadoresTemperaturaLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(lblVentilador, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlAtuadoresTemperaturaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sliVentilador, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addComponent(sepAtuadores, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAtuadoresTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAtuadoresTemperaturaLayout.createSequentialGroup()
                        .addComponent(lblAquecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAtuadoresTemperaturaLayout.createSequentialGroup()
                        .addComponent(sliAquecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pnlAtuadoresTemperaturaLayout.setVerticalGroup(
            pnlAtuadoresTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtuadoresTemperaturaLayout.createSequentialGroup()
                .addGroup(pnlAtuadoresTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAquecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVentilador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlAtuadoresTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliVentilador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliAquecedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(pnlAtuadoresTemperaturaLayout.createSequentialGroup()
                .addComponent(sepAtuadores)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlControleTemperaturaLayout = new javax.swing.GroupLayout(pnlControleTemperatura);
        pnlControleTemperatura.setLayout(pnlControleTemperaturaLayout);
        pnlControleTemperaturaLayout.setHorizontalGroup(
            pnlControleTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControleTemperaturaLayout.createSequentialGroup()
                .addComponent(pnlSensorTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAtuadoresTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlControleTemperaturaLayout.setVerticalGroup(
            pnlControleTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAtuadoresTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlSensorTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlControleLuminosidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Controle de Luminosidade"));

        pnlSensorLuminosidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Sensor"));

        lblSensorLuminosidade.setText("sensor de luminosidade");
        lblSensorLuminosidade.setBorder(new javax.swing.border.MatteBorder(null));
        lblSensorLuminosidade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSensorLuminosidade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblLuminosidade.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLuminosidade.setText("0%");

        javax.swing.GroupLayout pnlSensorLuminosidadeLayout = new javax.swing.GroupLayout(pnlSensorLuminosidade);
        pnlSensorLuminosidade.setLayout(pnlSensorLuminosidadeLayout);
        pnlSensorLuminosidadeLayout.setHorizontalGroup(
            pnlSensorLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorLuminosidadeLayout.createSequentialGroup()
                .addGroup(pnlSensorLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSensorLuminosidadeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSensorLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSensorLuminosidadeLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblLuminosidade)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSensorLuminosidadeLayout.setVerticalGroup(
            pnlSensorLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorLuminosidadeLayout.createSequentialGroup()
                .addComponent(lblSensorLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLuminosidade)
                .addContainerGap())
        );

        pnlAtuadorLuminosidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Atuador"));

        lblLampada.setText("lâmpada");
        lblLampada.setBorder(new javax.swing.border.MatteBorder(null));
        lblLampada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLampada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliLampada.setMaximum(3);
        sliLampada.setPaintLabels(true);
        sliLampada.setSnapToTicks(true);
        sliLampada.setValue(0);

        javax.swing.GroupLayout pnlAtuadorLuminosidadeLayout = new javax.swing.GroupLayout(pnlAtuadorLuminosidade);
        pnlAtuadorLuminosidade.setLayout(pnlAtuadorLuminosidadeLayout);
        pnlAtuadorLuminosidadeLayout.setHorizontalGroup(
            pnlAtuadorLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtuadorLuminosidadeLayout.createSequentialGroup()
                .addComponent(sliLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAtuadorLuminosidadeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        pnlAtuadorLuminosidadeLayout.setVerticalGroup(
            pnlAtuadorLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtuadorLuminosidadeLayout.createSequentialGroup()
                .addComponent(lblLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sliLampada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlControleLuminosidadeLayout = new javax.swing.GroupLayout(pnlControleLuminosidade);
        pnlControleLuminosidade.setLayout(pnlControleLuminosidadeLayout);
        pnlControleLuminosidadeLayout.setHorizontalGroup(
            pnlControleLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControleLuminosidadeLayout.createSequentialGroup()
                .addComponent(pnlSensorLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAtuadorLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlControleLuminosidadeLayout.setVerticalGroup(
            pnlControleLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControleLuminosidadeLayout.createSequentialGroup()
                .addGroup(pnlControleLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAtuadorLuminosidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSensorLuminosidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlControleUmidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Controle de Umidade"));

        pnlSensorUmidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Sensor"));

        lblSensorUmidade.setText("sensor de umidade");
        lblSensorUmidade.setBorder(new javax.swing.border.MatteBorder(null));
        lblSensorUmidade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSensorUmidade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblUmidade.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUmidade.setText("0%");

        javax.swing.GroupLayout pnlSensorUmidadeLayout = new javax.swing.GroupLayout(pnlSensorUmidade);
        pnlSensorUmidade.setLayout(pnlSensorUmidadeLayout);
        pnlSensorUmidadeLayout.setHorizontalGroup(
            pnlSensorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorUmidadeLayout.createSequentialGroup()
                .addGroup(pnlSensorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSensorUmidadeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSensorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSensorUmidadeLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(lblUmidade)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSensorUmidadeLayout.setVerticalGroup(
            pnlSensorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorUmidadeLayout.createSequentialGroup()
                .addComponent(lblSensorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUmidade)
                .addContainerGap())
        );

        pnlAtuadorUmidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Atuador"));

        lblUmidificador.setText("umidificador");
        lblUmidificador.setBorder(new javax.swing.border.MatteBorder(null));
        lblUmidificador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUmidificador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        sliUmidificador.setMaximum(3);
        sliUmidificador.setPaintLabels(true);
        sliUmidificador.setSnapToTicks(true);
        sliUmidificador.setValue(0);

        javax.swing.GroupLayout pnlAtuadorUmidadeLayout = new javax.swing.GroupLayout(pnlAtuadorUmidade);
        pnlAtuadorUmidade.setLayout(pnlAtuadorUmidadeLayout);
        pnlAtuadorUmidadeLayout.setHorizontalGroup(
            pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                .addComponent(sliUmidificador, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAtuadorUmidadeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUmidificador, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        pnlAtuadorUmidadeLayout.setVerticalGroup(
            pnlAtuadorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtuadorUmidadeLayout.createSequentialGroup()
                .addComponent(lblUmidificador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(sliUmidificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlControleUmidadeLayout = new javax.swing.GroupLayout(pnlControleUmidade);
        pnlControleUmidade.setLayout(pnlControleUmidadeLayout);
        pnlControleUmidadeLayout.setHorizontalGroup(
            pnlControleUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControleUmidadeLayout.createSequentialGroup()
                .addComponent(pnlSensorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAtuadorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlControleUmidadeLayout.setVerticalGroup(
            pnlControleUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSensorUmidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlControleUmidadeLayout.createSequentialGroup()
                .addComponent(pnlAtuadorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Controle de simulação"));

        tbtnIniciarSimulacao.setSelected(true);
        tbtnIniciarSimulacao.setText("Pausar simulação");
        tbtnIniciarSimulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnIniciarSimulacaoActionPerformed(evt);
            }
        });

        lblCiclos.setText("0");

        lblTituloCiclos.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTituloCiclos.setText("Ciclos:");

        lblTituloTempoAtual.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTituloTempoAtual.setText("Tempo atual:");

        lblTempoAtual.setText("0");

        chkSimuladorCondicoesMeteorologicas.setText("Ativar simulador de condições meteorológicas");
        chkSimuladorCondicoesMeteorologicas.setEnabled(false);
        chkSimuladorCondicoesMeteorologicas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkSimuladorCondicoesMeteorologicasStateChanged(evt);
            }
        });

        lblTituloTempoAcumulado.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTituloTempoAcumulado.setText("Tempo acumulado:");

        lblTempoAcumulado.setText("0");

        lblTituloIntervaloPorCiclo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTituloIntervaloPorCiclo.setText("Intervalo por ciclo:");

        lblIntervaloPorCiclo.setText("0");

        lblCondicaoTempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCondicaoTempo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblCondicaoTempo.setName(""); // NOI18N
        lblCondicaoTempo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloCiclos)
                            .addComponent(lblTituloTempoAtual)
                            .addComponent(lblTituloTempoAcumulado)
                            .addComponent(lblTituloIntervaloPorCiclo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTempoAtual)
                            .addComponent(lblTempoAcumulado)
                            .addComponent(lblIntervaloPorCiclo)
                            .addComponent(lblCiclos))
                        .addGap(106, 106, 106))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkSimuladorCondicoesMeteorologicas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblCondicaoTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tbtnIniciarSimulacao)
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tbtnIniciarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloCiclos)
                    .addComponent(lblCiclos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloTempoAtual)
                    .addComponent(lblTempoAtual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloTempoAcumulado)
                    .addComponent(lblTempoAcumulado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloIntervaloPorCiclo)
                    .addComponent(lblIntervaloPorCiclo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSimuladorCondicoesMeteorologicas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCondicaoTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlControleUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlControleLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlControleTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlControleUmidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlControleLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlControleTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlControleTemperatura.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnIniciarSimulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnIniciarSimulacaoActionPerformed
        if (tbtnIniciarSimulacao.isSelected()) {
            ambiente.getControladorSimulacao().iniciarSimulacao();
            tbtnIniciarSimulacao.setText("Pausar simulação");
        } else {
            ambiente.getControladorSimulacao().pausarSimulacao();
            tbtnIniciarSimulacao.setText("Iniciar simulação");
        }
        chkSimuladorCondicoesMeteorologicas.setEnabled(!(ambiente.getControladorSimulacao().isEmSimulacao()));
    }//GEN-LAST:event_tbtnIniciarSimulacaoActionPerformed

    private void chkSimuladorCondicoesMeteorologicasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkSimuladorCondicoesMeteorologicasStateChanged
        ambiente.getControladorSimulacao().getSimuladorCondicoesMeteorologicas().setLigado(((JCheckBox) evt.getSource()).isSelected());
    }//GEN-LAST:event_chkSimuladorCondicoesMeteorologicasStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormAmbiente().exibir();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkSimuladorCondicoesMeteorologicas;
    private javax.swing.JPanel jPanel1;
    private final javax.swing.JLabel lblAquecedor = new javax.swing.JLabel();
    private javax.swing.JLabel lblCiclos;
    private javax.swing.JLabel lblCondicaoTempo;
    private javax.swing.JLabel lblIntervaloPorCiclo;
    private final javax.swing.JLabel lblLampada = new javax.swing.JLabel();
    private final javax.swing.JLabel lblLuminosidade = new javax.swing.JLabel();
    private final javax.swing.JLabel lblSensorLuminosidade = new javax.swing.JLabel();
    private final javax.swing.JLabel lblSensorTemperatura = new javax.swing.JLabel();
    private final javax.swing.JLabel lblSensorUmidade = new javax.swing.JLabel();
    private final javax.swing.JLabel lblTemperatura = new javax.swing.JLabel();
    private javax.swing.JLabel lblTempoAcumulado;
    private javax.swing.JLabel lblTempoAtual;
    private javax.swing.JLabel lblTituloCiclos;
    private javax.swing.JLabel lblTituloIntervaloPorCiclo;
    private javax.swing.JLabel lblTituloTempoAcumulado;
    private javax.swing.JLabel lblTituloTempoAtual;
    private final javax.swing.JLabel lblUmidade = new javax.swing.JLabel();
    private final javax.swing.JLabel lblUmidificador = new javax.swing.JLabel();
    private final javax.swing.JLabel lblVentilador = new javax.swing.JLabel();
    private final javax.swing.JPanel pnlAtuadorLuminosidade = new javax.swing.JPanel();
    private final javax.swing.JPanel pnlAtuadorUmidade = new javax.swing.JPanel();
    private final javax.swing.JPanel pnlAtuadoresTemperatura = new javax.swing.JPanel();
    private final javax.swing.JPanel pnlControleLuminosidade = new javax.swing.JPanel();
    private final javax.swing.JPanel pnlControleTemperatura = new javax.swing.JPanel();
    private final javax.swing.JPanel pnlControleUmidade = new javax.swing.JPanel();
    private final javax.swing.JPanel pnlSensorLuminosidade = new javax.swing.JPanel();
    private final javax.swing.JPanel pnlSensorTemperatura = new javax.swing.JPanel();
    private final javax.swing.JPanel pnlSensorUmidade = new javax.swing.JPanel();
    private javax.swing.JSeparator sepAtuadores;
    private javax.swing.JSlider sliAquecedor;
    private javax.swing.JSlider sliLampada;
    private javax.swing.JSlider sliUmidificador;
    private javax.swing.JSlider sliVentilador;
    private final javax.swing.JToggleButton tbtnIniciarSimulacao = new javax.swing.JToggleButton();
    // End of variables declaration//GEN-END:variables
}
