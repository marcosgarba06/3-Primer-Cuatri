package gui;

import aplicacion.FachadaAplicacion;

//clase que xestiona a ventá principal do sistema dende a que os traballadores acceden aos distintos módulos de xestión
public class VPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VPrincipal.class.getName());
    private FachadaAplicacion fa;
    
    public VPrincipal(FachadaAplicacion fa) {
        //gardamos a instancia da fachada para que todas as ventás fillas que se abran dende aquí manteñan a mesma conexión
        this.fa = fa;
        initComponents();
        
        //situamos a ventá principal no centro para que o administrador teña unha visión clara de todas as opcións de menú
        setLocationRelativeTo(null);
    }

    public VPrincipal() {
        initComponents();
        
        //mantemos o centrado automático por se a ventá se executa de xeito illado durante as probas de deseño
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PrincipalBOMD = new javax.swing.JButton();
        PrincipalBP = new javax.swing.JButton();
        datosdsala = new javax.swing.JButton();
        batras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel proncipal");
        setBackground(new java.awt.Color(102, 102, 102));

        PrincipalBOMD.setText("Modificación datos dun usuario");
        PrincipalBOMD.addActionListener(this::PrincipalBOMDActionPerformed);

        PrincipalBP.setText("Modificación datos inventario");
        PrincipalBP.addActionListener(this::PrincipalBPActionPerformed);

        datosdsala.setText("Xestionar limpeza salas");
        datosdsala.addActionListener(this::datosdsalaActionPerformed);

        batras.setText("<--");
        batras.addActionListener(this::batrasActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batras, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(269, 269, 269)
                            .addComponent(datosdsala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(PrincipalBOMD, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(PrincipalBP, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PrincipalBP, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(PrincipalBOMD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(datosdsala, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(batras, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PrincipalBOMDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrincipalBOMDActionPerformed
        
        new VUsuario(fa).setVisible(true);
        this.dispose(); // Cierra la principal al entrar
    }//GEN-LAST:event_PrincipalBOMDActionPerformed

    private void PrincipalBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrincipalBPActionPerformed
        
        new VInventario(fa).setVisible(true);
        this.dispose(); // Cierra la principal al entrar
    }//GEN-LAST:event_PrincipalBPActionPerformed

    private void datosdsalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datosdsalaActionPerformed
        
        new VSalasLimpieza(fa).setVisible(true);
        this.dispose(); // Cierra la principal al entrar
    }//GEN-LAST:event_datosdsalaActionPerformed

    private void batrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batrasActionPerformed
        
        this.dispose();
        new VLogin1(fa).setVisible(true);
    }//GEN-LAST:event_batrasActionPerformed


   

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new VPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PrincipalBOMD;
    private javax.swing.JButton PrincipalBP;
    private javax.swing.JButton batras;
    private javax.swing.JButton datosdsala;
    // End of variables declaration//GEN-END:variables
}
