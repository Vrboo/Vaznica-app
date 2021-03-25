/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogy;

import javax.swing.JDialog;
import java.util.HashMap;

import vaznica.objektyvaznice.StupenNebezpecenstva;

/**
 *
 * @author dominik
 */
public class UvezniBankovehoLupicaDialog extends javax.swing.JDialog {
    private JDialog dialog;
    private HashMap<String , Object> preberaneUdaje;
    
    public UvezniBankovehoLupicaDialog(java.awt.Frame parent, boolean modal, HashMap<String , Object> preberaneUdaje) {
        super(parent, modal);
        initComponents();
        this.dialog = this;
        this.preberaneUdaje = preberaneUdaje;
        this.btngStpNe.add(this.jrbtnNizky);
        this.btngStpNe.add(this.jrbtnStredny);
        this.btngStpNe.add(this.jrbtnVysoky);
        this.preberaneUdaje.put("StlaceneUvezniBankLupica", new Boolean(false));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngStpNe = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxfCisloVezna = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jrbtnNizky = new javax.swing.JRadioButton();
        jrbtnStredny = new javax.swing.JRadioButton();
        jrbtnVysoky = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jtxfZdravStav = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxfPocRokOdsudenia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxfUkradCiastPen = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jbtnUvezniLupica = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Bankový lupič:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Číslo väzňa:");

        jtxfCisloVezna.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Stupeň nebezpečenstva:");

        btngStpNe.add(jrbtnNizky);
        jrbtnNizky.setText("Nízky");

        btngStpNe.add(jrbtnStredny);
        jrbtnStredny.setText("Stredný");
        jrbtnStredny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnStrednyActionPerformed(evt);
            }
        });

        btngStpNe.add(jrbtnVysoky);
        jrbtnVysoky.setText("Vysoký");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Zdravotný stav [%]:");

        jtxfZdravStav.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Počet rokov odsúdenia:");

        jtxfPocRokOdsudenia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Ukradnutá čiastka peňazí [€]:");

        jtxfUkradCiastPen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Počet povolených vycházok na dvor:");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jbtnUvezniLupica.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtnUvezniLupica.setText("Uväzni bankového lupiča");
        jbtnUvezniLupica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUvezniLupicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxfCisloVezna)
                    .addComponent(jtxfZdravStav)
                    .addComponent(jtxfPocRokOdsudenia)
                    .addComponent(jtxfUkradCiastPen)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbtnVysoky)
                            .addComponent(jrbtnStredny)
                            .addComponent(jrbtnNizky)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jbtnUvezniLupica))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxfCisloVezna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbtnNizky)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbtnStredny)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbtnVysoky)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxfZdravStav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxfPocRokOdsudenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxfUkradCiastPen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnUvezniLupica)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jrbtnStrednyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnStrednyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbtnStrednyActionPerformed

    private void jbtnUvezniLupicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUvezniLupicaActionPerformed
        StupenNebezpecenstva stNeb = null;
        this.preberaneUdaje.put("StlaceneUvezniBankLuppica", new Boolean(true));
        this.preberaneUdaje.put("cisloVezna", this.jtxfCisloVezna.getText());
        this.preberaneUdaje.put("pocetRokovOdsudenia", Integer.parseInt(this.jtxfPocRokOdsudenia.getText()));
        this.preberaneUdaje.put("zdravotnyStav", Double.parseDouble(this.jtxfZdravStav.getText()));
        this.preberaneUdaje.put("pocetPovolenychVych", Integer.parseInt((String)this.jComboBox1.getSelectedItem()));
        this.preberaneUdaje.put("ukradnutaCiastka", Double.parseDouble(this.jtxfUkradCiastPen.getText()));
        
        if (this.jrbtnNizky.isSelected()) {
            stNeb = StupenNebezpecenstva.NIZKY;
        } 
        if (this.jrbtnStredny.isSelected()) {
            stNeb = StupenNebezpecenstva.STREDNY;
        } 
        if (this.jrbtnVysoky.isSelected()) {
            stNeb = StupenNebezpecenstva.VYSOKY;
        } 
        this.preberaneUdaje.put("stupenNebezpecenstva", stNeb);
        this.dialog.setVisible(false);
    }//GEN-LAST:event_jbtnUvezniLupicaActionPerformed

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
            java.util.logging.Logger.getLogger(UvezniBankovehoLupicaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UvezniBankovehoLupicaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UvezniBankovehoLupicaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UvezniBankovehoLupicaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UvezniBankovehoLupicaDialog dialog = new UvezniBankovehoLupicaDialog(new javax.swing.JFrame(), true, new HashMap<String, Object>());
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
    private javax.swing.ButtonGroup btngStpNe;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton jbtnUvezniLupica;
    private javax.swing.JRadioButton jrbtnNizky;
    private javax.swing.JRadioButton jrbtnStredny;
    private javax.swing.JRadioButton jrbtnVysoky;
    private javax.swing.JTextField jtxfCisloVezna;
    private javax.swing.JTextField jtxfPocRokOdsudenia;
    private javax.swing.JTextField jtxfUkradCiastPen;
    private javax.swing.JTextField jtxfZdravStav;
    // End of variables declaration//GEN-END:variables
}
