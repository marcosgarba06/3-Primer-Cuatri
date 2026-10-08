package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.SalaLibre;
import aplicacion.TraballadorLimpiezaLibre;
import java.util.List;

//clase que xestiona a interface para coordinar as tarefas de mantemento, asignando persoal de limpeza ás salas baleiras
public class VSalasLimpieza extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VSalasLimpieza.class.getName());
    private FachadaAplicacion fa;
    
    //listas auxiliares para manter en memoria as salas e traballadores dispoñibles sen ter que consultar a base de datos en cada clic
    private List<SalaLibre> salasCargadas;
    private List<TraballadorLimpiezaLibre> traballadoresCargados;

    public VSalasLimpieza(FachadaAplicacion fa) {
        //gardamos a fachada para realizar as operacións de asignación e refresco de datos laborais
        this.fa = fa;
        initComponents();
        
        //centramos a ventá e cargamos os datos iniciais de salas sen limpar e persoal libre para que a interface estea lista para o uso
        setLocationRelativeTo(null);
        inicializarDatos();
    }

    public VSalasLimpieza() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sala = new javax.swing.JLabel();
        ATRAS = new javax.swing.JButton();
        S = new javax.swing.JLabel();
        E = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        comboSala = new javax.swing.JComboBox<>();
        comboTraballador = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sala Limpieza");

        sala.setText("Salas");

        ATRAS.setText("<--");
        ATRAS.addActionListener(this::ATRASActionPerformed);

        S.setText("Sala:");

        E.setText("Trabajador:");

        btnAceptar.setText("-->");
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        comboSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboSala.addActionListener(this::comboSalaActionPerformed);

        comboTraballador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboTraballador.addActionListener(this::comboTraballadorActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(sala, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ATRAS, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(E, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(S, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboTraballador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboSala, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(sala)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(E)
                    .addComponent(comboTraballador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ATRAS, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ATRASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATRASActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new VPrincipal(fa).setVisible(true);
    }//GEN-LAST:event_ATRASActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        
        // Validaciones básicas
        if (fa == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Erro: a aplicación non está inicializada.",
                    "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!comboSala.isEnabled() || !comboTraballador.isEnabled()) {
            return; // no hay opciones reales en alguno de los combos
        }

        int idxSala = comboSala.getSelectedIndex();
        int idxTrab = comboTraballador.getSelectedIndex();

        if (idxSala < 0 || idxTrab < 0) {
            return;
        }

        if (salasCargadas == null || traballadoresCargados == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Erro: non se cargaron as listas (salas/traballadores).",
                    "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idxSala >= salasCargadas.size() || idxTrab >= traballadoresCargados.size()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Erro: selección inválida.",
                    "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
    }

    // Recuperar objetos seleccionados
    SalaLibre salaSel = salasCargadas.get(idxSala);
    TraballadorLimpiezaLibre trabSel = traballadoresCargados.get(idxTrab);

    // Ejecutar asignación (devuelve true si se insertó; false si ya existía o falló)
    boolean ok = fa.asignarLimpiezaASala(trabSel.getDni(), salaSel.getIdSala());

    if (ok) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Asignación realizada: " + trabSel.getDni() + " -> Sala " + salaSel.getIdSala(),
                "OK", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    } 

    // Recargar combos (opcional pero recomendable)
    inicializarDatos();
    
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void comboTraballadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTraballadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTraballadorActionPerformed

    private void comboSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSalaActionPerformed


    private void inicializarDatos() {
        //centralizamos a carga de compoñentes para garantir que a ventá amose información actualizada ao abrirse
        cargarComboSalas();
        cargarComboTraballadores();
    }

    private void cargarComboSalas() {
        //limpamos o selector e consultamos as salas na base de datos para sincronizar a interface co estado real do ximnasio
        comboSala.removeAllItems();
        salasCargadas = fa.consultarSalas(); 

        //se a consulta non devolve resultados, bloqueamos o selector para evitar que o usuario tente realizar asignacións imposibles
        if (salasCargadas == null || salasCargadas.isEmpty()) {
            comboSala.addItem("Non hai salas");
            comboSala.setEnabled(false);
            return;
        }

        //activamos o selector e enchemos a lista cun formato lexible que inclúe o ID da sala e o nome do centro asociado
        comboSala.setEnabled(true);
        for (SalaLibre s : salasCargadas) {
            comboSala.addItem(s.getIdSala() + " - " + s.getNomeXimnasio());
        }
    }

    private void cargarComboTraballadores() {
        //baleiramos o listado de persoal e obtemos da fachada a relación de traballadores de limpeza dispoñibles actualmente
        comboTraballador.removeAllItems();
        traballadoresCargados = fa.consultarTraballadoresLimpiezaLibres(); 

        //informamos visualmente se non hai persoal libre nese momento e desactivamos a interacción para previr erros de inserción
        if (traballadoresCargados == null || traballadoresCargados.isEmpty()) {
            comboTraballador.addItem("Non hai persoal de limpeza");
            comboTraballador.setEnabled(false);
            return;
        }

        //habilitamos o combo e amosamos o DNI xunto co nome completo para facilitar a identificación correcta do traballador
        comboTraballador.setEnabled(true);
        for (TraballadorLimpiezaLibre t : traballadoresCargados) {
            comboTraballador.addItem(t.getDni() + " - " + t.getNomeCompleto());
        }
    }
   
        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new VSalasLimpieza().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ATRAS;
    private javax.swing.JLabel E;
    private javax.swing.JLabel S;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JComboBox<String> comboSala;
    private javax.swing.JComboBox<String> comboTraballador;
    private javax.swing.JLabel sala;
    // End of variables declaration//GEN-END:variables
}
