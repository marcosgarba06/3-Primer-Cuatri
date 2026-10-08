package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.UsuarioGym;
import aplicacion.TraballadorGym;
import aplicacion.TipoUsuarioGym;
import aplicacion.TipoTraballadorGym;
import java.math.BigDecimal;
import java.sql.Date;

//clase que xestiona a ventá de edición de usuarios para modificar os seus datos persoais ou laborais
public class DEditarUsuario extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DEditarUsuario.class.getName());
    private FachadaAplicacion fa;
    
    //gardamos o usuario orixinal para ter os seus datos base e un obxecto traballador extra que se manterá a null se a persoa é só cliente
    private UsuarioGym usuarioOriginal;
    private TraballadorGym traballadorBD; 
    
    //neste construtor recibimos o usuario a editar e enchemos automaticamente todos os campos da interface cos seus datos actuais
    public DEditarUsuario(java.awt.Frame parent, boolean modal, FachadaAplicacion fa, UsuarioGym u) {
        super(parent, modal);
        this.fa = fa;
        this.usuarioOriginal = u;

        initComponents();
        setLocationRelativeTo(parent);

        //o DNI é a clave primaria na base de datos polo que non deixamos que o administrador o modifique bloqueando o campo de texto
        txtDni.setText(u.getDni());
        txtDni.setEditable(false);

        //cubrimos os atributos comúns a calquera usuario do ximnasio sacándoos do obxecto que nos pasaron dende a táboa de busca
        txtNome.setText(u.getNome());
        txtApelido1.setText(u.getApelido1());
        txtApelido2.setText(u.getApelido2());
        txtEmail.setText(u.getEmail());
        txtMovil.setText(u.getMovil());
        txtDireccion.setText(u.getDireccion());
        txtDataNacemento.setText(String.valueOf(u.getDataNacemento()));
        txtXimnasio.setText(u.getNomeXimnasio());

        //cargamos o despregable cos tipos de traballador sacándoos directamente do enum para asegurarnos de que sempre coincidan cos definidos na base de datos
        cmbTipoTraballador.removeAllItems();
        for (TipoTraballadorGym tt : TipoTraballadorGym.values()) {
            cmbTipoTraballador.addItem(tt.name());
        }
        cmbTipoTraballador.addActionListener(e -> actualizarSubtipo());

        //se resulta que o usuario a editar é un empregado facemos unha consulta extra á base de datos para traer o seu soldo horario e especialidade antes de pintar a ventá
        if (u.getTipoUsuario() == TipoUsuarioGym.TRABALLADOR) {
            traballadorBD = fa.obterTraballadorPorDni(u.getDni());
        }

        cargarZonaTraballador();
        actualizarSubtipo();
    }
    
    public DEditarUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelDni = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        labelApelido1 = new javax.swing.JLabel();
        txtApelido1 = new javax.swing.JTextField();
        txtApelido2 = new javax.swing.JTextField();
        labelAp2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        labelDir = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        labelTlf = new javax.swing.JLabel();
        txtMovil = new javax.swing.JTextField();
        txtDataNacemento = new javax.swing.JTextField();
        labelData = new javax.swing.JLabel();
        labelXim = new javax.swing.JLabel();
        txtXimnasio = new javax.swing.JTextField();
        labelTraballador = new javax.swing.JLabel();
        labelSoldo = new javax.swing.JLabel();
        txtSoldo = new javax.swing.JTextField();
        labelHorario = new javax.swing.JLabel();
        txtHorario = new javax.swing.JTextField();
        labelTipo = new javax.swing.JLabel();
        cmbTipoTraballador = new javax.swing.JComboBox<>();
        labelCargo = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        labelEspecialidade = new javax.swing.JLabel();
        txtEspecialidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Editar datos de un usuario");

        labelDni.setText("DNI:");

        labelNombre.setText("Nome:");

        labelApelido1.setText("Apelido1:");

        labelAp2.setText("Apelido2:");

        labelEmail.setText("Email:");

        labelDir.setText("Direccion:");

        labelTlf.setText("Teléfono:");

        txtDataNacemento.addActionListener(this::txtDataNacementoActionPerformed);

        labelData.setText("Data Nacemento (YYYY-MM-DD)");

        labelXim.setText("Ximnasio:");

        labelTraballador.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        labelTraballador.setText("Traballador:");

        labelSoldo.setText("Soldo:");

        labelHorario.setText("Horario:");

        labelTipo.setText("Tipo:");

        cmbTipoTraballador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "MONITOR", "LIMPEZA", " " }));

        labelCargo.setText("Cargo:");

        labelEspecialidade.setText("Especialidade:");

        btnCancelar.setText("<--");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnAceptar.setText("-->");
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSoldo)
                                .addGap(57, 57, 57)
                                .addComponent(txtSoldo))
                            .addComponent(labelTraballador)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelXim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtXimnasio, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDataNacemento))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDni)
                                    .addComponent(labelNombre)
                                    .addComponent(labelApelido1)
                                    .addComponent(labelAp2)
                                    .addComponent(labelEmail)
                                    .addComponent(labelDir)
                                    .addComponent(labelTlf))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApelido2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApelido1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelHorario)
                                    .addComponent(labelTipo)
                                    .addComponent(labelCargo))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHorario)
                                    .addComponent(cmbTipoTraballador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCargo)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelEspecialidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEspecialidade))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDni)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelApelido1)
                    .addComponent(txtApelido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAp2)
                    .addComponent(txtApelido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmail))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDir)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTlf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelData)
                    .addComponent(txtDataNacemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXim)
                    .addComponent(txtXimnasio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelTraballador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSoldo)
                    .addComponent(txtSoldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHorario))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTipo)
                    .addComponent(cmbTipoTraballador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCargo)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEspecialidade)
                    .addComponent(txtEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataNacementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNacementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNacementoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        
        try {
            //lemos de novo todos os campos de texto por se o administrador cambiou algunha letra limpando os espazos residuais co trim
            String dni = txtDni.getText().trim();
            String nome = txtNome.getText().trim();
            String ap1 = txtApelido1.getText().trim();
            String ap2 = txtApelido2.getText().trim();
            String email = txtEmail.getText().trim();
            String movil = txtMovil.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String xim = txtXimnasio.getText().trim();

            //filtramos que os campos basicos obrigatorios da base de datos non foran borrados antes de lanzar o update
            if (nome.isEmpty() || ap1.isEmpty() || email.isEmpty() || xim.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Faltan campos obrigatorios.", "Erro",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            //tentamos parsear a cadea da data a formato SQL para que non salte excepcion no DAO
            Date dataNac = Date.valueOf(txtDataNacemento.getText().trim());

            //creamos un obxecto usuario clonando o contrasinal orixinal porque a ventá de edición non permite cambiar contrasinais
            UsuarioGym uNovo = new UsuarioGym(
                    dni,
                    usuarioOriginal.getPasswordHash(),
                    nome, ap1, ap2,
                    direccion, movil, email,
                    dataNac, xim,
                    usuarioOriginal.getTipoUsuario()
            );

            //mandamos o obxecto modificado á fachada que executa a primeira fase do update na táboa pai
            boolean okU = fa.modificarUsuarioPorDni(uNovo);
            if (!okU) {
                javax.swing.JOptionPane.showMessageDialog(this, "Non se puido actualizar o usuario.", "Erro",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            //se a actualización xeral funcionou e ademais resulta que a persoa é empregada actualizamos os seus datos laborais especificos
            if (usuarioOriginal.getTipoUsuario() == TipoUsuarioGym.TRABALLADOR) {
                BigDecimal soldo = new BigDecimal(txtSoldo.getText().trim());
                String horario = txtHorario.getText().trim();
                TipoTraballadorGym tipoTrab = TipoTraballadorGym.valueOf((String) cmbTipoTraballador.getSelectedItem());
                String cargo = txtCargo.getText().trim();
                String especialidade = txtEspecialidade.getText().trim();

                //se antes era de limpeza e agora o fan monitor validamos que o administrador lle escriba unha disciplina si ou si
                if (tipoTrab == TipoTraballadorGym.MONITOR && especialidade.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Especialidade obrigatoria para MONITOR.", "Erro",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //creamos o obxecto traballador cos datos novos da interface e o hash antigo
                TraballadorGym tNovo = new TraballadorGym(
                        dni,
                        usuarioOriginal.getPasswordHash(),
                        nome, ap1, ap2,
                        direccion, movil, email,
                        dataNac, xim,
                        TipoUsuarioGym.TRABALLADOR,
                        soldo, horario, tipoTrab,
                        cargo, especialidade
                );

                //executamos a segunda fase do update onde o DAO borra o seu rol antigo e insíreo no novo para evitar discordancias
                boolean okT = fa.modificarTraballadorPorDni(tNovo);
                if (!okT) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Usuario actualizado, pero non se puido actualizar TRABALLADOR.",
                            "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            javax.swing.JOptionPane.showMessageDialog(this, "Datos actualizados.", "OK",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    
    private void cargarZonaTraballador() {
        //comprobamos se o usuario que estamos a editar é un traballador para habilitar ou bloquear os campos laborais da interface
        boolean esTrab = (usuarioOriginal.getTipoUsuario() == TipoUsuarioGym.TRABALLADOR);

        txtSoldo.setEnabled(esTrab);
        txtHorario.setEnabled(esTrab);
        cmbTipoTraballador.setEnabled(esTrab);
        txtCargo.setEnabled(esTrab);
        txtEspecialidade.setEnabled(esTrab);

        //se non é traballador limpamos os textos por precaución para que non queden datos pantasma antes de saír do método
        if (!esTrab) {
            txtSoldo.setText("");
            txtHorario.setText("");
            txtCargo.setText("");
            txtEspecialidade.setText("");
            return;
        }

        if (traballadorBD == null) return;

        //se é un traballador e temos os seus datos da base de datos enchemos os campos para que o administrador poida editalos
        txtSoldo.setText(traballadorBD.getSoldo().toPlainString());
        txtHorario.setText(traballadorBD.getHorario());
        cmbTipoTraballador.setSelectedItem(traballadorBD.getTipoTraballador().name());
        txtCargo.setText(traballadorBD.getCargo());
        txtEspecialidade.setText(traballadorBD.getEspecialidade());
    }
    
    private void actualizarSubtipo() {
        if (usuarioOriginal.getTipoUsuario() != TipoUsuarioGym.TRABALLADOR) return;

        String tt = (String) cmbTipoTraballador.getSelectedItem();
        if (tt == null) return;

        //este método lánzase cando cambian o despregable de tipo de traballador para activar só os campos extra que lle corresponden a ese rol exacto
        boolean esAdmin = "ADMINISTRADOR".equals(tt);
        boolean esMonitor = "MONITOR".equals(tt);

        txtCargo.setEnabled(esAdmin);
        txtEspecialidade.setEnabled(esMonitor);

        if (!esAdmin) txtCargo.setText("");
        if (!esMonitor) txtEspecialidade.setText("");
    }

    public static void main(String args[]) {
        //cargamos o aspecto visual do sistema operativo por defecto no canto do look feo base de java
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DEditarUsuario dialog = new DEditarUsuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cmbTipoTraballador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelAp2;
    private javax.swing.JLabel labelApelido1;
    private javax.swing.JLabel labelCargo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDir;
    private javax.swing.JLabel labelDni;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEspecialidade;
    private javax.swing.JLabel labelHorario;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelSoldo;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelTlf;
    private javax.swing.JLabel labelTraballador;
    private javax.swing.JLabel labelXim;
    private javax.swing.JTextField txtApelido1;
    private javax.swing.JTextField txtApelido2;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtDataNacemento;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEspecialidade;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtMovil;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSoldo;
    private javax.swing.JTextField txtXimnasio;
    // End of variables declaration//GEN-END:variables
}
