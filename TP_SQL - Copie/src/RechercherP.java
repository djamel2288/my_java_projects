/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VIP5-SEPT-17
 */
public class RechercherP extends javax.swing.JFrame {

    String url = "jdbc:sqlserver://localhost:1433;databaseName=bibliodec;user=enidde;password=azert";
    Connection c;
    Statement st;
    PreparedStatement ps;
    ResultSet re;

    public static Connection getConnection() throws SQLException {
        Connection con = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=bibliodec;user=enidde;password=azert";
        try {
            con = DriverManager.getConnection(url);
            JOptionPane.showMessageDialog(null, "connsecter avec succes");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    Statement st0,st2;
    ResultSet rst,rst2;
    DefaultTableModel model;
    public RechercherP() {
        initComponents();
        model=(DefaultTableModel) perio.getModel();   
    }
    
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numP = new javax.swing.JTextField();
        titreP = new javax.swing.JTextField();
        editeur = new javax.swing.JTextField();
        theme = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        recherche = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        perio = new javax.swing.JTable();
        Empreinter = new javax.swing.JButton();
        accueil = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        numP.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                numPAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        numP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numPMouseClicked(evt);
            }
        });
        numP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numPActionPerformed(evt);
            }
        });

        theme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                themeMouseClicked(evt);
            }
        });

        jLabel1.setText("Num");

        jLabel2.setText("Titre");

        jLabel4.setText("Editeur");

        jLabel6.setText("Theme");

        recherche.setText("Recherche");
        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });

        perio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Titre", "Theme", "Editeur", "Date d'acquisition"
            }
        ));
        perio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(perio);

        Empreinter.setText("Empreinter");
        Empreinter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpreinterActionPerformed(evt);
            }
        });

        accueil.setText("Accueil ");
        accueil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accueilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(accueil)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editeur, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(titreP, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(numP)
                            .addComponent(theme, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(recherche)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Empreinter, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titreP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editeur, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(theme, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40)
                        .addComponent(recherche))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(Empreinter)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accueil)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themeMouseClicked
        theme.removeAllItems();
        try{
            c = getConnection();
            //Connection cnx=connect();
            st = c.createStatement();
            //Connection cnx=connect();
            //st=cnx.createStatement();
            rst=st.executeQuery("Select distinct theme from livre");
            while (rst.next()){
                theme.addItem(rst.getString("theme"));
            } 
            
        }catch (SQLException ex) {
            Logger.getLogger(RechercherP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_themeMouseClicked

    
    private void numPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numPMouseClicked
        /*if(numL.getText().length()>=1){
            titreL.setText("");
            auteur.setText("");
            editeur.setText("");
            genre.removeAllItems();
            theme.removeAllItems();
        }*/
    }//GEN-LAST:event_numPMouseClicked

    private void numPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numPActionPerformed
        
    }//GEN-LAST:event_numPActionPerformed

    private void numPAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_numPAncestorAdded
       
    }//GEN-LAST:event_numPAncestorAdded

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        if(("".equals(numP.getText())) && ("".equals(titreP.getText())) && ("".equals(editeur.getText())) && (theme.getSelectedIndex()==-1 )){
            JOptionPane.showMessageDialog(null, "Veuillez remplir un des champs vides");
        }else{
            
        //Recherche par numéro de périodique
        if(!"".equals(numP.getText())){
            try{
                /*Connection cnx=connect();
                st=cnx.createStatement();*/
                c = getConnection();
            //Connection cnx=connect();
            st = c.createStatement();
                rst=st.executeQuery("Select COUNT(*) as count from periodique where nPeriodique='"+Integer.parseInt(numP.getText())+"'");
                while(rst.next()){
                    if(rst.getInt("count")==0){
                        JOptionPane.showMessageDialog(null,"Périodique n'existe pas");
                        numP.setText("");
                    
                    }else{
                        st=c.createStatement();
                        rst2=st.executeQuery("Select distinct * from periodique where nPeriodique='"+numP.getText()+"'");
                        int i=0;
                        model.getDataVector().removeAllElements();
                        model.setColumnCount(5);
                        while (rst2.next()){
                            model.addRow(new Object[WIDTH]);
                                model.setValueAt(rst2.getInt("nPeriodique"), i, 0);
                                model.setValueAt(rst2.getString("titre"), i, 1);
                                model.setValueAt(rst2.getString("theme"), i, 2);
                                model.setValueAt(rst2.getString("editeur"), i, 3);
                                model.setValueAt(rst2.getDate("dateAcquisition"), i, 4);
                            i++;
                            perio.setEnabled(false);  
                            numP.setText("");
                            
                        }
                    }
                }
            }catch (SQLException ex) {
                Logger.getLogger(RechercherP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Recherche par titre
        if(!"".equals(titreP.getText())){
            try{
                /*Connection cnx=connect();
                st=cnx.createStatement();*/
                c = getConnection();
            //Connection cnx=connect();
            st = c.createStatement();
                rst=st.executeQuery("Select COUNT(*) as count from periodique where titre='"+numP.getText()+"'");
                while(rst.next()){
                    if(rst.getInt("count")==0){
                        JOptionPane.showMessageDialog(null,"Périodique n'existe pas");
                        titreP.setText("");
                    
                    }else{
                        st=c.createStatement();
                        rst2=st.executeQuery("Select distinct * from periodique where titre='"+titreP.getText()+"'");
                        int i=0;
                        model.getDataVector().removeAllElements();
                        model.setColumnCount(5);
                        while (rst2.next()){
                            model.addRow(new Object[WIDTH]);
                                model.setValueAt(rst2.getInt("nPeriodique"), i, 0);
                                model.setValueAt(rst2.getString("titre"), i, 1);
                                model.setValueAt(rst2.getString("theme"), i, 2);
                                model.setValueAt(rst2.getString("editeur"), i, 3);
                                model.setValueAt(rst2.getDate("dateAcquisition"), i, 4);
                            i++;
                            perio.setEnabled(false);   
                        }
                    }
                }
            }catch (SQLException ex) {
                Logger.getLogger(RechercherP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Recherche par editeur
        if(!"".equals(editeur.getText())){
            try{
                c = getConnection();
            //Connection cnx=connect();
            st = c.createStatement();
               /* Connection cnx=connect();
                st=cnx.createStatement();*/
                rst=st.executeQuery("Select COUNT(*) as count from periodique where editeur='"+editeur.getText()+"'");
                while(rst.next()){
                    if(rst.getInt("count")==0){
                        JOptionPane.showMessageDialog(null,"Périodique n'existe pas");
                        editeur.setText("");
                    }else{
                        st=c.createStatement();
                        rst=st.executeQuery("Select distinct * from periodique where editeur='"+editeur.getText()+"'");
                        int i=0;
                        model.getDataVector().removeAllElements();
                        model.setColumnCount(5);
                        while (rst.next()){
                            model.addRow(new Object[WIDTH]);
                                model.setValueAt(rst.getInt("nPeriodique"), i, 0);
                                model.setValueAt(rst.getString("titre"), i, 1);
                                model.setValueAt(rst.getString("theme"), i, 2);
                                model.setValueAt(rst.getString("editeur"), i, 3);
                                model.setValueAt(rst.getDate("dateAcquisition"), i, 4);
                            i++;
                            perio.setEnabled(false);  
                        }
                    }
                }
            }catch (SQLException ex) {
                Logger.getLogger(RechercherP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        //Recherche par theme
        if(theme.getSelectedIndex()>-1){
            try{
                c = getConnection();
            //Connection cnx=connect();
            st = c.createStatement();
                /*Connection cnx=connect();
                st=cnx.createStatement();*/
                rst=st.executeQuery("Select distinct * from periodique where theme='"+theme.getSelectedItem().toString()+"'");
                int i=0;
                model.getDataVector().removeAllElements();
                model.setColumnCount(5);
                while (rst.next()){
                    model.addRow(new Object[WIDTH]);
                        model.setValueAt(rst.getInt("nPeriodique"), i, 0);
                        model.setValueAt(rst.getString("titre"), i, 1);
                        model.setValueAt(rst.getString("theme"), i, 2);
                        model.setValueAt(rst.getString("editeur"), i, 3);
                        model.setValueAt(rst.getDate("dateAcquisition"), i, 4);
                    i++;
                    perio.setEnabled(false);  
                } 
            }catch (SQLException ex) {
                Logger.getLogger(RechercherP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }//GEN-LAST:event_rechercheActionPerformed

    private void EmpreinterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpreinterActionPerformed
        int n;
        ArrayList<Integer> L = new ArrayList<>();
        if(perio.getRowCount()!=0){
            JOptionPane.showMessageDialog(null, "Vous devez confirmer votre adhésion",null,JOptionPane.WARNING_MESSAGE);
                for(int i=0;i<perio.getRowCount();i++){
                    L.add(Integer.parseInt(perio.getValueAt(i, 0)+""));
                }
            String t="Périodique";    
            Vérifier v= new Vérifier(L,t);
            v.setVisible(true);
        }else{
             JOptionPane.showMessageDialog(null, "Vous devez faire une recherche d'abord ",null,JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_EmpreinterActionPerformed

    private void perioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perioMouseClicked
      
    }//GEN-LAST:event_perioMouseClicked

    private void accueilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accueilActionPerformed
        this.setVisible(false);
        AccueilN a= new AccueilN();
        a.setVisible(true);
    }//GEN-LAST:event_accueilActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RechercherP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RechercherP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Empreinter;
    private javax.swing.JButton accueil;
    private javax.swing.JTextField editeur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numP;
    private javax.swing.JTable perio;
    private javax.swing.JButton recherche;
    private javax.swing.JComboBox<String> theme;
    private javax.swing.JTextField titreP;
    // End of variables declaration//GEN-END:variables
}
