package View;

import Controller.CTRLFlowMeasurement;
import Controller.CTRLUser;


public class Typist extends javax.swing.JFrame {

    CTRLUser CU = new CTRLUser();
    CTRLFlowMeasurement CF= new CTRLFlowMeasurement();
    
    private int userId;

    public Typist(int userId) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.userId = userId;;
        this.listUser();
        this.listFlow();
        this.cbxRoles();
        this.cbxEntity();
        this.cbxsNascent();
        this.cbxSampling();
        this.clear(); 
    }

    private void listUser() {
        this.CU.loadDataUserDigitador(tblUserDigitador, userId);
    }
    private void listFlow(){    
        this.CF.loadDataFlowMeasurements(tblFlow);
    }

    private void clear() {
        this.CU.clearFields(txtNameDigitador, txtFirst_NameDigitador, txtSecond_NameDigitador, txtEmailDigitador, txtpasswordDigitador);
        this.CF.clearFields(txtCapacity, txtMethod, txtObservation, txtDate);
    }
    private void cbxRoles() {
        boolean isAdmin = false; // Puedes ajustar este valor según tus necesidades.
        boolean isDigitador = true;
        this.CU.loadRole(cbxRoleUser, isAdmin, isDigitador);
    }
    private void cbxEntity() {
        this.CU.loadEntityDigitador(cbxEntity, userId);
    }
     private void cbxsNascent() {
        this.CF.loadNascent(cbxNascent);
    }
     private void cbxSampling(){
         this.CF.loadSampling(cbxSites);
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabAdmNaciente = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtNameDigitador = new javax.swing.JTextField();
        txtFirst_NameDigitador = new javax.swing.JTextField();
        txtSecond_NameDigitador = new javax.swing.JTextField();
        txtEmailDigitador = new javax.swing.JTextField();
        txtpasswordDigitador = new javax.swing.JTextField();
        jButtonExitUser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUserDigitador = new javax.swing.JTable();
        btnEditUser = new javax.swing.JButton();
        cbxEntity = new javax.swing.JComboBox<>();
        cbxRoleUser = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtCapacity = new javax.swing.JTextField();
        txtMethod = new javax.swing.JTextField();
        txtObservation = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        cbxDone = new javax.swing.JComboBox<>();
        cbxNascent = new javax.swing.JComboBox<>();
        cbxSites = new javax.swing.JComboBox<>();
        jButtonExitMeasurement = new javax.swing.JButton();
        btnAddFlow = new javax.swing.JButton();
        btnEditFlow = new javax.swing.JButton();
        btnDeleteFlow = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblFlow = new javax.swing.JTable();
        cbxWeather = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNameDigitador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNameDigitador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.add(txtNameDigitador, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 349, 50));

        txtFirst_NameDigitador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFirst_NameDigitador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Primer Apellido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.add(txtFirst_NameDigitador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 349, 50));

        txtSecond_NameDigitador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSecond_NameDigitador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Segundo Apellido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.add(txtSecond_NameDigitador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 349, -1));

        txtEmailDigitador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmailDigitador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Correo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.add(txtEmailDigitador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 349, 50));

        txtpasswordDigitador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtpasswordDigitador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contraseña", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.add(txtpasswordDigitador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 349, 50));

        jButtonExitUser.setForeground(new java.awt.Color(204, 0, 102));
        jButtonExitUser.setText("Atras");
        jButtonExitUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitUserActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonExitUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        tblUserDigitador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblUserDigitador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Primer Apellido", "Segundo Apellido", "Correo", "Contraseña", "Entidad", "Rol"
            }
        ));
        tblUserDigitador.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblUserDigitadorAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblUserDigitador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserDigitadorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUserDigitador);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 37, 840, 500));

        btnEditUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditUser.setText("Editar");
        btnEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUserActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 260, 49));

        cbxEntity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxEntity.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Entity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        cbxEntity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEntityItemStateChanged(evt);
            }
        });
        jPanel2.add(cbxEntity, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 350, 60));

        cbxRoleUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxRoleUser.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Rol", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        cbxRoleUser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxRoleUserItemStateChanged(evt);
            }
        });
        jPanel2.add(cbxRoleUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 350, 60));

        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 560));

        jTabAdmNaciente.addTab("Usuario", jPanel2);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCapacity.setEditable(false);
        txtCapacity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCapacity.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.add(txtCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 49));

        txtMethod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMethod.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Metodo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.add(txtMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 240, 50));

        txtObservation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtObservation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.add(txtObservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 240, 50));

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 240, 50));

        cbxDone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxDone.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));
        cbxDone.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Realizado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.add(cbxDone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 240, 50));

        cbxNascent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxNascent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxNascent.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Naciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        cbxNascent.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxNascentItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxNascent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 240, 50));

        cbxSites.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSites.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSites.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Sitio de Muestreo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        cbxSites.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSitesItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxSites, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 240, 50));

        jButtonExitMeasurement.setForeground(new java.awt.Color(153, 0, 102));
        jButtonExitMeasurement.setText("Atras");
        jButtonExitMeasurement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitMeasurementActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonExitMeasurement, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        btnAddFlow.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddFlow.setText("Agregar");
        btnAddFlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFlowActionPerformed(evt);
            }
        });
        jPanel5.add(btnAddFlow, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, 120, 50));

        btnEditFlow.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditFlow.setText("Editar");
        btnEditFlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditFlowActionPerformed(evt);
            }
        });
        jPanel5.add(btnEditFlow, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 500, 105, 50));

        btnDeleteFlow.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteFlow.setText("Eliminar");
        btnDeleteFlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFlowActionPerformed(evt);
            }
        });
        jPanel5.add(btnDeleteFlow, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 106, 50));

        tblFlow.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblFlow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Capacidad", "Metodo", "Observación", "Fecha", "Clima", "Realizado", "Naciente", "Sitio de Muestreo"
            }
        ));
        tblFlow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFlowMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblFlow);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 41, 974, 450));

        cbxWeather.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxWeather.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soleado", "Lluvioso", "Nublado" }));
        cbxWeather.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Clima", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.add(cbxWeather, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 240, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoMedicion-DIGI.jpg"))); // NOI18N
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, -1));

        jTabAdmNaciente.addTab("Medición", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabAdmNaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 1234, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabAdmNaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitMeasurementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitMeasurementActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Login log = new Login();
        log.setVisible(true);
    }//GEN-LAST:event_jButtonExitMeasurementActionPerformed

    private void jButtonExitUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitUserActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Login log = new Login();
        log.setVisible(true);
    }//GEN-LAST:event_jButtonExitUserActionPerformed

    private void tblUserDigitadorAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblUserDigitadorAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblUserDigitadorAncestorAdded

    private void btnEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUserActionPerformed
        // TODO add your handling code here:
        this.CU.updateUser(txtNameDigitador, txtFirst_NameDigitador, txtSecond_NameDigitador, txtEmailDigitador, txtpasswordDigitador);
        this.clear();
        this.listUser();
    }//GEN-LAST:event_btnEditUserActionPerformed

    private void tblUserDigitadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserDigitadorMouseClicked
        // TODO add your handling code here:
        this.CU.selectedRow(tblUserDigitador, txtNameDigitador, txtFirst_NameDigitador, txtSecond_NameDigitador, txtEmailDigitador, txtpasswordDigitador,cbxEntity,cbxRoleUser);
    }//GEN-LAST:event_tblUserDigitadorMouseClicked

    private void cbxRoleUserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxRoleUserItemStateChanged
        // TODO add your handling code here:
        this.CU.getIdRole(cbxRoleUser);
    }//GEN-LAST:event_cbxRoleUserItemStateChanged

    private void cbxEntityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEntityItemStateChanged
        // TODO add your handling code here:
        this.CU.getIdEntity(cbxEntity);
    }//GEN-LAST:event_cbxEntityItemStateChanged

    private void btnAddFlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFlowActionPerformed
        // TODO add your handling code here:
        this.CF.addFlowMeasurement(txtMethod, txtObservation, cbxWeather, cbxDone);
        this.clear();
        this.listFlow();
    }//GEN-LAST:event_btnAddFlowActionPerformed

    private void btnEditFlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditFlowActionPerformed
        // TODO add your handling code here:
        this.CF.updateflowMeasurement(txtCapacity, txtMethod, txtObservation, txtDate, cbxWeather, cbxDone);
        this.clear();
        this.listFlow();
    }//GEN-LAST:event_btnEditFlowActionPerformed

    private void btnDeleteFlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFlowActionPerformed
        // TODO add your handling code here:
        this.CF.deleteFlow();
        this.clear();
        this.listFlow();
    }//GEN-LAST:event_btnDeleteFlowActionPerformed

    private void tblFlowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFlowMouseClicked
        // TODO add your handling code here:
        this.CF.selectedRow(tblFlow, txtCapacity, txtMethod, txtObservation, txtDate, cbxWeather, cbxDone, cbxNascent, cbxSites);
    }//GEN-LAST:event_tblFlowMouseClicked

    private void cbxNascentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNascentItemStateChanged
        // TODO add your handling code here:
        this.CF.getIdNascent(cbxNascent);
    }//GEN-LAST:event_cbxNascentItemStateChanged

    private void cbxSitesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSitesItemStateChanged
        // TODO add your handling code here:
        this.CF.getIdSampling(cbxSites);
    }//GEN-LAST:event_cbxSitesItemStateChanged

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFlow;
    private javax.swing.JButton btnDeleteFlow;
    private javax.swing.JButton btnEditFlow;
    private javax.swing.JButton btnEditUser;
    private javax.swing.JComboBox<String> cbxDone;
    private javax.swing.JComboBox<String> cbxEntity;
    private javax.swing.JComboBox<String> cbxNascent;
    private javax.swing.JComboBox<String> cbxRoleUser;
    private javax.swing.JComboBox<String> cbxSites;
    private javax.swing.JComboBox<String> cbxWeather;
    private javax.swing.JButton jButtonExitMeasurement;
    private javax.swing.JButton jButtonExitUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabAdmNaciente;
    private javax.swing.JTable tblFlow;
    private javax.swing.JTable tblUserDigitador;
    private javax.swing.JTextField txtCapacity;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtEmailDigitador;
    private javax.swing.JTextField txtFirst_NameDigitador;
    private javax.swing.JTextField txtMethod;
    private javax.swing.JTextField txtNameDigitador;
    private javax.swing.JTextField txtObservation;
    private javax.swing.JTextField txtSecond_NameDigitador;
    private javax.swing.JTextField txtpasswordDigitador;
    // End of variables declaration//GEN-END:variables
}
