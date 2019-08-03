/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import static GestionDesPrêts.DBconnect.connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VIP5-SEPT-17
 */
public class Rechercher extends javax.swing.JFrame {

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
    
    Statement st1,st2;
    ResultSet rst,rst2;
    DefaultTableModel model;
    public Rechercher() {
        try {
            initComponents();
            c = getConnection();
               
            model=(DefaultTableModel) livre.getModel();
            
            /*model.setRowCount(0);
            
        st=c.createStatement();
            rst=st.executeQuery("Select distinct genre from livre");
            while (rst.next()){
                genre.addItem(rst.getString("genre"));
            }     */
            //c = getConnection();
        /*st = c.createStatement();
        String req2 = "Select distinct genre from livre";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                    
                genre.addItem(re.getString("genre"));
                //model.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }*/
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numL = new javax.swing.JTextField();
        titreL = new javax.swing.JTextField();
        auteur = new javax.swing.JTextField();
        editeur = new javax.swing.JTextField();
        genre = new javax.swing.JComboBox<>();
        theme = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        recherche = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        livre = new javax.swing.JTable();
        Empreinter = new javax.swing.JButton();
        accueil = new javax.swing.JButton();
        dateAc = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        numL.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                numLAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        numL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numLMouseClicked(evt);
            }
        });
        numL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numLActionPerformed(evt);
            }
        });

        genre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genreMouseClicked(evt);
            }
        });
        genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreActionPerformed(evt);
            }
        });

        theme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                themeMouseClicked(evt);
            }
        });

        jLabel1.setText("Num");

        jLabel2.setText("Titre");

        jLabel3.setText("Auteur");

        jLabel4.setText("Editeur");

        jLabel5.setText("Genre");

        jLabel6.setText("Theme");

        recherche.setText("Recherche");
        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });

        livre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Titre", "Genre", "Theme", "Auteur", "Editeur", "Date d'acquisition", "Rayon", "Etage", "Exemplaires"
            }
        ));
        livre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                livreMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(livre);

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

        jLabel7.setText("Date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(accueil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(recherche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(dateAc, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(836, 836, 836)
                                .addComponent(Empreinter, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(200, 200, 200))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(genre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editeur)
                                    .addComponent(auteur)
                                    .addComponent(titreL)
                                    .addComponent(theme, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(numL))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titreL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(auteur, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editeur, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(theme, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Empreinter)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(dateAc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(recherche)
                .addGap(5, 5, 5)
                .addComponent(accueil))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genreMouseClicked
        try {                                   
            //ki yecliquer yekhredj les genre li kaynin f la BD yetafichaw
            genre.removeAllItems();
            theme.removeAllItems();
            /*try{
            //
            st=c.createStatement();
            rst=st.executeQuery("Select distinct genre from livre");
            while (rst.next()){
            genre.addItem(rst.getString("genre"));
            } 
            
            }catch (SQLException ex) {
            Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            genre.removeAllItems();
            theme.removeAllItems();
            c = getConnection();
            st = c.createStatement();
            String req2 = "Select distinct genre from livre";
            try {
                re = st.executeQuery(req2);
                while (re.next()) {
                    
                    genre.addItem(re.getString("genre"));
                    //model.addRow(t);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error :" + e.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE,null, ex);
        }   
    }//GEN-LAST:event_genreMouseClicked

    private void genreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genreActionPerformed

    private void themeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themeMouseClicked
            try {                                   
                //ki yecliquer ala theme yaffichilo les themes qui existe f la BD
                genre.removeAllItems();
                theme.removeAllItems();
                c = getConnection();
                st = c.createStatement();
                String req2 = "Select distinct theme from livre";
                try {
                    re = st.executeQuery(req2);
                    while (re.next()) {
                        
                        theme.addItem(re.getString("theme"));
                        //model.addRow(t);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error :" + e.toString());
                }
                
               /* theme.removeAllItems();
                genre.removeAllItems();
                try{
                    
                    st=c.createStatement();
                    rst=st.executeQuery("Select distinct theme from livre");
                    while (rst.next()){
                        theme.addItem(rst.getString("theme"));
                    }
                    
                }catch (SQLException ex) {
                    Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            } catch (SQLException ex) {
                Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE,null, ex);
            }
    }//GEN-LAST:event_themeMouseClicked

    private void numLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numLMouseClicked
        //*********** hna habit ki ydji y3amer un champs les autres ywello 
        //innaccessible mais ma3reftch  kifach :( sorry 

        /*if(numL.getText().length()>=1){
            titreL.setText("");
            auteur.setText("");
            editeur.setText("");
            genre.removeAllItems();
            theme.removeAllItems();
        }*/
    }//GEN-LAST:event_numLMouseClicked

    private void numLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numLActionPerformed
        
    }//GEN-LAST:event_numLActionPerformed

    private void numLAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_numLAncestorAdded
       
    }//GEN-LAST:event_numLAncestorAdded

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        try {
            //condition yvérifier ida makanch m3amer aucun champs yaffichilo message
            c = getConnection();
            if(("".equals(numL.getText())) && ("".equals(titreL.getText())) && ("".equals(auteur.getText())) && (genre.getSelectedIndex()==-1 )&& (theme.getSelectedIndex()==-1) && (dateAc.getDate()==null) ){
                JOptionPane.showMessageDialog(null, "Veuillez remplir un des champs vides");
            }
            
            //Recherche par numéro de livre
            if(!"".equals(numL.getText())){ //ki ymed un numero de livre
                try{
                    
                    st=c.createStatement();
                    rst=st.executeQuery("Select COUNT(*) as count from livre where nLivre='"+Integer.parseInt(numL.getText())+"'");
                    while(rst.next()){
                        if(rst.getInt("count")==0){ //si le num n'existe pas dans la BD
                            JOptionPane.showMessageDialog(null,"Livre n'existe pas");
                            numL.setText("");
                        }else{
                            st2=c.createStatement();
                            rst2=st2.executeQuery("Select distinct * from livre where nLivre='"+numL.getText()+"'");
                            int i=0;
                            model.getDataVector().removeAllElements();
                            model.setColumnCount(10);
                            while (rst2.next()){
                                if(rst2.getInt("nExemp")==0){//si makan aucun exemple
                                    JOptionPane.showMessageDialog(null, "Livre non disponible");
                                    numL.setText("");
                                }else{//yet3amer tableau
                                    model.addRow(new Object[WIDTH]);
                                    model.setValueAt(rst2.getInt("nLivre"), i, 0);
                                    model.setValueAt(rst2.getString("titre"), i, 1);
                                    model.setValueAt(rst2.getString("genre"), i, 2);
                                    model.setValueAt(rst2.getString("theme"), i, 3);
                                    model.setValueAt(rst2.getString("auteur"), i, 4);
                                    model.setValueAt(rst2.getString("editeur"), i, 5);
                                    model.setValueAt(rst2.getDate("dateAcquisition"), i, 6);
                                    model.setValueAt(rst2.getInt("nRayon"), i, 7);
                                    model.setValueAt(rst2.getInt("etage"), i, 8);
                                    model.setValueAt(rst2.getInt("nExemp"), i, 9);
                                    
                                    i++;
                                    livre.setEnabled(false);  
                                }
                            }
                        }
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //les autres recherche la meme chose kima la 1ere
            
            //Recherche par titre
            if(!"".equals(titreL.getText())){
                try{
                    
                    st=c.createStatement();
                    rst=st.executeQuery("Select COUNT(*) as count from livre where titre='"+titreL.getText()+"'");
                    while(rst.next()){
                        if(rst.getInt("count")==0){
                            JOptionPane.showMessageDialog(null,"Livre n'existe pas");
                            titreL.setText("");
                        }else{
                            st2=c.createStatement();
                            rst2=st2.executeQuery("Select distinct * from livre where titre='"+titreL.getText()+"'");
                            
                            int i=0;
                            model.getDataVector().removeAllElements();
                            model.setColumnCount(10);
                            while (rst.next()){
                                if(rst.getInt("nExemp")==0){
                                    JOptionPane.showMessageDialog(null, "Livre non disponible");
                                    titreL.setText("");
                                }else{
                                    model.addRow(new Object[WIDTH]);
                                    model.setValueAt(rst.getInt("nLivre"), i, 0);
                                    model.setValueAt(rst.getString("titre"), i, 1);
                                    model.setValueAt(rst.getString("genre"), i, 2);
                                    model.setValueAt(rst.getString("theme"), i, 3);
                                    model.setValueAt(rst.getString("auteur"), i, 4);
                                    model.setValueAt(rst.getString("editeur"), i, 5);
                                    model.setValueAt(rst.getDate("dateAcquisition"), i, 6);
                                    model.setValueAt(rst.getInt("nRayon"), i, 7);
                                    model.setValueAt(rst.getInt("etage"), i, 8);
                                    model.setValueAt(rst.getInt("nExemp"), i, 9);
                                    
                                    i++;
                                    livre.setEnabled(false);
                                } 
                            }
                        }
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            //Recherche par auteur
            
            if(!"".equals(auteur.getText())){
                try{
                    
                    st=c.createStatement();
                    rst=st.executeQuery("Select COUNT(*) as count from livre where auteur='"+auteur.getText()+"'");
                    
                    while(rst.next()){
                        
                        if(rst.getInt("count")==0){
                            JOptionPane.showMessageDialog(null,"Livre n'existe pas");
                            auteur.setText("");
                            
                        }else{
                            st2=c.createStatement();
                            rst2=st2.executeQuery("Select distinct * from livre where auteur='"+auteur.getText()+"'");
                            int i=0;
                            model.getDataVector().removeAllElements();
                            model.setColumnCount(10);
                            while (rst.next()){
                                if(rst.getInt("nExemp")==0){
                                    JOptionPane.showMessageDialog(null, "Livre non disponible");
                                    auteur.setText("");
                                }else{
                                    model.addRow(new Object[WIDTH]);
                                    model.setValueAt(rst.getInt("nLivre"), i, 0);
                                    model.setValueAt(rst.getString("titre"), i, 1);
                                    model.setValueAt(rst.getString("genre"), i, 2);
                                    model.setValueAt(rst.getString("theme"), i, 3);
                                    model.setValueAt(rst.getString("auteur"), i, 4);
                                    model.setValueAt(rst.getString("editeur"), i, 5);
                                    model.setValueAt(rst.getDate("dateAcquisition"), i, 6);
                                    model.setValueAt(rst.getInt("nRayon"), i, 7);
                                    model.setValueAt(rst.getInt("etage"), i, 8);
                                    model.setValueAt(rst.getInt("nExemp"), i, 9);
                                    
                                    i++;
                                    livre.setEnabled(false);  
                                }
                            }
                        } 
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            
            //Recherche par editeur
            
            if(!"".equals(editeur.getText())){
                try{
                    
                    st=c.createStatement();
                    rst=st.executeQuery("Select COUNT(*) as count from livre where editeur='"+editeur.getText()+"'");
                    
                    while(rst.next()){
                        
                        if(rst.getInt("count")==0){
                            JOptionPane.showMessageDialog(null,"Livre n'existe pas");
                            editeur.setText("");
                            
                        }else{
                            st2=c.createStatement();
                            rst2=st2.executeQuery("Select distinct * from livre where editeur='"+editeur.getText()+"'");
                            int i=0;
                            model.getDataVector().removeAllElements();
                            model.setColumnCount(10);
                            while (rst.next()){
                                if(rst.getInt("nExemp")==0){
                                    JOptionPane.showMessageDialog(null, "Livre non disponible");
                                    editeur.setText("");
                                }else{
                                    model.addRow(new Object[WIDTH]);
                                    model.setValueAt(rst.getInt("nLivre"), i, 0);
                                    model.setValueAt(rst.getString("titre"), i, 1);
                                    model.setValueAt(rst.getString("genre"), i, 2);
                                    model.setValueAt(rst.getString("theme"), i, 3);
                                    model.setValueAt(rst.getString("auteur"), i, 4);
                                    model.setValueAt(rst.getString("editeur"), i, 5);
                                    model.setValueAt(rst.getDate("dateAcquisition"), i, 6);
                                    model.setValueAt(rst.getInt("nRayon"), i, 7);
                                    model.setValueAt(rst.getInt("etage"), i, 8);
                                    model.setValueAt(rst.getInt("nExemp"), i, 9);
                                    
                                    i++;
                                    livre.setEnabled(false);  
                                }
                            }
                        }
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            

            
            //Recherche par genre
            if(genre.getSelectedIndex()>-1 ){
                
                try{
                    
                    st=c.createStatement();
                    rst=st.executeQuery("Select distinct * from livre where genre='"+genre.getSelectedItem().toString()+"'");
                    int i=0;
                    model.getDataVector().removeAllElements();
                    model.setColumnCount(10);
                    while (rst.next()){
                        if(rst.getInt("nExemp")==0){
                            JOptionPane.showMessageDialog(null, "Livre non disponible");
                            
                        }else{
                            model.addRow(new Object[WIDTH]);
                            model.setValueAt(rst.getInt("nLivre"), i, 0);
                            model.setValueAt(rst.getString("titre"), i, 1);
                            model.setValueAt(rst.getString("genre"), i, 2);
                            model.setValueAt(rst.getString("theme"), i, 3);
                            model.setValueAt(rst.getString("auteur"), i, 4);
                            model.setValueAt(rst.getString("editeur"), i, 5);
                            model.setValueAt(rst.getDate("dateAcquisition"), i, 6);
                            model.setValueAt(rst.getInt("nRayon"), i, 7);
                            model.setValueAt(rst.getInt("etage"), i, 8);
                            model.setValueAt(rst.getInt("nExemp"), i, 9);
                            
                            i++;
                            livre.setEnabled(false);  
                        }
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            

            //Recherche par theme
            if(theme.getSelectedIndex()>-1 ){
                
                try{
                    
                    st=c.createStatement();
                    rst=st.executeQuery("Select distinct * from livre where theme='"+theme.getSelectedItem().toString()+"'");
                    int i=0;
                    model.getDataVector().removeAllElements();
                    model.setColumnCount(10);
                    while (rst.next()){
                        if(rst.getInt("nExemp")==0){
                            JOptionPane.showMessageDialog(null, "Livre non disponible");
                            
                        }else{
                            model.addRow(new Object[WIDTH]);
                            model.setValueAt(rst.getInt("nLivre"), i, 0);
                            model.setValueAt(rst.getString("titre"), i, 1);
                            model.setValueAt(rst.getString("genre"), i, 2);
                            model.setValueAt(rst.getString("theme"), i, 3);
                            model.setValueAt(rst.getString("auteur"), i, 4);
                            model.setValueAt(rst.getString("editeur"), i, 5);
                            model.setValueAt(rst.getDate("dateAcquisition"), i, 6);
                            model.setValueAt(rst.getInt("nRayon"), i, 7);
                            model.setValueAt(rst.getInt("etage"), i, 8);
                            model.setValueAt(rst.getInt("nExemp"), i, 9);
                            
                            i++;
                            livre.setEnabled(false);  
                        }
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //Recherche par date d'acquisition
            if(dateAc.getDate()!=null){
                
                try{
                    
                    st=c.createStatement();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date d=new java.util.Date();
                    System.out.println(sdf.format(d));
                    
                    
                    rst=st.executeQuery("Select distinct * from livre where dateAcquisition between'"+sdf.format(dateAc.getDate())+"' and '"+sdf.format(d)+"'");
                    int i=0;
                    model.getDataVector().removeAllElements();
                    model.setColumnCount(10);
                    while (rst.next()){
                        if(rst.getInt("nExemp")==0){
                            JOptionPane.showMessageDialog(null, "Livre non disponible");
                            
                        }else{
                            model.addRow(new Object[WIDTH]);
                            model.setValueAt(rst.getInt("nLivre"), i, 0);
                            model.setValueAt(rst.getString("titre"), i, 1);
                            model.setValueAt(rst.getString("genre"), i, 2);
                            model.setValueAt(rst.getString("theme"), i, 3);
                            model.setValueAt(rst.getString("auteur"), i, 4);
                            model.setValueAt(rst.getString("editeur"), i, 5);
                            model.setValueAt(rst.getDate("dateAcquisition"), i, 6);
                            model.setValueAt(rst.getInt("nRayon"), i, 7);
                            model.setValueAt(rst.getInt("etage"), i, 8);
                            model.setValueAt(rst.getInt("nExemp"), i, 9);
                            
                            i++;
                            livre.setEnabled(false);  
                        }
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rechercher.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_rechercheActionPerformed

    private void EmpreinterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpreinterActionPerformed
        int n;
        ArrayList<Integer> L = new ArrayList<>();
        if(livre.getRowCount()!=0){
            JOptionPane.showMessageDialog(null, "Vous devez confirmer votre adhésion",null,JOptionPane.WARNING_MESSAGE);
            
                
                for(int i=0;i<livre.getRowCount();i++){
                    L.add(Integer.parseInt(livre.getValueAt(i, 0)+""));
                }
                
            String t="Livre";
            Vérifier v= new Vérifier(L,t);
            v.setVisible(true);
        }else{
             JOptionPane.showMessageDialog(null, "Vous devez faire une recherche d'abord ",null,JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_EmpreinterActionPerformed

    private void livreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_livreMouseClicked
      
    }//GEN-LAST:event_livreMouseClicked

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
            java.util.logging.Logger.getLogger(Rechercher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rechercher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Empreinter;
    private javax.swing.JButton accueil;
    private javax.swing.JTextField auteur;
    private com.toedter.calendar.JDateChooser dateAc;
    private javax.swing.JTextField editeur;
    private javax.swing.JComboBox<String> genre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable livre;
    private javax.swing.JTextField numL;
    private javax.swing.JButton recherche;
    private javax.swing.JComboBox<String> theme;
    private javax.swing.JTextField titreL;
    // End of variables declaration//GEN-END:variables
}
