
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
public class diag_1 extends javax.swing.JFrame {

    /**
     * Creates new form diag_1
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
    
    public diag_1() throws SQLException {
        initComponents();
        this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight() - 30));
        this.setLocationRelativeTo(null);
        
        c = getConnection();
        st = c.createStatement();
        
        
        adherent1.setForeground(Color.white);
        auteur.setForeground(Color.white);
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
        
        String lv= null;
        //Livre
        String req1 = "select count(*) as nbrL from livre";
        re = st.executeQuery(req1);
        while (re.next()) 
        {
            re.getInt("nbrL");
            lv = re.getInt("nbrL")+"";
            livre.setText(lv);
        }
        
        
        //Periodique
        String pr= null;
        String req3 = "select count(*) as nbrP from periodique";
        re = st.executeQuery(req3);
        while (re.next()) 
        {
            re.getInt("nbrP");
            pr = re.getInt("nbrP")+"";
            jLabel6.setText(pr);
        }
        
        
        //Auteur
        String req5 = "select  count(DISTINCT  auteur) as nbrAt from livre";
        re = st.executeQuery(req5);
        while (re.next()) 
        {
            re.getInt("nbrAt");
            auteur.setText(""+re.getInt("nbrAt"));
            
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
        pnl = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        adherent1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        livre = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        auteur = new javax.swing.JLabel();
        pnl2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        pnl.setLayout(new javax.swing.BoxLayout(pnl, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adherent1.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        adherent1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adherent1.setText("00");
        jPanel2.add(adherent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        jPanel5.setBackground(new java.awt.Color(0, 204, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        livre.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        livre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        livre.setText("00");
        jPanel5.add(livre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        jPanel6.setBackground(new java.awt.Color(0, 102, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("00");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        jPanel4.setBackground(new java.awt.Color(153, 80, 50));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        auteur.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        auteur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        auteur.setText("00");
        jPanel4.add(auteur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        pnl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl2MouseEntered(evt);
            }
        });
        pnl2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                pnl2ComponentHidden(evt);
            }
        });
        pnl2.setLayout(new javax.swing.BoxLayout(pnl2, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setText("Editeur");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Editeur");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnl, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl2, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addComponent(pnl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(320, Short.MAX_VALUE))
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

    private void pnl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnl2MouseClicked

    private void pnl2ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnl2ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_pnl2ComponentHidden

    private void pnl2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl2MouseEntered
        boolean t = false;
       
        if(!t)
        {
            t=true;
                try {
            // TODO add your handling code here:
            float a = 0,b = 0;
            int l = 0,p = 0;
            
            DefaultPieDataset pieDatset = new DefaultPieDataset();
            
            String r ="select\n" +
                    "(select count(*)  from livre) as nbrL,\n" +
                    "(select count(*)  from periodique)as nbrP";
            re = st.executeQuery(r);
            while(re.next())
            {
                l = re.getInt("nbrL");
                p = re.getInt("nbrP");
            }

            String reqe1 = "select round((count(*) * 100) / "
                    + "cast((select count(*) from livre)+(select count(*) from periodique)AS FLOAT)"
                    + ",2) as nbrPL from livre";
            re = st.executeQuery(reqe1);
            while (re.next())
            {
                a = re.getFloat("nbrPL");
            }
            
            String req = "select round((count(*) * 100) / "
                    + "cast((select count(*) from livre)+(select count(*) from periodique)AS FLOAT)"
                    + ",2) as nbrPP from periodique";
            re = st.executeQuery(req);
            while (re.next())
            {
                b = re.getFloat("nbrPP");
            }
            
            
            pieDatset.setValue(l+" Livre", a);
            pieDatset.setValue(p+" Periodique", b);
            
            JFreeChart jchart = ChartFactory.createPieChart3D("le nombre (%) des livres / "
                    + "periodiques", pieDatset, true, true, true);
            jchart.setBackgroundPaint(Color.gray);
            
            ChartPanel chartpanel = new ChartPanel(jchart);
            
            pnl2.removeAll();
            pnl2.add(chartpanel);
            pnl2.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(diag_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_pnl2MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try 
        {
            float a = 0,b = 0;
            int l = 0,p = 0;
            
            String s,s1;
            DefaultPieDataset pieDatset = new DefaultPieDataset();
            
            String r ="select  count(DISTINCT editeur) as nbrLL from livre";
            re = st.executeQuery(r);
            while(re.next())
            {
                b = re.getInt("nbrLL");
                l = re.getInt("nbrLL");
            }

            String reqe1 = "select count(DISTINCT editeur) as nbrLP from periodique ";
            re = st.executeQuery(reqe1);
            while (re.next())
            {
                a = re.getFloat("nbrLP");
                p = re.getInt("nbrLP");
            }
            
            
            pieDatset.setValue(l+" Editeur par Livre", a);
            pieDatset.setValue(p+" Editeur par Periodique", b);
            
            JFreeChart jchart = ChartFactory.createPieChart3D("le nombre (%) d'editeur par livres / "
                    + "periodiques", pieDatset, true, true, true);
            jchart.setBackgroundPaint(Color.gray);
            
            ChartPanel chartpanel = new ChartPanel(jchart);
            
            pnl2.removeAll();
            pnl2.add(chartpanel);
            pnl2.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(diag_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            float a = 0,b = 0;
            int l = 0,p = 0;
            
            String s,s1;
            DefaultPieDataset pieDatset = new DefaultPieDataset();
            
            String r ="select  count(DISTINCT theme) as nbrLL from livre";
            re = st.executeQuery(r);
            while(re.next())
            {
                b = re.getInt("nbrLL");
                l = re.getInt("nbrLL");
            }

            String reqe1 = "select count(DISTINCT theme) as nbrLP from periodique ";
            re = st.executeQuery(reqe1);
            while (re.next())
            {
                a = re.getFloat("nbrLP");
                p = re.getInt("nbrLP");
            }
            
            
            pieDatset.setValue(l+" Themes par Livre", a);
            pieDatset.setValue(p+" THemes par Periodique", b);
            
            JFreeChart jchart = ChartFactory.createPieChart3D("le nombre (%) des themes par livres / "
                    + "periodiques", pieDatset, true, true, true);
            jchart.setBackgroundPaint(Color.gray);
            
            ChartPanel chartpanel = new ChartPanel(jchart);
            
            pnl2.removeAll();
            pnl2.add(chartpanel);
            pnl2.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(diag_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(diag_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diag_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diag_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diag_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new diag_1().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(diag_1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adherent1;
    private javax.swing.JLabel auteur;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel livre;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnl2;
    // End of variables declaration//GEN-END:variables
}