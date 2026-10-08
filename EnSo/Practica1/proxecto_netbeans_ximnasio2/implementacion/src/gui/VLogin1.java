package gui;

import aplicacion.FachadaAplicacion;

//clase que xestiona a ventá de acceso inicial onde os usuarios e administradores introducen as súas credenciais
public class VLogin1 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VLogin1.class.getName());

    private FachadaAplicacion fa;

    public VLogin1(FachadaAplicacion fa) {
        //gardamos a referencia á fachada para poder validar o usuario contra a base de datos ao pulsar o botón de entrar
        this.fa = fa;
        initComponents();
        
        //centramos a ventá de login no medio da pantalla para que sexa o primeiro que vexa o usuario ao arrincar o programa
        setLocationRelativeTo(null);
    }

    public VLogin1() {
        initComponents();
        
        //asegurámonos de que a ventá apareza sempre centrada incluso se se utiliza o construtor por defecto para probas rápidas
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginIS = new javax.swing.JLabel();
        LoginTNU = new javax.swing.JTextField();
        LoginNU = new javax.swing.JLabel();
        LoginC = new javax.swing.JLabel();
        LoginTC = new javax.swing.JPasswordField();
        LoginB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de sesion");
        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(300, 250));

        LoginIS.setText("INICIAR SESION");

        LoginTNU.addActionListener(this::LoginTNUActionPerformed);

        LoginNU.setText("DNI:");

        LoginC.setText("Contrasinal:");

        LoginTC.addActionListener(this::LoginTCActionPerformed);

        LoginB.setText("-->");
        LoginB.addActionListener(this::LoginBActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LoginNU, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LoginTNU)
                            .addComponent(LoginTC)
                            .addComponent(LoginC, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LoginB, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(LoginIS, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(LoginIS)
                .addGap(18, 18, 18)
                .addComponent(LoginNU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LoginTNU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LoginC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoginTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LoginB)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginTNUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginTNUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoginTNUActionPerformed

    private void LoginTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginTCActionPerformed
        // TODO add your handling code here:
        entrar();
    }//GEN-LAST:event_LoginTCActionPerformed

    private void LoginBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBActionPerformed
        // TODO add your handling code here:
        entrar();
    }//GEN-LAST:event_LoginBActionPerformed


    private void entrar() {
        //recollemos o texto do usuario e convertemos a matriz de caracteres do contrasinal nunha cadea de texto para a súa validación
        String usuario = LoginTNU.getText().trim();
        String contrasena = new String(LoginTC.getPassword());

        //comprobamos que os campos non estean baleiros para evitar consultas innecesarias á base de datos
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Introduce usuario e contrasinal.");
            return;
        }

        // chamamos á fachada para verificar as credenciais; tipoUsuario gardará "CLIENTE", "TRABALLADOR" o null se o login falla
        String tipoUsuario = null;
        if (fa != null) {
            tipoUsuario = fa.autenticarUsuario(usuario, contrasena);
        }

        if (tipoUsuario != null) {
            // Login correcto. ¿Qué ventana abrimos?
            if (tipoUsuario.equals("CLIENTE")) {
                //se o usuario é un cliente avisamos de que polo momento non ten acceso á xestión interna do ximnasio
                javax.swing.JOptionPane.showMessageDialog(this, "Os clientes non teñen ningunha funcionalidade implementada.");
                // Si en el futuro quieres abrir su ventana, borrarías el JOptionPane y pondrías:
                // new VPrincipalCliente(fa).setVisible(true);
                // this.dispose();
            } else {
                // se é un TRABALLADOR (admin, monitor, etc.), abrimos a ventá principal de xestión e pechamos o login
                new VPrincipal(fa).setVisible(true);
                this.dispose();
            }
        } else {
            //se a base de datos devolve null informamos de que as credenciais introducidas non son válidas
            javax.swing.JOptionPane.showMessageDialog(this, "Usuario ou contrasinal incorrectos.");
        }
    }
     
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
        java.awt.EventQueue.invokeLater(() -> new VLogin1().setVisible(true));

        /* Create and display the form */
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginB;
    private javax.swing.JLabel LoginC;
    private javax.swing.JLabel LoginIS;
    private javax.swing.JLabel LoginNU;
    private javax.swing.JPasswordField LoginTC;
    private javax.swing.JTextField LoginTNU;
    // End of variables declaration//GEN-END:variables
}
