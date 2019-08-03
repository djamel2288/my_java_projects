
import com.mxrck.autocompleter.TextAutoCompleter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Djimmy
 */
public class NewVeto extends javax.swing.JFrame {

    /**
     * Creates new form NewVeto
     */
    String url = "jdbc:sqlserver://localhost:1433;databaseName=TPBD;user=enidde;password=2703djm";
    Connection c;
    Statement st;
    PreparedStatement ps;
    ResultSet re;
    double tar;

    DefaultTableModel d = new DefaultTableModel();
    DefaultTableModel dd = new DefaultTableModel();
    DefaultTableModel table1 = new DefaultTableModel();
    DefaultTableModel table3 = new DefaultTableModel();
    DefaultTableModel table2 = new DefaultTableModel();
    DefaultTableModel table4 = new DefaultTableModel();

    String j[] = new String[1354];
    ArrayList<String> jj = new ArrayList<String>();

    private static String[] arr = {"autre"};

    String ImgPath = null;
    private TextAutoCompleter ac;

    public static Connection getConnection() throws SQLException {
        Connection con = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=TPBD;user=enidde;password=2703djm";
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

    public static Connection getConnectionn() throws SQLException {
        Connection con = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=bibliodec;user=enidde;password=2703djm";
        try {
            con = DriverManager.getConnection(url);
            //JOptionPane.showMessageDialog(null, "connecter avec succes");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public NewVeto() throws SQLException {

        initComponents();

        this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight() - 30));
        showDate();
        showTime();
        //.getTableHeader.setVisible(false)

        Adherent.setVisible(false);
        Periodiques.setVisible(false);
        Abonnements.setVisible(false);
        Office.setVisible(false);
        Revue.setVisible(false);

        lbl_4.setIcon(ResizeImage(lbl_4, "src/image/adherent.png", null));
        lbl_image.setIcon(ResizeImage(lbl_image, "src/image/SLR Back Side_104px.png", null));

        d = (DefaultTableModel) jTable1.getModel();

        this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight() - 30));
        c = getConnectionn();
        st = c.createStatement();

        d.setRowCount(0);
        String req2 = "select * from adherent";
        //JOptionPane.showMessageDialog(null, "succes");
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nAdhesion"),
                    re.getString("nom"),
                    re.getString("prenom"),
                    re.getString("mail"),
                    re.getString("dateInsc"),
                    re.getString("valdité")};
                d.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        /**
         * *************************************************************************
         */
        dd = (DefaultTableModel) tab2.getModel();
        dd.setRowCount(0);
        String req2q = "select * from empreinter";
        //JOptionPane.showMessageDialog(null, "succes");
        try {
            re = st.executeQuery(req2q);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nDoc"),
                    re.getString("typeDoc"),
                    re.getString("dateEmp"),
                    re.getString("nAdhesion")};
                dd.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        /**
         * *************************************************************************
         */
        errpss.setVisible(false);
        table3 = (DefaultTableModel) tab3.getModel();
        table3.setRowCount(0);
        String reqq = "select * from periodique";
        try {
            re = st.executeQuery(reqq);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nPeriodique"),
                    re.getString("titre"),
                    re.getString("theme"),
                    re.getString("editeur"),
                    re.getString("dateAcquisition"),
                    re.getString("langue")};
                table3.addRow(t);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        /**
         * *************************************************************************
         */
        table2 = (DefaultTableModel) tab.getModel();

        Date d1 = new Date();
        //dta.setDate(d1);
        Date d2 = new Date();
        //dta2.setDate(d2);

        try {
            c = getConnectionn();
            st = c.createStatement();
            //JOptionPane.showMessageDialog(null, "connecter avec succes");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        table2.setRowCount(0);
        String req4 = "select * from abonnement";
        try {
            re = st.executeQuery(req4);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nAbon"),
                    re.getString("tarifAbon"),
                    re.getString("dateDebut"),
                    re.getString("dateExpiration"),
                    re.getString("nRevue")};
                table2.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        /**
         * ****************************************************************
         */
        table1 = (DefaultTableModel) tab1.getModel();

        Date d1a = new Date();
        //dta.setDate(d1a);
        Date d2a = new Date();
        //dta2.setDate(d2a);

        try {
            c = getConnectionn();
            st = c.createStatement();
            //JOptionPane.showMessageDialog(null, "connecter avec succes");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        table1.setRowCount(0);
        String req4a = "select * from revue";
        try {
            re = st.executeQuery(req4a);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nRevue"),
                    re.getString("nParution"),
                    re.getString("nPeriodique")};
                table1.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        /**
         * ****************************************************************
         */
        c = getConnectionn();
        st = c.createStatement();

        errpss.setVisible(false);
        table4 = (DefaultTableModel) tab4.getModel();
        table4.setRowCount(0);

        table4.setRowCount(0);
        String req28 = "select * from livre";
        try {
            re = st.executeQuery(req28);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nLivre"),
                    re.getString("titre"),
                    re.getString("genre"),
                    re.getString("theme"),
                    re.getString("auteur"),
                    re.getString("editeur"),
                    re.getString("dateAcquisition"),
                    re.getString("nRayon"),
                    re.getString("etage"),
                    re.getString("langue")};
                table4.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        /**
         * *******************************************************************
         */
        c = getConnectionn();
        st = c.createStatement();

        adherent.setForeground(Color.white);
        adherent1.setForeground(Color.white);
        auteur.setForeground(Color.white);
        editeur.setForeground(Color.white);
        livre.setForeground(Color.white);
        periodique.setForeground(Color.white);

        //Adherent
        String req = "select count(*) as nbrA from adherent";
        re = st.executeQuery(req);
        while (re.next()) {
            re.getInt("nbrA");
            adherent1.setText("" + re.getInt("nbrA"));
        }

        String lv = null, lv1 = null;
        //Livre
        String req1 = "select count(*) as nbrL from livre";
        re = st.executeQuery(req1);
        while (re.next()) {
            re.getInt("nbrL");
            lv = re.getInt("nbrL") + "";
        }

        //Livre %
        String req22 = "select round((count(*) * 100) / cast("
                + "(select count(*) from livre)+"
                + "(select count(*) from periodique)AS FLOAT),2) as nbrPL from livre";

        re = st.executeQuery(req22);
        while (re.next()) {
            re.getInt("nbrPL");
            lv1 = re.getFloat("nbrPL") + "%";
        }
        livre.setText(lv + " - (" + lv1 + ")");

        //Periodique
        String pr = null, pr1 = null;
        String req3 = "select count(*) as nbrP from periodique";
        re = st.executeQuery(req3);
        while (re.next()) {
            re.getInt("nbrP");
            pr = re.getInt("nbrP") + "";
        }

        //Periodique %
        String req44 = "select round((count(*) * 100) / cast("
                + "(select count(*) from livre)+"
                + "(select count(*) from periodique)AS FLOAT),2) as nbrPP from periodique";

        re = st.executeQuery(req44);
        while (re.next()) {
            re.getInt("nbrPP");
            pr1 = re.getFloat("nbrPP") + "%";
        }
        periodique.setText(pr + " - (" + pr1 + ")");

        //Auteur
        String req5 = "select  count(DISTINCT  auteur) as nbrAt from livre";
        re = st.executeQuery(req5);
        while (re.next()) {
            re.getInt("nbrAt");
            auteur.setText("" + re.getInt("nbrAt"));

        }

        //Maison d'edition
        String req6 = "select ((select  count(DISTINCT  editeur) from livre) + "
                + "(select count(DISTINCT  editeur) from periodique)) as nbrE";
        re = st.executeQuery(req6);
        while (re.next()) {
            re.getInt("nbrE");
            editeur.setText("" + re.getInt("nbrE"));
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //Chart
        String reqe = "select YEAR(dateInsc) as nbrY ,count(YEAR(dateInsc)) as nbrYA"
                + " from adherent group by YEAR(dateInsc)";
        re = st.executeQuery(reqe);
        while (re.next()) {
            dataset.addValue(re.getInt("nbrYA"), "Adherents", "" + re.getInt("nbrY"));
        }

        String reqe1 = "select YEAR(dateAcquisition) as nbrY ,count(YEAR(dateAcquisition)) as nbrYA"
                + " from livre group by YEAR(dateAcquisition)";
        re = st.executeQuery(reqe1);
        while (re.next()) {
            dataset.addValue(re.getInt("nbrYA"), "Livres", "" + re.getInt("nbrY"));
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

    private void cargerAutoCompliter() {
        for (int i = 0; i < arr.length; i++) {
            ac.addItem(arr[i]);
        }
    }

    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(s.format(d));

    }

    void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss-a");
                time.setText(s.format(d));
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        ButtonUpd1 = new javax.swing.JButton();
        khayer1 = new javax.swing.JButton();
        lbl_image1 = new javax.swing.JLabel();
        s23 = new javax.swing.JSeparator();
        adr3 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        s24 = new javax.swing.JSeparator();
        val1 = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        s34 = new javax.swing.JSeparator();
        nom4 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        s49 = new javax.swing.JSeparator();
        dta7 = new com.toedter.calendar.JDateChooser();
        s50 = new javax.swing.JSeparator();
        nom5 = new javax.swing.JTextField();
        jDialog2 = new javax.swing.JDialog();
        jPanel20 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        nrv = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        npp = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        zz10 = new javax.swing.JTextField();
        ButtonUpdaa2 = new javax.swing.JButton();
        jDialog3 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        ButtonUpda2 = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        nomm6 = new javax.swing.JTextField();
        s51 = new javax.swing.JSeparator();
        jLabel88 = new javax.swing.JLabel();
        numm2 = new javax.swing.JTextField();
        prenomm6 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        s52 = new javax.swing.JSeparator();
        s53 = new javax.swing.JSeparator();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        s54 = new javax.swing.JSeparator();
        lng1 = new javax.swing.JComboBox<>();
        s55 = new javax.swing.JSeparator();
        jLabel92 = new javax.swing.JLabel();
        dta9 = new com.toedter.calendar.JDateChooser();
        jDialog4 = new javax.swing.JDialog();
        ButtonUpdaa1 = new javax.swing.JButton();
        s15 = new javax.swing.JSeparator();
        dure1 = new javax.swing.JComboBox<>();
        zz3 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        par1 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        s17 = new javax.swing.JSeparator();
        s22 = new javax.swing.JSeparator();
        aa = new com.toedter.calendar.JDateChooser();
        aa1 = new com.toedter.calendar.JDateChooser();
        jDialog5 = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        ButtonUpda4 = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        tire1 = new javax.swing.JTextField();
        s56 = new javax.swing.JSeparator();
        gr1 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        s57 = new javax.swing.JSeparator();
        s58 = new javax.swing.JSeparator();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        s59 = new javax.swing.JSeparator();
        lang1 = new javax.swing.JComboBox<>();
        aut1 = new javax.swing.JTextField();
        s28 = new javax.swing.JSeparator();
        jLabel55 = new javax.swing.JLabel();
        them1 = new javax.swing.JTextField();
        s29 = new javax.swing.JSeparator();
        jLabel56 = new javax.swing.JLabel();
        edit1 = new javax.swing.JTextField();
        s30 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        r1 = new javax.swing.JTextField();
        s60 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        ne1 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        s61 = new javax.swing.JSeparator();
        s62 = new javax.swing.JSeparator();
        vid13 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        dtd1 = new com.toedter.calendar.JDateChooser();
        jDialog6 = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tab2 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        tbtb = new javax.swing.JButton();
        btbt = new javax.swing.JButton();
        lang2 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        s63 = new javax.swing.JSeparator();
        tire2 = new javax.swing.JTextField();
        tire3 = new javax.swing.JTextField();
        p1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ex = new javax.swing.JLabel();
        ex1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        user2 = new javax.swing.JLabel();
        user5 = new javax.swing.JLabel();
        user6 = new javax.swing.JLabel();
        user7 = new javax.swing.JLabel();
        user8 = new javax.swing.JLabel();
        user3 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        lbl_4 = new javax.swing.JLabel();
        p5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        s4 = new javax.swing.JSeparator();
        vid2 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        nomm2 = new javax.swing.JTextField();
        s36 = new javax.swing.JSeparator();
        prenomm2 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        s40 = new javax.swing.JSeparator();
        s41 = new javax.swing.JSeparator();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        s44 = new javax.swing.JSeparator();
        adrm2 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        ButtonSrcha1 = new javax.swing.JButton();
        ButtonDlta2 = new javax.swing.JButton();
        ButtonUpda3 = new javax.swing.JButton();
        genderm2 = new javax.swing.JComboBox<>();
        ButtonADDa2 = new javax.swing.JButton();
        pss2 = new javax.swing.JPasswordField();
        errpss1 = new javax.swing.JLabel();
        vid3 = new javax.swing.JTextField();
        s9 = new javax.swing.JSeparator();
        jLabel43 = new javax.swing.JLabel();
        p3 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        p6 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        p4 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        p7 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        p2 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        s43 = new javax.swing.JSeparator();
        Office = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        pnl = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        adherent1 = new javax.swing.JLabel();
        adherent = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        editeur = new javax.swing.JLabel();
        editeur1 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        livre = new javax.swing.JLabel();
        livre1 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        periodique = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        auteur = new javax.swing.JLabel();
        auteur1 = new javax.swing.JLabel();
        pnl1 = new javax.swing.JPanel();
        Adherent = new javax.swing.JScrollPane();
        tp1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ptid2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        nom1 = new javax.swing.JTextField();
        s3 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        s6 = new javax.swing.JSeparator();
        s8 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        s7 = new javax.swing.JSeparator();
        adr1 = new javax.swing.JTextField();
        ButtonDlt = new javax.swing.JButton();
        ButtonUpd = new javax.swing.JButton();
        val = new javax.swing.JComboBox<>();
        ButtonADD = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        khayer = new javax.swing.JButton();
        lbl_image = new javax.swing.JLabel();
        rstt = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        ptid = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        s19 = new javax.swing.JSeparator();
        nom3 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        dta6 = new com.toedter.calendar.JDateChooser();
        jLabel50 = new javax.swing.JLabel();
        ButtonSrch1 = new javax.swing.JButton();
        empt = new javax.swing.JButton();
        Periodiques = new javax.swing.JScrollPane();
        tp3 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tab3 = new javax.swing.JTable();
        ptid3 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        s2 = new javax.swing.JSeparator();
        vid = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        nomm1 = new javax.swing.JTextField();
        s35 = new javax.swing.JSeparator();
        jLabel73 = new javax.swing.JLabel();
        numm1 = new javax.swing.JTextField();
        prenomm1 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        s37 = new javax.swing.JSeparator();
        s38 = new javax.swing.JSeparator();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        s39 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        ButtonSrcha = new javax.swing.JButton();
        ButtonDlta = new javax.swing.JButton();
        ButtonUpda = new javax.swing.JButton();
        lng = new javax.swing.JComboBox<>();
        ButtonADDa = new javax.swing.JButton();
        errpss = new javax.swing.JLabel();
        s42 = new javax.swing.JSeparator();
        jLabel86 = new javax.swing.JLabel();
        dta8 = new com.toedter.calendar.JDateChooser();
        Livres = new javax.swing.JScrollPane();
        tp4 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tab4 = new javax.swing.JTable();
        ptid4 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        s16 = new javax.swing.JSeparator();
        vid6 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        tire = new javax.swing.JTextField();
        s45 = new javax.swing.JSeparator();
        gr = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        s46 = new javax.swing.JSeparator();
        s47 = new javax.swing.JSeparator();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        s48 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        ButtonSrcha2 = new javax.swing.JButton();
        ButtonDlta3 = new javax.swing.JButton();
        ButtonUpda5 = new javax.swing.JButton();
        lang = new javax.swing.JComboBox<>();
        ButtonADDa3 = new javax.swing.JButton();
        errpss2 = new javax.swing.JLabel();
        aut = new javax.swing.JTextField();
        s18 = new javax.swing.JSeparator();
        jLabel47 = new javax.swing.JLabel();
        them = new javax.swing.JTextField();
        s20 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        edit = new javax.swing.JTextField();
        s21 = new javax.swing.JSeparator();
        jLabel49 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        r = new javax.swing.JTextField();
        s25 = new javax.swing.JSeparator();
        jLabel51 = new javax.swing.JLabel();
        ne = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        s26 = new javax.swing.JSeparator();
        s27 = new javax.swing.JSeparator();
        vid12 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        dtd = new com.toedter.calendar.JDateChooser();
        Abonnements = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tab = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        ButtonADDa1 = new javax.swing.JButton();
        ButtonUpda1 = new javax.swing.JButton();
        ButtonDlta1 = new javax.swing.JButton();
        zz = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        zz2 = new javax.swing.JTextField();
        par = new javax.swing.JComboBox<>();
        s13 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        dure = new javax.swing.JComboBox<>();
        s14 = new javax.swing.JSeparator();
        Revue = new javax.swing.JScrollPane();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tab1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        ButtonADDa4 = new javax.swing.JButton();
        ButtonUpda6 = new javax.swing.JButton();
        ButtonDlta4 = new javax.swing.JButton();
        zz1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        zz7 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        zz8 = new javax.swing.JTextField();

        jDialog1.setBackground(new java.awt.Color(222, 222, 222));
        jDialog1.setLocation(new java.awt.Point(0, 0));
        jDialog1.setMinimumSize(new java.awt.Dimension(910, 448));
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ButtonUpd1.setBackground(new java.awt.Color(16, 106, 143));
        ButtonUpd1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Change User_40px.png"))); // NOI18N
        ButtonUpd1.setText("UpDate");
        ButtonUpd1.setBorder(null);
        ButtonUpd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpd1ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(ButtonUpd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 150, 53));

        khayer1.setText("Selectioner");
        khayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khayer1ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(khayer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 230, 160, 55));

        lbl_image1.setBackground(new java.awt.Color(0, 255, 0));
        lbl_image1.setForeground(new java.awt.Color(200, 200, 200));
        jDialog1.getContentPane().add(lbl_image1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 160, 170));

        s23.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 670, 10));

        adr3.setBackground(new java.awt.Color(222, 222, 222));
        adr3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        adr3.setForeground(new java.awt.Color(0, 0, 0));
        adr3.setBorder(null);
        adr3.setCaretColor(new java.awt.Color(255, 153, 0));
        adr3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adr3FocusGained(evt);
            }
        });
        adr3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                adr3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        adr3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adr3ActionPerformed(evt);
            }
        });
        adr3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adr3KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adr3KeyTyped(evt);
            }
        });
        jDialog1.getContentPane().add(adr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 670, 22));

        jLabel52.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(35, 69, 103));
        jLabel52.setText("email :");
        jDialog1.getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 22));
        jDialog1.getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 800, 10));

        s24.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s24, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 250, 20));

        val1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        val1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                val1ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(val1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 250, -1));

        jLabel64.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(35, 69, 103));
        jLabel64.setText("validité :");
        jDialog1.getContentPane().add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 100, 22));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(35, 69, 103));
        jLabel32.setText("Nom :");
        jDialog1.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 22));

        s34.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s34, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 250, 10));

        nom4.setBackground(new java.awt.Color(222, 222, 222));
        nom4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nom4.setForeground(new java.awt.Color(0, 0, 0));
        nom4.setBorder(null);
        nom4.setCaretColor(new java.awt.Color(255, 153, 0));
        nom4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nom4FocusGained(evt);
            }
        });
        nom4.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nom4CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nom4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom4ActionPerformed(evt);
            }
        });
        nom4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nom4KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nom4KeyTyped(evt);
            }
        });
        jDialog1.getContentPane().add(nom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 250, 22));

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(35, 69, 103));
        jLabel34.setText("Prenom :");
        jDialog1.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, 22));

        jLabel68.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(35, 69, 103));
        jLabel68.setText("Date");
        jDialog1.getContentPane().add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 60, -1));

        jLabel69.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(35, 69, 103));
        jLabel69.setText("Inscription :");
        jDialog1.getContentPane().add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 140, -1));

        s49.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s49, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 250, 10));

        dta7.setForeground(new java.awt.Color(0, 0, 0));
        dta7.setToolTipText("");
        dta7.setDateFormatString("yyyy-MM-dd");
        dta7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dta7KeyTyped(evt);
            }
        });
        jDialog1.getContentPane().add(dta7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 250, 30));

        s50.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s50, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 270, 10));

        nom5.setBackground(new java.awt.Color(222, 222, 222));
        nom5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nom5.setForeground(new java.awt.Color(0, 0, 0));
        nom5.setBorder(null);
        nom5.setCaretColor(new java.awt.Color(255, 153, 0));
        nom5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nom5FocusGained(evt);
            }
        });
        nom5.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nom5CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nom5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom5ActionPerformed(evt);
            }
        });
        nom5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nom5KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nom5KeyTyped(evt);
            }
        });
        jDialog1.getContentPane().add(nom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 270, 22));

        jPanel20.setBackground(new java.awt.Color(204, 204, 204));

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("N°Revue :");

        nrv.setBackground(new java.awt.Color(255, 255, 255));
        nrv.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        nrv.setForeground(new java.awt.Color(0, 0, 0));
        nrv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nrvActionPerformed(evt);
            }
        });
        nrv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nrvKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nrvKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("N°Parution :");

        npp.setBackground(new java.awt.Color(255, 255, 255));
        npp.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        npp.setForeground(new java.awt.Color(0, 0, 0));
        npp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nppActionPerformed(evt);
            }
        });
        npp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nppKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nppKeyTyped(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("N°Periodique :");

        zz10.setBackground(new java.awt.Color(255, 255, 255));
        zz10.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz10.setForeground(new java.awt.Color(0, 0, 0));
        zz10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zz10ActionPerformed(evt);
            }
        });
        zz10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zz10KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zz10KeyTyped(evt);
            }
        });

        ButtonUpdaa2.setBackground(new java.awt.Color(16, 106, 143));
        ButtonUpdaa2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpdaa2.setText("UpDate");
        ButtonUpdaa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdaa2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nrv, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(npp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zz10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(ButtonUpdaa2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(npp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(nrv, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46)
                    .addComponent(zz10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addComponent(ButtonUpdaa2)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog3.setMinimumSize(new java.awt.Dimension(900, 513));

        jPanel3.setBackground(new java.awt.Color(222, 222, 222));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ButtonUpda2.setBackground(new java.awt.Color(16, 106, 143));
        ButtonUpda2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpda2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Change User_40px.png"))); // NOI18N
        ButtonUpda2.setText("UpDate");
        ButtonUpda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpda2ActionPerformed(evt);
            }
        });
        jPanel3.add(ButtonUpda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, -1, 53));

        jLabel87.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(35, 69, 103));
        jLabel87.setText("Titre :");
        jPanel3.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 22));

        nomm6.setBackground(new java.awt.Color(222, 222, 222));
        nomm6.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nomm6.setForeground(new java.awt.Color(0, 0, 0));
        nomm6.setBorder(null);
        nomm6.setCaretColor(new java.awt.Color(255, 153, 0));
        nomm6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nomm6FocusGained(evt);
            }
        });
        nomm6.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nomm6CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nomm6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomm6ActionPerformed(evt);
            }
        });
        nomm6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomm6KeyTyped(evt);
            }
        });
        jPanel3.add(nomm6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 158, 22));

        s51.setBackground(new java.awt.Color(129, 187, 118));
        jPanel3.add(s51, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 158, 10));

        jLabel88.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(35, 69, 103));
        jLabel88.setText("Editeur :");
        jPanel3.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 22));

        numm2.setBackground(new java.awt.Color(222, 222, 222));
        numm2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        numm2.setForeground(new java.awt.Color(0, 0, 0));
        numm2.setBorder(null);
        numm2.setCaretColor(new java.awt.Color(255, 153, 0));
        numm2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                numm2FocusGained(evt);
            }
        });
        numm2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                numm2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        numm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numm2ActionPerformed(evt);
            }
        });
        numm2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numm2KeyTyped(evt);
            }
        });
        jPanel3.add(numm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 158, 22));

        prenomm6.setBackground(new java.awt.Color(222, 222, 222));
        prenomm6.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        prenomm6.setForeground(new java.awt.Color(0, 0, 0));
        prenomm6.setBorder(null);
        prenomm6.setCaretColor(new java.awt.Color(255, 153, 0));
        prenomm6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                prenomm6FocusGained(evt);
            }
        });
        prenomm6.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                prenomm6CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        prenomm6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomm6ActionPerformed(evt);
            }
        });
        prenomm6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenomm6KeyTyped(evt);
            }
        });
        jPanel3.add(prenomm6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 158, 22));

        jLabel89.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(35, 69, 103));
        jLabel89.setText("Theme :");
        jPanel3.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, 22));

        s52.setBackground(new java.awt.Color(129, 187, 118));
        jPanel3.add(s52, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 158, 10));

        s53.setBackground(new java.awt.Color(129, 187, 118));
        jPanel3.add(s53, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 158, 10));

        jLabel90.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(35, 69, 103));
        jLabel90.setText("Acquisition");
        jPanel3.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, 22));

        jLabel91.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(35, 69, 103));
        jLabel91.setText("Langue :");
        jPanel3.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 22));

        s54.setBackground(new java.awt.Color(129, 187, 118));
        jPanel3.add(s54, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 170, 10));

        lng1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arabe", "Anglais", "Tamzaght", "Francais" }));
        jPanel3.add(lng1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 158, -1));

        s55.setBackground(new java.awt.Color(129, 187, 118));
        jPanel3.add(s55, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 158, 10));

        jLabel92.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(35, 69, 103));
        jLabel92.setText("Date");
        jPanel3.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, 22));

        dta9.setForeground(new java.awt.Color(0, 0, 0));
        dta9.setToolTipText("");
        dta9.setDateFormatString("yyyy-MM-dd");
        dta9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dta9KeyTyped(evt);
            }
        });
        jPanel3.add(dta9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 160, 30));

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
        );

        jDialog4.setBackground(new java.awt.Color(222, 222, 222));
        jDialog4.setLocation(new java.awt.Point(0, 0));
        jDialog4.setMinimumSize(new java.awt.Dimension(924, 293));

        ButtonUpdaa1.setBackground(new java.awt.Color(16, 106, 143));
        ButtonUpdaa1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpdaa1.setText("UpDate");
        ButtonUpdaa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdaa1ActionPerformed(evt);
            }
        });

        s15.setBackground(new java.awt.Color(33, 155, 191));

        dure1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));

        zz3.setBackground(new java.awt.Color(255, 255, 255));
        zz3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz3.setForeground(new java.awt.Color(0, 0, 0));
        zz3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zz3ActionPerformed(evt);
            }
        });
        zz3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zz3KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zz3KeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("N°Revue :");

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Duré :");

        par1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mois", "Annes" }));
        par1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                par1MouseClicked(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Par :");

        s17.setBackground(new java.awt.Color(33, 155, 191));

        s22.setBackground(new java.awt.Color(33, 155, 191));

        aa.setToolTipText("");
        aa.setDateFormatString("yyyy-MM-dd");

        aa1.setToolTipText("");
        aa1.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(ButtonUpdaa1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialog4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(13, 13, 13)
                        .addComponent(zz3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(s22, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog4Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(s17, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(s15, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel28)
                        .addGap(24, 24, 24)
                        .addComponent(par1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel26)
                        .addGap(23, 23, 23)
                        .addComponent(dure1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(aa1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aa1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zz3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialog4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel28)
                            .addComponent(par1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(dure1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(ButtonUpdaa1))
        );

        jDialog5.setMinimumSize(new java.awt.Dimension(900, 513));

        jPanel5.setBackground(new java.awt.Color(222, 222, 222));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ButtonUpda4.setBackground(new java.awt.Color(16, 106, 143));
        ButtonUpda4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpda4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Change User_40px.png"))); // NOI18N
        ButtonUpda4.setText("UpDate");
        ButtonUpda4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpda4ActionPerformed(evt);
            }
        });
        jPanel5.add(ButtonUpda4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 160, 53));

        jLabel94.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(35, 69, 103));
        jLabel94.setText("Titre :");
        jPanel5.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 22));

        tire1.setBackground(new java.awt.Color(222, 222, 222));
        tire1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        tire1.setForeground(new java.awt.Color(0, 0, 0));
        tire1.setBorder(null);
        tire1.setCaretColor(new java.awt.Color(255, 153, 0));
        tire1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tire1FocusGained(evt);
            }
        });
        tire1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tire1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tire1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tire1ActionPerformed(evt);
            }
        });
        tire1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tire1KeyTyped(evt);
            }
        });
        jPanel5.add(tire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 158, 22));

        s56.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s56, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 158, 10));

        gr1.setBackground(new java.awt.Color(222, 222, 222));
        gr1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        gr1.setForeground(new java.awt.Color(0, 0, 0));
        gr1.setBorder(null);
        gr1.setCaretColor(new java.awt.Color(255, 153, 0));
        gr1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                gr1FocusGained(evt);
            }
        });
        gr1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                gr1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        gr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gr1ActionPerformed(evt);
            }
        });
        gr1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gr1KeyTyped(evt);
            }
        });
        jPanel5.add(gr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 158, 22));

        jLabel95.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(35, 69, 103));
        jLabel95.setText("Genre :");
        jPanel5.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, 22));

        s57.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s57, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 158, 10));

        s58.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s58, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 160, 10));

        jLabel96.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(35, 69, 103));
        jLabel96.setText("Langue :");
        jPanel5.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 30));

        jLabel97.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(35, 69, 103));
        jLabel97.setText("Acquisition :");
        jPanel5.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 150, 22));

        s59.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s59, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 160, 10));

        lang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arabe", "Anglais", "Tamazight", "Francais" }));
        lang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lang1ActionPerformed(evt);
            }
        });
        jPanel5.add(lang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 160, -1));

        aut1.setBackground(new java.awt.Color(222, 222, 222));
        aut1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        aut1.setForeground(new java.awt.Color(0, 0, 0));
        aut1.setBorder(null);
        aut1.setCaretColor(new java.awt.Color(255, 153, 0));
        aut1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                aut1FocusGained(evt);
            }
        });
        aut1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                aut1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        aut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aut1ActionPerformed(evt);
            }
        });
        aut1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                aut1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aut1KeyTyped(evt);
            }
        });
        jPanel5.add(aut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 150, 22));

        s28.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s28, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 150, 10));

        jLabel55.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(35, 69, 103));
        jLabel55.setText("Auteur :");
        jPanel5.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, 22));

        them1.setBackground(new java.awt.Color(222, 222, 222));
        them1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        them1.setForeground(new java.awt.Color(0, 0, 0));
        them1.setBorder(null);
        them1.setCaretColor(new java.awt.Color(255, 153, 0));
        them1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                them1FocusGained(evt);
            }
        });
        them1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                them1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                them1ActionPerformed(evt);
            }
        });
        them1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                them1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                them1KeyTyped(evt);
            }
        });
        jPanel5.add(them1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 150, 22));

        s29.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s29, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 110, 150, 10));

        jLabel56.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(35, 69, 103));
        jLabel56.setText("Theme :");
        jPanel5.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, -1, 22));

        edit1.setBackground(new java.awt.Color(222, 222, 222));
        edit1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        edit1.setForeground(new java.awt.Color(0, 0, 0));
        edit1.setBorder(null);
        edit1.setCaretColor(new java.awt.Color(255, 153, 0));
        edit1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edit1FocusGained(evt);
            }
        });
        edit1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                edit1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit1ActionPerformed(evt);
            }
        });
        edit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edit1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edit1KeyTyped(evt);
            }
        });
        jPanel5.add(edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, 150, 22));

        s30.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s30, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 190, 150, 10));

        jLabel57.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(35, 69, 103));
        jLabel57.setText("Editeur :");
        jPanel5.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, -1, 22));

        jLabel98.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(35, 69, 103));
        jLabel98.setText("Date");
        jPanel5.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, 22));

        r1.setBackground(new java.awt.Color(222, 222, 222));
        r1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        r1.setForeground(new java.awt.Color(0, 0, 0));
        r1.setBorder(null);
        r1.setCaretColor(new java.awt.Color(255, 153, 0));
        r1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                r1FocusGained(evt);
            }
        });
        r1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                r1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });
        r1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                r1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                r1KeyTyped(evt);
            }
        });
        jPanel5.add(r1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 150, 22));

        s60.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s60, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 150, 10));

        jLabel58.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(35, 69, 103));
        jLabel58.setText("N°Rayon :");
        jPanel5.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, 30));

        ne1.setBackground(new java.awt.Color(222, 222, 222));
        ne1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ne1.setForeground(new java.awt.Color(0, 0, 0));
        ne1.setBorder(null);
        ne1.setCaretColor(new java.awt.Color(255, 153, 0));
        ne1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ne1FocusGained(evt);
            }
        });
        ne1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ne1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ne1ActionPerformed(evt);
            }
        });
        ne1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ne1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ne1KeyTyped(evt);
            }
        });
        jPanel5.add(ne1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 220, 150, 22));

        jLabel59.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(35, 69, 103));
        jLabel59.setText("N°Etage :");
        jPanel5.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, -1, 30));

        s61.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s61, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 250, 150, 10));

        s62.setBackground(new java.awt.Color(129, 187, 118));
        jPanel5.add(s62, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 150, 10));

        vid13.setBackground(new java.awt.Color(222, 222, 222));
        vid13.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        vid13.setForeground(new java.awt.Color(0, 0, 0));
        vid13.setBorder(null);
        vid13.setCaretColor(new java.awt.Color(255, 153, 0));
        vid13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vid13FocusGained(evt);
            }
        });
        vid13.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                vid13CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        vid13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vid13ActionPerformed(evt);
            }
        });
        vid13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vid13KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vid13KeyTyped(evt);
            }
        });
        jPanel5.add(vid13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 150, 22));

        jLabel60.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(35, 69, 103));
        jLabel60.setText("N°Rayon :");
        jPanel5.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, 30));

        dtd1.setForeground(new java.awt.Color(0, 0, 0));
        dtd1.setToolTipText("");
        dtd1.setDateFormatString("yyyy-MM-dd");
        dtd1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dtd1KeyTyped(evt);
            }
        });
        jPanel5.add(dtd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 160, 30));

        javax.swing.GroupLayout jDialog5Layout = new javax.swing.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        jDialog6.setMinimumSize(new java.awt.Dimension(1102, 524));

        jPanel22.setBackground(new java.awt.Color(222, 222, 222));
        jPanel22.setForeground(new java.awt.Color(244, 244, 244));
        jPanel22.setMinimumSize(new java.awt.Dimension(1078, 500));

        tab2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nDoc", "typeDoc", "dateEmp", "nAdhesion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab2.setGridColor(new java.awt.Color(204, 0, 0));
        jScrollPane10.setViewportView(tab2);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Type Document:");

        tbtb.setBackground(new java.awt.Color(129, 187, 118));
        tbtb.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        tbtb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Calendar Plus_45px.png"))); // NOI18N
        tbtb.setText("ADD");
        tbtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtbActionPerformed(evt);
            }
        });

        btbt.setBackground(new java.awt.Color(129, 187, 118));
        btbt.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btbt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Calendar Delete_45px.png"))); // NOI18N
        btbt.setText("Delete");
        btbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbtActionPerformed(evt);
            }
        });
        btbt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btbtKeyPressed(evt);
            }
        });

        lang2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Livre", "Periodique" }));
        lang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lang2ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("N°Document :");

        s63.setBackground(new java.awt.Color(129, 187, 118));

        tire2.setBackground(new java.awt.Color(222, 222, 222));
        tire2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        tire2.setForeground(new java.awt.Color(0, 0, 0));
        tire2.setBorder(null);
        tire2.setCaretColor(new java.awt.Color(255, 153, 0));
        tire2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tire2FocusGained(evt);
            }
        });
        tire2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tire2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tire2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tire2ActionPerformed(evt);
            }
        });
        tire2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tire2KeyTyped(evt);
            }
        });

        tire3.setBackground(new java.awt.Color(153, 153, 153));
        tire3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        tire3.setForeground(new java.awt.Color(0, 0, 0));
        tire3.setBorder(null);
        tire3.setCaretColor(new java.awt.Color(255, 153, 0));
        tire3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tire3FocusGained(evt);
            }
        });
        tire3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tire3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tire3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tire3ActionPerformed(evt);
            }
        });
        tire3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tire3KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap(263, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(tbtb, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btbt))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tire3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(314, 314, 314))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10)))
                .addContainerGap())
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(0, 145, Short.MAX_VALUE)
                    .addComponent(s63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 933, Short.MAX_VALUE)))
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(0, 145, Short.MAX_VALUE)
                    .addComponent(tire2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 933, Short.MAX_VALUE)))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(tire3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbtb)
                    .addComponent(btbt))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(0, 111, Short.MAX_VALUE)
                    .addComponent(s63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 387, Short.MAX_VALUE)))
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(0, 106, Short.MAX_VALUE)
                    .addComponent(tire2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 373, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jDialog6Layout = new javax.swing.GroupLayout(jDialog6.getContentPane());
        jDialog6.getContentPane().setLayout(jDialog6Layout);
        jDialog6Layout.setHorizontalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1102, Short.MAX_VALUE)
            .addGroup(jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog6Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jDialog6Layout.setVerticalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
            .addGroup(jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog6Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        p1.setBackground(new java.awt.Color(35, 69, 103));
        p1.setPreferredSize(new java.awt.Dimension(1318, 68));
        p1.setLayout(null);

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        p1.add(jLabel6);
        jLabel6.setBounds(642, 66, 0, 0);

        ex.setBackground(new java.awt.Color(0, 102, 102));
        ex.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ex.setForeground(new java.awt.Color(255, 255, 255));
        ex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exit (2).png"))); // NOI18N
        ex.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exMouseClicked(evt);
            }
        });
        p1.add(ex);
        ex.setBounds(1320, 10, 26, 24);

        ex1.setBackground(new java.awt.Color(0, 102, 102));
        ex1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ex1.setForeground(new java.awt.Color(255, 255, 255));
        ex1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ex1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ex1MouseClicked(evt);
            }
        });
        p1.add(ex1);
        ex1.setBounds(654, 66, 0, 0);

        jPanel2.setBackground(new java.awt.Color(222, 222, 222));
        jPanel2.setForeground(new java.awt.Color(244, 244, 244));

        jPanel1.setBackground(new java.awt.Color(35, 69, 103));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user2.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        user2.setForeground(new java.awt.Color(212, 175, 55));
        user2.setText("xxxxxxxxxxxx@gmail.com");
        jPanel1.add(user2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, -1, -1));

        user5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        user5.setForeground(new java.awt.Color(255, 255, 255));
        user5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Phone_30Gpx.png"))); // NOI18N
        jPanel1.add(user5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, -1, 40));

        user6.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        user6.setForeground(new java.awt.Color(212, 175, 55));
        user6.setText("+213 550 XXX XXX");
        jPanel1.add(user6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 560, -1, 20));

        user7.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        user7.setForeground(new java.awt.Color(212, 175, 55));
        user7.setText("+213 697 XXX XXX");
        jPanel1.add(user7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, -1, 20));

        user8.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        user8.setForeground(new java.awt.Color(212, 175, 55));
        user8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        user8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/User Male_30Gpx.png"))); // NOI18N
        user8.setText("Nom_Utilsateur");
        jPanel1.add(user8, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 100, 160, 40));

        user3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        user3.setForeground(new java.awt.Color(255, 255, 255));
        user3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/New Post_30Gpx.png"))); // NOI18N
        user3.setText(" ");
        jPanel1.add(user3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 30, 20));

        time.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        time.setForeground(new java.awt.Color(212, 175, 55));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time.setText("time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 610, -1, -1));

        date.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        date.setForeground(new java.awt.Color(212, 175, 55));
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 630, -1, -1));
        jPanel1.add(lbl_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 130, 90));

        p5.setBackground(new java.awt.Color(35, 69, 103));
        p5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                p5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                p5MouseReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(213, 175, 55));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Office");

        jPanel12.setBackground(new java.awt.Color(222, 222, 222));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s4.setBackground(new java.awt.Color(129, 187, 118));
        jPanel12.add(s4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 80, 10));

        vid2.setBackground(new java.awt.Color(222, 222, 222));
        vid2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        vid2.setForeground(new java.awt.Color(0, 0, 0));
        vid2.setBorder(null);
        vid2.setCaretColor(new java.awt.Color(255, 153, 0));
        vid2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vid2FocusGained(evt);
            }
        });
        vid2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                vid2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        vid2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vid2ActionPerformed(evt);
            }
        });
        vid2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vid2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vid2KeyTyped(evt);
            }
        });
        jPanel12.add(vid2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 80, 22));

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(35, 69, 103));
        jLabel39.setText("N°Hotel :");
        jPanel12.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, 22));

        jLabel77.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(35, 69, 103));
        jLabel77.setText("Etage :");
        jPanel12.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, 22));

        nomm2.setBackground(new java.awt.Color(222, 222, 222));
        nomm2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nomm2.setForeground(new java.awt.Color(0, 0, 0));
        nomm2.setBorder(null);
        nomm2.setCaretColor(new java.awt.Color(255, 153, 0));
        nomm2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nomm2FocusGained(evt);
            }
        });
        nomm2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nomm2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nomm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomm2ActionPerformed(evt);
            }
        });
        nomm2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomm2KeyTyped(evt);
            }
        });
        jPanel12.add(nomm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 158, 22));

        s36.setBackground(new java.awt.Color(129, 187, 118));
        jPanel12.add(s36, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 158, 10));

        prenomm2.setBackground(new java.awt.Color(222, 222, 222));
        prenomm2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        prenomm2.setForeground(new java.awt.Color(0, 0, 0));
        prenomm2.setBorder(null);
        prenomm2.setCaretColor(new java.awt.Color(255, 153, 0));
        prenomm2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                prenomm2FocusGained(evt);
            }
        });
        prenomm2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                prenomm2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        prenomm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomm2ActionPerformed(evt);
            }
        });
        prenomm2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenomm2KeyTyped(evt);
            }
        });
        jPanel12.add(prenomm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 158, 22));

        jLabel79.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(35, 69, 103));
        jLabel79.setText("Prix / Nuit :");
        jPanel12.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, -1, 22));

        s40.setBackground(new java.awt.Color(129, 187, 118));
        jPanel12.add(s40, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 158, 10));

        s41.setBackground(new java.awt.Color(129, 187, 118));
        jPanel12.add(s41, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 160, 10));

        jLabel80.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(35, 69, 103));
        jLabel80.setText("Type :");
        jPanel12.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, 30));

        jLabel81.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(35, 69, 103));
        jLabel81.setText("Address :");
        jPanel12.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, 22));

        s44.setBackground(new java.awt.Color(129, 187, 118));
        jPanel12.add(s44, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 518, 10));

        adrm2.setBackground(new java.awt.Color(222, 222, 222));
        adrm2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        adrm2.setForeground(new java.awt.Color(0, 0, 0));
        adrm2.setBorder(null);
        adrm2.setCaretColor(new java.awt.Color(255, 153, 0));
        adrm2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adrm2FocusGained(evt);
            }
        });
        adrm2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                adrm2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        adrm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adrm2ActionPerformed(evt);
            }
        });
        adrm2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adrm2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adrm2KeyTyped(evt);
            }
        });
        jPanel12.add(adrm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 518, 22));
        jPanel12.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 645, 10));

        ButtonSrcha1.setBackground(new java.awt.Color(35, 69, 103));
        ButtonSrcha1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonSrcha1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find User Male_40px.png"))); // NOI18N
        ButtonSrcha1.setText("Search");
        ButtonSrcha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSrcha1ActionPerformed(evt);
            }
        });
        jPanel12.add(ButtonSrcha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, -1, 53));

        ButtonDlta2.setBackground(new java.awt.Color(35, 69, 103));
        ButtonDlta2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonDlta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Denied_40px.png"))); // NOI18N
        ButtonDlta2.setText("Delete");
        ButtonDlta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDlta2aActionPerformed(evt);
            }
        });
        jPanel12.add(ButtonDlta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 152, 53));

        ButtonUpda3.setBackground(new java.awt.Color(35, 69, 103));
        ButtonUpda3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpda3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Change User_40px.png"))); // NOI18N
        ButtonUpda3.setText("UpDate");
        ButtonUpda3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpda3ActionPerformed(evt);
            }
        });
        jPanel12.add(ButtonUpda3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, 53));

        genderm2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "simple", "double", "triple", "suite", "autre" }));
        genderm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderm2ActionPerformed(evt);
            }
        });
        jPanel12.add(genderm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 160, -1));

        ButtonADDa2.setBackground(new java.awt.Color(35, 69, 103));
        ButtonADDa2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonADDa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add User Male_40px1.png"))); // NOI18N
        ButtonADDa2.setText("ADD");
        ButtonADDa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonADDa2ActionPerformed(evt);
            }
        });
        jPanel12.add(ButtonADDa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 140, -1));

        pss2.setBackground(new java.awt.Color(222, 222, 222));
        pss2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        pss2.setForeground(new java.awt.Color(0, 0, 0));
        pss2.setBorder(null);
        pss2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pss2FocusGained(evt);
            }
        });
        pss2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                pss2InputMethodTextChanged(evt);
            }
        });
        pss2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pss2ActionPerformed(evt);
            }
        });
        pss2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pss2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pss2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pss2KeyTyped(evt);
            }
        });
        jPanel12.add(pss2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 160, 20));

        errpss1.setForeground(new java.awt.Color(255, 0, 0));
        jPanel12.add(errpss1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, -1, -1));

        vid3.setBackground(new java.awt.Color(222, 222, 222));
        vid3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        vid3.setForeground(new java.awt.Color(0, 0, 0));
        vid3.setBorder(null);
        vid3.setCaretColor(new java.awt.Color(255, 153, 0));
        vid3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vid3FocusGained(evt);
            }
        });
        vid3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                vid3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        vid3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vid3ActionPerformed(evt);
            }
        });
        vid3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vid3KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vid3KeyTyped(evt);
            }
        });
        jPanel12.add(vid3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 80, 22));

        s9.setBackground(new java.awt.Color(129, 187, 118));
        jPanel12.add(s9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 80, 10));

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(35, 69, 103));
        jLabel43.setText("N°Chambre :");
        jPanel12.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, 22));

        javax.swing.GroupLayout p5Layout = new javax.swing.GroupLayout(p5);
        p5.setLayout(p5Layout);
        p5Layout.setHorizontalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        p5Layout.setVerticalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(p5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 246, 50));

        p3.setBackground(new java.awt.Color(35, 69, 103));
        p3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                p3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                p3MouseReleased(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(213, 175, 55));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Revues");

        javax.swing.GroupLayout p3Layout = new javax.swing.GroupLayout(p3);
        p3.setLayout(p3Layout);
        p3Layout.setHorizontalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p3Layout.setVerticalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        jPanel1.add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 246, 50));

        p6.setBackground(new java.awt.Color(35, 69, 103));
        p6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                p6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                p6MouseReleased(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(213, 175, 55));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Abonnements");

        javax.swing.GroupLayout p6Layout = new javax.swing.GroupLayout(p6);
        p6.setLayout(p6Layout);
        p6Layout.setHorizontalGroup(
            p6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
        );
        p6Layout.setVerticalGroup(
            p6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        jPanel1.add(p6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 246, 50));

        p4.setBackground(new java.awt.Color(35, 69, 103));
        p4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                p4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                p4MouseReleased(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(213, 175, 55));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Adherents");

        javax.swing.GroupLayout p4Layout = new javax.swing.GroupLayout(p4);
        p4.setLayout(p4Layout);
        p4Layout.setHorizontalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p4Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p4Layout.setVerticalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        jPanel1.add(p4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 246, 50));

        p7.setBackground(new java.awt.Color(35, 69, 103));
        p7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                p7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                p7MouseReleased(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(213, 175, 55));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Périodiques");

        javax.swing.GroupLayout p7Layout = new javax.swing.GroupLayout(p7);
        p7.setLayout(p7Layout);
        p7Layout.setHorizontalGroup(
            p7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
        );
        p7Layout.setVerticalGroup(
            p7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p7Layout.createSequentialGroup()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(p7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 246, 50));

        p2.setBackground(new java.awt.Color(35, 69, 103));
        p2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                p2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                p2MouseReleased(evt);
            }
        });
        p2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                p2KeyPressed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(213, 175, 55));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Livres");

        javax.swing.GroupLayout p2Layout = new javax.swing.GroupLayout(p2);
        p2.setLayout(p2Layout);
        p2Layout.setHorizontalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
        );
        p2Layout.setVerticalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        jPanel1.add(p2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 246, 50));

        s43.setBackground(new java.awt.Color(129, 187, 118));
        s43.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(s43, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 150, 230, 10));

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Langues");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 190, 82));

        pnl.setLayout(new javax.swing.BoxLayout(pnl, javax.swing.BoxLayout.LINE_AXIS));
        jPanel7.add(pnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 911, 280));

        jButton2.setText("Themes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 190, 77));

        jButton3.setText("Maisons d'edition");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 190, 83));

        jPanel14.setBackground(new java.awt.Color(255, 102, 0));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adherent1.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        adherent1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adherent1.setText("00");
        jPanel14.add(adherent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        adherent.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        adherent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adherent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/User Group Man Man_100px.png"))); // NOI18N
        jPanel14.add(adherent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 80));

        jPanel7.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 217, 81));

        jPanel15.setBackground(new java.awt.Color(204, 51, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editeur.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        editeur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editeur.setText("00");
        jPanel15.add(editeur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        editeur1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        editeur1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editeur1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Bungalow_96px.png"))); // NOI18N
        jPanel15.add(editeur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 80));

        jPanel7.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 217, -1));

        jPanel16.setBackground(new java.awt.Color(0, 204, 51));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        livre.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        livre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        livre.setText("00");
        jPanel16.add(livre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        livre1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        livre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        livre1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Book_104px.png"))); // NOI18N
        jPanel16.add(livre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 90));

        jPanel7.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 217, 81));

        jPanel17.setBackground(new java.awt.Color(0, 102, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        periodique.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        periodique.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        periodique.setText("00");
        jPanel17.add(periodique, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Magazine_104px.png"))); // NOI18N
        jPanel17.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 100, 90));

        jPanel7.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 217, -1));

        jPanel18.setBackground(new java.awt.Color(153, 80, 50));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        auteur.setFont(new java.awt.Font("Agency FB", 1, 45)); // NOI18N
        auteur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        auteur.setText("00");
        jPanel18.add(auteur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 80));

        auteur1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        auteur1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        auteur1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Businessman_100px.png"))); // NOI18N
        jPanel18.add(auteur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 80));

        jPanel7.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 217, -1));

        pnl1.setLayout(new javax.swing.BoxLayout(pnl1, javax.swing.BoxLayout.LINE_AXIS));
        jPanel7.add(pnl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 911, 301));

        Office.setViewportView(jPanel7);

        tp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tp1MouseClicked(evt);
            }
        });
        tp1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tp1KeyPressed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(222, 222, 222));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nAdhesion", "nom", "prenom", "mail", "datInsc", "validité"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(204, 0, 0));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        ptid2.setBackground(new java.awt.Color(222, 222, 222));
        ptid2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ptid2.setForeground(new java.awt.Color(0, 0, 0));
        ptid2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ptid2.setCaretColor(new java.awt.Color(255, 153, 0));
        ptid2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptid2FocusGained(evt);
            }
        });
        ptid2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ptid2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ptid2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ptid2ActionPerformed(evt);
            }
        });
        ptid2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ptid2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ptid2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ptid2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(485, 485, 485)
                .addComponent(ptid2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1074, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 126, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(ptid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(287, Short.MAX_VALUE))
        );

        tp1.addTab("View", jPanel6);

        jPanel4.setBackground(new java.awt.Color(222, 222, 222));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(35, 69, 103));
        jLabel30.setText("Nom :");
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, 22));

        nom1.setBackground(new java.awt.Color(222, 222, 222));
        nom1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nom1.setForeground(new java.awt.Color(0, 0, 0));
        nom1.setBorder(null);
        nom1.setCaretColor(new java.awt.Color(255, 153, 0));
        nom1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nom1FocusGained(evt);
            }
        });
        nom1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nom1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom1ActionPerformed(evt);
            }
        });
        nom1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nom1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nom1KeyTyped(evt);
            }
        });
        jPanel4.add(nom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 250, 22));

        s3.setBackground(new java.awt.Color(33, 155, 191));
        jPanel4.add(s3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 250, 10));

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(35, 69, 103));
        jLabel35.setText("Inscription :");
        jPanel4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, 140, -1));

        s6.setBackground(new java.awt.Color(33, 155, 191));
        jPanel4.add(s6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 250, 10));

        s8.setBackground(new java.awt.Color(33, 155, 191));
        jPanel4.add(s8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 250, 20));

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(35, 69, 103));
        jLabel36.setText("validité :");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 100, 22));

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(35, 69, 103));
        jLabel40.setText("email :");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, 22));

        s7.setBackground(new java.awt.Color(33, 155, 191));
        jPanel4.add(s7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 670, 10));

        adr1.setBackground(new java.awt.Color(222, 222, 222));
        adr1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        adr1.setForeground(new java.awt.Color(0, 0, 0));
        adr1.setBorder(null);
        adr1.setCaretColor(new java.awt.Color(255, 153, 0));
        adr1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adr1FocusGained(evt);
            }
        });
        adr1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                adr1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        adr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adr1ActionPerformed(evt);
            }
        });
        adr1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adr1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adr1KeyTyped(evt);
            }
        });
        jPanel4.add(adr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 670, 22));

        ButtonDlt.setBackground(new java.awt.Color(35, 69, 103));
        ButtonDlt.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonDlt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Denied_40px.png"))); // NOI18N
        ButtonDlt.setText("Delete");
        ButtonDlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDltActionPerformed(evt);
            }
        });
        ButtonDlt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ButtonDltKeyPressed(evt);
            }
        });
        jPanel4.add(ButtonDlt, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, 150, 50));

        ButtonUpd.setBackground(new java.awt.Color(35, 69, 103));
        ButtonUpd.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Change User_40px.png"))); // NOI18N
        ButtonUpd.setText("UpDate");
        ButtonUpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdActionPerformed(evt);
            }
        });
        jPanel4.add(ButtonUpd, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 460, -1, -1));

        val.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        val.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valActionPerformed(evt);
            }
        });
        jPanel4.add(val, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 250, -1));

        ButtonADD.setBackground(new java.awt.Color(35, 69, 103));
        ButtonADD.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add User Male_40px1.png"))); // NOI18N
        ButtonADD.setText("ADD");
        ButtonADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonADDActionPerformed(evt);
            }
        });
        ButtonADD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ButtonADDKeyPressed(evt);
            }
        });
        jPanel4.add(ButtonADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, 150, -1));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 800, 10));

        khayer.setText("Selectioner");
        khayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khayerActionPerformed(evt);
            }
        });
        jPanel4.add(khayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 320, 167, 55));

        lbl_image.setBackground(new java.awt.Color(0, 255, 0));
        lbl_image.setForeground(new java.awt.Color(200, 200, 200));
        jPanel4.add(lbl_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 160, 170));

        rstt.setBackground(new java.awt.Color(222, 222, 222));
        rstt.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        rstt.setForeground(new java.awt.Color(16, 106, 143));
        rstt.setText("Restituer");
        rstt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsttActionPerformed(evt);
            }
        });
        jPanel4.add(rstt, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 150, 50));

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 55));
        jLabel42.setText("ID :");
        jPanel4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 22));

        ptid.setBackground(new java.awt.Color(222, 222, 222));
        ptid.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ptid.setForeground(new java.awt.Color(0, 0, 0));
        ptid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ptid.setCaretColor(new java.awt.Color(255, 153, 0));
        ptid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptidFocusGained(evt);
            }
        });
        ptid.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ptidCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ptid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ptidActionPerformed(evt);
            }
        });
        ptid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ptidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ptidKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ptidKeyTyped(evt);
            }
        });
        jPanel4.add(ptid, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 150, 22));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 800, 10));

        s19.setBackground(new java.awt.Color(33, 155, 191));
        jPanel4.add(s19, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 270, 10));

        nom3.setBackground(new java.awt.Color(222, 222, 222));
        nom3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nom3.setForeground(new java.awt.Color(0, 0, 0));
        nom3.setBorder(null);
        nom3.setCaretColor(new java.awt.Color(255, 153, 0));
        nom3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nom3FocusGained(evt);
            }
        });
        nom3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nom3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nom3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom3ActionPerformed(evt);
            }
        });
        nom3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nom3KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nom3KeyTyped(evt);
            }
        });
        jPanel4.add(nom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, 270, 22));

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(35, 69, 103));
        jLabel31.setText("Prenom :");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, -1, 22));

        dta6.setForeground(new java.awt.Color(0, 0, 0));
        dta6.setToolTipText("");
        dta6.setDateFormatString("yyyy-MM-dd");
        dta6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dta6KeyTyped(evt);
            }
        });
        jPanel4.add(dta6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 250, 30));

        jLabel50.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(35, 69, 103));
        jLabel50.setText("Date");
        jPanel4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 60, -1));

        ButtonSrch1.setBackground(new java.awt.Color(222, 222, 222));
        ButtonSrch1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonSrch1.setForeground(new java.awt.Color(16, 106, 143));
        ButtonSrch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find User Male_40px_3.png"))); // NOI18N
        ButtonSrch1.setText("Search");
        ButtonSrch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSrch1ActionPerformed(evt);
            }
        });
        jPanel4.add(ButtonSrch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 150, -1));

        empt.setBackground(new java.awt.Color(222, 222, 222));
        empt.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        empt.setForeground(new java.awt.Color(16, 106, 143));
        empt.setText("Emprenter");
        empt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptActionPerformed(evt);
            }
        });
        jPanel4.add(empt, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 150, 50));

        tp1.addTab("Seting", jPanel4);

        Adherent.setViewportView(tp1);

        jPanel8.setBackground(new java.awt.Color(222, 222, 222));

        tab3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nPeriodique", "titre", "theme", "editeur", "dateAcquisition", "langue"
            }
        ));
        tab3.setEnabled(false);
        tab3.setGridColor(new java.awt.Color(204, 0, 0));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tab3);

        ptid3.setBackground(new java.awt.Color(222, 222, 222));
        ptid3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ptid3.setForeground(new java.awt.Color(0, 0, 0));
        ptid3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ptid3.setCaretColor(new java.awt.Color(255, 153, 0));
        ptid3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptid3FocusGained(evt);
            }
        });
        ptid3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ptid3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ptid3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ptid3ActionPerformed(evt);
            }
        });
        ptid3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ptid3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ptid3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ptid3KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(ptid3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(ptid3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
        );

        tp3.addTab("View", jPanel8);

        jPanel10.setBackground(new java.awt.Color(222, 222, 222));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s2.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 111, 10));

        vid.setBackground(new java.awt.Color(222, 222, 222));
        vid.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        vid.setForeground(new java.awt.Color(0, 0, 0));
        vid.setBorder(null);
        vid.setCaretColor(new java.awt.Color(255, 153, 0));
        vid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vidFocusGained(evt);
            }
        });
        vid.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                vidCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        vid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vidActionPerformed(evt);
            }
        });
        vid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vidKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vidKeyTyped(evt);
            }
        });
        jPanel10.add(vid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 111, 22));

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(35, 69, 103));
        jLabel37.setText("ID :");
        jPanel10.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, 22));

        jLabel72.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(35, 69, 103));
        jLabel72.setText("Titre :");
        jPanel10.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, 22));

        nomm1.setBackground(new java.awt.Color(222, 222, 222));
        nomm1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nomm1.setForeground(new java.awt.Color(0, 0, 0));
        nomm1.setBorder(null);
        nomm1.setCaretColor(new java.awt.Color(255, 153, 0));
        nomm1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nomm1FocusGained(evt);
            }
        });
        nomm1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nomm1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nomm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomm1ActionPerformed(evt);
            }
        });
        nomm1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomm1KeyTyped(evt);
            }
        });
        jPanel10.add(nomm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 158, 22));

        s35.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s35, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 158, 10));

        jLabel73.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(35, 69, 103));
        jLabel73.setText("Editeur :");
        jPanel10.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, 22));

        numm1.setBackground(new java.awt.Color(222, 222, 222));
        numm1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        numm1.setForeground(new java.awt.Color(0, 0, 0));
        numm1.setBorder(null);
        numm1.setCaretColor(new java.awt.Color(255, 153, 0));
        numm1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                numm1FocusGained(evt);
            }
        });
        numm1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                numm1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        numm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numm1ActionPerformed(evt);
            }
        });
        numm1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numm1KeyTyped(evt);
            }
        });
        jPanel10.add(numm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 158, 22));

        prenomm1.setBackground(new java.awt.Color(222, 222, 222));
        prenomm1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        prenomm1.setForeground(new java.awt.Color(0, 0, 0));
        prenomm1.setBorder(null);
        prenomm1.setCaretColor(new java.awt.Color(255, 153, 0));
        prenomm1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                prenomm1FocusGained(evt);
            }
        });
        prenomm1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                prenomm1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        prenomm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomm1ActionPerformed(evt);
            }
        });
        prenomm1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenomm1KeyTyped(evt);
            }
        });
        jPanel10.add(prenomm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 158, 22));

        jLabel74.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(35, 69, 103));
        jLabel74.setText("Theme :");
        jPanel10.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, -1, 22));

        s37.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s37, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 158, 10));

        s38.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s38, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, 158, 10));

        jLabel75.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(35, 69, 103));
        jLabel75.setText("Acquisition");
        jPanel10.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, -1, 22));

        jLabel76.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(35, 69, 103));
        jLabel76.setText("Langue :");
        jPanel10.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, 22));

        s39.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s39, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 170, 10));
        jPanel10.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 645, 10));

        ButtonSrcha.setBackground(new java.awt.Color(35, 69, 103));
        ButtonSrcha.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonSrcha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find User Male_40px.png"))); // NOI18N
        ButtonSrcha.setText("Search");
        ButtonSrcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSrchaActionPerformed(evt);
            }
        });
        jPanel10.add(ButtonSrcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, 53));

        ButtonDlta.setBackground(new java.awt.Color(35, 69, 103));
        ButtonDlta.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonDlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Denied_40px.png"))); // NOI18N
        ButtonDlta.setText("Delete");
        ButtonDlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDltaaActionPerformed(evt);
            }
        });
        jPanel10.add(ButtonDlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 152, 53));

        ButtonUpda.setBackground(new java.awt.Color(35, 69, 103));
        ButtonUpda.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Change User_40px.png"))); // NOI18N
        ButtonUpda.setText("UpDate");
        ButtonUpda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdaActionPerformed(evt);
            }
        });
        jPanel10.add(ButtonUpda, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, 53));

        lng.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arabe", "Anglais", "Tamzaght", "Francais" }));
        jPanel10.add(lng, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 158, -1));

        ButtonADDa.setBackground(new java.awt.Color(35, 69, 103));
        ButtonADDa.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonADDa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add User Male_40px1.png"))); // NOI18N
        ButtonADDa.setText("ADD");
        ButtonADDa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonADDaActionPerformed(evt);
            }
        });
        jPanel10.add(ButtonADDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 140, -1));

        errpss.setForeground(new java.awt.Color(255, 0, 0));
        jPanel10.add(errpss, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, -1, -1));

        s42.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s42, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 158, 10));

        jLabel86.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(35, 69, 103));
        jLabel86.setText("Date");
        jPanel10.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, -1, 22));

        dta8.setForeground(new java.awt.Color(0, 0, 0));
        dta8.setToolTipText("");
        dta8.setDateFormatString("yyyy-MM-dd");
        dta8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dta8KeyTyped(evt);
            }
        });
        jPanel10.add(dta8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 160, 30));

        tp3.addTab("Seting", jPanel10);

        Periodiques.setViewportView(tp3);

        jPanel11.setBackground(new java.awt.Color(222, 222, 222));

        tab4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nLivre", "titre", "genre", "theme", "auteur", "editeur", "dateAcquisition", "nRayon", "etage", "langue"
            }
        ));
        tab4.setEnabled(false);
        tab4.setGridColor(new java.awt.Color(204, 0, 0));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tab4);

        ptid4.setBackground(new java.awt.Color(222, 222, 222));
        ptid4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ptid4.setForeground(new java.awt.Color(0, 0, 0));
        ptid4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ptid4.setCaretColor(new java.awt.Color(255, 153, 0));
        ptid4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptid4FocusGained(evt);
            }
        });
        ptid4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ptid4MouseReleased(evt);
            }
        });
        ptid4.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ptid4CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ptid4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ptid4ActionPerformed(evt);
            }
        });
        ptid4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ptid4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ptid4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ptid4KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(ptid4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(561, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(ptid4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        tp4.addTab("View", jPanel11);

        jPanel13.setBackground(new java.awt.Color(222, 222, 222));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s16.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s16, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 80, 10));

        vid6.setBackground(new java.awt.Color(222, 222, 222));
        vid6.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        vid6.setForeground(new java.awt.Color(0, 0, 0));
        vid6.setBorder(null);
        vid6.setCaretColor(new java.awt.Color(255, 153, 0));
        vid6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vid6FocusGained(evt);
            }
        });
        vid6.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                vid6CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        vid6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vid6ActionPerformed(evt);
            }
        });
        vid6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vid6KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vid6KeyTyped(evt);
            }
        });
        jPanel13.add(vid6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 80, 22));

        jLabel44.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(35, 69, 103));
        jLabel44.setText("N°Livre :");
        jPanel13.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 22));

        jLabel82.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(35, 69, 103));
        jLabel82.setText("Titre :");
        jPanel13.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 22));

        tire.setBackground(new java.awt.Color(222, 222, 222));
        tire.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        tire.setForeground(new java.awt.Color(0, 0, 0));
        tire.setBorder(null);
        tire.setCaretColor(new java.awt.Color(255, 153, 0));
        tire.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tireFocusGained(evt);
            }
        });
        tire.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tireCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tireActionPerformed(evt);
            }
        });
        tire.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tireKeyTyped(evt);
            }
        });
        jPanel13.add(tire, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 158, 22));

        s45.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s45, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 158, 10));

        gr.setBackground(new java.awt.Color(222, 222, 222));
        gr.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        gr.setForeground(new java.awt.Color(0, 0, 0));
        gr.setBorder(null);
        gr.setCaretColor(new java.awt.Color(255, 153, 0));
        gr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grFocusGained(evt);
            }
        });
        gr.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                grCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        gr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grActionPerformed(evt);
            }
        });
        gr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grKeyTyped(evt);
            }
        });
        jPanel13.add(gr, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 158, 22));

        jLabel83.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(35, 69, 103));
        jLabel83.setText("Genre :");
        jPanel13.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, -1, 22));

        s46.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s46, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 158, 10));

        s47.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s47, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 160, 10));

        jLabel84.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(35, 69, 103));
        jLabel84.setText("Langue :");
        jPanel13.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, 30));

        jLabel85.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(35, 69, 103));
        jLabel85.setText("Acquisition :");
        jPanel13.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 150, 22));

        s48.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s48, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 160, 10));
        jPanel13.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 990, 10));

        ButtonSrcha2.setBackground(new java.awt.Color(35, 69, 103));
        ButtonSrcha2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonSrcha2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find User Male_40px.png"))); // NOI18N
        ButtonSrcha2.setText("Search");
        ButtonSrcha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSrcha2ActionPerformed(evt);
            }
        });
        jPanel13.add(ButtonSrcha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, -1, 53));

        ButtonDlta3.setBackground(new java.awt.Color(35, 69, 103));
        ButtonDlta3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonDlta3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Denied_40px.png"))); // NOI18N
        ButtonDlta3.setText("Delete");
        ButtonDlta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDlta3aActionPerformed(evt);
            }
        });
        jPanel13.add(ButtonDlta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 152, -1));

        ButtonUpda5.setBackground(new java.awt.Color(35, 69, 103));
        ButtonUpda5.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpda5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Change User_40px.png"))); // NOI18N
        ButtonUpda5.setText("UpDate");
        ButtonUpda5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpda5ActionPerformed(evt);
            }
        });
        jPanel13.add(ButtonUpda5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, -1, -1));

        lang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arabe", "Anglais", "Tamazight", "Francais" }));
        lang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                langActionPerformed(evt);
            }
        });
        jPanel13.add(lang, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 160, -1));

        ButtonADDa3.setBackground(new java.awt.Color(35, 69, 103));
        ButtonADDa3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonADDa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add User Male_40px1.png"))); // NOI18N
        ButtonADDa3.setText("ADD");
        ButtonADDa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonADDa3ActionPerformed(evt);
            }
        });
        jPanel13.add(ButtonADDa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 140, -1));

        errpss2.setForeground(new java.awt.Color(255, 0, 0));
        jPanel13.add(errpss2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, -1, -1));

        aut.setBackground(new java.awt.Color(222, 222, 222));
        aut.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        aut.setForeground(new java.awt.Color(0, 0, 0));
        aut.setBorder(null);
        aut.setCaretColor(new java.awt.Color(255, 153, 0));
        aut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                autFocusGained(evt);
            }
        });
        aut.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                autCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        aut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autActionPerformed(evt);
            }
        });
        aut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                autKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                autKeyTyped(evt);
            }
        });
        jPanel13.add(aut, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 150, 22));

        s18.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 150, 10));

        jLabel47.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(35, 69, 103));
        jLabel47.setText("Auteur :");
        jPanel13.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, -1, 22));

        them.setBackground(new java.awt.Color(222, 222, 222));
        them.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        them.setForeground(new java.awt.Color(0, 0, 0));
        them.setBorder(null);
        them.setCaretColor(new java.awt.Color(255, 153, 0));
        them.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                themFocusGained(evt);
            }
        });
        them.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                themCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });
        them.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                themKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                themKeyTyped(evt);
            }
        });
        jPanel13.add(them, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 180, 150, 22));

        s20.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s20, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 210, 150, 10));

        jLabel48.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(35, 69, 103));
        jLabel48.setText("Theme :");
        jPanel13.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, -1, 22));

        edit.setBackground(new java.awt.Color(222, 222, 222));
        edit.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        edit.setForeground(new java.awt.Color(0, 0, 0));
        edit.setBorder(null);
        edit.setCaretColor(new java.awt.Color(255, 153, 0));
        edit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                editFocusGained(evt);
            }
        });
        edit.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                editCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        edit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editKeyTyped(evt);
            }
        });
        jPanel13.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 260, 150, 22));

        s21.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s21, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 150, 10));

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(35, 69, 103));
        jLabel49.setText("Editeur :");
        jPanel13.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, -1, 22));

        jLabel93.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(35, 69, 103));
        jLabel93.setText("Date");
        jPanel13.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, 22));

        r.setBackground(new java.awt.Color(222, 222, 222));
        r.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        r.setForeground(new java.awt.Color(0, 0, 0));
        r.setBorder(null);
        r.setCaretColor(new java.awt.Color(255, 153, 0));
        r.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rFocusGained(evt);
            }
        });
        r.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                rCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rActionPerformed(evt);
            }
        });
        r.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rKeyTyped(evt);
            }
        });
        jPanel13.add(r, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 150, 22));

        s25.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s25, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 150, 10));

        jLabel51.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(35, 69, 103));
        jLabel51.setText("N°Rayon :");
        jPanel13.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, -1, 30));

        ne.setBackground(new java.awt.Color(222, 222, 222));
        ne.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ne.setForeground(new java.awt.Color(0, 0, 0));
        ne.setBorder(null);
        ne.setCaretColor(new java.awt.Color(255, 153, 0));
        ne.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                neFocusGained(evt);
            }
        });
        ne.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                neCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neActionPerformed(evt);
            }
        });
        ne.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                neKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                neKeyTyped(evt);
            }
        });
        jPanel13.add(ne, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 320, 150, 22));

        jLabel53.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(35, 69, 103));
        jLabel53.setText("N°Etage :");
        jPanel13.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 320, -1, 30));

        s26.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s26, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 350, 150, 10));

        s27.setBackground(new java.awt.Color(129, 187, 118));
        jPanel13.add(s27, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 150, 10));

        vid12.setBackground(new java.awt.Color(222, 222, 222));
        vid12.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        vid12.setForeground(new java.awt.Color(0, 0, 0));
        vid12.setBorder(null);
        vid12.setCaretColor(new java.awt.Color(255, 153, 0));
        vid12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vid12FocusGained(evt);
            }
        });
        vid12.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                vid12CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        vid12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vid12ActionPerformed(evt);
            }
        });
        vid12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vid12KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vid12KeyTyped(evt);
            }
        });
        jPanel13.add(vid12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 150, 22));

        jLabel54.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(35, 69, 103));
        jLabel54.setText("N°Rayon :");
        jPanel13.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, -1, 30));

        dtd.setForeground(new java.awt.Color(0, 0, 0));
        dtd.setToolTipText("");
        dtd.setDateFormatString("yyyy-MM-dd");
        dtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dtdKeyTyped(evt);
            }
        });
        jPanel13.add(dtd, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 160, 30));

        tp4.addTab("Seting", jPanel13);

        Livres.setViewportView(tp4);

        jPanel9.setBackground(new java.awt.Color(222, 222, 222));
        jPanel9.setForeground(new java.awt.Color(244, 244, 244));

        tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nAbon", "tarifAbon", "dateDebut", "dateExpiration", "nRevue"
            }
        ));
        tab.setAutoscrolls(false);
        tab.setGridColor(new java.awt.Color(204, 0, 0));
        jScrollPane6.setViewportView(tab);
        if (tab.getColumnModel().getColumnCount() > 0) {
            tab.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("N°Abonnelent :");

        ButtonADDa1.setBackground(new java.awt.Color(35, 69, 103));
        ButtonADDa1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonADDa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Calendar Plus_45px.png"))); // NOI18N
        ButtonADDa1.setText("ADD");
        ButtonADDa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonADDa1ActionPerformed(evt);
            }
        });

        ButtonUpda1.setBackground(new java.awt.Color(35, 69, 103));
        ButtonUpda1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpda1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Date Span_45px.png"))); // NOI18N
        ButtonUpda1.setText("UpDate");
        ButtonUpda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpda1ActionPerformed(evt);
            }
        });
        ButtonUpda1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ButtonUpda1KeyPressed(evt);
            }
        });

        ButtonDlta1.setBackground(new java.awt.Color(35, 69, 103));
        ButtonDlta1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonDlta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Calendar Delete_45px.png"))); // NOI18N
        ButtonDlta1.setText("Delete");
        ButtonDlta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDlta1ActionPerformed(evt);
            }
        });
        ButtonDlta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ButtonDlta1KeyPressed(evt);
            }
        });

        zz.setBackground(new java.awt.Color(255, 255, 255));
        zz.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz.setForeground(new java.awt.Color(0, 0, 0));
        zz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zzActionPerformed(evt);
            }
        });
        zz.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zzKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zzKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("N°Revue :");

        zz2.setBackground(new java.awt.Color(255, 255, 255));
        zz2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz2.setForeground(new java.awt.Color(0, 0, 0));
        zz2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zz2ActionPerformed(evt);
            }
        });
        zz2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zz2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zz2KeyTyped(evt);
            }
        });

        par.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mois", "Annes" }));
        par.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                parMouseClicked(evt);
            }
        });

        s13.setBackground(new java.awt.Color(33, 155, 191));

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Par :");

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Duré :");

        dure.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));

        s14.setBackground(new java.awt.Color(33, 155, 191));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(ButtonADDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(ButtonUpda1)
                        .addGap(64, 64, 64)
                        .addComponent(ButtonDlta1))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zz, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zz2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(par, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(s13, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dure, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(s14, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(309, 309, 309))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(zz, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(zz2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(9, 9, 9)
                        .addComponent(s13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addComponent(par, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(s14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonADDa1)
                    .addComponent(ButtonUpda1)
                    .addComponent(ButtonDlta1))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Abonnements.setViewportView(jPanel9);

        jPanel19.setBackground(new java.awt.Color(222, 222, 222));
        jPanel19.setForeground(new java.awt.Color(244, 244, 244));

        tab1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nRevue", "nParution", "nPeriodique"
            }
        ));
        tab1.setAutoscrolls(false);
        tab1.setGridColor(new java.awt.Color(204, 0, 0));
        jScrollPane7.setViewportView(tab1);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("N°Parution :");

        ButtonADDa4.setBackground(new java.awt.Color(35, 69, 103));
        ButtonADDa4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonADDa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Calendar Plus_45px.png"))); // NOI18N
        ButtonADDa4.setText("ADD");
        ButtonADDa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonADDa4ActionPerformed(evt);
            }
        });

        ButtonUpda6.setBackground(new java.awt.Color(35, 69, 103));
        ButtonUpda6.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpda6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Date Span_45px.png"))); // NOI18N
        ButtonUpda6.setText("UpDate");
        ButtonUpda6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpda6ActionPerformed(evt);
            }
        });
        ButtonUpda6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ButtonUpda6KeyPressed(evt);
            }
        });

        ButtonDlta4.setBackground(new java.awt.Color(35, 69, 103));
        ButtonDlta4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonDlta4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Calendar Delete_45px.png"))); // NOI18N
        ButtonDlta4.setText("Delete");
        ButtonDlta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDlta4ActionPerformed(evt);
            }
        });
        ButtonDlta4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ButtonDlta4KeyPressed(evt);
            }
        });

        zz1.setBackground(new java.awt.Color(255, 255, 255));
        zz1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz1.setForeground(new java.awt.Color(0, 0, 0));
        zz1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zz1ActionPerformed(evt);
            }
        });
        zz1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zz1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zz1KeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("N°Periodique :");

        zz7.setBackground(new java.awt.Color(255, 255, 255));
        zz7.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz7.setForeground(new java.awt.Color(0, 0, 0));
        zz7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zz7ActionPerformed(evt);
            }
        });
        zz7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zz7KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zz7KeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("N°Revue :");

        zz8.setBackground(new java.awt.Color(255, 255, 255));
        zz8.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz8.setForeground(new java.awt.Color(0, 0, 0));
        zz8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zz8ActionPerformed(evt);
            }
        });
        zz8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zz8KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zz8KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(ButtonADDa4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(ButtonUpda6)
                        .addGap(64, 64, 64)
                        .addComponent(ButtonDlta4))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zz8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zz1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zz7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 34, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(zz1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(zz8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(zz7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonADDa4)
                    .addComponent(ButtonUpda6)
                    .addComponent(ButtonDlta4))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Revue.setViewportView(jPanel19);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(Adherent, javax.swing.GroupLayout.PREFERRED_SIZE, 1095, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(265, Short.MAX_VALUE)
                    .addComponent(Periodiques, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(263, Short.MAX_VALUE)
                    .addComponent(Abonnements, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(0, 273, Short.MAX_VALUE)
                    .addComponent(Livres, javax.swing.GroupLayout.PREFERRED_SIZE, 1106, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(0, 273, Short.MAX_VALUE)
                    .addComponent(Office, javax.swing.GroupLayout.PREFERRED_SIZE, 1106, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(0, 264, Short.MAX_VALUE)
                    .addComponent(Revue, javax.swing.GroupLayout.PREFERRED_SIZE, 1115, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
            .addComponent(Adherent, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Periodiques))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Abonnements, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(Livres)
                    .addContainerGap()))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(Office, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Revue, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 1381, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1358, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void p2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_p2KeyPressed

    private void p2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_p2MouseReleased

    private void p2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_p2MousePressed

    private void p2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseEntered
        // TODO add your handling code here:
        p2.setBackground(new Color(212, 175, 55));
        jLabel41.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_p2MouseEntered

    private void p2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseClicked
        // TODO add your handling code here:
        /*String data = user8.getText();
        Patient p = new Patient(data);
        p.setVisible(true);
        p.pack();
        p.setLocationRelativeTo(null);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();*/
        Adherent.setVisible(false);
        Periodiques.setVisible(false);
        Abonnements.setVisible(false);
        Livres.setVisible(true);
        Office.setVisible(false);
        Revue.setVisible(false);
    }//GEN-LAST:event_p2MouseClicked

    private void p7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p7MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_p7MouseReleased

    private void p7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_p7MousePressed

    private void p7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p7MouseClicked
        // TODO add your handling code here:
        /*String data = user8.getText();
        Veterinary v = new Veterinary(data);
        v.setVisible(true);
        v.pack();
        v.setLocationRelativeTo(null);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();*/
        Adherent.setVisible(false);
        Periodiques.setVisible(true);
        Abonnements.setVisible(false);
        Livres.setVisible(false);
        Office.setVisible(false);
        Revue.setVisible(false);

    }//GEN-LAST:event_p7MouseClicked

    private void p6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_p6MouseReleased

    private void p6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_p6MousePressed

    private void p6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseClicked
        // TODO add your handling code here:
        /*String data = user8.getText();
        Appointment a = new Appointment("", data);
        a.setVisible(true);
        a.pack();
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();*/
        Adherent.setVisible(false);
        Periodiques.setVisible(false);
        Livres.setVisible(false);
        Abonnements.setVisible(true);
        Office.setVisible(false);
        Revue.setVisible(false);

    }//GEN-LAST:event_p6MouseClicked

    private void p4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_p4MouseReleased

    private void p4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_p4MousePressed

    private void p4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseClicked
        // TODO add your handling code here:
        /*String data = user8.getText();
        ptt p = new ptt(data);
        p.setVisible(true);
        //p.pack();
        //p.setLocationRelativeTo(null);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();*/
        Adherent.setVisible(true);
        Periodiques.setVisible(false);
        Abonnements.setVisible(false);
        Livres.setVisible(false);
        Office.setVisible(false);
        Revue.setVisible(false);
    }//GEN-LAST:event_p4MouseClicked

    private void p3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_p3MouseReleased

    private void p3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_p3MousePressed

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        // TODO add your handling code here:
        Adherent.setVisible(false);
        Periodiques.setVisible(false);
        Abonnements.setVisible(false);
        Livres.setVisible(false);
        Office.setVisible(false);
        Revue.setVisible(true);
    }//GEN-LAST:event_p3MouseClicked

    private void ex1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ex1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ex1MouseClicked

    private void exMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exMouseClicked
        // TODO add your handling code here:
        L1 l;
        try {
            l = new L1();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewVeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*l.setVisible(true);
        l.pack();
        l.setLocationRelativeTo(null);
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        this.dispose();
    }//GEN-LAST:event_exMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void p6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseEntered
        // TODO add your handling code here:
        p6.setBackground(new Color(212, 175, 55));
        jLabel71.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_p6MouseEntered

    private void p3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseEntered
        // TODO add your handling code here:
        p3.setBackground(new Color(212, 175, 55));
        jLabel29.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_p3MouseEntered

    private void p4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseEntered
        // TODO add your handling code here:
        p4.setBackground(new Color(212, 175, 55));
        jLabel33.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_p4MouseEntered

    private void p7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p7MouseEntered
        // TODO add your handling code here:
        p7.setBackground(new Color(212, 175, 55));
        jLabel38.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_p7MouseEntered

    private void p3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseExited
        // TODO add your handling code here:
        p3.setBackground(new Color(35, 69, 103));
        jLabel29.setForeground(new Color(213, 175, 55));
    }//GEN-LAST:event_p3MouseExited

    private void p6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseExited
        // TODO add your handling code here:
        p6.setBackground(new Color(35, 69, 103));
        jLabel71.setForeground(new Color(213, 175, 55));
    }//GEN-LAST:event_p6MouseExited

    private void p4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseExited
        // TODO add your handling code here:
        p4.setBackground(new Color(35, 69, 103));
        jLabel33.setForeground(new Color(213, 175, 55));
    }//GEN-LAST:event_p4MouseExited

    private void p7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p7MouseExited
        // TODO add your handling code here:
        p7.setBackground(new Color(35, 69, 103));
        jLabel38.setForeground(new Color(213, 175, 55));
    }//GEN-LAST:event_p7MouseExited

    private void p2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseExited
        // TODO add your handling code here:
        p2.setBackground(new Color(35, 69, 103));
        jLabel41.setForeground(new Color(213, 175, 55));
    }//GEN-LAST:event_p2MouseExited

//Methode To Resize The ImageIcon
    public ImageIcon ResizeImage(JLabel lbl_image1, String imagePath, byte[] pic) {
        ImageIcon myImage;

        if (imagePath != null) {
            myImage = new ImageIcon(imagePath);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_image1.getWidth(), lbl_image1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;

    }


    private void ButtonUpda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda2ActionPerformed
        // TODO add your handling code here:
        /*Date d1;
        try {
            /*
            String nPeriodique = null,titre, theme, editeur,langue,dateAcquisition;
            //Date d1;
            nPeriodique = vid.getText().toString();
            titre = nomm6.getText();
            theme = prenomm6.getText();
            editeur = numm2.getText();
            langue = lng1.getSelectedItem().toString();
            d1 = dta9.getDate();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateAcquisition = dtt.format(d1);
            
            String nAdhesion, dateInsc, nom, prenom, valdité, mail;
            nAdhesion = ptid.getText().toString();
            nom = nom4.getText();
            prenom = nom5.getText();
            valdité = val1.getSelectedItem().toString();
            mail = nom1.getText();
            d1 = dta7.getDate();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateInsc = dtt.format(d1);

            String req = "update adherent set"
                    + " nom='" + nom + "',prenom='" + prenom + "'," + "mail='" + mail + "'," + "dateInsc='" + dateInsc + "'," + "valdité='" + valdité + "' "
                    + "where nAdhesion='" + nAdhesion + "'";
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "Modifier avec succes");
                jDialog4.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "n'est pas modifier");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        d.setRowCount(0);
        String req2 = "select * from adherent";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nAdhesion"),
                    re.getString("nom"),
                    re.getString("prenom"),
                    re.getString("mail"),
                    re.getString("dateInsc"),
                    re.getString("valdité")};
                d.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

         */

        try {
            String nPeriodique = null, titre, theme, editeur, langue, dateAcquisition;
            Date d1;
            nPeriodique = vid.getText().toString();
            titre = nomm6.getText();
            theme = prenomm6.getText();
            editeur = numm2.getText();
            langue = lng1.getSelectedItem().toString();
            d1 = dta9.getDate();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateAcquisition = dtt.format(d1);

            String req = "update periodique set "
                    + "titre='" + titre + "',theme='" + theme + "',editeur='" + editeur + "',dateAcquisition='" + dateAcquisition + "',langue='" + langue + "' where nPeriodique=" + nPeriodique + " ";
            int test = st.executeUpdate(req);

            if (test == 1) {
                JOptionPane.showMessageDialog(null, "modifier avec succes");
            }
            /*else {
                JOptionPane.showMessageDialog(null, "modifier ??");
            }*/

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        table3.setRowCount(0);
        String req2 = "select * from periodique";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nPeriodique"),
                    re.getString("titre"),
                    re.getString("theme"),
                    re.getString("editeur"),
                    re.getString("dateAcquisition"),
                    re.getString("langue")};
                table3.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e);
        }
        //jDialog3.setVisible(false);
    }//GEN-LAST:event_ButtonUpda2ActionPerformed

    private void ButtonUpdaa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdaa1ActionPerformed
        // TODO add your handling code here:

        try {
            Double tarifAbon = 0.0;
            String nAbon, dateDebut = null, dateExpiration = null, nRevue;
            Date d1, d2, d3;
            int x, i = 0;

            d1 = aa.getDate();
            d3 = aa1.getDate();

            Calendar c = Calendar.getInstance();
            c.setTime(d1);

            if (par1.getSelectedItem().equals("Mois")) {

                x = Integer.parseInt((String) dure1.getSelectedItem());
                tarifAbon = x * 5000.0 + tar;
                c.add(Calendar.MONTH, x);
            } else {

                x = Integer.parseInt((String) dure1.getSelectedItem());
                tarifAbon = x * 55000.0 + tar;
                c.add(Calendar.YEAR, x);
            }

            d2 = c.getTime();
            d1.setMinutes(0);
            d2.setMinutes(0);
            d3.setMinutes(0);

            nAbon = zz.getText();
            nRevue = zz3.getText();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateDebut = dtt.format(d3);
            dateExpiration = dtt.format(d2);

            String req = "update abonnement set tarifAbon=" + tarifAbon + ",dateDebut='" + dateDebut + "',dateExpiration='" + dateExpiration + "',nRevue=" + nRevue + " "
                    + "where nAbon=" + nAbon;
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "Modifier avec succes");
                jDialog4.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "n'est pas modifier");
            }

            jDialog4.setVisible(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "errr " + e);
        }
        table2.setRowCount(0);
        String req2 = "select * from abonnement";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("nAbon"), re.getString("tarifAbon"), re.getString("dateDebut"), re.getString("dateExpiration"), re.getString("nRevue")};
                table2.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        jDialog4.setVisible(false);
    }//GEN-LAST:event_ButtonUpdaa1ActionPerformed

    private void ButtonADDa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDa1ActionPerformed
        // TODO add your handling code here:
        try {
            //String id, nomc, prenomc, nPeriodique, animalc, nomac, addc, gender = null, datec;
            //String r = "select * from revue";

            Double tarifAbon = 0.0;
            String nAbon, dateDebut = null, dateExpiration = null, nRevue;
            Date d1, d2;
            int x, i = 0;
            if (par.getSelectedItem().equals("Mois")) {

                x = Integer.parseInt((String) dure.getSelectedItem());
                tarifAbon = x * 5000.0;
            } else {
                x = Integer.parseInt((String) dure.getSelectedItem());
                tarifAbon = x * 55000.0;
            }

            d1 = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(d1);
            c.add(Calendar.YEAR, i);
            c.add(Calendar.MONTH, i);

            d2 = c.getTime();
            d1.setMinutes(0);
            d2.setMinutes(0);

            nAbon = zz.getText();
            nRevue = zz2.getText();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateDebut = dtt.format(d1);
            dateExpiration = dtt.format(d2);

            String req = "insert into abonnement values(" + tarifAbon + ",'" + dateDebut + "','" + dateExpiration + "'," + nRevue + ") ";

            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "add abonnement");
            } else {
                JOptionPane.showMessageDialog(null, "n'est pas ajouter");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "deja existe " + e);
        }

        table2.setRowCount(0);
        String req2 = "select * from abonnement";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("nAbon"), re.getString("tarifAbon"), re.getString("dateDebut"), re.getString("dateExpiration"), re.getString("nRevue")};
                table2.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

    }//GEN-LAST:event_ButtonADDa1ActionPerformed

    private void ButtonUpda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda1ActionPerformed
        // TODO add your handling code here:
        String nAbon, dateDebut = null, dateExpiration = null, nRevue;
        Date d1, d2;
        //nPeriodique = zz3.getText().toString();
        nAbon = zz.getText();
        nRevue = zz3.getText();
        try {
            String req = "select * from abonnement where nAbon=" + nAbon;

            re = st.executeQuery(req);

            while (re.next()) {

                zz.setText(re.getString("nAbon"));
                tar = re.getDouble("tarifAbon");
                zz3.setText(re.getString("nRevue"));
                d1 = re.getDate("dateExpiration");
                aa.setDate(d1);
                aa.setVisible(false);
                d2 = re.getDate("dateDebut");
                aa1.setDate(d2);
                aa1.setVisible(false);
                jDialog4.setVisible(true);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_ButtonUpda1ActionPerformed

    private void ButtonUpda1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ButtonUpda1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonUpda1KeyPressed

    private void ButtonDlta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDlta1ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String nAbon;
            nAbon = zz.getText();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");

            String req = "delete from abonnement where nAbon=" + nAbon;
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "supprimer avec succes");
            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        table2.setRowCount(0);
        String req2 = "select * from abonnement";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("nAbon"), re.getString("tarifAbon"), re.getString("dateDebut"), re.getString("dateExpiration"), re.getString("nRevue")};
                table2.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonDlta1ActionPerformed

    private void ButtonDlta1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ButtonDlta1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDlta1KeyPressed

    private void zzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zzActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zzActionPerformed

    private void zzKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zzKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zzKeyPressed

    private void zzKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zzKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_zzKeyTyped

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        ptid.setText(d.getValueAt(i, 0).toString());
        try {
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "select * from periodique where nPeriodique=" + ptid.getText() + "";

            re = st.executeQuery(req);

            table3.setRowCount(0);

            while (re.next()) {
                Object t[] = new Object[]{re.getString("nPeriodique"), re.getString("titre"), re.getString("theme"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("langue"), re.getString("nPeriodique")};
                table3.addRow(t);
                ptid.setText(re.getString("nPeriodique"));
                nom1.setText(re.getString("nomc"));
                nom3.setText(re.getString("prenomc"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas " + e);
        }
    }//GEN-LAST:event_tab3MouseClicked

    private void vidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vidFocusGained

    }//GEN-LAST:event_vidFocusGained

    private void vidCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vidCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vidCaretPositionChanged

    private void vidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vidActionPerformed

    private void vidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vidKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_F5) {
            if ((vid.getText()) != null && !(vid.getText()).isEmpty()) {
                try {
                    String req = "select * from login where nPeriodique=" + vid.getText() + " ";

                    re = st.executeQuery(req);

                    while (re.next()) {
                        //vid1.setText(re.getString("nPeriodique"));
                        nomm6.setText(re.getString("nomc"));
                        prenomm6.setText(re.getString("prenomc"));
                        jDialog3.setVisible(true);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "il n'existe pas");
                }
            }
        }
        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            try {
                // TODO add your handling code here:
                //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
                String req = "delete from periodique where nPeriodique=" + vid.getText() + "";
                int test = st.executeUpdate(req);
                if (test == 1) {
                    JOptionPane.showMessageDialog(null, "supprimer avec succes");
                    vid.setText("");
                    nomm1.setText("");
                    prenomm1.setText("");
                    numm1.setText("");
                    lng.setSelectedItem(1);

                } else {
                    JOptionPane.showMessageDialog(null, "il n'existe pas");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "il n'existe pas");
            }

            table3.setRowCount(0);
            String req2 = "select * from periodique";
            try {
                re = st.executeQuery(req2);
                while (re.next()) {
                    String t[] = new String[]{re.getString("nPeriodique"), re.getString("titre"), re.getString("theme"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("langue")};
                    table3.addRow(t);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error :" + e.toString());
            }
        }

    }//GEN-LAST:event_vidKeyPressed

    private void vidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vidKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_vidKeyTyped

    private void nomm1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomm1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm1FocusGained

    private void nomm1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nomm1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm1CaretPositionChanged

    private void nomm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomm1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm1ActionPerformed

    private void nomm1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomm1KeyTyped
        // TODO add your handling code here:
        /* char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }*/
    }//GEN-LAST:event_nomm1KeyTyped

    private void numm1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numm1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_numm1FocusGained

    private void numm1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_numm1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_numm1CaretPositionChanged

    private void numm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numm1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numm1ActionPerformed

    private void numm1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numm1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_numm1KeyTyped

    private void prenomm1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenomm1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm1FocusGained

    private void prenomm1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_prenomm1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm1CaretPositionChanged

    private void prenomm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomm1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm1ActionPerformed

    private void prenomm1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenomm1KeyTyped
        // TODO add your handling code here:
        /*char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }*/
    }//GEN-LAST:event_prenomm1KeyTyped

    private void ButtonSrchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSrchaActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "select * from periodique where nPeriodique=" + vid.getText() + "";
            re = st.executeQuery(req);

            table3.setRowCount(0);
            Date d1;
            while (re.next()) {
                vid.setText(re.getString("nPeriodique"));
                nomm1.setText(re.getString("titre"));
                prenomm1.setText(re.getString("theme"));
                lng.setSelectedItem(re.getString("langue"));
                numm1.setText(re.getString("editeur"));
                adr1.setText("");
                d1 = new Date();
                d1.setMinutes(0);
                dta8.setDate(re.getDate("dateAcquisition"));

                String t[] = new String[]{
                    re.getString("nPeriodique"),
                    re.getString("titre"),
                    re.getString("theme"),
                    re.getString("editeur"),
                    re.getString("dateAcquisition"),
                    re.getString("langue")};
                table3.addRow(t);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas");
        }
    }//GEN-LAST:event_ButtonSrchaActionPerformed

    private void ButtonDltaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDltaaActionPerformed
        try {
            Date d1;
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "delete from periodique where nPeriodique=" + vid.getText();
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "supprimer avec succes");
                vid.setText("");
                nomm1.setText("");
                prenomm1.setText("");
                lng.setSelectedItem(1);
                numm1.setText("");
                adr1.setText("");
                d1 = new Date();
                d1.setMinutes(0);
                dta6.setDate(d1);

            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "il n'existe pas");
        }

        table3.setRowCount(0);
        String req2 = "select * from periodique";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("nPeriodique"), re.getString("titre"), re.getString("theme"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("langue")};
                table3.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonDltaaActionPerformed

    private void ButtonUpdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdaActionPerformed
        if ((vid.getText()) != null && !(vid.getText()).isEmpty()) {
            try {
                String req = "select * from periodique where nPeriodique=" + vid.getText() + " ";

                re = st.executeQuery(req);

                while (re.next()) {
                    //vid1.setText(re.getString("nPeriodique"));
                    nomm6.setText(re.getString("titre"));
                    prenomm6.setText(re.getString("theme"));
                    lng1.setSelectedItem(re.getString("langue"));
                    numm2.setText(re.getString("editeur"));
                    dta9.setDate(re.getDate("dateAcquisition"));

                    jDialog3.setVisible(true);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex + "il n'existe pas");
            }
        }
    }//GEN-LAST:event_ButtonUpdaActionPerformed

    private void ButtonADDaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDaActionPerformed
        try {
            String nPeriodique, titre, theme, editeur, dateAcquisition, langue;
            Date d1;
            nPeriodique = vid.getText().toString();
            titre = nomm1.getText();
            theme = prenomm1.getText();
            editeur = numm1.getText();

            langue = lng.getSelectedItem().toString();
            d1 = dta8.getDate();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateAcquisition = dtt.format(d1);

            String req = "insert into periodique values"
                    + "('" + titre + "','" + theme + "','" + editeur + "','" + dateAcquisition + "','" + langue + "') ";

            int test = st.executeUpdate(req);

            if (test == 1) {
                JOptionPane.showMessageDialog(null, "ajouter avec succes");

                vid.setText("");
                nomm1.setText("");
                prenomm1.setText("");
                lng.setSelectedItem(1);
                numm1.setText("");
                adr1.setText("");
                d1 = new Date();
                d1.setMinutes(0);
                dta8.setDate(d1);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "deja existe" + e);
        }

        table3.setRowCount(0);
        String req2 = "select * from periodique";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nPeriodique"),
                    re.getString("titre"),
                    re.getString("theme"),
                    re.getString("editeur"),
                    re.getString("dateAcquisition"),
                    re.getString("langue")};
                table3.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonADDaActionPerformed

    private void ptid3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptid3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid3FocusGained

    private void ptid3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ptid3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid3CaretPositionChanged

    private void ptid3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptid3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid3ActionPerformed

    private void ptid3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid3KeyPressed

    private void ptid3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid3KeyReleased
        // TODO add your handling code here:
        String search = ptid3.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table3);
        tab3.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_ptid3KeyReleased

    private void ptid3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid3KeyTyped

    private void tp1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tp1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tp1KeyPressed

    private void tp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tp1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tp1MouseClicked

    private void ptid2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid2KeyTyped

    private void ptid2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid2KeyReleased
        // TODO add your handling code here:
        String search = ptid2.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(d);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_ptid2KeyReleased

    private void ptid2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid2KeyPressed

    private void ptid2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptid2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid2ActionPerformed

    private void ptid2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ptid2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid2CaretPositionChanged

    private void ptid2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptid2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid2FocusGained

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        Date dtt = null;

        ptid.setText(d.getValueAt(i, 0).toString());
        try {
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "select * from adherent where numadherent=" + ptid.getText() + "";

            re = st.executeQuery(req);

            d.setRowCount(0);

            while (re.next()) {

                Object t[] = new Object[]{re.getString("numadherent"), re.getString("nom"), re.getString("ville"), re.getString("etoiles")};

                d.addRow(t);
                ptid.setText(re.getString("numadherent"));
                nom1.setText(re.getString("nom"));
                nom3.setText(re.getString("ville"));
                String gdr = re.getString("etoiles");
                val.setSelectedItem(gdr);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas");
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void p5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_p5MouseReleased

    private void p5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_p5MousePressed

    private void p5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseExited
        // TODO add your handling code here:
        p5.setBackground(new Color(35, 69, 103));
        jLabel27.setForeground(new Color(213, 175, 55));
    }//GEN-LAST:event_p5MouseExited

    private void p5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseEntered
        // TODO add your handling code here:
        p5.setBackground(new Color(212, 175, 55));
        jLabel27.setForeground(Color.DARK_GRAY);

    }//GEN-LAST:event_p5MouseEntered

    private void p5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseClicked
        // TODO add your handling code here:
        Office.setVisible(true);
        Adherent.setVisible(false);
        Periodiques.setVisible(false);
        Abonnements.setVisible(false);
        Livres.setVisible(false);

        try {
            // TODO add your handling code here:

            //Bar
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();

            String reqz1 = "select editeur as l, count(editeur) as nbrLL from livre group by editeur";
            re = st.executeQuery(reqz1);
            while (re.next()) {
                dcd.setValue(re.getInt("nbrLL"), "" + re.getString("l"), "livre");
            }

            String reqz2 = "select editeur as l, count(editeur) as nbrLP from periodique group by editeur";
            re = st.executeQuery(reqz2);
            while (re.next()) {
                dcd.setValue(re.getInt("nbrLP"), "" + re.getString("l"), "periodique");
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

    }//GEN-LAST:event_p5MouseClicked

    private void ptidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptidKeyTyped

    }//GEN-LAST:event_ptidKeyTyped

    private void ptidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptidKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ptidKeyReleased

    private void ptidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptidKeyPressed

    private void ptidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptidActionPerformed

    private void ptidCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ptidCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ptidCaretPositionChanged

    private void ptidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptidFocusGained

    }//GEN-LAST:event_ptidFocusGained

    private void rsttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsttActionPerformed
        jDialog6.setVisible(true);
        btbt.setVisible(true);
        tbtb.setVisible(false);

    }//GEN-LAST:event_rsttActionPerformed

    private void ButtonADDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ButtonADDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonADDKeyPressed

    private void ButtonADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDActionPerformed

        String nAdhesion, nom, prenom, mail, datInsc, valdité, datea;

        Date d1;

        nAdhesion = ptid.getText().toString();
        //if (!ptid.getText().toString().equals("")) {

        nom = nom1.getText();
        prenom = nom3.getText();
        //datInsc = adr1.getText();
        valdité = val.getSelectedItem().toString();
        mail = nom1.getText();
        d1 = dta6.getDate();
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
        datInsc = dtt.format(d1);

        try {
            c = getConnectionn();
            //JOptionPane.showMessageDialog(null, "ajouter");

            ps = c.prepareStatement("INSERT INTO adherent"
                    + "( nom, prenom, mail,dateInsc, valdité)"
                    + "values(?,?,?,?,?) ");
            //JOptionPane.showMessageDialog(null, "avec");

            //ps.setString(1, nAdhesion);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, mail);
            ps.setString(4, datInsc);
            ps.setString(5, valdité);
            /*InputStream img = new FileInputStream(new File(ImgPath));
                    ps.setBlob(7, img);*/
            //0JOptionPane.showMessageDialog(null, "succes");

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "ajouter avec succes");
            ptid.setText("");
            nom1.setText("");
            nom3.setText("");
            val.setSelectedIndex(1);
            adr1.setText("");
            d1 = new Date();
            d1.setMinutes(0);
            dta6.setDate(d1);

            //lbl_image.setIcon(ResizeImage(lbl_image, "src/image/SLR Back Side_104px.png", null));
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, ex + " kkk");
        }
        //}

        d.setRowCount(0);
        String req2 = "select * from adherent";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nAdhesion"),
                    re.getString("nom"),
                    re.getString("prenom"),
                    re.getString("mail"),
                    re.getString("dateInsc"),
                    re.getString("valdité")};
                d.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonADDActionPerformed

    private void ButtonUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdActionPerformed
        if ((ptid.getText()) != null && !(ptid.getText()).isEmpty()) {
            try {
                String req = "select * from adherent where nAdhesion=" + ptid.getText() + "";

                re = st.executeQuery(req);

                Date d1;
                while (re.next()) {
                    //ptid1.setText(re.getString("nAdhesion"));
                    nom4.setText(re.getString("nom"));
                    nom5.setText(re.getString("prenom"));
                    adr3.setText(re.getString("mail"));
                    /*d1 = new Date();
                    d1.setMinutes(0);*/
                    dta7.setDate(re.getDate("dateInsc"));
                    String gdr = re.getString("valdité");
                    val1.setSelectedItem(gdr);

                    ptid.setText(re.getString("nAdhesion"));
                    nom1.setText(re.getString("nom"));
                    nom3.setText(re.getString("prenom"));
                    adr1.setText(re.getString("mail"));
                    /*d1 = new Date();
                    d1.setMinutes(0);*/
                    dta6.setDate(re.getDate("dateInsc"));
                    String gdr1 = re.getString("valdité");
                    val.setSelectedItem(gdr1);

                    jDialog1.setVisible(true);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex + " il n'existe pas");
            }
        }
    }//GEN-LAST:event_ButtonUpdActionPerformed

    private void ButtonDltKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ButtonDltKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDltKeyPressed

    private void ButtonDltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDltActionPerformed
        try {
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "delete from adherent where nAdhesion=" + ptid.getText() + "";
            int test = st.executeUpdate(req);
            if (test == 1) {
                Date d1;
                JOptionPane.showMessageDialog(null, "Supprimer avec succes");
                ptid.setText("");
                nom1.setText("");
                nom3.setText("");
                val.setSelectedIndex(1);
                adr1.setText("");
                d1 = new Date();
                d1.setMinutes(0);
                dta6.setDate(d1);

            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "il n'existe pas");
        }

        d.setRowCount(0);
        String req2 = "select * from adherent";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numadherent"), re.getString("nom"), re.getString("ville"), re.getString("etoiles")};
                d.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonDltActionPerformed

    private void adr1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adr1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_adr1KeyTyped

    private void adr1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adr1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_adr1KeyPressed

    private void adr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adr1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adr1ActionPerformed

    private void adr1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_adr1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_adr1CaretPositionChanged

    private void adr1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adr1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_adr1FocusGained

    private void nom1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_nom1KeyTyped

    private void nom1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom1KeyPressed

    private void nom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom1ActionPerformed

    private void nom1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nom1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nom1CaretPositionChanged

    private void nom1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nom1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nom1FocusGained

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tab4MouseClicked

    private void zz2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz2ActionPerformed

    private void zz2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz2KeyPressed

    private void zz2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz2KeyTyped

    private void vid3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3KeyTyped

    private void vid3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3KeyPressed

    private void vid3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3ActionPerformed

    private void vid3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3CaretPositionChanged

    private void vid3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3FocusGained

    private void pss2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2KeyTyped

    private void pss2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2KeyReleased

    private void pss2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2KeyPressed

    private void pss2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pss2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2ActionPerformed

    private void pss2InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_pss2InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2InputMethodTextChanged

    private void pss2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pss2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2FocusGained

    private void ButtonADDa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDa2ActionPerformed
        // TODO add your handling code here:
        try {
            String nLivre, numadherent, etage, typelivre, prixnuit;

            nLivre = vid3.getText().toString();
            numadherent = vid2.getText().toString();
            etage = nomm2.getText();
            prixnuit = prenomm2.getText().toString();
            typelivre = genderm2.getSelectedItem().toString();

            String req = "insert into livre values(" + nLivre + "," + numadherent + "," + etage + ",'" + typelivre + "'," + prixnuit + ") ";

            int test = st.executeUpdate(req);

            if (test == 1) {
                JOptionPane.showMessageDialog(null, "ajouter avec succes");

                vid3.setText("");
                nomm2.setText("");
                vid2.setText("");
                genderm2.setSelectedItem(1);
                prenomm2.setText("");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "deja existe " + e);
        }
        String r = "select * from livre";
        table4.setRowCount(0);
        try {
            re = st.executeQuery(r);
            while (re.next()) {
                String t[] = new String[]{re.getString("nLivre"), re.getString("titre"), re.getString("genre"), re.getString("theme"), re.getString("auteur"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("nRayon"), re.getString("etage"), re.getString("langue")};
                table4.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonADDa2ActionPerformed

    private void genderm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderm2ActionPerformed

    private void ButtonUpda3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda3ActionPerformed
        // TODO add your handling code here:
        /* if (!(vid2.getText().equals("") && vid3.getText().equals(""))) {
            try {
                String req = "select * from livre where nLivre=" + vid3.getText() + " and numadherent=" + vid2.getText() + "";

                re = st.executeQuery(req);

                while (re.next()) {
                    vid4.setText(re.getString("numadherent"));
                    vid5.setText(re.getString("nLivre"));
                    prenomm4.setText(re.getString("prixnuit"));
                    nomm4.setText(re.getString("etage"));
                    String gdr = re.getString("typelivre");
                    genderm4.setSelectedItem(gdr);

                    jDialog5.setVisible(true);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "il n'existe pas " + ex);
            }
        }*/
    }//GEN-LAST:event_ButtonUpda3ActionPerformed

    private void ButtonDlta2aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDlta2aActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "delete from livre where nLivre=" + vid3.getText() + " and numadherent=" + vid2.getText() + "";
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "supprimer avec succes");

                vid2.setText("");
                vid3.setText("");
                nomm2.setText("");
                prenomm2.setText("");
                //gender2.setSelectedItem(1);
            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "il n'existe pas " + e);
        }

        String r = "select * from livre";

        table4.setRowCount(0);
        try {
            re = st.executeQuery(r);
            while (re.next()) {
                String t[] = new String[]{re.getString("nLivre"), re.getString("titre"), re.getString("genre"), re.getString("theme"), re.getString("auteur"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("nRayon"), re.getString("etage"), re.getString("langue")};
                table4.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonDlta2aActionPerformed

    private void ButtonSrcha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSrcha1ActionPerformed
        // TODO add your handling code here:
        try {
            if (vid2 != null && vid3 != null) {
                String req = "select * from livre where nLivre=" + vid3.getText() + " and numadherent=" + vid2.getText() + "";

                re = st.executeQuery(req);

                table4.setRowCount(0);
                try {
                    re = st.executeQuery(req);
                    while (re.next()) {
                        String t[] = new String[]{re.getString("nLivre"), re.getString("titre"), re.getString("genre"), re.getString("theme"), re.getString("auteur"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("nRayon"), re.getString("etage"), re.getString("langue")};
                        table4.addRow(t);

                        vid2.setText(re.getString("numadherent"));
                        vid3.setText(re.getString("nLivre"));
                        nomm2.setText(re.getString("etage"));
                        prenomm2.setText(re.getString("prixnuit"));
                        String gdr = re.getString("typelivre");
                        genderm2.setSelectedItem(gdr);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error :" + e.toString());
                }
            } else {
                JOptionPane.showMessageDialog(null, "error : Numero d'adherent ou/et Numero de la livre est vide !");
                String req = "select * from livre";

                re = st.executeQuery(req);

                table4.setRowCount(0);
                try {
                    re = st.executeQuery(req);
                    while (re.next()) {
                        String t[] = new String[]{re.getString("nLivre"), re.getString("titre"), re.getString("genre"), re.getString("theme"), re.getString("auteur"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("nRayon"), re.getString("etage"), re.getString("langue")};
                        table4.addRow(t);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error :" + e.toString());
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas " + e);

        }
    }//GEN-LAST:event_ButtonSrcha1ActionPerformed

    private void adrm2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adrm2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2KeyTyped

    private void adrm2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adrm2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2KeyPressed

    private void adrm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adrm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2ActionPerformed

    private void adrm2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_adrm2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2CaretPositionChanged

    private void adrm2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adrm2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2FocusGained

    private void prenomm2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenomm2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm2KeyTyped

    private void prenomm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm2ActionPerformed

    private void prenomm2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_prenomm2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm2CaretPositionChanged

    private void prenomm2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenomm2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm2FocusGained

    private void nomm2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomm2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm2KeyTyped

    private void nomm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm2ActionPerformed

    private void nomm2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nomm2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm2CaretPositionChanged

    private void nomm2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomm2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm2FocusGained

    private void vid2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2KeyTyped

    private void vid2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2KeyPressed

    private void vid2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2ActionPerformed

    private void vid2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2CaretPositionChanged

    private void vid2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2FocusGained

    private void ptid4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4KeyTyped

    private void ptid4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid4KeyReleased
        // TODO add your handling code here:
        String search = ptid4.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table4);
        tab4.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_ptid4KeyReleased

    private void ptid4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4KeyPressed

    private void ptid4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptid4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4ActionPerformed

    private void ptid4CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ptid4CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4CaretPositionChanged

    private void ptid4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ptid4MouseReleased
        // TODO add your handling code here:
        String search = ptid3.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table3);
        tab3.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_ptid4MouseReleased

    private void ptid4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptid4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4FocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            //Bar
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();

            String reqz1 = "select editeur as l, count(editeur) as nbrLL from livre group by editeur";
            re = st.executeQuery(reqz1);
            while (re.next()) {
                dcd.setValue(re.getInt("nbrLL"), "" + re.getString("l"), "livre");
            }

            String reqz2 = "select editeur as l, count(editeur) as nbrLP from periodique group by editeur";
            re = st.executeQuery(reqz2);
            while (re.next()) {
                dcd.setValue(re.getInt("nbrLP"), "" + re.getString("l"), "periodique");
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            //Bar
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();

            String reqz1 = "select theme as l, count(theme) as nbrLL from livre group by theme";
            re = st.executeQuery(reqz1);
            while (re.next()) {
                dcd.setValue(re.getInt("nbrLL"), "" + re.getString("l"), "livre");
            }

            String reqz2 = "select theme as l, count(theme) as nbrLP from periodique group by theme";
            re = st.executeQuery(reqz2);
            while (re.next()) {
                dcd.setValue(re.getInt("nbrLP"), "" + re.getString("l"), "periodique");
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:

            //Bar
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();

            String reqz1 = "select langue as l, count(langue) as nbrLL from livre group by langue";
            re = st.executeQuery(reqz1);
            while (re.next()) {
                dcd.setValue(re.getInt("nbrLL"), "" + re.getString("l"), "livre");
            }

            String reqz2 = "select langue as l, count(langue) as nbrLP from periodique group by langue";
            re = st.executeQuery(reqz2);
            while (re.next()) {
                dcd.setValue(re.getInt("nbrLP"), "" + re.getString("l"), "periodique");
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

    private void vid6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid6FocusGained

    private void vid6CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid6CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid6CaretPositionChanged

    private void vid6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid6ActionPerformed

    private void vid6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid6KeyPressed

    private void vid6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_vid6KeyTyped

    private void tireFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tireFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tireFocusGained

    private void tireCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tireCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tireCaretPositionChanged

    private void tireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tireActionPerformed

    private void tireKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tireKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tireKeyTyped

    private void grFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grFocusGained

    private void grCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_grCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_grCaretPositionChanged

    private void grActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grActionPerformed

    private void grKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_grKeyTyped

    private void ButtonSrcha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSrcha2ActionPerformed
        // TODO add your handling code here:
        Date d1;
        try {
            if (vid6 != null) {
                String req = "select * from livre where nLivre=" + vid6.getText();

                re = st.executeQuery(req);

                table4.setRowCount(0);
                try {
                    re = st.executeQuery(req);
                    while (re.next()) {
                        String t[] = new String[]{re.getString("nLivre"), re.getString("titre"), re.getString("genre"), re.getString("theme"), re.getString("auteur"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("nRayon"), re.getString("etage"), re.getString("langue")};
                        table4.addRow(t);

                        vid6.setText(re.getString("nLivre"));
                        tire.setText(re.getString("titre"));
                        gr.setText(re.getString("genre"));
                        them.setText(re.getString("theme"));
                        edit.setText(re.getString("editeur"));
                        aut.setText(re.getString("auteur"));
                        r.setText(re.getString("nRayon"));
                        ne.setText(re.getString("etage"));
                        lang.setSelectedItem(re.getString("langue"));
                        dtd.setDate(re.getDate("dateAcquisition"));
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error :" + e.toString());
                }
            } else {
                JOptionPane.showMessageDialog(null, "champs vide !");
                String req = "select * from livre";

                re = st.executeQuery(req);

                table4.setRowCount(0);
                try {
                    re = st.executeQuery(req);
                    while (re.next()) {
                        String t[] = new String[]{re.getString("nLivre"), re.getString("titre"), re.getString("genre"), re.getString("theme"), re.getString("auteur"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("nRayon"), re.getString("etage"), re.getString("langue")};
                        table4.addRow(t);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error :" + e.toString());
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas " + e);

        }
    }//GEN-LAST:event_ButtonSrcha2ActionPerformed

    private void ButtonDlta3aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDlta3aActionPerformed
        // TODO add your handling code here:
        try {
            Date d1;
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "delete from livre where nLivre=" + vid6.getText();
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "supprimer avec succes");

                vid6.setText("");
                tire.setText("");
                gr.setText("");
                them.setText("");
                edit.setText("");
                aut.setText("");
                r.setText("");
                ne.setText("");
                lang.setSelectedItem(1);
                d1 = new Date();
                d1.setMinutes(0);
                dtd.setDate(d1);
            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "il n'existe pas " + e);
        }

        String r = "select * from livre";

        table4.setRowCount(0);
        try {
            re = st.executeQuery(r);
            while (re.next()) {
                String t[] = new String[]{re.getString("nLivre"), re.getString("titre"), re.getString("genre"), re.getString("theme"), re.getString("auteur"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("nRayon"), re.getString("etage"), re.getString("langue")};
                table4.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonDlta3aActionPerformed

    private void ButtonUpda5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda5ActionPerformed
        // TODO add your handling code here:
        if (!(vid6.getText().equals(""))) {
            try {
                String req = "select * from livre where nLivre=" + vid6.getText();

                re = st.executeQuery(req);

                while (re.next()) {
                    //vid7.setText(re.getString("nLivre"));
                    tire1.setText(re.getString("titre"));
                    gr1.setText(re.getString("genre"));
                    them1.setText(re.getString("theme"));
                    edit1.setText(re.getString("editeur"));
                    aut1.setText(re.getString("auteur"));
                    r1.setText(re.getString("nRayon"));
                    ne1.setText(re.getString("etage"));
                    lang1.setSelectedItem(re.getString("langue"));
                    dtd1.setDate(re.getDate("dateAcquisition"));

                    jDialog5.setVisible(true);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "il n'existe pas " + ex);
            }
        }
    }//GEN-LAST:event_ButtonUpda5ActionPerformed

    private void langActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_langActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_langActionPerformed

    private void ButtonADDa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDa3ActionPerformed
        // TODO add your handling code here:
        try {
            String nLivre, titre, genre, theme, auteur, editeur, dateAcquisition, nRayon, etage, langue;
            Date d1;

            nLivre = vid6.getText().toString();
            titre = tire.getText();
            genre = gr.getText();
            theme = them.getText();
            editeur = edit.getText();
            auteur = aut.getText();
            nRayon = r.getText();
            etage = ne.getText();
            langue = lang.getSelectedItem().toString();
            d1 = dtd.getDate();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateAcquisition = dtt.format(d1);

            String req = "insert into livre values('" + titre + "','" + genre + "','" + theme + "','" + auteur + "'"
                    + ",'" + editeur + "','" + dateAcquisition + "'," + nRayon + "," + etage + ",'" + langue + "') ";

            int test = st.executeUpdate(req);

            if (test == 1) {
                JOptionPane.showMessageDialog(null, "ajouter avec succes");

                vid6.setText("");
                tire.setText("");
                gr.setText("");
                them.setText("");
                edit.setText("");
                aut.setText("");
                r.setText("");
                ne.setText("");
                lang.setSelectedItem(1);
                d1 = new Date();
                d1.setMinutes(0);
                dtd.setDate(d1);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "deja existe " + e);
        }
        String r = "select * from livre";
        table4.setRowCount(0);
        try {
            re = st.executeQuery(r);
            while (re.next()) {
                String t[] = new String[]{re.getString("nLivre"), re.getString("titre"), re.getString("genre"), re.getString("theme"), re.getString("auteur"), re.getString("editeur"), re.getString("dateAcquisition"), re.getString("nRayon"), re.getString("etage"), re.getString("langue")};
                table4.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonADDa3ActionPerformed

    private void autFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_autFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_autFocusGained

    private void autCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_autCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_autCaretPositionChanged

    private void autActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_autActionPerformed

    private void autKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_autKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_autKeyPressed

    private void autKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_autKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_autKeyTyped

    private void ButtonADDa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDa4ActionPerformed
        // TODO add your handling code here:
        try {
            String nParution, nPeriodique, nRevue;

            nParution = zz1.getText();
            nPeriodique = zz7.getText();
            String req = "insert into revue values(" + nParution + "," + nPeriodique + ") ";
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "add Revue");
            } else {
                JOptionPane.showMessageDialog(null, "n'est pas ajouter");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "deja existe " + e);
        }

        table1.setRowCount(0);
        String req2 = "select * from revue";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nRevue"),
                    re.getString("nParution"),
                    re.getString("nPeriodique")};
                table1.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

    }//GEN-LAST:event_ButtonADDa4ActionPerformed

    private void ButtonUpda6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda6ActionPerformed
        // TODO add your handling code here:
        String nParution, nPeriodique, nRevue;
        nRevue = zz8.getText();
        try {
            String req = "select * from revue where nRevue=" + nRevue;

            re = st.executeQuery(req);

            while (re.next()) {

                zz8.setText(re.getString("nRevue"));
                npp.setText(re.getString("nParution"));
                zz10.setText(re.getString("nPeriodique"));
                jDialog2.setVisible(true);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_ButtonUpda6ActionPerformed

    private void ButtonUpda6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ButtonUpda6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonUpda6KeyPressed

    private void ButtonDlta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDlta4ActionPerformed
        // TODO add your handling code here:

        try {
            // TODO add your handling code here:
            String nRevue;
            nRevue = zz8.getText();

            String req = "delete from revue where nRevue=" + nRevue;
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "supprimer avec succes");
            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        table1.setRowCount(0);
        String req2 = "select * from revue";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nRevue"),
                    re.getString("nParution"),
                    re.getString("nPeriodique")};
                table1.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

    }//GEN-LAST:event_ButtonDlta4ActionPerformed

    private void ButtonDlta4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ButtonDlta4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDlta4KeyPressed

    private void zz1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz1ActionPerformed

    private void zz1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz1KeyPressed

    private void zz1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz1KeyTyped

    private void zz7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz7ActionPerformed

    private void zz7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz7KeyPressed

    private void zz7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz7KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz7KeyTyped

    private void nom3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nom3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nom3FocusGained

    private void nom3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nom3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nom3CaretPositionChanged

    private void nom3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom3ActionPerformed

    private void nom3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom3KeyPressed

    private void nom3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nom3KeyTyped

    private void valActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valActionPerformed

    private void dta6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dta6KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_dta6KeyTyped

    private void khayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khayerActionPerformed

        // TODO add your handling code here://
        // TODO add your handling code here://
        //browse image
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(lbl_image, path, null));
            ImgPath = path;
        } else {
            System.out.println("No File Selected");
        }
    }//GEN-LAST:event_khayerActionPerformed

    private void nom5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nom5KeyTyped

    private void nom5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom5KeyPressed

    private void nom5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom5ActionPerformed

    private void nom5CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nom5CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nom5CaretPositionChanged

    private void nom5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nom5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nom5FocusGained

    private void dta7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dta7KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dta7KeyTyped

    private void nom4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nom4KeyTyped

    private void nom4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom4KeyPressed

    private void nom4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom4ActionPerformed

    private void nom4CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nom4CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nom4CaretPositionChanged

    private void nom4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nom4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nom4FocusGained

    private void val1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_val1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_val1ActionPerformed

    private void adr3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adr3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_adr3KeyTyped

    private void adr3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adr3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_adr3KeyPressed

    private void adr3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adr3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adr3ActionPerformed

    private void adr3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_adr3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_adr3CaretPositionChanged

    private void adr3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adr3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_adr3FocusGained

    private void khayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khayer1ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image1.setIcon(ResizeImage(lbl_image1, path, null));
            ImgPath = path;
        } else {
            System.out.println("No File Selected");
        }
    }//GEN-LAST:event_khayer1ActionPerformed

    private void ButtonUpd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpd1ActionPerformed
        Date d1;
        try {
            String nAdhesion, dateInsc, nom, prenom, valdité, mail;
            nAdhesion = ptid.getText().toString();
            nom = nom4.getText();
            prenom = nom5.getText();
            valdité = val1.getSelectedItem().toString();
            mail = nom1.getText();
            d1 = dta7.getDate();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateInsc = dtt.format(d1);

            String req = "update adherent set"
                    + " nom='" + nom + "',prenom='" + prenom + "'," + "mail='" + mail + "'," + "dateInsc='" + dateInsc + "'," + "valdité='" + valdité + "' "
                    + "where nAdhesion='" + nAdhesion + "'";
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "Modifier avec succes");
                jDialog4.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "n'est pas modifier");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        d.setRowCount(0);
        String req2 = "select * from adherent";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nAdhesion"),
                    re.getString("nom"),
                    re.getString("prenom"),
                    re.getString("mail"),
                    re.getString("dateInsc"),
                    re.getString("valdité")};
                d.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        jDialog1.setVisible(false);
    }//GEN-LAST:event_ButtonUpd1ActionPerformed

    private void dta8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dta8KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_dta8KeyTyped

    private void nomm6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomm6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm6FocusGained

    private void nomm6CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nomm6CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm6CaretPositionChanged

    private void nomm6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomm6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm6ActionPerformed

    private void nomm6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomm6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm6KeyTyped

    private void numm2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numm2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_numm2FocusGained

    private void numm2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_numm2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_numm2CaretPositionChanged

    private void numm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numm2ActionPerformed

    private void numm2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numm2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_numm2KeyTyped

    private void prenomm6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenomm6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm6FocusGained

    private void prenomm6CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_prenomm6CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm6CaretPositionChanged

    private void prenomm6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomm6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm6ActionPerformed

    private void prenomm6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenomm6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm6KeyTyped

    private void dta9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dta9KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dta9KeyTyped

    private void themFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_themFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_themFocusGained

    private void themCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_themCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_themCaretPositionChanged

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_themActionPerformed

    private void themKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_themKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_themKeyPressed

    private void themKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_themKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_themKeyTyped

    private void editFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_editFocusGained

    private void editCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_editCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_editCaretPositionChanged

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editActionPerformed

    private void editKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_editKeyPressed

    private void editKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_editKeyTyped

    private void rFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_rFocusGained

    private void rCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_rCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_rCaretPositionChanged

    private void rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rActionPerformed

    private void rKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rKeyPressed

    private void rKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_rKeyTyped

    private void neFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_neFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_neFocusGained

    private void neCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_neCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_neCaretPositionChanged

    private void neActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_neActionPerformed

    private void neKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_neKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_neKeyPressed

    private void neKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_neKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_neKeyTyped

    private void vid12FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid12FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid12FocusGained

    private void vid12CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid12CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid12CaretPositionChanged

    private void vid12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid12ActionPerformed

    private void vid12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid12KeyPressed

    private void vid12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid12KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_vid12KeyTyped

    private void dtdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtdKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_dtdKeyTyped

    private void ButtonUpda4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda4ActionPerformed
        // TODO add your handling code here:
        try {
            String nLivre, titre, genre, theme, auteur, editeur, dateAcquisition, nRayon, etage, langue;
            Date d1;

            nLivre = vid6.getText().toString();
            titre = tire1.getText();
            genre = gr1.getText();
            theme = them1.getText();
            editeur = edit1.getText();
            auteur = aut1.getText();
            nRayon = r1.getText();
            etage = ne1.getText();
            langue = lang1.getSelectedItem().toString();
            d1 = dtd1.getDate();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateAcquisition = dtt.format(d1);

            String req = "update livre set titre='" + titre + "',genre='" + genre + "',theme='" + theme + "',auteur='" + auteur + "',editeur='" + editeur + "',dateAcquisition='" + dateAcquisition + "',nRayon=" + nRayon + ",etage=" + etage + ",langue='" + langue + "'"
                    + " where nLivre=" + nLivre;
            int test = st.executeUpdate(req);

            if (test == 1) {
                JOptionPane.showMessageDialog(null, "modifier avec succes");
            } else {
                JOptionPane.showMessageDialog(null, " non modifier");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        table4.setRowCount(0);
        String req2 = "select * from livre";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("nLivre"), re.getString("titre"), re.getString("genre"), re.getString("theme"), re.getString("auteur"),
                    re.getString("editeur"), re.getString("dateAcquisition"), re.getString("nRayon"), re.getString("etage"), re.getString("langue")};
                table3.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
        jDialog5.setVisible(false);
    }//GEN-LAST:event_ButtonUpda4ActionPerformed

    private void tire1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tire1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tire1FocusGained

    private void tire1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tire1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tire1CaretPositionChanged

    private void tire1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tire1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tire1ActionPerformed

    private void tire1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tire1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tire1KeyTyped

    private void gr1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_gr1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_gr1FocusGained

    private void gr1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_gr1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_gr1CaretPositionChanged

    private void gr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gr1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gr1ActionPerformed

    private void gr1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gr1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_gr1KeyTyped

    private void lang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lang1ActionPerformed

    private void aut1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_aut1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_aut1FocusGained

    private void aut1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_aut1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_aut1CaretPositionChanged

    private void aut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aut1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aut1ActionPerformed

    private void aut1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aut1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_aut1KeyPressed

    private void aut1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aut1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_aut1KeyTyped

    private void them1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_them1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_them1FocusGained

    private void them1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_them1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_them1CaretPositionChanged

    private void them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_them1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_them1ActionPerformed

    private void them1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_them1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_them1KeyPressed

    private void them1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_them1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_them1KeyTyped

    private void edit1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edit1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_edit1FocusGained

    private void edit1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_edit1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_edit1CaretPositionChanged

    private void edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit1ActionPerformed

    private void edit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit1KeyPressed

    private void edit1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edit1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_edit1KeyTyped

    private void r1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_r1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_r1FocusGained

    private void r1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_r1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_r1CaretPositionChanged

    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r1ActionPerformed

    private void r1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_r1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_r1KeyPressed

    private void r1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_r1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_r1KeyTyped

    private void ne1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ne1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ne1FocusGained

    private void ne1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ne1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ne1CaretPositionChanged

    private void ne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ne1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ne1ActionPerformed

    private void ne1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ne1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ne1KeyPressed

    private void ne1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ne1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ne1KeyTyped

    private void vid13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid13FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid13FocusGained

    private void vid13CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid13CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid13CaretPositionChanged

    private void vid13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid13ActionPerformed

    private void vid13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid13KeyPressed

    private void vid13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid13KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_vid13KeyTyped

    private void dtd1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtd1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dtd1KeyTyped

    private void parMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_parMouseClicked
        // TODO add your handling code here:
        dure.removeAllItems();
        if (par.getSelectedItem().equals("Mois")) {
            dure.addItem("1");
            dure.addItem("2");
            dure.addItem("3");
            dure.addItem("4");
            dure.addItem("5");
            dure.addItem("6");

        } else {
            dure.addItem("1");
            dure.addItem("2");
        }
    }//GEN-LAST:event_parMouseClicked

    private void zz3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz3ActionPerformed

    private void zz3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz3KeyPressed

    private void zz3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz3KeyTyped

    private void par1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_par1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_par1MouseClicked

    private void nrvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nrvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nrvActionPerformed

    private void nrvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrvKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nrvKeyPressed

    private void nrvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrvKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nrvKeyTyped

    private void nppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nppActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nppActionPerformed

    private void nppKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nppKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nppKeyPressed

    private void nppKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nppKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nppKeyTyped

    private void ButtonUpdaa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdaa2ActionPerformed
        // TODO add your handling code here:
        try {
            String nParution, nPeriodique, nRevue;

            nParution = npp.getText();
            nPeriodique = zz10.getText();
            nrv.setText(zz8.getText());
            nRevue = zz8.getText();

            String req = "update revue set nParution=" + nParution + ",nPeriodique=" + nPeriodique + "where nRevue=" + nRevue;
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "Modifier avec succes");
            } else {
                JOptionPane.showMessageDialog(null, "n'est pas modifier");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "errr " + e);
        }

        table1.setRowCount(0);
        String req2 = "select * from revue";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{
                    re.getString("nRevue"),
                    re.getString("nParution"),
                    re.getString("nPeriodique")};
                table1.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        jDialog2.setVisible(false);
    }//GEN-LAST:event_ButtonUpdaa2ActionPerformed

    private void zz10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz10KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz10KeyTyped

    private void zz10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz10KeyPressed

    private void zz10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz10ActionPerformed

    private void zz8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz8KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz8KeyTyped

    private void zz8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz8KeyPressed

    private void zz8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz8ActionPerformed

    private void ButtonSrch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSrch1ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            if (ptid != null) {
                String req = "select * from adherent where nAdhesion=" + ptid.getText() + "";

                re = st.executeQuery(req);

                d.setRowCount(0);

                while (re.next()) {

                    Object t[] = new Object[]{
                        re.getString("nom"),
                        re.getString("prenom"),
                        re.getString("mail"),
                        re.getString("dateInsc"),
                        re.getString("valdité")};

                    d.addRow(t);
                    Date d1;
                    ptid.setText(re.getString("nAdhesion"));
                    nom1.setText(re.getString("nom"));
                    nom3.setText(re.getString("prenom"));
                    String gdrr = re.getString("valdité");
                    val.setSelectedItem(gdrr);
                    adr1.setText(re.getString("mail"));
                    dta6.setDate(re.getDate("dateInsc"));

                }
            } else {
                Date d1;
                ptid.setText("");
                nom1.setText("");
                nom3.setText("");
                val.setSelectedIndex(1);
                adr1.setText("");
                d1 = new Date();
                d1.setMinutes(0);
                dta6.setDate(d1);

                String req2 = "select * from hotel";
                d.setRowCount(0);
                try {
                    re = st.executeQuery(req2);
                    while (re.next()) {
                        String t[] = new String[]{
                            re.getString("nAdhesion"),
                            re.getString("nom"),
                            re.getString("prenom"),
                            re.getString("mail"),
                            re.getString("dateInsc"),
                            re.getString("valdité")};
                        d.addRow(t);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error :" + e.toString());
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas");

        }
    }//GEN-LAST:event_ButtonSrch1ActionPerformed

    private void emptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emptActionPerformed
        // TODO add your handling code here:
        jDialog6.setVisible(true);
        btbt.setVisible(false);
        tbtb.setVisible(true);


    }//GEN-LAST:event_emptActionPerformed

    private void tbtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtbActionPerformed
        tbtb.setVisible(true);
        btbt.setVisible(false);
        try {
            String nDoc, typeDoc, dateEmp, nAdhesion;

            nDoc = tire3.getText();
            nAdhesion = ptid.getText();
            typeDoc = lang2.getSelectedItem().toString();
            Date d1;
            d1 = new Date();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            dateEmp = dtt.format(d1);
            int x = 0;
            String req2 = "select count(*)as nbr from empreinter where nAdhesion = " + nAdhesion + " and typeDoc = '" + typeDoc + "' ";
            re = st.executeQuery(req2);
            while (re.next()) {
                x = re.getInt("nbr");
            }

            System.err.println("nDoc :" + nDoc + "nAdhesion  :" + nAdhesion + " typeDoc :" + typeDoc + " dateEmp :" + dateEmp + "x  :" + x);

            if (typeDoc.equals("Livre") && x < 3) {
                String req21 = "insert into empreinter values(" + nDoc + ",'" + typeDoc + "','" + dateEmp + "'," + nAdhesion + ") ";
                int test = st.executeUpdate(req21);

                if (test == 1) {
                    JOptionPane.showMessageDialog(null, "ajouter avec succes, vous avez exatement 3 semains pour rendre le document");

                }

            } else {
                if (typeDoc.equals("Livre") && x == 3) {
                    JOptionPane.showMessageDialog(null, "vous avez depacer le nbr limité");

                } else {
                    if (typeDoc.equals("Periodique") && x < 2) {
                        String req21 = "insert into empreinter values(" + nDoc + ",'" + typeDoc + "','" + dateEmp + "'," + nAdhesion + ") ";
                        int test = st.executeUpdate(req21);

                        if (test == 1) {
                            JOptionPane.showMessageDialog(null, "ajouter avec succes, vous avez exatement une semain pour rendre le document");

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "vous avez depacer le nbr limité");
                    }
                }
            }
            dd.setRowCount(0);
            String req2q = "select * from empreinter";
            try {
                re = st.executeQuery(req2q);
                while (re.next()) {
                    String t[] = new String[]{
                        re.getString("nDoc"),
                        re.getString("typeDoc"),
                        re.getString("dateEmp"),
                        re.getString("nAdhesion")};
                    dd.addRow(t);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error :" + e.toString());
            }
            //jDialog6.setVisible(false);

        } catch (SQLException ex) {
            Logger.getLogger(NewVeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tbtbActionPerformed

    private void btbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbtActionPerformed
        try {
            String nDoc, typeDoc, nAdhesion, dateEmp;
            nAdhesion = ptid.getText().toString();
            nDoc = tire3.getText().toString();
            typeDoc = lang2.getSelectedItem().toString();
            String req = "delete from empreinter where nDoc=" + nDoc + " and typeDoc='" + typeDoc + "' and nAdhesion=" + nAdhesion;
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "supprimer avec succes");
            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas ");
            }
            dd.setRowCount(0);
            String req2q = "select * from empreinter";
            try {
                re = st.executeQuery(req2q);
                while (re.next()) {
                    String t[] = new String[]{
                        re.getString("nDoc"),
                        re.getString("typeDoc"),
                        re.getString("dateEmp"),
                        re.getString("nAdhesion")};
                    dd.addRow(t);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error :" + e.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewVeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Date d1,d2;
        d1 = new Date();
        String nAdhesion,dateEmp;
        nAdhesion =ptid.getText();
        String rr = "select * from empreinter where nAdhesion ="+nAdhesion+"" ;
       int d=0,z;
        try {
                re = st.executeQuery(rr);
                while (re.next()) {
                    d2=re.getDate("dateEmp");
                    //int days = Days.daysBetween(d1,d1).getDays();
                    d = (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
                }
                z = (21);
                if(d >= z)
                {
                    JOptionPane.showMessageDialog(null,"Vous avez depasser le deleeeeee");
                }
                else 
                {
                    JOptionPane.showMessageDialog(null,"il vous reste "+d+" jour(s)");
                }
            } catch (SQLException e) {
                //JOptionPane.showMessageDialog(null, "error :" + e.toString());
            }

        
        
        
        
        /*String req2q = "SELECT empreinter.nAdhesion" +
        "nAdhesion" +
        "from empreinter " +
        "JOIN adheren" +
        "on" +
        "empreinter.nAdhesion = adherent.nAdhesion";
            
        try {
                re = st.executeQuery(req2q);
                while (re.next()) {
                    String t[] = new String[]{
                        re.getString("nAdhesion")
                            
                    };
                    dd.addRow(t);
                    System.err.println(re.getString("nAdhesion"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error :" + e.toString());
            }*/

    }//GEN-LAST:event_btbtActionPerformed

    private void btbtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btbtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btbtKeyPressed

    private void lang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lang2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lang2ActionPerformed

    private void tire2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tire2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tire2FocusGained

    private void tire2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tire2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tire2CaretPositionChanged

    private void tire2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tire2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tire2ActionPerformed

    private void tire2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tire2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tire2KeyTyped

    private void tire3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tire3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tire3FocusGained

    private void tire3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tire3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tire3CaretPositionChanged

    private void tire3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tire3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tire3ActionPerformed

    private void tire3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tire3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tire3KeyTyped

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
            java.util.logging.Logger.getLogger(NewVeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewVeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewVeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewVeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NewVeto().setVisible(true);
                } catch (Exception e) {
                    System.err.println(":/ :/ :/");
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Abonnements;
    private javax.swing.JScrollPane Adherent;
    private javax.swing.JButton ButtonADD;
    private javax.swing.JButton ButtonADDa;
    private javax.swing.JButton ButtonADDa1;
    private javax.swing.JButton ButtonADDa2;
    private javax.swing.JButton ButtonADDa3;
    private javax.swing.JButton ButtonADDa4;
    private javax.swing.JButton ButtonDlt;
    private javax.swing.JButton ButtonDlta;
    private javax.swing.JButton ButtonDlta1;
    private javax.swing.JButton ButtonDlta2;
    private javax.swing.JButton ButtonDlta3;
    private javax.swing.JButton ButtonDlta4;
    private javax.swing.JButton ButtonSrch1;
    private javax.swing.JButton ButtonSrcha;
    private javax.swing.JButton ButtonSrcha1;
    private javax.swing.JButton ButtonSrcha2;
    private javax.swing.JButton ButtonUpd;
    private javax.swing.JButton ButtonUpd1;
    private javax.swing.JButton ButtonUpda;
    private javax.swing.JButton ButtonUpda1;
    private javax.swing.JButton ButtonUpda2;
    private javax.swing.JButton ButtonUpda3;
    private javax.swing.JButton ButtonUpda4;
    private javax.swing.JButton ButtonUpda5;
    private javax.swing.JButton ButtonUpda6;
    private javax.swing.JButton ButtonUpdaa1;
    private javax.swing.JButton ButtonUpdaa2;
    private javax.swing.JScrollPane Livres;
    private javax.swing.JScrollPane Office;
    private javax.swing.JScrollPane Periodiques;
    private javax.swing.JScrollPane Revue;
    private com.toedter.calendar.JDateChooser aa;
    private com.toedter.calendar.JDateChooser aa1;
    private javax.swing.JLabel adherent;
    private javax.swing.JLabel adherent1;
    private javax.swing.JTextField adr1;
    private javax.swing.JTextField adr3;
    private javax.swing.JTextField adrm2;
    public static javax.swing.JTextField aut;
    public static javax.swing.JTextField aut1;
    private javax.swing.JLabel auteur;
    private javax.swing.JLabel auteur1;
    private javax.swing.JButton btbt;
    private javax.swing.JLabel date;
    private com.toedter.calendar.JDateChooser dta6;
    private com.toedter.calendar.JDateChooser dta7;
    private com.toedter.calendar.JDateChooser dta8;
    private com.toedter.calendar.JDateChooser dta9;
    private com.toedter.calendar.JDateChooser dtd;
    private com.toedter.calendar.JDateChooser dtd1;
    private javax.swing.JComboBox<String> dure;
    private javax.swing.JComboBox<String> dure1;
    public static javax.swing.JTextField edit;
    public static javax.swing.JTextField edit1;
    private javax.swing.JLabel editeur;
    private javax.swing.JLabel editeur1;
    private javax.swing.JButton empt;
    private javax.swing.JLabel errpss;
    private javax.swing.JLabel errpss1;
    private javax.swing.JLabel errpss2;
    private javax.swing.JLabel ex;
    private javax.swing.JLabel ex1;
    private javax.swing.JComboBox<String> genderm2;
    private javax.swing.JTextField gr;
    private javax.swing.JTextField gr1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JDialog jDialog6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton khayer;
    private javax.swing.JButton khayer1;
    private javax.swing.JComboBox<String> lang;
    private javax.swing.JComboBox<String> lang1;
    private javax.swing.JComboBox<String> lang2;
    private javax.swing.JLabel lbl_4;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lbl_image1;
    private javax.swing.JLabel livre;
    private javax.swing.JLabel livre1;
    private javax.swing.JComboBox<String> lng;
    private javax.swing.JComboBox<String> lng1;
    public static javax.swing.JTextField ne;
    public static javax.swing.JTextField ne1;
    private javax.swing.JTextField nom1;
    private javax.swing.JTextField nom3;
    private javax.swing.JTextField nom4;
    private javax.swing.JTextField nom5;
    private javax.swing.JTextField nomm1;
    private javax.swing.JTextField nomm2;
    private javax.swing.JTextField nomm6;
    public static javax.swing.JTextField npp;
    public static javax.swing.JTextField nrv;
    private javax.swing.JTextField numm1;
    private javax.swing.JTextField numm2;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p6;
    private javax.swing.JPanel p7;
    private javax.swing.JComboBox<String> par;
    private javax.swing.JComboBox<String> par1;
    private javax.swing.JLabel periodique;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnl1;
    private javax.swing.JTextField prenomm1;
    private javax.swing.JTextField prenomm2;
    private javax.swing.JTextField prenomm6;
    private javax.swing.JPasswordField pss2;
    public static javax.swing.JTextField ptid;
    public static javax.swing.JTextField ptid2;
    public static javax.swing.JTextField ptid3;
    public static javax.swing.JTextField ptid4;
    public static javax.swing.JTextField r;
    public static javax.swing.JTextField r1;
    private javax.swing.JButton rstt;
    private javax.swing.JSeparator s13;
    private javax.swing.JSeparator s14;
    private javax.swing.JSeparator s15;
    private javax.swing.JSeparator s16;
    private javax.swing.JSeparator s17;
    private javax.swing.JSeparator s18;
    private javax.swing.JSeparator s19;
    private javax.swing.JSeparator s2;
    private javax.swing.JSeparator s20;
    private javax.swing.JSeparator s21;
    private javax.swing.JSeparator s22;
    private javax.swing.JSeparator s23;
    private javax.swing.JSeparator s24;
    private javax.swing.JSeparator s25;
    private javax.swing.JSeparator s26;
    private javax.swing.JSeparator s27;
    private javax.swing.JSeparator s28;
    private javax.swing.JSeparator s29;
    private javax.swing.JSeparator s3;
    private javax.swing.JSeparator s30;
    private javax.swing.JSeparator s34;
    private javax.swing.JSeparator s35;
    private javax.swing.JSeparator s36;
    private javax.swing.JSeparator s37;
    private javax.swing.JSeparator s38;
    private javax.swing.JSeparator s39;
    private javax.swing.JSeparator s4;
    private javax.swing.JSeparator s40;
    private javax.swing.JSeparator s41;
    private javax.swing.JSeparator s42;
    private javax.swing.JSeparator s43;
    private javax.swing.JSeparator s44;
    private javax.swing.JSeparator s45;
    private javax.swing.JSeparator s46;
    private javax.swing.JSeparator s47;
    private javax.swing.JSeparator s48;
    private javax.swing.JSeparator s49;
    private javax.swing.JSeparator s50;
    private javax.swing.JSeparator s51;
    private javax.swing.JSeparator s52;
    private javax.swing.JSeparator s53;
    private javax.swing.JSeparator s54;
    private javax.swing.JSeparator s55;
    private javax.swing.JSeparator s56;
    private javax.swing.JSeparator s57;
    private javax.swing.JSeparator s58;
    private javax.swing.JSeparator s59;
    private javax.swing.JSeparator s6;
    private javax.swing.JSeparator s60;
    private javax.swing.JSeparator s61;
    private javax.swing.JSeparator s62;
    private javax.swing.JSeparator s63;
    private javax.swing.JSeparator s7;
    private javax.swing.JSeparator s8;
    private javax.swing.JSeparator s9;
    private javax.swing.JTable tab;
    private javax.swing.JTable tab1;
    private javax.swing.JTable tab2;
    private javax.swing.JTable tab3;
    private javax.swing.JTable tab4;
    private javax.swing.JButton tbtb;
    public static javax.swing.JTextField them;
    public static javax.swing.JTextField them1;
    private javax.swing.JLabel time;
    private javax.swing.JTextField tire;
    private javax.swing.JTextField tire1;
    private javax.swing.JTextField tire2;
    private javax.swing.JTextField tire3;
    private javax.swing.JTabbedPane tp1;
    private javax.swing.JTabbedPane tp3;
    private javax.swing.JTabbedPane tp4;
    private javax.swing.JLabel user2;
    private javax.swing.JLabel user3;
    private javax.swing.JLabel user5;
    private javax.swing.JLabel user6;
    private javax.swing.JLabel user7;
    private javax.swing.JLabel user8;
    private javax.swing.JComboBox<String> val;
    private javax.swing.JComboBox<String> val1;
    public static javax.swing.JTextField vid;
    public static javax.swing.JTextField vid12;
    public static javax.swing.JTextField vid13;
    public static javax.swing.JTextField vid2;
    public static javax.swing.JTextField vid3;
    public static javax.swing.JTextField vid6;
    public static javax.swing.JTextField zz;
    public static javax.swing.JTextField zz1;
    private javax.swing.JTextField zz10;
    public static javax.swing.JTextField zz2;
    public static javax.swing.JTextField zz3;
    public static javax.swing.JTextField zz7;
    private javax.swing.JTextField zz8;
    // End of variables declaration//GEN-END:variables
}
