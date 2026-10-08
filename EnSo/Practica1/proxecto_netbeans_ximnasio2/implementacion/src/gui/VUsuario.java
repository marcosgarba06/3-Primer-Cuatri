package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.UsuarioGym;
import aplicacion.TipoUsuarioGym;
import aplicacion.UtilidadesSeguridade;
import java.sql.Date;

//clase que xestiona a administración integral de usuarios, permitindo visualizar, crear e editar perfís dende unha táboa central
public class VUsuario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VUsuario.class.getName());
    private FachadaAplicacion fa;
    
    //definimos o modelo da táboa e unha lista auxiliar para sincronizar os datos visibles coas instancias de usuario en memoria
    private javax.swing.table.DefaultTableModel modeloUsuarios;
    private java.util.List<aplicacion.UsuarioGym> listaUsuarios = new java.util.ArrayList<>();
   
    public VUsuario(FachadaAplicacion fa) {
        this.fa = fa;
        initComponents();
        
        //centramos a ventá e preparamos a estrutura da táboa e os botóns para que a interface arranque nun estado neutro
        setLocationRelativeTo(null);
        initTabla();
        setModoSinUsuario();
    }  



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        UsuarioOMD = new javax.swing.JLabel();
        UsuarioD = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnAlta = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        UsuarioBA = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        scrollPanelBuscar = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        btnMembresia = new javax.swing.JButton();
        btnBaixa = new javax.swing.JButton();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Xestion datos dun usuario");
        setBackground(new java.awt.Color(102, 102, 102));

        UsuarioOMD.setText("Xestion datos dun usuario");

        UsuarioD.setText("DNI:");

        btnAlta.setText("Alta");
        btnAlta.addActionListener(this::btnAltaActionPerformed);

        btnEditar.setText("Editar datos");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        UsuarioBA.setText("<--");
        UsuarioBA.addActionListener(this::UsuarioBAActionPerformed);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnLimpiar.setText("Limpiar búsqueda");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPanelBuscar.setViewportView(tblUsuarios);

        btnMembresia.setText("Modificar Membresia");
        btnMembresia.addActionListener(this::btnMembresiaActionPerformed);

        btnBaixa.setText("Baixa");
        btnBaixa.addActionListener(this::btnBaixaActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(UsuarioOMD, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UsuarioBA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(UsuarioD, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPanelBuscar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMembresia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(UsuarioOMD)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsuarioD)
                    .addComponent(btnBuscar))
                .addGap(29, 29, 29)
                .addComponent(scrollPanelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(UsuarioBA, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        UsuarioGym u = getUsuarioSeleccionado();
        if (u == null) return;

        DEditarUsuario d = new DEditarUsuario(this, true, fa, u);
        d.setVisible(true);

        buscarPorDni(); // refrescar
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        DAltaUsuario d = new DAltaUsuario(this, true, fa);
        d.setVisible(true);
    }//GEN-LAST:event_btnAltaActionPerformed

    private void UsuarioBAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioBAActionPerformed

        this.dispose();
        new VPrincipal(fa).setVisible(true);
    }//GEN-LAST:event_UsuarioBAActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        buscarPorDni();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       
        txtDni.setText("");
        setModoSinUsuario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnMembresiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMembresiaActionPerformed
        
        aplicacion.UsuarioGym u = getUsuarioSeleccionado();
        if (u == null) return;

        // Solo si es cliente (en tu modelo nuevo TipoUsuarioGym=CLIENTE/TRABALLADOR)
        if (u.getTipoUsuario() != aplicacion.TipoUsuarioGym.CLIENTE) {
            javax.swing.JOptionPane.showMessageDialog(this, "Só CLIENTES teñen membresía.");
            return;
        }

        DMembresia d = new DMembresia(this, true, fa, u.getDni());
        d.setVisible(true);

        // Si quieres refrescar la búsqueda por DNI al cerrar:
        buscarPorDni();
    }//GEN-LAST:event_btnMembresiaActionPerformed

    private void btnBaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaixaActionPerformed
        
        aplicacion.UsuarioGym u = getUsuarioSeleccionado();
        if (u == null) return;

        int op = javax.swing.JOptionPane.showConfirmDialog(this,
            "Confirmas a baixa do usuario " + u.getDni() + "?",
            "Confirmar baixa",
            javax.swing.JOptionPane.YES_NO_OPTION);

        if (op != javax.swing.JOptionPane.YES_OPTION) return;

        fa.baixaUsuario(u.getDni());
        javax.swing.JOptionPane.showMessageDialog(this, "Baixa realizada.");
        setModoSinUsuario();
    }//GEN-LAST:event_btnBaixaActionPerformed

    private void initTabla() {
        //definimos a estrutura da táboa coas columnas necesarias e bloqueamos a edición directa sobre as celas para manter a integridade
        modeloUsuarios = new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"DNI", "Nome", "Apelidos", "Email", "Móbil", "Nacemento", "Tipo"}
        ) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        //aplicamos o modelo á táboa da interface e configuramos para que o administrador só poida seleccionar un usuario cada vez
        tblUsuarios.setModel(modeloUsuarios);
        tblUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }
    
    
    private void setModoSinUsuario() {
        //limpamos calquera dato previo da táboa e da lista en memoria para evitar confusións visuales
        modeloUsuarios.setRowCount(0);
        listaUsuarios.clear();

        //desactivamos as accións de edición, baixa e membresía xa que non hai ningunha entidade seleccionada sobre a que actuar
        btnEditar.setEnabled(false);
        btnBaixa.setEnabled(false);
        btnMembresia.setEnabled(false);
    }

    private void setModoConUsuario(aplicacion.UsuarioGym u) {
        //resetamos a táboa e engadimos o novo usuario á lista de control en memoria
        modeloUsuarios.setRowCount(0);
        listaUsuarios.clear();
        listaUsuarios.add(u);

        //concatenamos os apelidos tratando correctamente os valores nulos ou baleiros para que a visualización sexa limpa
        String apelidos = (u.getApelido1() == null ? "" : u.getApelido1()) +
                (u.getApelido2() == null || u.getApelido2().isBlank() ? "" : (" " + u.getApelido2()));

        //insertamos a fila coa información do usuario no modelo da táboa para que se amose na pantalla
        modeloUsuarios.addRow(new Object[]{
            u.getDni(),
            u.getNome(),
            apelidos.trim(),
            u.getEmail(),
            u.getMovil(),
            u.getDataNacemento(),
            u.getTipoUsuario().name()
        });

        //seleccionamos automaticamente a fila cargada e activamos os botóns de xestión xerais
        tblUsuarios.setRowSelectionInterval(0, 0);
        btnEditar.setEnabled(true);
        btnBaixa.setEnabled(true);
        
        //habilitamos o botón de membresía exclusivamente se o usuario é de tipo CLIENTE, xa que os traballadores non a teñen
        btnMembresia.setEnabled(u.getTipoUsuario() == aplicacion.TipoUsuarioGym.CLIENTE);
    }
    
    
    private aplicacion.UsuarioGym getUsuarioSeleccionado() {
        //obtemos o índice da fila marcada polo usuario e recuperamos o obxecto correspondente da nosa lista interna
        int row = tblUsuarios.getSelectedRow();
        if (row < 0 || row >= listaUsuarios.size()) return null;
        return listaUsuarios.get(row);
    }
    
    private void buscarPorDni() {
        //recollemos o DNI introducido e validamos que o campo non estea baleiro antes de lanzar a consulta á fachada
        String dni = txtDni.getText().trim();
        if (dni.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Introduce un DNI.");
            return;
        }

        //solicitamos á base de datos a información do usuario; se non existe, limpamos a interface e avisamos ao administrador
        java.util.List<aplicacion.UsuarioGym> res = fa.buscarUsuariosAdmin(dni, "");
        if (res == null || res.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Non existe usuario con ese DNI.");
            setModoSinUsuario();
            return;
        }

        //se atopamos o usuario, cargamos os seus datos na táboa e activamos as ferramentas de edición
        setModoConUsuario(res.get(0));
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
        System.out.println("Non executar VUsuario directamente. Abrir desde VPrincipal co obxecto FachadaAplicacion.");
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UsuarioBA;
    private javax.swing.JLabel UsuarioD;
    private javax.swing.JLabel UsuarioOMD;
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaixa;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMembresia;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane scrollPanelBuscar;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
