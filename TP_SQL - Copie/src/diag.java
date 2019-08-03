
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Djimmy
 */
public class diag extends javax.swing.JFrame {

    /**
     * Creates new form diag
     */
    
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
            JOptionPane.showMessageDialog(null, "connecter avec succes");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
        
    public diag() throws SQLException {
        initComponents();
        this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight() - 30));
        this.setLocationRelativeTo(null);
        
        c = getConnection();
        st = c.createStatement();
        
        
        adherent.setForeground(Color.white);
        adherent1.setForeground(Color.white);
        auteur.setForeground(Color.white);
        editeur.setForeground(Color.white);
        livre.setForeground(Color.white);
        jLabel6.setForeground(Color.white);
        
        
        //Adherent
        String req = "select count(*) as nbrA from adherent";
        re = st.executeQuery(req);
        while (re.next()) 
        {
            re.getInt("nbrA");
            adherent1.setText(""+re.getInt("nbrA"));
        }
        
        String lv= null,lv1 = null;
        //Livre
        String req1 = "select count(*) as nbrL from livre";
        re = st.executeQuery(req1);
        while (re.next()) 
        {
            re.getInt("nbrL");
            lv = re.getInt("nbrL")+"";
        }
        
        //Livre %
        String req2 = "select round((count(*) * 100) / cast("
                + "(select count(*) from livre)+"
                + "(select count(*) from periodique)AS FLOAT),2) as nbrPL from livre";
        
        re = st.executeQuery(req2);
        while (re.next()) 
        {
            re.getInt("nbrPL");
            lv1 = re.getFloat("nbrPL")+"%";
        }
        livre.setText(lv+" - ("+lv1+")");
        
        //Periodique
        String pr= null,pr1 = null;
        String req3 = "select count(*) as nbrP from periodique";
        re = st.executeQuery(req3);
        while (re.next()) 
        {
            re.getInt("nbrP");
            pr = re.getInt("nbrP")+"";
        }
        
        //Periodique %
        String req4 = "select round((count(*) * 100) / cast("
                + "(select count(*) from livre)+"
                + "(select count(*) from periodique)AS FLOAT),2) as nbrPP from periodique";
        
        re = st.executeQuery(req4);
        while (re.next()) 
        {
            re.getInt("nbrPP");
            pr1 = re.getFloat("nbrPP")+"%";
        }
        jLabel6.setText(pr+" - ("+pr1+")");
        
        //Auteur
        String req5 = "select  count(DISTINCT  auteur) as nbrAt from livre";
        re = st.executeQuery(req5);
        while (re.next()) 
        {
            re.getInt("nbrAt");
            auteur.setText(""+re.getInt("nbrAt"));
            
        }
        
        //Maison d'edition
        String req6 = "select ((select  count(DISTINCT  editeur) from livre) + "
                    + "(select count(DISTINCT  editeur) from periodique)) as nbrE";
        re = st.executeQuery(req6);
        while (re.next()) 
        {
            re.getInt("nbrE");
            editeur.setText(""+re.getInt("nbrE"));
        }
       
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        //Chart
        String reqe = "select YEAR(dateInsc) as nbrY ,count(YEAR(dateInsc)) as nbrYA"
                + " from adherent group by YEAR(dateInsc)";
        re = st.executeQuery(reqe);
        while (re.next()) 
        {
                     dataset.addValue(re.getInt("nbrYA"), "Adherents", ""+re.getInt("nbrY"));
        }
        
        String reqe1 = "select YEAR(dateAcquisition) as nbrY ,count(YEAR(dateAcquisition)) as nbrYA"
                + " from livre group by YEAR(dateAcquisition)";
        re = st.executeQuery(reqe1);
        while (re.next()) 
        {
                     dataset.addValue(re.getInt("nbrYA"), "Livres", ""+re.getInt("nbrY"));
        }
        
        
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Adherents et livres",
                "Annes",
                "Nombre d'Adherents et des livres",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        lineChart.setBackgroundPaint(Color.gray);
        
        ChartPanel chartpanel = new ChartPanel(lineChart);

        pnl.removeAll();
        pnl.add(chartpanel);
        pnl.updateUI();

        
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        pnl = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        adherent1 = new javax.swing.JLabel();
        adherent = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        editeur = new javax.swing.JLabel();
        editeur1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        livre = new javax.swing.JLabel();
        livre1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        auteur = new javax.swing.JLabel();
        auteur1 = new javax.swing.JLabel();
        pnl1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jButton1.setText("Langues");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pnl.setLayout(new javax.swing.BoxLayout(pnl, javax.swing.BoxLayout.LINE_AXIS));

        jButton2.setText("Themes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Maisons d'edition");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adherent1.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        adherent1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adherent1.setText("00");
        jPanel2.add(adherent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 80));

        adherent.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        adherent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adherent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/User Group Man Man_100px.png"))); // NOI18N
        jPanel2.add(adherent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 80));

        jPanel3.setBackground(new java.awt.Color(204, 51, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editeur.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        editeur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editeur.setText("00");
        jPanel3.add(editeur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        editeur1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        editeur1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editeur1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Bungalow_96px.png"))); // NOI18N
        jPanel3.add(editeur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 80));

        jPanel5.setBackground(new java.awt.Color(0, 204, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        livre.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        livre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        livre.setText("00");
        jPanel5.add(livre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        livre1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        livre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        livre1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Book_104px.png"))); // NOI18N
        jPanel5.add(livre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 90));

        jPanel6.setBackground(new java.awt.Color(0, 102, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("00");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        jLabel7.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Magazine_104px.png"))); // NOI18N
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 100, 90));

        jPanel4.setBackground(new java.awt.Color(153, 80, 50));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        auteur.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        auteur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        auteur.setText("00");
        jPanel4.add(auteur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        auteur1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        auteur1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        auteur1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Businessman_100px.png"))); // NOI18N
        jPanel4.add(auteur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 80));

        pnl1.setLayout(new javax.swing.BoxLayout(pnl1, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnl, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1349, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            
            //Bar
            
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            
            String reqz1 = "select langue as l, count(langue) as nbrLL from livre group by langue";
            re = st.executeQuery(reqz1);
            while (re.next())
            {
                dcd.setValue(re.getInt("nbrLL"), ""+re.getString("l"), "livre");
            }
            
            String reqz2 = "select langue as l, count(langue) as nbrLP from periodique group by langue";
            re = st.executeQuery(reqz2);
            while (re.next())
            {
                dcd.setValue(re.getInt("nbrLP"), ""+re.getString("l"), "periodique");
            }
            
            
         
            JFreeChart jchart = ChartFactory.createBarChart3D("le nbr des docuents par langue",
                                                              "document",
                                                              "le nombre",
                                                              dcd,
                                                              PlotOrientation.VERTICAL,
                                                              rootPaneCheckingEnabled,
                                                              rootPaneCheckingEnabled,
                                                              rootPaneCheckingEnabled);
            
            jchart.setBackgroundPaint(Color.gray);
            CategoryPlot plot = jchart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.BLACK);
            
            /*ChartFrame chartframe = new ChartFrame("record",jchart,true);
            chartframe.setVisible(true);
            chartframe.setSize(500,400);*/
            ChartPanel chartpanel = new ChartPanel(jchart);
            
            pnl1.removeAll();
            pnl1.add(chartpanel);
            pnl1.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(diag.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            
            //Bar
            
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            
            String reqz1 = "select theme as l, count(theme) as nbrLL from livre group by theme";
            re = st.executeQuery(reqz1);
            while (re.next())
            {
                dcd.setValue(re.getInt("nbrLL"), ""+re.getString("l"), "livre");
            }
            
            String reqz2 = "select theme as l, count(theme) as nbrLP from periodique group by theme";
            re = st.executeQuery(reqz2);
            while (re.next())
            {
                dcd.setValue(re.getInt("nbrLP"), ""+re.getString("l"), "periodique");
            }
            
            
         
            JFreeChart jchart = ChartFactory.createBarChart3D("le nbr des docuents par theme",
                                                              "document",
                                                              "le nombre",
                                                              dcd,
                                                              PlotOrientation.VERTICAL,
                                                              rootPaneCheckingEnabled,
                                                              rootPaneCheckingEnabled,
                                                              rootPaneCheckingEnabled);
            
            jchart.setBackgroundPaint(Color.gray);
            CategoryPlot plot = jchart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.BLACK);
            
            /*ChartFrame chartframe = new ChartFrame("record",jchart,true);
            chartframe.setVisible(true);
            chartframe.setSize(500,400);*/
            ChartPanel chartpanel = new ChartPanel(jchart);
            
            pnl1.removeAll();
            pnl1.add(chartpanel);
            pnl1.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(diag.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            
            //Bar
            
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            
            String reqz1 = "select editeur as l, count(editeur) as nbrLL from livre group by editeur";
            re = st.executeQuery(reqz1);
            while (re.next())
            {
                dcd.setValue(re.getInt("nbrLL"), ""+re.getString("l"), "livre");
            }
            
            String reqz2 = "select editeur as l, count(editeur) as nbrLP from periodique group by editeur";
            re = st.executeQuery(reqz2);
            while (re.next())
            {
                dcd.setValue(re.getInt("nbrLP"), ""+re.getString("l"), "periodique");
            }
            
            
         
            JFreeChart jchart = ChartFactory.createBarChart3D("le nbr des docuents par maisons d'edition",
                                                              "document",
                                                              "le nombre",
                                                              dcd,
                                                              PlotOrientation.VERTICAL,
                                                              rootPaneCheckingEnabled,
                                                              rootPaneCheckingEnabled,
                                                              rootPaneCheckingEnabled);
            
            jchart.setBackgroundPaint(Color.gray);
            CategoryPlot plot = jchart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.BLACK);
            
            /*ChartFrame chartframe = new ChartFrame("record",jchart,true);
            chartframe.setVisible(true);
            chartframe.setSize(500,400);*/
            ChartPanel chartpanel = new ChartPanel(jchart);
            
            pnl1.removeAll();
            pnl1.add(chartpanel);
            pnl1.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(diag.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(diag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new diag().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(diag.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adherent;
    private javax.swing.JLabel adherent1;
    private javax.swing.JLabel auteur;
    private javax.swing.JLabel auteur1;
    private javax.swing.JLabel editeur;
    private javax.swing.JLabel editeur1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel livre;
    private javax.swing.JLabel livre1;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnl1;
    // End of variables declaration//GEN-END:variables
}
