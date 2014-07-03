/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.integratesys.view;

import br.unesp.rc.integratesys.ambiente.Parametros;

/**
 *
 * @author Waldeilson
 */
public class FormParametros extends FormBase {

    private final Parametros parametros;

    public FormParametros() {
        initComponents();
        definirImagemSensores();        
        parametros = new Parametros();
        getParametros();
    }

    private void definirImagemSensores() {
        lblSensorTemperatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/temperatura.png")));
        lblSensorUmidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/umidade.png")));
        lblSensorLuminosidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sensores/luminosidade.png")));
    }
    
    private void getParametros(){
        txtTemperaturaIdeal.setText(String.valueOf(parametros.getTemperaturaIdeal()));
        txtTemperaturaMaxima.setText(String.valueOf(parametros.getTemperaturaMaximaCritica()));
        txtTemperaturaMinima.setText(String.valueOf(parametros.getTemperaturaMinimaCritica()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalvar = new javax.swing.JButton();
        pnlParametros = new javax.swing.JPanel();
        pnlSensorTemperatura = new javax.swing.JPanel();
        lblSensorTemperatura = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTemperaturaIdeal = new javax.swing.JTextField();
        txtTemperaturaMinima = new javax.swing.JTextField();
        txtTemperaturaMaxima = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlSensorLuminosidade = new javax.swing.JPanel();
        lblSensorLuminosidade = new javax.swing.JLabel();
        pnlSensorUmidade = new javax.swing.JPanel();
        lblSensorUmidade = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("IntegrateSys v1.0");

        btnSalvar.setText("OK");

        pnlParametros.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros"));
        pnlParametros.setName(""); // NOI18N

        pnlSensorTemperatura.setBorder(javax.swing.BorderFactory.createTitledBorder("Temperatura"));

        lblSensorTemperatura.setBorder(new javax.swing.border.MatteBorder(null));
        lblSensorTemperatura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSensorTemperatura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel1.setText("Temperatura ideal");

        txtTemperaturaIdeal.setColumns(5);
        txtTemperaturaIdeal.setText("jTextField1");

        txtTemperaturaMinima.setColumns(5);
        txtTemperaturaMinima.setText("jTextField2");

        txtTemperaturaMaxima.setColumns(5);
        txtTemperaturaMaxima.setText("jTextField3");

        jLabel2.setText("Temperatura Máxima");

        jLabel3.setText("Temperatura Mínima");

        javax.swing.GroupLayout pnlSensorTemperaturaLayout = new javax.swing.GroupLayout(pnlSensorTemperatura);
        pnlSensorTemperatura.setLayout(pnlSensorTemperaturaLayout);
        pnlSensorTemperaturaLayout.setHorizontalGroup(
            pnlSensorTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorTemperaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSensorTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSensorTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(txtTemperaturaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTemperaturaIdeal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTemperaturaMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSensorTemperaturaLayout.setVerticalGroup(
            pnlSensorTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorTemperaturaLayout.createSequentialGroup()
                .addComponent(lblSensorTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTemperaturaIdeal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(txtTemperaturaMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTemperaturaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pnlSensorLuminosidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Luminosidade"));

        lblSensorLuminosidade.setBorder(new javax.swing.border.MatteBorder(null));
        lblSensorLuminosidade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSensorLuminosidade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlSensorLuminosidadeLayout = new javax.swing.GroupLayout(pnlSensorLuminosidade);
        pnlSensorLuminosidade.setLayout(pnlSensorLuminosidadeLayout);
        pnlSensorLuminosidadeLayout.setHorizontalGroup(
            pnlSensorLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorLuminosidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSensorLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSensorLuminosidadeLayout.setVerticalGroup(
            pnlSensorLuminosidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorLuminosidadeLayout.createSequentialGroup()
                .addComponent(lblSensorLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSensorUmidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Umidade"));

        lblSensorUmidade.setBorder(new javax.swing.border.MatteBorder(null));
        lblSensorUmidade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSensorUmidade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlSensorUmidadeLayout = new javax.swing.GroupLayout(pnlSensorUmidade);
        pnlSensorUmidade.setLayout(pnlSensorUmidadeLayout);
        pnlSensorUmidadeLayout.setHorizontalGroup(
            pnlSensorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorUmidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSensorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSensorUmidadeLayout.setVerticalGroup(
            pnlSensorUmidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSensorUmidadeLayout.createSequentialGroup()
                .addComponent(lblSensorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlParametrosLayout = new javax.swing.GroupLayout(pnlParametros);
        pnlParametros.setLayout(pnlParametrosLayout);
        pnlParametrosLayout.setHorizontalGroup(
            pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlParametrosLayout.createSequentialGroup()
                .addComponent(pnlSensorTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSensorUmidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSensorLuminosidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlParametrosLayout.setVerticalGroup(
            pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSensorTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlSensorUmidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlSensorLuminosidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalvar)
                    .addComponent(pnlParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btnSalvar.getAccessibleContext().setAccessibleName("Salvar");
        pnlParametros.getAccessibleContext().setAccessibleName("Parametros");
        pnlParametros.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblSensorLuminosidade;
    private javax.swing.JLabel lblSensorTemperatura;
    private javax.swing.JLabel lblSensorUmidade;
    private javax.swing.JPanel pnlParametros;
    private javax.swing.JPanel pnlSensorLuminosidade;
    private javax.swing.JPanel pnlSensorTemperatura;
    private javax.swing.JPanel pnlSensorUmidade;
    private javax.swing.JTextField txtTemperaturaIdeal;
    private javax.swing.JTextField txtTemperaturaMaxima;
    private javax.swing.JTextField txtTemperaturaMinima;
    // End of variables declaration//GEN-END:variables

}
