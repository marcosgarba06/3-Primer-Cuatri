package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.ProductoInventario;
import aplicacion.TipoMaterial;
import aplicacion.EstadoMaterial;
import java.math.BigDecimal;

//clase que xestiona a ventá para dar de alta novos materiais no inventario do ximnasio
public class VAltaProducto extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VAltaProducto.class.getName());
    private FachadaAplicacion fa;
    private aplicacion.TipoMaterial tipo;

    public VAltaProducto(FachadaAplicacion fa, aplicacion.TipoMaterial tipo) {
        this.fa = fa;
        this.tipo = tipo;
        initComponents();
        setLocationRelativeTo(null);
        
        //chamamos a este método para que a ventá amose uns campos ou outros dependendo de se imos crear unha pesa, unha máquina ou uns guantes
        configurarVentana(); 
        
        //conectamos coa base de datos para encher o desplegable coas salas reais que hai no sistema
        cargarSalas();       
    }

    public VAltaProducto() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCodigo = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelSubtipo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        labelEstado = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        labelSala = new javax.swing.JLabel();
        comboSala = new javax.swing.JComboBox<>();
        txtSubtipo = new javax.swing.JTextField();
        labelMaterial = new javax.swing.JLabel();
        txtMaterialFabrica = new javax.swing.JTextField();
        labelPeso = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelCodigo.setText("CódigoId:");

        labelNombre.setText("Nombre:");

        labelSubtipo.setText("Subtipo:");

        txtCodigo.addActionListener(this::txtCodigoActionPerformed);

        txtNombre.addActionListener(this::txtNombreActionPerformed);

        labelEstado.setText("Estado:");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OPERATIVO", "ROTO", "MANTEMENTO" }));

        labelSala.setText("Sala:");

        comboSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelMaterial.setText("Material:");

        labelPeso.setText("Peso:");

        btnAceptar.setText("-->");
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        btnAtras.setText("<--");
        btnAtras.addActionListener(this::btnAtrasActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelPeso)
                    .addComponent(labelCodigo)
                    .addComponent(labelNombre)
                    .addComponent(labelEstado)
                    .addComponent(labelSala)
                    .addComponent(labelSubtipo)
                    .addComponent(labelMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(comboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboSala, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubtipo)
                    .addComponent(txtMaterialFabrica)
                    .addComponent(txtPeso))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnAtras, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addGap(94, 94, 94)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEstado)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSala, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSubtipo)
                    .addComponent(txtSubtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMaterial)
                    .addComponent(txtMaterialFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPeso)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarVentana() {
        //axustamos o título da xanela dinamicamente para que o usuario saiba exactamente que tipo de material está a rexistrar
        this.setTitle("Alta de " + tipo.name());
        
        //desactivamos por defecto todos os campos específicos para evitar que se introduzan datos que non corresponden ao tipo de material
        txtSubtipo.setEnabled(false);
        txtPeso.setEnabled(false);
        txtMaterialFabrica.setEnabled(false);

        //activamos selectivamente os cadros de texto necesarios dependendo de se o material require peso, subtipo ou material de fabricación
        if (tipo == aplicacion.TipoMaterial.PESO_LIBRE) {
            txtSubtipo.setEnabled(true);
            txtPeso.setEnabled(true);
        } else if (tipo == aplicacion.TipoMaterial.MAQUINA || tipo == aplicacion.TipoMaterial.GUANTE) {
            txtSubtipo.setEnabled(true);
        } else if (tipo == aplicacion.TipoMaterial.SACO) {
            txtMaterialFabrica.setEnabled(true);
            txtPeso.setEnabled(true);
        }
    }

    private void cargarSalas() {
        //limpamos o contido previo do desplegable para evitar duplicados se se recarga a xanela
        comboSala.removeAllItems();
        comboSala.addItem("Sen asignar"); 
        
        //percorremos a lista de IDs que nos devolve a base de datos para encher o combo e permitir ao usuario escoller unha sala real
        for (Integer id : fa.consultarIdSalas()) {
            comboSala.addItem(id.toString());
        }
    }
    
    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        
        try {
            //recollemos os datos básicos do material limpando espazos para evitar erros de duplicados por espazos en branco
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();

            if (codigo.isEmpty() || nombre.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "O código e o nome son obrigatorios.");
                return;
            }

            //obtemos o estado seleccionado do combo e convertémolo ao tipo enum correspondente para o noso obxecto de aplicación
            aplicacion.EstadoMaterial estado = aplicacion.EstadoMaterial.valueOf(comboEstado.getSelectedItem().toString());

            //comprobamos se a sala seleccionada é un número real ou se debe quedar como un valor nulo na base de datos
            Integer idSala = null;
            if (!comboSala.getSelectedItem().toString().equals("Sen asignar")) {
                idSala = Integer.parseInt(comboSala.getSelectedItem().toString());
            }

            //recollemos o subtipo só se o campo está habilitado para o tipo de material que estamos a dar de alta
            String subtipo = null;
            if (txtSubtipo.isEnabled() && !txtSubtipo.getText().trim().isEmpty()) {
                subtipo = txtSubtipo.getText().trim();
            }

            //extraemos o material de fabricación se o cadro de texto está activo (por exemplo, no caso dos sacos)
            String materialFabrica = null;
            if (txtMaterialFabrica.isEnabled() && !txtMaterialFabrica.getText().trim().isEmpty()) {
                materialFabrica = txtMaterialFabrica.getText().trim();
            }

            //convertemos o texto do peso a BigDecimal tratando a coma como un punto para que Java non lance unha excepción de formato
            java.math.BigDecimal peso = null;
            if (txtPeso.isEnabled() && !txtPeso.getText().trim().isEmpty()) {
                peso = new java.math.BigDecimal(txtPeso.getText().trim().replace(",", "."));
            }

            //creamos a instancia do produto coa información recollida para mandalo á fachada e procesar a alta
            aplicacion.ProductoInventario p = new aplicacion.ProductoInventario(
                codigo, 
                nombre, 
                estado, 
                idSala, 
                tipo, 
                subtipo, 
                peso, 
                materialFabrica
            );

            fa.altaProducto(p);

            //se a operación ten éxito avisamos ao usuario e recargamos a ventá do inventario para amosar os cambios
            javax.swing.JOptionPane.showMessageDialog(this, "Producto dado de alta correctamente.");
            new VInventario(fa).setVisible(true);
            this.dispose();

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro: Revisa que o peso sexa un número correcto.");
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao dar de alta: " + ex.getMessage());
        }
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        new VAltaTipoProducto(fa).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new VAltaProducto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboSala;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelMaterial;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelPeso;
    private javax.swing.JLabel labelSala;
    private javax.swing.JLabel labelSubtipo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtMaterialFabrica;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtSubtipo;
    // End of variables declaration//GEN-END:variables
}
