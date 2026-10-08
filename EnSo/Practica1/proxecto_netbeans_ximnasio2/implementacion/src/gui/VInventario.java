package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.ProductoInventario;
import aplicacion.EstadoMaterial;
import aplicacion.TipoMaterial;
import java.math.BigDecimal;

//clase que xestiona o panel principal do inventario onde se centralizan as accións de alta, baixa e consulta de material
public class VInventario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VInventario.class.getName());
    private FachadaAplicacion fa;

    public VInventario(FachadaAplicacion fa) {
        //gardamos a referencia á fachada para poder abrir as ventás de xestión de productos pasándolle o control da lóxica
        this.fa = fa;
        initComponents();
        
        //centramos a ventá na pantalla para que o acceso ao inventario sexa visualmente cómodo para o administrador
        setLocationRelativeTo(null);
    }

    public VInventario() {
        initComponents();
        setLocationRelativeTo(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Inventarioalabel = new javax.swing.JLabel();
        Ap = new javax.swing.JButton();
        MEP = new javax.swing.JButton();
        DBP = new javax.swing.JButton();
        atras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario");

        Inventarioalabel.setText("Inventario");

        Ap.setText("Alta producto");
        Ap.addActionListener(this::ApActionPerformed);

        MEP.setText("Modificar estado producto");
        MEP.addActionListener(this::MEPActionPerformed);

        DBP.setText("Dar de baixa producto");
        DBP.addActionListener(this::DBPActionPerformed);

        atras.setText("<--");
        atras.addActionListener(this::atrasActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(Inventarioalabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(241, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(atras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Ap, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MEP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DBP)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Inventarioalabel)
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ap, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MEP, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DBP, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(atras, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(90, 90, 90))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ApActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApActionPerformed
        
        new VAltaTipoProducto(fa).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ApActionPerformed
        
    private void MEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MEPActionPerformed
        new VModificarEstado(fa).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MEPActionPerformed

    private void DBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DBPActionPerformed
        
        new VBajaProducto(fa).setVisible(true);
        this.dispose();        
    }//GEN-LAST:event_DBPActionPerformed

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
 
        new VPrincipal(fa).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_atrasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new VInventario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ap;
    private javax.swing.JButton DBP;
    private javax.swing.JLabel Inventarioalabel;
    private javax.swing.JButton MEP;
    private javax.swing.JButton atras;
    // End of variables declaration//GEN-END:variables
}
