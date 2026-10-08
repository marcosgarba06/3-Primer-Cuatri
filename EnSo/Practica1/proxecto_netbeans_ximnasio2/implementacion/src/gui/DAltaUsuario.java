
package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.UsuarioGym;
import aplicacion.TraballadorGym;
import aplicacion.TipoUsuarioGym;
import aplicacion.TipoTraballadorGym;
import java.math.BigDecimal;
import java.sql.Date;


//clase que xestiona a ventá de altas para rexistrar novos clientes ou traballadores no sistema
public class DAltaUsuario extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DAltaUsuario.class.getName());
    private FachadaAplicacion fa;
    
    public DAltaUsuario(java.awt.Frame parent, boolean modal) {
        this(parent, modal, null);
    }

    public DAltaUsuario(java.awt.Frame parent, boolean modal, FachadaAplicacion fa) {
        super(parent, modal);
        this.fa = fa;
        initComponents();
        setLocationRelativeTo(parent);

        //poñemos uns valores por defecto nos campos máis comúns para aforrar tempo á hora de facer probas manuais
        if (txtXimnasio.getText() == null || txtXimnasio.getText().isBlank()) {
            txtXimnasio.setText("Ximnasio Central");
        }
        if (txtImporte.getText() == null || txtImporte.getText().isBlank()) {
            txtImporte.setText("30.00");
        }

        //engadimos listeners aos desplegables para que a interface cambie en tempo real cando o usuario escolle opcións
        cmbTipoAlta.addActionListener(e -> actualizarModo());
        cmbTipoTraballador.addActionListener(e -> actualizarSubtipoTraballador());

        actualizarModo();
    }

    //metodos de apoio para ir activando ou desactivando os campos de texto da ventá dependendo do rol que se estea dando de alta
    private void actualizarModo() {
        String tipo = (String) cmbTipoAlta.getSelectedItem();
        if ("CLIENTE".equals(tipo)) {
            setModoCliente();
        } else {
            setModoTraballador();
        }
    }

    private void setModoCliente() {
        txtImporte.setEnabled(true);
        cmbMetodoPago.setEnabled(true);

        txtSoldo.setEnabled(false);
        txtHorario.setEnabled(false);
        cmbTipoTraballador.setEnabled(false);
        txtCargo.setEnabled(false);
        txtEspecialidade.setEnabled(false);

        txtSoldo.setText("");
        txtHorario.setText("");
        txtCargo.setText("");
        txtEspecialidade.setText("");
    }

    private void setModoTraballador() {
        txtImporte.setEnabled(false);
        cmbMetodoPago.setEnabled(false);

        txtSoldo.setEnabled(true);
        txtHorario.setEnabled(true);
        cmbTipoTraballador.setEnabled(true);

        actualizarSubtipoTraballador();
    }

    private void actualizarSubtipoTraballador() {
        String tt = (String) cmbTipoTraballador.getSelectedItem();
        boolean esAdmin = "ADMINISTRADOR".equals(tt);
        boolean esMonitor = "MONITOR".equals(tt);

        txtCargo.setEnabled(esAdmin);
        txtEspecialidade.setEnabled(esMonitor);

        if (!esAdmin) txtCargo.setText("");
        if (!esMonitor) txtEspecialidade.setText("");
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAlta = new javax.swing.JLabel();
        labelDni = new javax.swing.JLabel();
        labelContraseña = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelAp1 = new javax.swing.JLabel();
        labelAp2 = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelDireccion = new javax.swing.JLabel();
        labelMovil = new javax.swing.JLabel();
        labelGym = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtApelido1 = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtApelido2 = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtMovil = new javax.swing.JTextField();
        txtDataNacemento = new javax.swing.JTextField();
        txtXimnasio = new javax.swing.JTextField();
        labelTrabajador = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        labelImporte = new javax.swing.JLabel();
        labelMetodoPago = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        labelSoldo = new javax.swing.JLabel();
        labelHorario = new javax.swing.JLabel();
        txtSoldo = new javax.swing.JTextField();
        txtHorario = new javax.swing.JTextField();
        cmbTipoTraballador = new javax.swing.JComboBox<>();
        labelTipo = new javax.swing.JLabel();
        labelCargo = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtEspecialidade = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        cmbTipoAlta = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtAlta.setText("Alta de un nuevo usuario de tipo");

        labelDni.setText("Dni:");

        labelContraseña.setText("Contraseña:");

        labelNombre.setText("Nombre:");

        labelAp1.setText("Apellido1:");

        labelAp2.setText("Apellido2:");

        labelEmail.setText("Email:");

        labelDireccion.setText("Direccion:");

        labelMovil.setText("Movil:");

        labelGym.setText("Ximnasio:");

        labelFecha.setText("Data Nacemento (YYYY-MM-DD):");

        txtMovil.addActionListener(this::txtMovilActionPerformed);

        labelTrabajador.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        labelTrabajador.setText("Trabajador:");

        labelCliente.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        labelCliente.setText("Cliente:");

        labelImporte.setText("Importe (€):");

        labelMetodoPago.setText("Metodo de pago:");

        cmbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TARXETA", "TRANSFERENCIA" }));

        labelSoldo.setText("Soldo:");

        labelHorario.setText("Horario:");

        cmbTipoTraballador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "MONITOR", "LIMPEZA" }));

        labelTipo.setText("Tipo:");

        labelCargo.setText("Cargo:");

        jLabel1.setText("Especialidade:");

        btnCancelar.setText("<--");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnGuardar.setText("-->");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        cmbTipoAlta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CLIENTE", "TRABALLADOR" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(labelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoldo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelImporte)
                            .addGap(18, 18, 18)
                            .addComponent(txtImporte))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelMetodoPago)
                            .addGap(18, 18, 18)
                            .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelSoldo)
                        .addGap(185, 185, 185))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelHorario)
                            .addComponent(labelTipo)
                            .addComponent(labelCargo)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipoTraballador, 0, 179, Short.MAX_VALUE)
                            .addComponent(txtHorario)
                            .addComponent(txtCargo)
                            .addComponent(txtEspecialidade))))
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelGym)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtXimnasio))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataNacemento))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAp1)
                            .addComponent(labelNombre)
                            .addComponent(labelAp2)
                            .addComponent(labelContraseña)
                            .addComponent(labelDni))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtApelido2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtApelido1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDni, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelMovil, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDireccion, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtAlta)
                        .addGap(18, 18, 18)
                        .addComponent(cmbTipoAlta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(83, 83, 83))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlta)
                    .addComponent(cmbTipoAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelContraseña))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApelido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApelido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAp2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMovil)
                    .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFecha)
                    .addComponent(txtDataNacemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGym)
                    .addComponent(txtXimnasio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelImporte)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSoldo)
                    .addComponent(txtSoldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetodoPago)
                    .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHorario)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTipo)
                    .addComponent(cmbTipoTraballador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCargo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMovilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMovilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMovilActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

         if (fa == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Erro: FachadaAplicacion non inicializada. Abre este diálogo desde VUsuario, non desde o main().",
                    "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            //recollemos os datos xerais que comparten tanto clientes coma traballadores limpando os espazos en branco con trim para evitar erros de formato na base de datos
            String dni = txtDni.getText().trim();
            String pass = txtPassword.getText().trim();
            String nome = txtNome.getText().trim();
            String apel1 = txtApelido1.getText().trim();
            String apel2 = txtApelido2.getText().trim();
            String email = txtEmail.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String movil = txtMovil.getText().trim();
            String dataTxt = txtDataNacemento.getText().trim();
            String ximnasio = txtXimnasio.getText().trim();

            //comprobamos que ningún dos campos que son obrigatorios NOT NULL na nosa base de datos estean baleiros antes de intentar facer a inserción
            if (dni.isEmpty() || pass.isEmpty() || nome.isEmpty() || apel1.isEmpty()
                    || email.isEmpty() || dataTxt.isEmpty() || ximnasio.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Faltan campos obrigatorios: DNI, contrasinal, nome, apelido1, email, data nacemento, ximnasio.",
                        "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date dataNac;
            try {
                //tentamos parsear a cadea de texto ao obxecto Date de java.sql que necesita o PreparedStatement
                dataNac = Date.valueOf(dataTxt);
            } catch (IllegalArgumentException ex) {
                //se o usuario escribe a data mal avisamos e cortamos a execución para non mandar lixo ao DAO
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Data de nacemento inválida. Formato correcto: YYYY-MM-DD",
                        "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tipoAlta = (String) cmbTipoAlta.getSelectedItem();

            //se o despregable esta en CLIENTE lemos tamén o importe e o metodo de pago para crear a primeira factura asociada
            if ("CLIENTE".equals(tipoAlta)) {
                String importeTxt = txtImporte.getText().trim();
                if (importeTxt.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Importe obrigatorio para o cliente.",
                            "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                BigDecimal importe;
                try {
                    //convertemos o texto ao tipo BigDecimal exacto para que non haxa problemas cos céntimos na base de datos
                    importe = new BigDecimal(importeTxt);
                } catch (NumberFormatException nfe) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Importe inválido (exemplo: 30.00).",
                            "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String metodoPago = (String) cmbMetodoPago.getSelectedItem();

                //creamos a instancia do obxecto UsuarioGym só cos atributos do cliente
                UsuarioGym u = new UsuarioGym(
                        dni, pass, nome, apel1, apel2,
                        direccion, movil, email, dataNac,
                        ximnasio, TipoUsuarioGym.CLIENTE
                );

                //chamamos á nosa fachada de aplicación para que inicie toda a transacción multi-sentenza no DAO
                boolean ok = fa.altaUsuarioClienteConFactura(u, importe, metodoPago);

                if (ok) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Cliente creado e factura inicial xerada.",
                            "OK", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    dispose(); //se todo foi ben pechamos esta ventá emerxente
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Non se puido crear o cliente. Revisa que non exista o DNI/email e que os datos sexan válidos.",
                            "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                return;
            }

            //se o despregable estaba en TRABALLADOR ignoramos a factura e pasamos a ler o soldo e as especialidades
            String soldoTxt = txtSoldo.getText().trim();
            String horario = txtHorario.getText().trim();
            String tipoTrabTxt = (String) cmbTipoTraballador.getSelectedItem();

            if (soldoTxt.isEmpty() || horario.isEmpty() || tipoTrabTxt == null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Faltan datos de traballador: soldo, horario e tipo.",
                        "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            BigDecimal soldo;
            try {
                soldo = new BigDecimal(soldoTxt);
            } catch (NumberFormatException nfe) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Soldo inválido (exemplo: 1200.00).",
                        "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            TipoTraballadorGym tipoTrab = TipoTraballadorGym.valueOf(tipoTrabTxt);

            String cargo = txtCargo.getText().trim();
            String especialidade = txtEspecialidade.getText().trim();

            //facemos unha validación extra para asegurarnos de que se o traballador é monitor si ou si se lle asigne unha disciplina
            if (tipoTrab == TipoTraballadorGym.MONITOR && especialidade.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Especialidade obrigatoria para MONITOR.",
                        "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            //instanciamos a clase filla TraballadorGym pasándolle tamén os datos herdados do pai UsuarioGym
            TraballadorGym t = new TraballadorGym(
                    dni, pass, nome, apel1, apel2,
                    direccion, movil, email, dataNac,
                    ximnasio, TipoUsuarioGym.TRABALLADOR,
                    soldo, horario, tipoTrab,
                    cargo, especialidade
            );

            boolean ok = fa.altaUsuarioTraballador(t);

            if (ok) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Traballador creado correctamente.",
                        "OK", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Non se puido crear o traballador. Revisa que non exista o DNI/email e que os datos sexan válidos.",
                        "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Erro inesperado: " + e.getMessage(),
                    "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DAltaUsuario dialog = new DAltaUsuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JComboBox<String> cmbTipoAlta;
    private javax.swing.JComboBox<String> cmbTipoTraballador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelAp1;
    private javax.swing.JLabel labelAp2;
    private javax.swing.JLabel labelCargo;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelDni;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelGym;
    private javax.swing.JLabel labelHorario;
    private javax.swing.JLabel labelImporte;
    private javax.swing.JLabel labelMetodoPago;
    private javax.swing.JLabel labelMovil;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelSoldo;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelTrabajador;
    private javax.swing.JLabel txtAlta;
    private javax.swing.JTextField txtApelido1;
    private javax.swing.JTextField txtApelido2;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtDataNacemento;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEspecialidade;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtMovil;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSoldo;
    private javax.swing.JTextField txtXimnasio;
    // End of variables declaration//GEN-END:variables
}
