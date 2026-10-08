/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import aplicacion.FachadaAplicacion;

/**
 *
 * @author alumnogreibd
 */
public class VBajaProducto extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VBajaProducto.class.getName());
    private FachadaAplicacion fa;

    public VBajaProducto(FachadaAplicacion fa) {
        this.fa = fa;
        initComponents();
        this.setTitle("Dar de Baixa Producto");
        setLocationRelativeTo(null); // Centra la ventana
        cargarProductos();           // Llena el combo al abrir
    }
    /**
     * Creates new form VBajaProducto
     */
    public VBajaProducto() {
        initComponents();
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelEliminar = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox<>();
        btnAceptar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelEliminar.setText("Producto a eliminar:");

        comboProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAceptar.setText("-->");
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        btnAtras.setText("<--");
        btnAtras.addActionListener(this::btnAtrasActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEliminar)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEliminar)
                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        new VInventario(fa).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (comboProductos.getSelectedItem() == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto.");
            return;
        }

        String seleccionado = comboProductos.getSelectedItem().toString();
        String codigo = seleccionado.split(" - ")[0]; // Extrae el código

        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, 
                "¿Seguro que desexas eliminar o producto " + seleccionado + "?", 
                "Confirmar Baixa", 
                javax.swing.JOptionPane.YES_NO_OPTION);

        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            try {
                fa.baixaProducto(codigo);
                javax.swing.JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
                cargarProductos(); // Refresca la lista para que desaparezca el borrado
            } catch (Exception e) {
                // Captura el error si la base de datos falla
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao eliminar: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed
    
    private void cargarProductos() {
        comboProductos.removeAllItems();
        for (aplicacion.ProductoInventario p : fa.consultarProductos()) {
            comboProductos.addItem(p.getCodigoIdentificador() + " - " + p.getNome());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new VBajaProducto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JLabel labelEliminar;
    // End of variables declaration//GEN-END:variables
}
