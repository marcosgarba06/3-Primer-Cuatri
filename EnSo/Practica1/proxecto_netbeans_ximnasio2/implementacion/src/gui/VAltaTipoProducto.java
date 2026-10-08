package gui;

import aplicacion.FachadaAplicacion;

//clase que xestiona a ventá de selección inicial para decidir que categoría de material imos dar de alta no inventario
public class VAltaTipoProducto extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VAltaTipoProducto.class.getName());
    private FachadaAplicacion fa;

    public VAltaTipoProducto(FachadaAplicacion fa) {
        //gardamos a referencia á fachada para poder abrir as seguintes ventás de alta pasándolle o control da aplicación
        this.fa = fa;
        initComponents();
        
        //centramos a ventá na pantalla para que a interface sexa máis amigable e profesional para o administrador
        setLocationRelativeTo(null); 
    }
    
    public VAltaTipoProducto() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelDarAltaTipo = new javax.swing.JLabel();
        btnPesoLibre = new javax.swing.JButton();
        btnMaquina = new javax.swing.JButton();
        btnSaco = new javax.swing.JButton();
        btnGuante = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelDarAltaTipo.setText("Tipo de producto que quiere dar de alta:");

        btnPesoLibre.setText("Material de peso libre");
        btnPesoLibre.addActionListener(this::btnPesoLibreActionPerformed);

        btnMaquina.setText("Máquina");
        btnMaquina.addActionListener(this::btnMaquinaActionPerformed);

        btnSaco.setText("Sacos");
        btnSaco.addActionListener(this::btnSacoActionPerformed);

        btnGuante.setText("Guantes");
        btnGuante.addActionListener(this::btnGuanteActionPerformed);

        btnAtras.setText("<--");
        btnAtras.addActionListener(this::btnAtrasActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSaco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPesoLibre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(btnGuante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(labelDarAltaTipo)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(labelDarAltaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuante, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPesoLibre, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaco, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnAtras, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesoLibreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesoLibreActionPerformed
        
        new VAltaProducto(fa, aplicacion.TipoMaterial.PESO_LIBRE).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPesoLibreActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        
        new VInventario(fa).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaquinaActionPerformed
        
        new VAltaProducto(fa, aplicacion.TipoMaterial.MAQUINA).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMaquinaActionPerformed

    private void btnGuanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuanteActionPerformed
        new VAltaProducto(fa, aplicacion.TipoMaterial.GUANTE).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGuanteActionPerformed

    private void btnSacoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacoActionPerformed
        
        new VAltaProducto(fa, aplicacion.TipoMaterial.SACO).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSacoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new VAltaTipoProducto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuante;
    private javax.swing.JButton btnMaquina;
    private javax.swing.JButton btnPesoLibre;
    private javax.swing.JButton btnSaco;
    private javax.swing.JLabel labelDarAltaTipo;
    // End of variables declaration//GEN-END:variables
}
