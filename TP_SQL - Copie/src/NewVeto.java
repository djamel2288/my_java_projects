
import com.mxrck.autocompleter.TextAutoCompleter;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

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
    String url = "jdbc:sqlserver://localhost:1433;databaseName=TPBD;user=enidde;password=azert";
    Connection c;
    Statement st;
    PreparedStatement ps;
    ResultSet re;

    DefaultTableModel d = new DefaultTableModel();
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
        String url = "jdbc:sqlserver://localhost:1433;databaseName=TPBD;user=enidde;password=azert";
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

    public NewVeto() throws SQLException {

        initComponents();

        this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight() - 30));
        showDate();
        showTime();
        //.getTableHeader.setVisible(false)

        hotel.setVisible(false);
        client.setVisible(false);
        reservation.setVisible(false);

        lbl_4.setIcon(ResizeImage(lbl_4, "src/image/hotel.png", null));
        lbl_image.setIcon(ResizeImage(lbl_image, "src/image/SLR Back Side_104px.png", null));

        d = (DefaultTableModel) jTable1.getModel();

        this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight() - 30));
        c = getConnection();
        st = c.createStatement();

        d.setRowCount(0);
        String req2 = "select * from hotel";
        //JOptionPane.showMessageDialog(null, "succes");
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numhotel"), re.getString("nom"), re.getString("ville"), re.getString("etoiles")};
                d.addRow(t);
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
        String reqq = "select * from client";
        try {
            re = st.executeQuery(reqq);
            while (re.next()) {
                String t[] = new String[]{re.getString("numc"), re.getString("nomc"), re.getString("prenomc")};
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
        dta.setDate(d1);
        Date d2 = new Date();
        dta2.setDate(d2);

        try {
            c = DriverManager.getConnection(url);
            st = c.createStatement();
            JOptionPane.showMessageDialog(null, "connecter avec succes");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        table2.setRowCount(0);
        String req4 = "select * from reservation";
        try {
            re = st.executeQuery(req4);
            while (re.next()) {
                String t[] = new String[]{re.getString("numc"), re.getString("numhotel"), re.getString("date_a"), re.getString("date_d"), re.getString("numchambre")};
                table2.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        /**
         * ****************************************************************
         */
        errpss.setVisible(false);
        table4 = (DefaultTableModel) tab4.getModel();
        table4.setRowCount(0);

        table4.setRowCount(0);
        String req28 = "select * from chambre";
        try {
            re = st.executeQuery(req28);
            while (re.next()) {
                String t[] = new String[]{re.getString("numchambre"), re.getString("numhotel"), re.getString("etage"), re.getString("typechambre"), re.getString("prixnuit")};
                table4.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

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
        s10 = new javax.swing.JSeparator();
        ptid1 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        s11 = new javax.swing.JSeparator();
        nom2 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        s12 = new javax.swing.JSeparator();
        prenom2 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        s13 = new javax.swing.JSeparator();
        s17 = new javax.swing.JSeparator();
        adr2 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        ButtonUpd1 = new javax.swing.JButton();
        gender2 = new javax.swing.JComboBox<>();
        khayer1 = new javax.swing.JButton();
        lbl_image1 = new javax.swing.JLabel();
        jDialog3 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        s5 = new javax.swing.JSeparator();
        vid1 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        nomm3 = new javax.swing.JTextField();
        s20 = new javax.swing.JSeparator();
        jLabel55 = new javax.swing.JLabel();
        numm3 = new javax.swing.JTextField();
        s21 = new javax.swing.JSeparator();
        prenomm3 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        s28 = new javax.swing.JSeparator();
        s29 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        s30 = new javax.swing.JSeparator();
        adrm3 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        ButtonUpda2 = new javax.swing.JButton();
        genderm3 = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        s25 = new javax.swing.JSeparator();
        s26 = new javax.swing.JSeparator();
        jLabel61 = new javax.swing.JLabel();
        pss3 = new javax.swing.JPasswordField();
        pss4 = new javax.swing.JPasswordField();
        psd1 = new javax.swing.JTextField();
        s27 = new javax.swing.JSeparator();
        pseudou1 = new javax.swing.JLabel();
        jDialog4 = new javax.swing.JDialog();
        ButtonUpdaa1 = new javax.swing.JButton();
        zz4 = new javax.swing.JTextField();
        dta3 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        dta4 = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        zz5 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        zz6 = new javax.swing.JTextField();
        jDialog5 = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        s14 = new javax.swing.JSeparator();
        vid4 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        nomm4 = new javax.swing.JTextField();
        s22 = new javax.swing.JSeparator();
        prenomm4 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        s31 = new javax.swing.JSeparator();
        s32 = new javax.swing.JSeparator();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        s33 = new javax.swing.JSeparator();
        adrm4 = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        ButtonUpda4 = new javax.swing.JButton();
        genderm4 = new javax.swing.JComboBox<>();
        vid5 = new javax.swing.JTextField();
        s15 = new javax.swing.JSeparator();
        jLabel78 = new javax.swing.JLabel();
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
        hotel = new javax.swing.JScrollPane();
        tp1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ptid2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        nom1 = new javax.swing.JTextField();
        s3 = new javax.swing.JSeparator();
        prenom1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        s6 = new javax.swing.JSeparator();
        s8 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        s7 = new javax.swing.JSeparator();
        adr1 = new javax.swing.JTextField();
        ButtonDlt = new javax.swing.JButton();
        ButtonUpd = new javax.swing.JButton();
        gender1 = new javax.swing.JComboBox<>();
        ButtonADD = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        khayer = new javax.swing.JButton();
        lbl_image = new javax.swing.JLabel();
        ButtonSrch = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        ptid = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        client = new javax.swing.JScrollPane();
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
        adrm1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        ButtonSrcha = new javax.swing.JButton();
        ButtonDlta = new javax.swing.JButton();
        ButtonUpda = new javax.swing.JButton();
        genderm1 = new javax.swing.JComboBox<>();
        ButtonADDa = new javax.swing.JButton();
        pss1 = new javax.swing.JPasswordField();
        errpss = new javax.swing.JLabel();
        s42 = new javax.swing.JSeparator();
        chambre = new javax.swing.JScrollPane();
        tp4 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tab4 = new javax.swing.JTable();
        ptid4 = new javax.swing.JTextField();
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
        reservation = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tab = new javax.swing.JTable();
        dta = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ButtonADDa1 = new javax.swing.JButton();
        ButtonUpda1 = new javax.swing.JButton();
        ButtonDlta1 = new javax.swing.JButton();
        zz = new javax.swing.JTextField();
        dta2 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        zz2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        zz3 = new javax.swing.JTextField();

        jDialog1.setBackground(new java.awt.Color(222, 222, 222));
        jDialog1.setLocation(new java.awt.Point(0, 0));
        jDialog1.setMinimumSize(new java.awt.Dimension(910, 448));
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s10.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 111, 10));

        ptid1.setEditable(false);
        ptid1.setBackground(new java.awt.Color(222, 222, 222));
        ptid1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ptid1.setForeground(new java.awt.Color(0, 0, 0));
        ptid1.setBorder(null);
        ptid1.setCaretColor(new java.awt.Color(255, 153, 0));
        ptid1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptid1FocusGained(evt);
            }
        });
        ptid1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ptid1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ptid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ptid1ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(ptid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 111, 22));

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(16, 106, 143));
        jLabel45.setText("NumHotel :");
        jDialog1.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 22));

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(16, 106, 143));
        jLabel46.setText("Nom :");
        jDialog1.getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 22));

        s11.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 158, 10));

        nom2.setBackground(new java.awt.Color(222, 222, 222));
        nom2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nom2.setForeground(new java.awt.Color(0, 0, 0));
        nom2.setBorder(null);
        nom2.setCaretColor(new java.awt.Color(255, 153, 0));
        nom2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nom2FocusGained(evt);
            }
        });
        nom2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nom2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom2ActionPerformed(evt);
            }
        });
        nom2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nom2KeyTyped(evt);
            }
        });
        jDialog1.getContentPane().add(nom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 158, 22));

        jLabel48.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(16, 106, 143));
        jLabel48.setText("Ville :");
        jDialog1.getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, 22));

        s12.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 158, 10));

        prenom2.setBackground(new java.awt.Color(222, 222, 222));
        prenom2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        prenom2.setForeground(new java.awt.Color(0, 0, 0));
        prenom2.setBorder(null);
        prenom2.setCaretColor(new java.awt.Color(255, 153, 0));
        prenom2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                prenom2FocusGained(evt);
            }
        });
        prenom2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                prenom2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        prenom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenom2ActionPerformed(evt);
            }
        });
        prenom2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenom2KeyTyped(evt);
            }
        });
        jDialog1.getContentPane().add(prenom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 158, 22));

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(16, 106, 143));
        jLabel49.setText("Gender :");
        jDialog1.getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 110, 22));

        s13.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 158, 10));

        s17.setBackground(new java.awt.Color(33, 155, 191));
        jDialog1.getContentPane().add(s17, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 312, 518, -1));

        adr2.setBackground(new java.awt.Color(222, 222, 222));
        adr2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        adr2.setForeground(new java.awt.Color(0, 0, 0));
        adr2.setBorder(null);
        adr2.setCaretColor(new java.awt.Color(255, 153, 0));
        adr2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adr2FocusGained(evt);
            }
        });
        adr2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                adr2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        adr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adr2ActionPerformed(evt);
            }
        });
        adr2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adr2KeyTyped(evt);
            }
        });
        jDialog1.getContentPane().add(adr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 284, 518, 22));

        jLabel51.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(16, 106, 143));
        jLabel51.setText("Address :");
        jDialog1.getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 284, -1, 22));

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

        gender2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        jDialog1.getContentPane().add(gender2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 159, -1));

        khayer1.setText("Selectioner");
        khayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khayer1ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(khayer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 220, 160, 55));

        lbl_image1.setBackground(new java.awt.Color(0, 255, 0));
        lbl_image1.setForeground(new java.awt.Color(200, 200, 200));
        jDialog1.getContentPane().add(lbl_image1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 160, 170));

        jDialog3.setMinimumSize(new java.awt.Dimension(900, 513));

        jPanel3.setBackground(new java.awt.Color(222, 222, 222));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s5.setBackground(new java.awt.Color(33, 155, 191));
        jPanel3.add(s5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 111, 10));

        vid1.setBackground(new java.awt.Color(222, 222, 222));
        vid1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        vid1.setForeground(new java.awt.Color(0, 0, 0));
        vid1.setBorder(null);
        vid1.setCaretColor(new java.awt.Color(255, 153, 0));
        vid1.setEnabled(false);
        vid1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vid1FocusGained(evt);
            }
        });
        vid1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                vid1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        vid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vid1ActionPerformed(evt);
            }
        });
        jPanel3.add(vid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 111, 22));

        jLabel53.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(16, 106, 143));
        jLabel53.setText("ID :");
        jPanel3.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 22));

        jLabel54.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(16, 106, 143));
        jLabel54.setText("Nom :");
        jPanel3.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 22));

        nomm3.setBackground(new java.awt.Color(222, 222, 222));
        nomm3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nomm3.setForeground(new java.awt.Color(0, 0, 0));
        nomm3.setBorder(null);
        nomm3.setCaretColor(new java.awt.Color(255, 153, 0));
        nomm3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nomm3FocusGained(evt);
            }
        });
        nomm3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nomm3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nomm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomm3ActionPerformed(evt);
            }
        });
        nomm3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomm3KeyTyped(evt);
            }
        });
        jPanel3.add(nomm3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 158, 22));

        s20.setBackground(new java.awt.Color(33, 155, 191));
        jPanel3.add(s20, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 158, 10));

        jLabel55.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(16, 106, 143));
        jLabel55.setText("N°Tel :");
        jPanel3.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 22));

        numm3.setBackground(new java.awt.Color(222, 222, 222));
        numm3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        numm3.setForeground(new java.awt.Color(0, 0, 0));
        numm3.setBorder(null);
        numm3.setCaretColor(new java.awt.Color(255, 153, 0));
        numm3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                numm3FocusGained(evt);
            }
        });
        numm3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                numm3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        numm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numm3ActionPerformed(evt);
            }
        });
        numm3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numm3KeyTyped(evt);
            }
        });
        jPanel3.add(numm3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 158, 22));

        s21.setBackground(new java.awt.Color(33, 155, 191));
        jPanel3.add(s21, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 158, 10));

        prenomm3.setBackground(new java.awt.Color(222, 222, 222));
        prenomm3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        prenomm3.setForeground(new java.awt.Color(0, 0, 0));
        prenomm3.setBorder(null);
        prenomm3.setCaretColor(new java.awt.Color(255, 153, 0));
        prenomm3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                prenomm3FocusGained(evt);
            }
        });
        prenomm3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                prenomm3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        prenomm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomm3ActionPerformed(evt);
            }
        });
        prenomm3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenomm3KeyTyped(evt);
            }
        });
        jPanel3.add(prenomm3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 158, 22));

        jLabel56.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(16, 106, 143));
        jLabel56.setText("Prenom:");
        jPanel3.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, 22));

        s28.setBackground(new java.awt.Color(33, 155, 191));
        jPanel3.add(s28, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 158, 10));

        s29.setBackground(new java.awt.Color(33, 155, 191));
        jPanel3.add(s29, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 158, 10));

        jLabel57.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(16, 106, 143));
        jLabel57.setText("Gender :");
        jPanel3.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, -1, 22));

        jLabel58.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(16, 106, 143));
        jLabel58.setText("Address :");
        jPanel3.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, 22));

        s30.setBackground(new java.awt.Color(33, 155, 191));
        jPanel3.add(s30, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 518, 10));

        adrm3.setBackground(new java.awt.Color(222, 222, 222));
        adrm3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        adrm3.setForeground(new java.awt.Color(0, 0, 0));
        adrm3.setBorder(null);
        adrm3.setCaretColor(new java.awt.Color(255, 153, 0));
        adrm3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adrm3FocusGained(evt);
            }
        });
        adrm3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                adrm3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        adrm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adrm3ActionPerformed(evt);
            }
        });
        jPanel3.add(adrm3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 518, 22));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 645, 10));

        ButtonUpda2.setBackground(new java.awt.Color(16, 106, 143));
        ButtonUpda2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpda2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Change User_40px.png"))); // NOI18N
        ButtonUpda2.setText("UpDate");
        ButtonUpda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpda2ActionPerformed(evt);
            }
        });
        jPanel3.add(ButtonUpda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, -1, 53));

        genderm3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel3.add(genderm3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 158, -1));

        jLabel59.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(16, 106, 143));
        jLabel59.setText("Nom :");
        jPanel3.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 22));

        jLabel60.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(16, 106, 143));
        jLabel60.setText("Password :");
        jPanel3.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, -1, 22));

        s25.setBackground(new java.awt.Color(33, 155, 191));
        jPanel3.add(s25, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 158, 10));

        s26.setBackground(new java.awt.Color(33, 155, 191));
        jPanel3.add(s26, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 158, 10));

        jLabel61.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(16, 106, 143));
        jLabel61.setText("Confirmer :");
        jPanel3.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, -1, 22));

        pss3.setBackground(new java.awt.Color(222, 222, 222));
        pss3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        pss3.setForeground(new java.awt.Color(0, 0, 0));
        pss3.setBorder(null);
        pss3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pss3FocusGained(evt);
            }
        });
        pss3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pss3ActionPerformed(evt);
            }
        });
        pss3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pss3KeyTyped(evt);
            }
        });
        jPanel3.add(pss3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, 160, 20));

        pss4.setBackground(new java.awt.Color(222, 222, 222));
        pss4.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        pss4.setForeground(new java.awt.Color(0, 0, 0));
        pss4.setBorder(null);
        pss4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pss4FocusGained(evt);
            }
        });
        pss4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pss4ActionPerformed(evt);
            }
        });
        pss4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pss4KeyTyped(evt);
            }
        });
        jPanel3.add(pss4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 160, 20));

        psd1.setBackground(new java.awt.Color(222, 222, 222));
        psd1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        psd1.setForeground(new java.awt.Color(0, 0, 0));
        psd1.setBorder(null);
        psd1.setCaretColor(new java.awt.Color(255, 153, 0));
        psd1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                psd1FocusGained(evt);
            }
        });
        psd1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                psd1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        psd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                psd1ActionPerformed(evt);
            }
        });
        psd1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                psd1KeyTyped(evt);
            }
        });
        jPanel3.add(psd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 158, 22));

        s27.setBackground(new java.awt.Color(33, 155, 191));
        jPanel3.add(s27, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 158, 10));

        pseudou1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        pseudou1.setForeground(new java.awt.Color(16, 106, 143));
        pseudou1.setText("Pseudou :");
        jPanel3.add(pseudou1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, 22));

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
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

        zz4.setBackground(new java.awt.Color(255, 255, 255));
        zz4.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz4.setForeground(new java.awt.Color(0, 0, 0));
        zz4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zz4ActionPerformed(evt);
            }
        });
        zz4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zz4KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zz4KeyTyped(evt);
            }
        });

        dta3.setForeground(new java.awt.Color(0, 0, 0));
        dta3.setToolTipText("");
        dta3.setDateFormatString("yyyy-MM-dd");
        dta3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dta3KeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Date Arriver :");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("N°Hotel :");

        dta4.setForeground(new java.awt.Color(0, 0, 0));
        dta4.setToolTipText("");
        dta4.setDateFormatString("yyyy-MM-dd");
        dta4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dta4KeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Date  Deppart :");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("N°Chamber :");

        zz5.setBackground(new java.awt.Color(255, 255, 255));
        zz5.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz5.setForeground(new java.awt.Color(0, 0, 0));
        zz5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zz5ActionPerformed(evt);
            }
        });
        zz5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zz5KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zz5KeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("N°Client :");

        zz6.setBackground(new java.awt.Color(255, 255, 255));
        zz6.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        zz6.setForeground(new java.awt.Color(0, 0, 0));
        zz6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zz6ActionPerformed(evt);
            }
        });
        zz6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zz6KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zz6KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                .addContainerGap(393, Short.MAX_VALUE)
                .addComponent(ButtonUpdaa1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(371, 371, 371))
            .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jDialog4Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dta4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDialog4Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dta3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(51, 51, 51)
                    .addComponent(jLabel14)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(zz4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(67, 67, 67)
                    .addComponent(jLabel19)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(zz5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(62, 62, 62)
                    .addComponent(jLabel20)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(zz6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addComponent(ButtonUpdaa1)
                .addGap(47, 47, 47))
            .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog4Layout.createSequentialGroup()
                    .addGap(114, 114, 114)
                    .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(zz4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(zz6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(zz5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(dta3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(jLabel18))
                            .addComponent(dta4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(114, Short.MAX_VALUE)))
        );

        jDialog5.setMinimumSize(new java.awt.Dimension(900, 513));

        jPanel5.setBackground(new java.awt.Color(222, 222, 222));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s14.setBackground(new java.awt.Color(33, 155, 191));
        jPanel5.add(s14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 111, 10));

        vid4.setEditable(false);
        vid4.setBackground(new java.awt.Color(153, 153, 153));
        vid4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        vid4.setForeground(new java.awt.Color(0, 0, 0));
        vid4.setBorder(null);
        vid4.setCaretColor(new java.awt.Color(255, 153, 0));
        vid4.setEnabled(false);
        vid4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vid4FocusGained(evt);
            }
        });
        vid4.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                vid4CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        vid4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vid4ActionPerformed(evt);
            }
        });
        jPanel5.add(vid4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 111, 22));

        jLabel62.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(16, 106, 143));
        jLabel62.setText("N°Hotel :");
        jPanel5.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 22));

        jLabel63.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(16, 106, 143));
        jLabel63.setText("Etage :");
        jPanel5.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        nomm4.setBackground(new java.awt.Color(222, 222, 222));
        nomm4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nomm4.setForeground(new java.awt.Color(0, 0, 0));
        nomm4.setBorder(null);
        nomm4.setCaretColor(new java.awt.Color(255, 153, 0));
        nomm4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nomm4FocusGained(evt);
            }
        });
        nomm4.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                nomm4CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        nomm4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomm4ActionPerformed(evt);
            }
        });
        nomm4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomm4KeyTyped(evt);
            }
        });
        jPanel5.add(nomm4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 158, 22));

        s22.setBackground(new java.awt.Color(33, 155, 191));
        jPanel5.add(s22, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 158, 10));

        prenomm4.setBackground(new java.awt.Color(222, 222, 222));
        prenomm4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        prenomm4.setForeground(new java.awt.Color(0, 0, 0));
        prenomm4.setBorder(null);
        prenomm4.setCaretColor(new java.awt.Color(255, 153, 0));
        prenomm4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                prenomm4FocusGained(evt);
            }
        });
        prenomm4.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                prenomm4CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        prenomm4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomm4ActionPerformed(evt);
            }
        });
        prenomm4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenomm4KeyTyped(evt);
            }
        });
        jPanel5.add(prenomm4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 158, 22));

        jLabel65.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(16, 106, 143));
        jLabel65.setText("Prix / Nuit :");
        jPanel5.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, 22));

        s31.setBackground(new java.awt.Color(33, 155, 191));
        jPanel5.add(s31, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 158, 10));

        s32.setBackground(new java.awt.Color(33, 155, 191));
        jPanel5.add(s32, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 158, 10));

        jLabel66.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(16, 106, 143));
        jLabel66.setText("Type :");
        jPanel5.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 30));

        jLabel67.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(16, 106, 143));
        jLabel67.setText("Address :");
        jPanel5.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, 22));

        s33.setBackground(new java.awt.Color(33, 155, 191));
        jPanel5.add(s33, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 518, 10));

        adrm4.setBackground(new java.awt.Color(222, 222, 222));
        adrm4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        adrm4.setForeground(new java.awt.Color(0, 0, 0));
        adrm4.setBorder(null);
        adrm4.setCaretColor(new java.awt.Color(255, 153, 0));
        adrm4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adrm4FocusGained(evt);
            }
        });
        adrm4.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                adrm4CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        adrm4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adrm4ActionPerformed(evt);
            }
        });
        jPanel5.add(adrm4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 518, 22));
        jPanel5.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 645, 10));

        ButtonUpda4.setBackground(new java.awt.Color(16, 106, 143));
        ButtonUpda4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonUpda4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Change User_40px.png"))); // NOI18N
        ButtonUpda4.setText("UpDate");
        ButtonUpda4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpda4ActionPerformed(evt);
            }
        });
        jPanel5.add(ButtonUpda4, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 330, 160, 53));

        genderm4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "simple", "double", "triple", "suite", "autre", " " }));
        genderm4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderm4ActionPerformed(evt);
            }
        });
        jPanel5.add(genderm4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 158, -1));

        vid5.setEditable(false);
        vid5.setBackground(new java.awt.Color(153, 153, 153));
        vid5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        vid5.setForeground(new java.awt.Color(0, 0, 0));
        vid5.setBorder(null);
        vid5.setCaretColor(new java.awt.Color(255, 153, 0));
        vid5.setEnabled(false);
        vid5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vid5FocusGained(evt);
            }
        });
        vid5.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                vid5CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        vid5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vid5ActionPerformed(evt);
            }
        });
        jPanel5.add(vid5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 111, 22));

        s15.setBackground(new java.awt.Color(33, 155, 191));
        jPanel5.add(s15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 111, 10));

        jLabel78.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(16, 106, 143));
        jLabel78.setText("N°Chambre :");
        jPanel5.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, 22));

        javax.swing.GroupLayout jDialog5Layout = new javax.swing.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
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

        javax.swing.GroupLayout p5Layout = new javax.swing.GroupLayout(p5);
        p5.setLayout(p5Layout);
        p5Layout.setHorizontalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174))
        );
        p5Layout.setVerticalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel1.add(p5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 246, 50));

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
        jLabel29.setText("Checkout");

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

        jPanel1.add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 246, 50));

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
        jLabel71.setText("Reservation");

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

        jPanel1.add(p6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 246, 50));

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
        jLabel33.setText("Hotels");

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

        jPanel1.add(p4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 246, 50));

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
        jLabel38.setText("Clients");

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

        jPanel1.add(p7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 246, 50));

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
        jLabel41.setText("Chambres");

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

        jPanel1.add(p2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 246, 50));

        s43.setBackground(new java.awt.Color(129, 187, 118));
        s43.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(s43, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 150, 230, 10));

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
                "NumHotel", "Nom", "Ville", "Etoil", "tt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Byte.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
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
                .addContainerGap(263, Short.MAX_VALUE))
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

        prenom1.setBackground(new java.awt.Color(222, 222, 222));
        prenom1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        prenom1.setForeground(new java.awt.Color(0, 0, 0));
        prenom1.setBorder(null);
        prenom1.setCaretColor(new java.awt.Color(255, 153, 0));
        prenom1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                prenom1FocusGained(evt);
            }
        });
        prenom1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                prenom1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        prenom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenom1ActionPerformed(evt);
            }
        });
        prenom1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenom1KeyTyped(evt);
            }
        });
        jPanel4.add(prenom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, 270, 22));

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(35, 69, 103));
        jLabel35.setText("Ville");
        jPanel4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, -1, 22));

        s6.setBackground(new java.awt.Color(33, 155, 191));
        jPanel4.add(s6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 270, 10));

        s8.setBackground(new java.awt.Color(33, 155, 191));
        jPanel4.add(s8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 250, 20));

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(35, 69, 103));
        jLabel36.setText("Etoiles :");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 100, 22));

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(35, 69, 103));
        jLabel40.setText("Address :");
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

        gender1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        jPanel4.add(gender1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 250, -1));

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

        jLabel5.setBackground(new java.awt.Color(222, 222, 222));
        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Take appointment!!");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 600, -1, -1));
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

        ButtonSrch.setBackground(new java.awt.Color(222, 222, 222));
        ButtonSrch.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ButtonSrch.setForeground(new java.awt.Color(16, 106, 143));
        ButtonSrch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Find User Male_40px_3.png"))); // NOI18N
        ButtonSrch.setText("Search");
        ButtonSrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSrchActionPerformed(evt);
            }
        });
        jPanel4.add(ButtonSrch, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 150, -1));

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

        tp1.addTab("Seting", jPanel4);

        hotel.setViewportView(tp1);

        jPanel8.setBackground(new java.awt.Color(222, 222, 222));

        tab3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NumClient", "Nom", "Prenom"
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
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
        jLabel72.setText("Nom :");
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
        jLabel73.setText("N°Tel :");
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
        jPanel10.add(numm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 158, 22));

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
        jLabel74.setText("Prenom:");
        jPanel10.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, 22));

        s37.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s37, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 158, 10));

        s38.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s38, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, 158, 10));

        jLabel75.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(35, 69, 103));
        jLabel75.setText("Gender :");
        jPanel10.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, -1, 22));

        jLabel76.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(35, 69, 103));
        jLabel76.setText("Address :");
        jPanel10.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, 22));

        s39.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s39, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 518, 10));

        adrm1.setBackground(new java.awt.Color(222, 222, 222));
        adrm1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        adrm1.setForeground(new java.awt.Color(0, 0, 0));
        adrm1.setBorder(null);
        adrm1.setCaretColor(new java.awt.Color(255, 153, 0));
        adrm1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adrm1FocusGained(evt);
            }
        });
        adrm1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                adrm1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        adrm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adrm1ActionPerformed(evt);
            }
        });
        adrm1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adrm1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adrm1KeyTyped(evt);
            }
        });
        jPanel10.add(adrm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 518, 22));
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

        genderm1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel10.add(genderm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 158, -1));

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

        pss1.setBackground(new java.awt.Color(222, 222, 222));
        pss1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        pss1.setForeground(new java.awt.Color(0, 0, 0));
        pss1.setBorder(null);
        pss1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pss1FocusGained(evt);
            }
        });
        pss1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                pss1InputMethodTextChanged(evt);
            }
        });
        pss1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pss1ActionPerformed(evt);
            }
        });
        pss1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pss1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pss1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pss1KeyTyped(evt);
            }
        });
        jPanel10.add(pss1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 160, 20));

        errpss.setForeground(new java.awt.Color(255, 0, 0));
        jPanel10.add(errpss, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, -1, -1));

        s42.setBackground(new java.awt.Color(129, 187, 118));
        jPanel10.add(s42, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 158, 10));

        tp3.addTab("Seting", jPanel10);

        client.setViewportView(tp3);

        jPanel11.setBackground(new java.awt.Color(222, 222, 222));

        tab4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NumChambre", "NumHotel", "Etage", "Type", "PrixNuit"
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
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(ptid4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(ptid4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
        );

        tp4.addTab("View", jPanel11);

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

        tp4.addTab("Seting", jPanel12);

        chambre.setViewportView(tp4);

        jPanel9.setBackground(new java.awt.Color(222, 222, 222));
        jPanel9.setForeground(new java.awt.Color(244, 244, 244));

        tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°Client", "N°Hotel", "Date_A", "Date_D", "N°Chambre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab.setAutoscrolls(false);
        tab.setGridColor(new java.awt.Color(204, 0, 0));
        jScrollPane6.setViewportView(tab);

        dta.setForeground(new java.awt.Color(0, 0, 0));
        dta.setToolTipText("");
        dta.setDateFormatString("yyyy-MM-dd");
        dta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dtaKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Date Arriver :");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("N°Hotel :");

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

        dta2.setForeground(new java.awt.Color(0, 0, 0));
        dta2.setToolTipText("");
        dta2.setDateFormatString("yyyy-MM-dd");
        dta2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dta2KeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Date  Deppart :");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("N°Chamber :");

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

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("N°Client :");

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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dta2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zz, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zz2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zz3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(ButtonADDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(ButtonUpda1)
                        .addGap(64, 64, 64)
                        .addComponent(ButtonDlta1))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(zz, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(zz3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(zz2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(dta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addComponent(jLabel15))
                        .addComponent(dta2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonADDa1)
                    .addComponent(ButtonUpda1)
                    .addComponent(ButtonDlta1))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        reservation.setViewportView(jPanel9);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(hotel, javax.swing.GroupLayout.PREFERRED_SIZE, 1095, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(265, Short.MAX_VALUE)
                    .addComponent(client, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(264, Short.MAX_VALUE)
                    .addComponent(reservation, javax.swing.GroupLayout.PREFERRED_SIZE, 1093, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(273, Short.MAX_VALUE)
                    .addComponent(chambre, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
            .addComponent(hotel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(client))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reservation, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addComponent(chambre, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        hotel.setVisible(false);
        client.setVisible(false);
        reservation.setVisible(false);
        chambre.setVisible(true);
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
        hotel.setVisible(false);
        client.setVisible(true);
        reservation.setVisible(false);
        chambre.setVisible(false);

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
        hotel.setVisible(false);
        client.setVisible(false);
        chambre.setVisible(false);
        reservation.setVisible(true);

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
        hotel.setVisible(true);
        client.setVisible(false);
        reservation.setVisible(false);
        chambre.setVisible(false);

    }//GEN-LAST:event_p4MouseClicked

    private void p3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_p3MouseReleased

    private void p3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_p3MousePressed

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        // TODO add your handling code here:
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


    private void ptid1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptid1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid1FocusGained

    private void ptid1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ptid1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid1CaretPositionChanged

    private void ptid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid1ActionPerformed

    private void nom2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nom2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nom2FocusGained

    private void nom2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nom2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nom2CaretPositionChanged

    private void nom2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom2ActionPerformed

    private void nom2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom2KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_nom2KeyTyped

    private void prenom2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenom2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_prenom2FocusGained

    private void prenom2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_prenom2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_prenom2CaretPositionChanged

    private void prenom2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenom2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenom2ActionPerformed

    private void prenom2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenom2KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_prenom2KeyTyped

    private void adr2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adr2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_adr2FocusGained

    private void adr2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_adr2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_adr2CaretPositionChanged

    private void adr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adr2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adr2ActionPerformed

    private void adr2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adr2KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE
                || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_adr2KeyTyped

    private void ButtonUpd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpd1ActionPerformed
        try {
            String numhotelu, nomu, villeu, etoilesu;
            numhotelu = ptid1.getText().toString();
            nomu = nom2.getText();
            villeu = prenom2.getText();
            etoilesu = gender2.getSelectedItem().toString();
            /*
            String UpdateQuery = "UPDATE hotel SET nom = ?,ville = ?,etoiles = ? WHERE numhotel = ?";
            PreparedStatement ps = getConnection().prepareStatement(UpdateQuery);
            ps.setString(1, nomu);
            ps.setString(2, villeu);
            ps.setString(3, etoilesu);
            ps.setString(4, numhotelu);
            JOptionPane.showMessageDialog(null, "Modifier avec succer");*/

            String req = "update hotel set nom='" + nomu + "',ville='" + villeu + "'," + "etoiles='" + etoilesu + "' "
                    + "where numhotel='" + numhotelu + "'";
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
        String req2 = "select * from hotel";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numhotel"), re.getString("nom"), re.getString("ville"), re.getString("etoiles")};
                d.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }

        jDialog1.setVisible(false);
    }//GEN-LAST:event_ButtonUpd1ActionPerformed

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

    private void vid1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid1FocusGained

    private void vid1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid1CaretPositionChanged

    private void vid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid1ActionPerformed

    private void nomm3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomm3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm3FocusGained

    private void nomm3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nomm3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm3CaretPositionChanged

    private void nomm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomm3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm3ActionPerformed

    private void nomm3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomm3KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_nomm3KeyTyped

    private void numm3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numm3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_numm3FocusGained

    private void numm3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_numm3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_numm3CaretPositionChanged

    private void numm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numm3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numm3ActionPerformed

    private void numm3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numm3KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_numm3KeyTyped

    private void prenomm3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenomm3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm3FocusGained

    private void prenomm3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_prenomm3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm3CaretPositionChanged

    private void prenomm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomm3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm3ActionPerformed

    private void prenomm3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenomm3KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_prenomm3KeyTyped

    private void adrm3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adrm3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm3FocusGained

    private void adrm3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_adrm3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm3CaretPositionChanged

    private void adrm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adrm3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm3ActionPerformed

    private void ButtonUpda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda2ActionPerformed
        // TODO add your handling code here:
        try {
            String idu, nomu, prenomu;
            idu = vid1.getText().toString();
            nomu = nomm3.getText();
            prenomu = prenomm3.getText();
            String req = "update client set nomc='" + nomu + "',prenomc='" + prenomu + "' where numc=" + idu + " ";
            int test = st.executeUpdate(req);

            if (test == 1) {
                JOptionPane.showMessageDialog(null, "modifier avec succes");
            } else {
                JOptionPane.showMessageDialog(null, "modifier ??");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        table3.setRowCount(0);
        String req2 = "select * from client";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numc"), re.getString("nomc"), re.getString("prenomc")};
                table3.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
        jDialog3.setVisible(false);
    }//GEN-LAST:event_ButtonUpda2ActionPerformed

    private void ButtonUpdaa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdaa1ActionPerformed
        // TODO add your handling code here:
        Date d1, d2, d3;

        d1 = new Date();
        d1.setMinutes(0);
        d2 = dta3.getDate();
        d3 = dta4.getDate();
        if (d2.after(d1) || d2.equals(d1)) {

            try {
                String numc, date_a, date_d, numchambre, numhotel;

                SimpleDateFormat dttt = new SimpleDateFormat("yyyy-MM-dd");
                date_a = dttt.format(dta.getDate());
                date_d = dttt.format(dta2.getDate());

                numhotel = zz4.getText().toString();
                numchambre = zz5.getText().toString();
                numc = zz6.getText().toString();

                String req = "update reservation set date_d='" + dttt.format(dta4.getDate()) + "',numchambre=" + numchambre + " "
                        + "where date_a='" + date_a + "' and numc=" + numc + " and numc=" + numc + " and numhotel=" + numhotel + "  ";
                int test = st.executeUpdate(req);
                if (test == 1) {
                    JOptionPane.showMessageDialog(null, "Modifier avec succes");
                    jDialog4.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "n'est pas modifier");
                }
                System.err.println("1 "+date_a);
                System.err.println("2 "+date_d);
                System.err.println("3 "+ dttt.format(dta4.getDate()));
                
                jDialog4.setVisible(false);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "errr "+e);
            }
            table2.setRowCount(0);
            String req2 = "select * from reservation";
            try {
                re = st.executeQuery(req2);
                while (re.next()) {
                    String t[] = new String[]{re.getString("numc"), re.getString("numhotel"), re.getString("date_a"), re.getString("date_d"), re.getString("numchambre")};
                    table2.addRow(t);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error :" + e.toString());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Imposible de prendre un rendez-vous avant ce jeur la");
        }
        jDialog4.setVisible(false);
    }//GEN-LAST:event_ButtonUpdaa1ActionPerformed

    private void dtaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_dtaKeyTyped

    private void ButtonADDa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDa1ActionPerformed
        // TODO add your handling code here:
        if (!zz.getText().toString().equals("") || !dta.getDate().equals(null)) {
            try {
                //String id, nomc, prenomc, numc, animalc, nomac, addc, gender = null, datec;

                Date d1, d2, d3;

                d1 = new Date();

                d1.setMinutes(0);
                d2 = dta.getDate();
                d3 = dta2.getDate();

                if (d3.after(d2)) {
                    if (d2.after(d1) || d2.equals(d1)) {

                        String numc, numhotel, datea = null, dated = null, numchambre;
                        numc = zz3.getText().toString();
                        numchambre = zz2.getText().toString();
                        numhotel = zz.getText().toString();
                        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
                        datea = dtt.format(dta.getDate());
                        dated = dtt.format(dta2.getDate());

                        //String req = "select from clienttab where id='" + ptid.getText() + "'";
                        String req = "insert into reservation values(" + numc + ",'" + numhotel + "','" + datea + "','" + dated + "','" + numchambre + "') ";

                        int test = st.executeUpdate(req);
                        if (test == 1) {
                            JOptionPane.showMessageDialog(null, "add rdv");
                            //Date d1 = new Date();
                            dta.setDate(d1);
                            dta2.setDate(d1);
                        } else {
                            JOptionPane.showMessageDialog(null, "n'est pas ajouter");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "imposible de prendre une date avant ce jeur la");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "la date de depart doit etre apris la date darriver !");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "deja existe " + e);
            }

            table2.setRowCount(0);
            String req2 = "select * from reservation";
            try {
                re = st.executeQuery(req2);
                while (re.next()) {
                    String t[] = new String[]{re.getString("numc"), re.getString("numhotel"), re.getString("date_a"), re.getString("date_d"), re.getString("numchambre")};
                    table2.addRow(t);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error :" + e.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "SVP, les champe vide non autorisé");
        }

        Date d1 = new Date();
        dta.setDate(d1);
    }//GEN-LAST:event_ButtonADDa1ActionPerformed

    private void ButtonUpda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda1ActionPerformed
        // TODO add your handling code here:

        String numc, numchambre, numhotel;
        numc = zz3.getText().toString();
        numchambre = zz2.getText().toString();
        numhotel = zz.getText().toString();

        try {

            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");
            String req = "select * from reservation where date_a='" + dtt.format(dta.getDate()) + "' and numc=" + numc + " and numchambre=" + numchambre + " and numhotel=" + numhotel + " ";

            re = st.executeQuery(req);

            while (re.next()) {

                zz5.setText(re.getString("numchambre"));
                zz6.setText(re.getString("numc"));
                zz4.setText(re.getString("numhotel"));

                //dt1.setDate(dt.getDate());
                dta3.setDate(re.getDate("date_a"));
                dta4.setDate(re.getDate("date_d"));
                
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
            String numc, numchambre, numhotel;

            numc = zz3.getText().toString();
            numchambre = zz2.getText().toString();
            numhotel = zz.getText().toString();
            SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd");

            String req = "delete from reservation where date_a='" + dtt.format(dta.getDate()) + "' and numc=" + numc + " and numchambre=" + numchambre + " and numhotel=" + numhotel + " ";
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "supprimer avec succes");
                Date d1 = new Date();
                dta.setDate(d1);
                dta2.setDate(d1);

            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        table2.setRowCount(0);
        String req2 = "select * from reservation";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numc"), re.getString("numhotel"), re.getString("date_a"), re.getString("date_d"), re.getString("numchambre")};
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
            String req = "select * from client where numc=" + ptid.getText() + "";

            re = st.executeQuery(req);

            table3.setRowCount(0);

            while (re.next()) {
                Object t[] = new Object[]{re.getString("numc"), re.getString("nomc"), re.getString("prenomc"), re.getString("numc")};
                table3.addRow(t);
                ptid.setText(re.getString("numc"));
                nom1.setText(re.getString("nomc"));
                prenom1.setText(re.getString("prenomc"));
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
                    String req = "select * from login where numc=" + vid.getText() + " ";

                    re = st.executeQuery(req);

                    while (re.next()) {
                        vid1.setText(re.getString("numc"));
                        nomm3.setText(re.getString("nomc"));
                        prenomm3.setText(re.getString("prenomc"));
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
                String req = "delete from client where numc=" + vid.getText() + "";
                int test = st.executeUpdate(req);
                if (test == 1) {
                    JOptionPane.showMessageDialog(null, "supprimer avec succes");
                    vid.setText("");
                    nomm1.setText("");
                    prenomm1.setText("");
                    numm1.setText("");
                    genderm1.setSelectedItem(1);

                } else {
                    JOptionPane.showMessageDialog(null, "il n'existe pas");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "il n'existe pas");
            }

            table3.setRowCount(0);
            String req2 = "select * from client";
            try {
                re = st.executeQuery(req2);
                while (re.next()) {
                    String t[] = new String[]{re.getString("numc"), re.getString("nomc"), re.getString("prenomc")};
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
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
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
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_prenomm1KeyTyped

    private void adrm1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adrm1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm1FocusGained

    private void adrm1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_adrm1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm1CaretPositionChanged

    private void adrm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adrm1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm1ActionPerformed

    private void adrm1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adrm1KeyPressed
        // TODO add your handling code here:
        try {
            String numc, nomc, prenomc;

            numc = vid.getText().toString();
            nomc = nomm1.getText();
            prenomc = prenomm1.getText();

            String req = "insert into client values(" + numc + ",'" + nomc + "','" + prenomc + "') ";

            int test = st.executeUpdate(req);

            if (test == 1) {
                JOptionPane.showMessageDialog(null, "ajouter avec succes");

                vid.setText("");
                nomm1.setText("");
                prenomm1.setText("");
                genderm1.setSelectedItem(1);
                adrm1.setText("");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "deja existe");
        }

        table3.setRowCount(0);
        String req2 = "select * from client";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numc"), re.getString("nomc"), re.getString("prenomc")};
                table3.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_adrm1KeyPressed

    private void adrm1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adrm1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE
                || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_adrm1KeyTyped

    private void ButtonSrchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSrchaActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "select * from client where numc=" + vid.getText() + "";
            re = st.executeQuery(req);

            table3.setRowCount(0);

            while (re.next()) {
                String t[] = new String[]{re.getString("numc"), re.getString("nomc"), re.getString("prenomc")};
                table3.addRow(t);
                nomm1.setText(re.getString("nomc"));
                prenomm1.setText(re.getString("prenomc"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas");
        }
    }//GEN-LAST:event_ButtonSrchaActionPerformed

    private void ButtonDltaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDltaaActionPerformed
        try {
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "delete from client where numc=" + vid.getText();
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "supprimer avec succes");
                vid.setText("");
                nomm1.setText("");
                prenomm1.setText("");
                numm1.setText("");
                genderm1.setSelectedItem(1);
                adrm1.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "il n'existe pas");
        }

        table3.setRowCount(0);
        String req2 = "select * from client";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numc"), re.getString("nomc"), re.getString("prenomc")};
                table3.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonDltaaActionPerformed

    private void ButtonUpdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdaActionPerformed
        if ((vid.getText()) != null && !(vid.getText()).isEmpty()) {
            try {
                String req = "select * from client where numc=" + vid.getText() + " ";

                re = st.executeQuery(req);

                while (re.next()) {
                    vid1.setText(re.getString("numc"));
                    nomm3.setText(re.getString("nomc"));
                    prenomm3.setText(re.getString("prenomc"));
                    jDialog3.setVisible(true);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex + "il n'existe pas");
            }
        }
    }//GEN-LAST:event_ButtonUpdaActionPerformed

    private void ButtonADDaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDaActionPerformed
        try {
            String numc, nomc, prenomc;

            numc = vid.getText().toString();
            nomc = nomm1.getText();
            prenomc = prenomm1.getText();

            String req = "insert into client values(" + numc + ",'" + nomc + "','" + prenomc + "') ";

            int test = st.executeUpdate(req);

            if (test == 1) {
                JOptionPane.showMessageDialog(null, "ajouter avec succes");

                vid.setText("");
                nomm1.setText("");
                prenomm1.setText("");
                genderm1.setSelectedItem(1);
                adrm1.setText("");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "deja existe");
        }

        table3.setRowCount(0);
        String req2 = "select * from client";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numc"), re.getString("nomc"), re.getString("prenomc")};
                table3.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonADDaActionPerformed

    private void pss1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pss1FocusGained

    }//GEN-LAST:event_pss1FocusGained

    private void pss1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_pss1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_pss1InputMethodTextChanged

    private void pss1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pss1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pss1ActionPerformed

    private void pss1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pss1KeyPressed

    private void pss1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss1KeyReleased

    }//GEN-LAST:event_pss1KeyReleased

    private void pss1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss1KeyTyped

    }//GEN-LAST:event_pss1KeyTyped

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
            String req = "select * from hotel where numhotel=" + ptid.getText() + "";

            re = st.executeQuery(req);

            d.setRowCount(0);

            while (re.next()) {

                Object t[] = new Object[]{re.getString("numhotel"), re.getString("nom"), re.getString("ville"), re.getString("etoiles")};

                d.addRow(t);
                ptid.setText(re.getString("numhotel"));
                nom1.setText(re.getString("nom"));
                prenom1.setText(re.getString("ville"));
                String gdr = re.getString("etoiles");
                gender1.setSelectedItem(gdr);

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

    private void ButtonSrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSrchActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            if (ptid != null) {
                String req = "select * from hotel where numhotel=" + ptid.getText() + "";

                re = st.executeQuery(req);

                d.setRowCount(0);

                while (re.next()) {

                    //lbl_image.setIcon(ResizeImage(lbl_image,null, re.getBytes("tt")));
                    Object t[] = new Object[]{re.getString("numhotel"), re.getString("nom"), re.getString("ville"), re.getString("etoiles")};

                    d.addRow(t);
                    ptid.setText(re.getString("numhotel"));
                    nom1.setText(re.getString("nom"));
                    prenom1.setText(re.getString("ville"));
                    //num1.setText(re.getString("etoiles"));
                    String gdr = re.getString("etoiles");
                    gender1.setSelectedItem(gdr);

                }
            } else {
                ptid.setText("");
                nom1.setText("");
                prenom1.setText("");
                gender1.setSelectedItem(1);
                adr1.setText("");
                lbl_image.setIcon(ResizeImage(lbl_image, "src/image/SLR Back Side_104px.png", null));

                String req2 = "select * from hotel";
                d.setRowCount(0);
                try {
                    re = st.executeQuery(req2);
                    while (re.next()) {
                        String t[] = new String[]{re.getString("numhotel"), re.getString("nom"), re.getString("ville"),
                            re.getString("etoiles")};
                        d.addRow(t);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error :" + e.toString());
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas");

        }
    }//GEN-LAST:event_ButtonSrchActionPerformed

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

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void ButtonADDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ButtonADDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonADDKeyPressed

    private void ButtonADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDActionPerformed

        String numhotel, nomc, ville, etoiles;

        numhotel = ptid.getText().toString();
        if (!ptid.getText().toString().equals("")) {
            nomc = nom1.getText();
            ville = prenom1.getText();
            //etoiles = adr1.getText();
            etoiles = gender1.getSelectedItem().toString();

            try {
                c = getConnection();
                ps = c.prepareStatement("INSERT INTO hotel(numhotel, nom, ville, etoiles)values(?,?,?,?) ");
                ps.setString(1, numhotel);
                ps.setString(2, nomc);
                ps.setString(3, ville);
                ps.setString(4, etoiles);
                /*InputStream img = new FileInputStream(new File(ImgPath));
                    ps.setBlob(7, img);*/
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "ajouter avec succes");
                ptid.setText("");
                nom1.setText("");
                prenom1.setText("");
                gender1.setSelectedIndex(1);
                adr1.setText("");
                lbl_image.setIcon(ResizeImage(lbl_image, "src/image/SLR Back Side_104px.png", null));

            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage());
                JOptionPane.showMessageDialog(null, ex + " kkk");
            }
        }

        d.setRowCount(0);
        String req2 = "select * from hotel";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numhotel"), re.getString("nom"), re.getString("ville"), re.getString("etoiles")};
                d.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonADDActionPerformed

    private void ButtonUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdActionPerformed
        if ((ptid.getText()) != null && !(ptid.getText()).isEmpty()) {
            try {
                String req = "select * from hotel where numhotel=" + ptid.getText() + "";

                re = st.executeQuery(req);

                while (re.next()) {
                    ptid1.setText(re.getString("numhotel"));
                    nom2.setText(re.getString("nom"));
                    prenom2.setText(re.getString("ville"));
                    String gdr = re.getString("etoiles");
                    gender2.setSelectedItem(gdr);

                    ptid.setText(re.getString("numhotel"));
                    nom1.setText(re.getString("nom"));
                    prenom1.setText(re.getString("ville"));
                    String gdr1 = re.getString("etoiles");
                    gender1.setSelectedItem(gdr1);

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
            String req = "delete from hotel where numhotel=" + ptid.getText() + "";
            int test = st.executeUpdate(req);
            if (test == 1) {

                JOptionPane.showMessageDialog(null, "Supprimer avec succes");
                ptid.setText("");
                nom1.setText("");
                prenom1.setText("");
                gender1.setSelectedItem(1);
                adr1.setText("");
                lbl_image.setIcon(ResizeImage(lbl_image, "src/image/SLR Back Side_104px.png", null));
            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "il n'existe pas");
        }

        d.setRowCount(0);
        String req2 = "select * from hotel";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numhotel"), re.getString("nom"), re.getString("ville"), re.getString("etoiles")};
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

    private void prenom1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenom1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_prenom1KeyTyped

    private void prenom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenom1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenom1ActionPerformed

    private void prenom1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_prenom1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_prenom1CaretPositionChanged

    private void prenom1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenom1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_prenom1FocusGained

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

    private void ptid4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptid4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4FocusGained

    private void ptid4CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ptid4CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4CaretPositionChanged

    private void ptid4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptid4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4ActionPerformed

    private void ptid4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4KeyPressed

    private void ptid4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4KeyReleased

    private void ptid4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptid4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ptid4KeyTyped

    private void vid2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2FocusGained

    private void vid2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2CaretPositionChanged

    private void vid2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2ActionPerformed

    private void vid2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2KeyPressed

    private void vid2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_vid2KeyTyped

    private void nomm2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomm2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm2FocusGained

    private void nomm2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nomm2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm2CaretPositionChanged

    private void nomm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm2ActionPerformed

    private void nomm2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomm2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm2KeyTyped

    private void prenomm2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenomm2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm2FocusGained

    private void prenomm2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_prenomm2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm2CaretPositionChanged

    private void prenomm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm2ActionPerformed

    private void prenomm2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenomm2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm2KeyTyped

    private void adrm2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adrm2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2FocusGained

    private void adrm2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_adrm2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2CaretPositionChanged

    private void adrm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adrm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2ActionPerformed

    private void adrm2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adrm2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2KeyPressed

    private void adrm2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adrm2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm2KeyTyped

    private void ButtonSrcha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSrcha1ActionPerformed
        // TODO add your handling code here:
        try {
            if (vid2 != null && vid3 != null) {
                String req = "select * from chambre where numchambre=" + vid3.getText() + " and numhotel=" + vid2.getText() + "";

                re = st.executeQuery(req);

                table4.setRowCount(0);
                try {
                    re = st.executeQuery(req);
                    while (re.next()) {
                        String t[] = new String[]{re.getString("numchambre"), re.getString("numhotel"), re.getString("etage"), re.getString("typechambre"), re.getString("prixnuit")};
                        table4.addRow(t);

                        vid2.setText(re.getString("numhotel"));
                        vid3.setText(re.getString("numchambre"));
                        nomm2.setText(re.getString("etage"));
                        prenomm2.setText(re.getString("prixnuit"));
                        String gdr = re.getString("typechambre");
                        genderm2.setSelectedItem(gdr);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error :" + e.toString());
                }
            } else {
                JOptionPane.showMessageDialog(null, "error : Numero d'hotel ou/et Numero de la chambre est vide !");
                String req = "select * from chambre";

                re = st.executeQuery(req);

                table4.setRowCount(0);
                try {
                    re = st.executeQuery(req);
                    while (re.next()) {
                        String t[] = new String[]{re.getString("numchambre"), re.getString("numhotel"), re.getString("etage"), re.getString("typechambre"), re.getString("prixnuit")};
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

    private void ButtonDlta2aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDlta2aActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "delete from chambre where numchambre=" + vid3.getText() + " and numhotel=" + vid2.getText() + "";
            int test = st.executeUpdate(req);
            if (test == 1) {
                JOptionPane.showMessageDialog(null, "supprimer avec succes");

                vid2.setText("");
                vid3.setText("");
                nomm2.setText("");
                prenomm2.setText("");
                gender2.setSelectedItem(1);
            } else {
                JOptionPane.showMessageDialog(null, "il n'existe pas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "il n'existe pas " + e);
        }

        String r = "select * from chambre";

        table4.setRowCount(0);
        try {
            re = st.executeQuery(r);
            while (re.next()) {
                String t[] = new String[]{re.getString("numchambre"), re.getString("numhotel"), re.getString("etage"), re.getString("typechambre"), re.getString("prixnuit")};
                table4.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonDlta2aActionPerformed

    private void ButtonUpda3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda3ActionPerformed
        // TODO add your handling code here:
        if (!(vid2.getText().equals("") && vid3.getText().equals(""))) {
            try {
                String req = "select * from chambre where numchambre=" + vid3.getText() + " and numhotel=" + vid2.getText() + "";

                re = st.executeQuery(req);

                while (re.next()) {
                    vid4.setText(re.getString("numhotel"));
                    vid5.setText(re.getString("numchambre"));
                    prenomm4.setText(re.getString("prixnuit"));
                    nomm4.setText(re.getString("etage"));
                    String gdr = re.getString("typechambre");
                    genderm4.setSelectedItem(gdr);

                    jDialog5.setVisible(true);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "il n'existe pas " + ex);
            }
        }
    }//GEN-LAST:event_ButtonUpda3ActionPerformed

    private void ButtonADDa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonADDa2ActionPerformed
        // TODO add your handling code here:
        try {
            String numchambre, numhotel, etage, typechambre, prixnuit;

            numchambre = vid3.getText().toString();
            numhotel = vid2.getText().toString();
            etage = nomm2.getText();
            prixnuit = prenomm2.getText().toString();
            typechambre = genderm2.getSelectedItem().toString();

            String req = "insert into chambre values(" + numchambre + "," + numhotel + "," + etage + ",'" + typechambre + "'," + prixnuit + ") ";

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
        String r = "select * from chambre";
        table4.setRowCount(0);
        try {
            re = st.executeQuery(r);
            while (re.next()) {
                String t[] = new String[]{re.getString("numchambre"), re.getString("numhotel"), re.getString("etage"), re.getString("typechambre"), re.getString("prixnuit")};
                table4.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
    }//GEN-LAST:event_ButtonADDa2ActionPerformed

    private void pss2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pss2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2FocusGained

    private void pss2InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_pss2InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2InputMethodTextChanged

    private void pss2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pss2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2ActionPerformed

    private void pss2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2KeyPressed

    private void pss2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2KeyReleased

    private void pss2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_pss2KeyTyped

    private void vid3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3FocusGained

    private void vid3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3CaretPositionChanged

    private void vid3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3ActionPerformed

    private void vid3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3KeyPressed

    private void vid3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vid3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_vid3KeyTyped

    private void genderm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderm2ActionPerformed

    private void ptid4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ptid4MouseReleased
        // TODO add your handling code here:
        String search = ptid3.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table3);
        tab3.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_ptid4MouseReleased

    private void vid4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid4FocusGained

    private void vid4CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid4CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid4CaretPositionChanged

    private void vid4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid4ActionPerformed

    private void nomm4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomm4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm4FocusGained

    private void nomm4CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nomm4CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm4CaretPositionChanged

    private void nomm4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomm4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm4ActionPerformed

    private void nomm4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomm4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nomm4KeyTyped

    private void prenomm4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenomm4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm4FocusGained

    private void prenomm4CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_prenomm4CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm4CaretPositionChanged

    private void prenomm4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomm4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm4ActionPerformed

    private void prenomm4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenomm4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomm4KeyTyped

    private void adrm4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adrm4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm4FocusGained

    private void adrm4CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_adrm4CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm4CaretPositionChanged

    private void adrm4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adrm4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adrm4ActionPerformed

    private void ButtonUpda4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpda4ActionPerformed
        // TODO add your handling code here:
        try {
            String numhotel, numchambre, etage, prixnuit, typechambre;
            numhotel = vid1.getText();
            numchambre = nomm3.getText();
            etage = prenomm3.getText();
            prixnuit = pss4.getText();
            typechambre = genderm3.getSelectedItem().toString();
            
            String req = "update client set etage=" + etage + ",typechambre='" + typechambre + "'prixnuit=" + prixnuit + " where numchambre=" + numchambre + " and where numhotel=" + numhotel + " ";
            
            //String req = "update chambre set etage=" + etage + " ,typechambre='" + typechambre + "',prixnuit=" + prixnuit + " where numchambre=" + numchambre + " and numhotel=" + numhotel + "  ";

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
        String req2 = "select * from chambre";
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("numchambre"), re.getString("numhotel"), re.getString("etage"),
                    re.getString("typechambre"), re.getString("prixnuit")};
                table3.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
        }
        jDialog5.setVisible(false);
    }//GEN-LAST:event_ButtonUpda4ActionPerformed

    private void vid5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vid5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_vid5FocusGained

    private void vid5CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vid5CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vid5CaretPositionChanged

    private void vid5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vid5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vid5ActionPerformed

    private void genderm4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderm4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderm4ActionPerformed

    private void dta2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dta2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dta2KeyTyped

    private void zz2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz2ActionPerformed

    private void zz2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz2KeyPressed

    private void zz2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz2KeyTyped

    private void zz3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz3ActionPerformed

    private void zz3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz3KeyPressed

    private void zz3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz3KeyTyped

    private void zz4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz4ActionPerformed

    private void zz4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz4KeyPressed

    private void zz4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz4KeyTyped

    private void dta3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dta3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dta3KeyTyped

    private void dta4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dta4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dta4KeyTyped

    private void zz5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz5ActionPerformed

    private void zz5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz5KeyPressed

    private void zz5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz5KeyTyped

    private void zz6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zz6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz6ActionPerformed

    private void zz6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_zz6KeyPressed

    private void zz6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zz6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zz6KeyTyped

    private void psd1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psd1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_psd1KeyTyped

    private void psd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_psd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_psd1ActionPerformed

    private void psd1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_psd1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_psd1CaretPositionChanged

    private void psd1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_psd1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_psd1FocusGained

    private void pss4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss4KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_pss4KeyTyped

    private void pss4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pss4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pss4ActionPerformed

    private void pss4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pss4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_pss4FocusGained

    private void pss3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pss3KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_pss3KeyTyped

    private void pss3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pss3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pss3ActionPerformed

    private void pss3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pss3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_pss3FocusGained

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
    private javax.swing.JButton ButtonADD;
    private javax.swing.JButton ButtonADDa;
    private javax.swing.JButton ButtonADDa1;
    private javax.swing.JButton ButtonADDa2;
    private javax.swing.JButton ButtonDlt;
    private javax.swing.JButton ButtonDlta;
    private javax.swing.JButton ButtonDlta1;
    private javax.swing.JButton ButtonDlta2;
    private javax.swing.JButton ButtonSrch;
    private javax.swing.JButton ButtonSrcha;
    private javax.swing.JButton ButtonSrcha1;
    private javax.swing.JButton ButtonUpd;
    private javax.swing.JButton ButtonUpd1;
    private javax.swing.JButton ButtonUpda;
    private javax.swing.JButton ButtonUpda1;
    private javax.swing.JButton ButtonUpda2;
    private javax.swing.JButton ButtonUpda3;
    private javax.swing.JButton ButtonUpda4;
    private javax.swing.JButton ButtonUpdaa1;
    private javax.swing.JTextField adr1;
    private javax.swing.JTextField adr2;
    private javax.swing.JTextField adrm1;
    private javax.swing.JTextField adrm2;
    private javax.swing.JTextField adrm3;
    private javax.swing.JTextField adrm4;
    private javax.swing.JScrollPane chambre;
    private javax.swing.JScrollPane client;
    private javax.swing.JLabel date;
    private com.toedter.calendar.JDateChooser dta;
    private com.toedter.calendar.JDateChooser dta2;
    private com.toedter.calendar.JDateChooser dta3;
    private com.toedter.calendar.JDateChooser dta4;
    private javax.swing.JLabel errpss;
    private javax.swing.JLabel errpss1;
    private javax.swing.JLabel ex;
    private javax.swing.JLabel ex1;
    private javax.swing.JComboBox<String> gender1;
    private javax.swing.JComboBox<String> gender2;
    private javax.swing.JComboBox<String> genderm1;
    private javax.swing.JComboBox<String> genderm2;
    private javax.swing.JComboBox<String> genderm3;
    private javax.swing.JComboBox<String> genderm4;
    private javax.swing.JScrollPane hotel;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton khayer;
    private javax.swing.JButton khayer1;
    private javax.swing.JLabel lbl_4;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lbl_image1;
    private javax.swing.JTextField nom1;
    private javax.swing.JTextField nom2;
    private javax.swing.JTextField nomm1;
    private javax.swing.JTextField nomm2;
    private javax.swing.JTextField nomm3;
    private javax.swing.JTextField nomm4;
    private javax.swing.JTextField numm1;
    private javax.swing.JTextField numm3;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p6;
    private javax.swing.JPanel p7;
    private javax.swing.JTextField prenom1;
    private javax.swing.JTextField prenom2;
    private javax.swing.JTextField prenomm1;
    private javax.swing.JTextField prenomm2;
    private javax.swing.JTextField prenomm3;
    private javax.swing.JTextField prenomm4;
    private javax.swing.JTextField psd1;
    private javax.swing.JLabel pseudou1;
    private javax.swing.JPasswordField pss1;
    private javax.swing.JPasswordField pss2;
    private javax.swing.JPasswordField pss3;
    private javax.swing.JPasswordField pss4;
    public static javax.swing.JTextField ptid;
    private javax.swing.JTextField ptid1;
    public static javax.swing.JTextField ptid2;
    public static javax.swing.JTextField ptid3;
    public static javax.swing.JTextField ptid4;
    private javax.swing.JScrollPane reservation;
    private javax.swing.JSeparator s10;
    private javax.swing.JSeparator s11;
    private javax.swing.JSeparator s12;
    private javax.swing.JSeparator s13;
    private javax.swing.JSeparator s14;
    private javax.swing.JSeparator s15;
    private javax.swing.JSeparator s17;
    private javax.swing.JSeparator s2;
    private javax.swing.JSeparator s20;
    private javax.swing.JSeparator s21;
    private javax.swing.JSeparator s22;
    private javax.swing.JSeparator s25;
    private javax.swing.JSeparator s26;
    private javax.swing.JSeparator s27;
    private javax.swing.JSeparator s28;
    private javax.swing.JSeparator s29;
    private javax.swing.JSeparator s3;
    private javax.swing.JSeparator s30;
    private javax.swing.JSeparator s31;
    private javax.swing.JSeparator s32;
    private javax.swing.JSeparator s33;
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
    private javax.swing.JSeparator s5;
    private javax.swing.JSeparator s6;
    private javax.swing.JSeparator s7;
    private javax.swing.JSeparator s8;
    private javax.swing.JSeparator s9;
    private javax.swing.JTable tab;
    private javax.swing.JTable tab3;
    private javax.swing.JTable tab4;
    private javax.swing.JLabel time;
    private javax.swing.JTabbedPane tp1;
    private javax.swing.JTabbedPane tp3;
    private javax.swing.JTabbedPane tp4;
    private javax.swing.JLabel user2;
    private javax.swing.JLabel user3;
    private javax.swing.JLabel user5;
    private javax.swing.JLabel user6;
    private javax.swing.JLabel user7;
    private javax.swing.JLabel user8;
    public static javax.swing.JTextField vid;
    public static javax.swing.JTextField vid1;
    public static javax.swing.JTextField vid2;
    public static javax.swing.JTextField vid3;
    public static javax.swing.JTextField vid4;
    public static javax.swing.JTextField vid5;
    public static javax.swing.JTextField zz;
    public static javax.swing.JTextField zz2;
    public static javax.swing.JTextField zz3;
    public static javax.swing.JTextField zz4;
    public static javax.swing.JTextField zz5;
    public static javax.swing.JTextField zz6;
    // End of variables declaration//GEN-END:variables
}
