package view;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.PlantaoBean;
import model.PlantaoDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import model.HospitalDAO;
import model.PlantaoBean.StatusPlantao;
import model.SessaoUsuario;


/**
 *
 * @author gaigu
 */

public class Inicio extends javax.swing.JFrame {
    SessaoUsuario session = SessaoUsuario.getInstance();
    HospitalDAO hospitalDAO = new HospitalDAO();
    DefaultTableModel model;

    public Inicio() {
        initComponents();
        initTable();
        esconderColunaID();
        listarPlantoes();
        initTableClickListener();
        if (!session.isLoggedIn()){
            sairButton.setVisible(false);
            meusPlantoes.setVisible(false);
        }
        if (session.isLoggedIn()){
            loginButton.setVisible(false);
        }
        TablePlantoes.getTableHeader().setReorderingAllowed(false);
    }
    
    private void esconderColunaID() {
        TablePlantoes.getColumnModel().getColumn(0).setMinWidth(0);
        TablePlantoes.getColumnModel().getColumn(0).setMaxWidth(0);
        TablePlantoes.getColumnModel().getColumn(0).setWidth(0);
        TablePlantoes.getColumnModel().getColumn(1).setMinWidth(0);
        TablePlantoes.getColumnModel().getColumn(1).setMaxWidth(0);
        TablePlantoes.getColumnModel().getColumn(1).setWidth(0);
    }
    
    private void initTable(){
        model = new DefaultTableModel(
            new Object[]{
                "id",
                "hospital_id",
                "Hospital",
                "Especialidade",
                "Data",
                "Hora Inicio",
                "Hora Fim",
                "Valor",
                "Status"
            }, 0
        );
    }
   
    private void initTableClickListener() {
    TablePlantoes.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (e.getClickCount() == 2) {

                if (!session.isLoggedIn()) {
                    JOptionPane.showMessageDialog(null, "É necessário realizar login para prosseguir.");
                    Login telaLogin = new Login();
                    telaLogin.setVisible(true);
                    dispose();
                    return;
                }
                
                int row = TablePlantoes.getSelectedRow();

                if (row >= 0) {
                    int id = (int) TablePlantoes.getValueAt(row, 0);
                    String hospital = (String) TablePlantoes.getValueAt(row, 1);
                    String especialidade = (String) TablePlantoes.getValueAt(row, 2);

                    LocalDate dateFormatted = (LocalDate) TablePlantoes.getValueAt(row, 3);
                    String inicioStr = (String) TablePlantoes.getValueAt(row, 4);
                    String fimStr = (String) TablePlantoes.getValueAt(row, 5);

                    LocalTime inicio = LocalTime.parse(inicioStr);
                    LocalTime fim = LocalTime.parse(fimStr);

                    Double valor = (Double) TablePlantoes.getValueAt(row, 6);
                    StatusPlantao status = (StatusPlantao) TablePlantoes.getValueAt(row, 7);

                    Timestamp timestampInicio = Timestamp.valueOf(dateFormatted.atTime(inicio));
                    Timestamp timestampFim = Timestamp.valueOf(dateFormatted.atTime(fim));

                    PlantaoBean plantao = new PlantaoBean(
                        id,
                        hospital,
                        especialidade,
                        dateFormatted,
                        timestampInicio,
                        timestampFim,
                        valor,
                        status
                    );

                    new PlantaoDetails(plantao).setVisible(true);
                    dispose();
                }
            }
        }
    });
    }
    
    private void listarPlantoes(){
        model = (DefaultTableModel) TablePlantoes.getModel();
        model.setRowCount(0);
        
        PlantaoDAO dao = new PlantaoDAO();
        List<PlantaoBean> plantoes = dao.listarPlantoes();
        
       
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        

        for (PlantaoBean p: plantoes){
           
            Timestamp horaInicio = (Timestamp) p.getHora_inicio();
            Timestamp horaFim = (Timestamp) p.getHora_fim();

            
            Object[] linha = {
                p.getId(),
                p.getTitulo(),
                p.getEspecialidade(),
                p.getData_plantao(),
                formatoHora.format(horaInicio),
                formatoHora.format(horaFim),
                p.getValor(),
                p.getStatus(),
            };
            model.addRow(linha);
        }
        
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        sairButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablePlantoes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        meusPlantoes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Início");

        jPanel1.setBackground(new java.awt.Color(249, 250, 251));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(250, 249, 251));

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));
        jPanel3.setForeground(new java.awt.Color(0, 255, 0));

        jLabel3.setBackground(new java.awt.Color(250, 249, 251));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\gaigu\\Downloads\\Logo Medlinker3.png")); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        loginButton.setBackground(new java.awt.Color(47, 128, 237));
        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loginButton.setForeground(new java.awt.Color(0, 0, 0));
        loginButton.setText("Fazer Login");
        loginButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Cadastrar Hospital");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Cadastrar Plantão");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        sairButton.setBackground(new java.awt.Color(255, 255, 255));
        sairButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sairButton.setForeground(new java.awt.Color(0, 0, 0));
        sairButton.setText("Sair da conta");
        sairButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sairButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sairButtonMouseClicked(evt);
            }
        });
        sairButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(410, 410, 410)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sairButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sairButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Plantões Médicos");

        TablePlantoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Hospital", "Especialidade", "Data", "Início", "Fim", "Valor", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablePlantoes);
        if (TablePlantoes.getColumnModel().getColumnCount() > 0) {
            TablePlantoes.getColumnModel().getColumn(0).setResizable(false);
            TablePlantoes.getColumnModel().getColumn(0).setPreferredWidth(0);
            TablePlantoes.getColumnModel().getColumn(1).setResizable(false);
            TablePlantoes.getColumnModel().getColumn(1).setPreferredWidth(200);
            TablePlantoes.getColumnModel().getColumn(2).setResizable(false);
            TablePlantoes.getColumnModel().getColumn(2).setPreferredWidth(200);
            TablePlantoes.getColumnModel().getColumn(3).setResizable(false);
            TablePlantoes.getColumnModel().getColumn(3).setPreferredWidth(100);
            TablePlantoes.getColumnModel().getColumn(4).setResizable(false);
            TablePlantoes.getColumnModel().getColumn(4).setPreferredWidth(100);
            TablePlantoes.getColumnModel().getColumn(5).setResizable(false);
            TablePlantoes.getColumnModel().getColumn(5).setPreferredWidth(100);
            TablePlantoes.getColumnModel().getColumn(6).setResizable(false);
            TablePlantoes.getColumnModel().getColumn(7).setResizable(false);
            TablePlantoes.getColumnModel().getColumn(7).setPreferredWidth(80);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 35)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("med");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText("Linker");

        meusPlantoes.setBackground(new java.awt.Color(255, 255, 255));
        meusPlantoes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        meusPlantoes.setForeground(new java.awt.Color(0, 0, 0));
        meusPlantoes.setText("Meus plantões");
        meusPlantoes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        meusPlantoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meusPlantoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(meusPlantoes, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meusPlantoes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (!session.isLoggedIn()) {
            JOptionPane.showMessageDialog(null, "É necessário realizar login para prosseguir.");
            Login telaLogin = new Login();
            telaLogin.setVisible(true);
            dispose();
            return;
        }
                
        CadastrarHospital telaCadastroHospital = new CadastrarHospital();

        telaCadastroHospital.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        Login telaLogin = new Login();

        telaLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Inicio telaInicio = new Inicio();
        telaInicio.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        if (!session.isLoggedIn()) {
            JOptionPane.showMessageDialog(null, "É necessário realizar login para prosseguir.");
            Login telaLogin = new Login();
            telaLogin.setVisible(true);
            dispose();
            return;
        }
        
        CadastrarPlantao telaPlantao = new CadastrarPlantao(hospitalDAO);
        telaPlantao.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void sairButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairButtonActionPerformed
        JanelaSair janelaSair = new JanelaSair();
        this.dispose();
        janelaSair.setVisible(true);
    }//GEN-LAST:event_sairButtonActionPerformed

    private void sairButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sairButtonMouseClicked
        
    }//GEN-LAST:event_sairButtonMouseClicked

    private void meusPlantoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meusPlantoesActionPerformed
        MeusPlantoes telaMeusPlantoes = new MeusPlantoes();
        telaMeusPlantoes.setVisible(true);
    }//GEN-LAST:event_meusPlantoesActionPerformed

    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
                
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablePlantoes;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton meusPlantoes;
    private javax.swing.JButton sairButton;
    // End of variables declaration//GEN-END:variables
}
