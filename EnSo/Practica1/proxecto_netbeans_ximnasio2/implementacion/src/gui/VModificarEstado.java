package gui;

import aplicacion.FachadaAplicacion;

//clase que xestiona a ventá para actualizar o estado de conservación ou dispoñibilidade dos materiais do inventario
public class VModificarEstado extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VModificarEstado.class.getName());
    
    private FachadaAplicacion fa;

    public VModificarEstado(FachadaAplicacion fa) {
        this.fa = fa;
        initComponents();
        
        //establecemos un título claro para a xanela e centramola para que sexa o foco de atención do administrador
        this.setTitle("Modificar Estado de Producto");
        setLocationRelativeTo(null); 
        
        //chamamos ao método que sincroniza o desplegable coa lista de produtos actuais da base de datos
        cargarProductos();           
    }
    
    public VModificarEstado() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelSeleccionar = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox<>();
        labelEstado = new javax.swing.JLabel();
        comboEstados = new javax.swing.JComboBox<>();
        btnAceptar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Modificar estado dun produto");

        labelSeleccionar.setText("Seleccionar producto:");

        comboProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelEstado.setText("Estado:");

        comboEstados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OPERATIVO", "ROTO", "MANTEMENTO" }));

        btnAceptar.setText("-->");
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        btnAtras.setText("<--");
        btnAtras.addActionListener(this::btnAtrasActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelSeleccionar)
                        .addComponent(labelEstado)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(96, 96, 96))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSeleccionar)
                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboEstados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEstado))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
            if (comboProductos.getSelectedItem() == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto.");
            return;
        }
                                             
            // 1. obtemos a cadea de texto do combo e separamos o código identificador do nome usando o guión como delimitador
            String seleccionado = comboProductos.getSelectedItem().toString();
            String codigo = seleccionado.split(" - ")[0]; 

            // 2. recuperamos o estado seleccionado e convertémolo directamente ao tipo Enum EstadoMaterial que entende a nosa lóxica de negocio
            aplicacion.EstadoMaterial estado = aplicacion.EstadoMaterial.valueOf(comboEstados.getSelectedItem().toString());

            try {
                // 3. enviamos o código e o novo estado á fachada para que o DAO execute a sentenza UPDATE correspondente en Postgres
                fa.actualizarEstadoProducto(codigo, estado);

                javax.swing.JOptionPane.showMessageDialog(this, "Estado actualizado correctamente.");

                // 4. unha vez confirmada a mudanza, regresamos á ventá principal do inventario e pechamos a actual para liberar memoria
                new VInventario(fa).setVisible(true);
                this.dispose();
            } catch (Exception e) {
                // capturamos calquera posible excepción lanzada polo driver de base de datos e informamos ao usuario mediante un diálogo
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao actualizar: " + e.getMessage());
            }
        }                                            

        private void cargarProductos() {
            // baleiramos o desplegable para evitar que se acumulen elementos se chamamos a este método tras varias actualizacións
            comboProductos.removeAllItems();

            // percorremos a lista de materiais obtida do DAO e engadimos cada un ao combo con un formato lexible de código e nome
            for (aplicacion.ProductoInventario p : fa.consultarProductos()) {
                comboProductos.addItem(p.getCodigoIdentificador() + " - " + p.getNome());
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        new VInventario(fa).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new VModificarEstado().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JComboBox<String> comboEstados;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelSeleccionar;
    // End of variables declaration//GEN-END:variables
}
