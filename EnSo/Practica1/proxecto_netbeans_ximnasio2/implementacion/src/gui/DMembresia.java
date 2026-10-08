package gui;

import aplicacion.EstadoMembresia;
import aplicacion.FachadaAplicacion;

//clase que xestiona a ventá para cambiar o estado da membresía dun cliente (activo, inactivo, etc.)
public class DMembresia extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DMembresia.class.getName());
    
    private FachadaAplicacion fa;
    private String dniCliente;

    public DMembresia(java.awt.Frame parent, boolean modal, FachadaAplicacion fa, String dniCliente) {
        super(parent, modal);
        this.fa = fa;
        this.dniCliente = dniCliente;

        initComponents();
        setLocationRelativeTo(parent);

        //o DNI vén xa fixado dende a táboa da interface anterior así que o bloqueamos para que o administrador non o poida editar por erro
        txtDni.setText(dniCliente);
        txtDni.setEditable(false);

        cargarEstados();
    }

    public DMembresia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void cargarEstados() {
        cmbEstado.removeAllItems();
        
        //cargamos o despregable cos estados dispoñibles lendo directamente os valores do enum EstadoMembresia para evitar fallos de texto coa base de datos
        for (EstadoMembresia e : EstadoMembresia.values()) {
            cmbEstado.addItem(e.name());
        }

        //poñemos por defecto o estado activo xa que adoita ser o máis común ao xestionar as contas
        cmbEstado.setSelectedItem(EstadoMembresia.ACTIVO.name());
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelMembresia = new javax.swing.JLabel();
        labelDni = new javax.swing.JLabel();
        labelNEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtDni = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelMembresia.setText("Modificar estado membresía");

        labelDni.setText("DNI:");

        labelNEstado.setText("Nuevo estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAceptar.setText("-->");
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        btnCancelar.setText("<--");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(labelMembresia))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelDni)
                                    .addComponent(labelNEstado))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbEstado, 0, 205, Short.MAX_VALUE)
                                    .addComponent(txtDni))))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMembresia)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDni)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:        
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        if (fa == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Erro: FachadaAplicacion non inicializada.",
                    "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            //lemos o estado seleccionado no despregable e verificamos que non estea baleiro por precaución antes de continuar
            String estadoTxt = (String) cmbEstado.getSelectedItem();
            if (estadoTxt == null || estadoTxt.isBlank()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Selecciona un estado.",
                        "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            //convertemos o texto seleccionado de novo ao obxecto enum EstadoMembresia para que o DAO poida facer o cast correcto para postgres
            aplicacion.EstadoMembresia novoEstado = aplicacion.EstadoMembresia.valueOf(estadoTxt);

            //chamamos á nosa fachada de aplicación para que lance a query de actualización na táboa cliente
            boolean ok = fa.modificarMembresia(dniCliente, novoEstado);

            //se a actualización na base de datos devolve true mostramos unha mensaxe de éxito e pechamos a ventá para rematar o proceso
            if (ok) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Membresía actualizada a: " + novoEstado.name(),
                        "OK", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Non se puido actualizar a membresía (cliente inexistente ou erro na BD).",
                        "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Erro ao modificar membresía: " + e.getMessage(),
                    "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DMembresia dialog = new DMembresia(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel labelDni;
    private javax.swing.JLabel labelMembresia;
    private javax.swing.JLabel labelNEstado;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
