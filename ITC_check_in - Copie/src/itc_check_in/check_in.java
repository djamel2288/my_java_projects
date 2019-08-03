/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itc_check_in;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.objects.NativeRegExp.source;

/**
 *
 * @author Djimmy
 */
public class check_in extends javax.swing.JFrame {

    /**
     * Creates new form check_in
     *
     * @throws java.io.IOException
     */
    String url = "jdbc:sqlserver://localhost:1433;databaseName=check;user=enidde;password=2703djm";
    Connection c;
    Statement st;
    PreparedStatement ps;
    ResultSet re;

    JFrame window = new JFrame("Test webcam capture");

    DefaultTableModel d = new DefaultTableModel();

    public static Connection getConnection() throws SQLException {
        Connection con = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=check;user=enidde;password=2703djm";
        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            JOptionPane.showMessageDialog(null, "connecter avec succes");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public BufferedImage getScren(Component cmp) {
        BufferedImage bf = new BufferedImage(cmp.getWidth(), cmp.getHeight(), BufferedImage.TYPE_INT_RGB);
        cmp.paint(bf.getGraphics());
        return bf;
    }

    public void savePic(Component cmp, String Filename) {
        try {
            BufferedImage bf = getScren(cmp);
            ImageIO.write(bf, "png", new File(Filename));
        } catch (IOException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public check_in() throws IOException {

        initComponents();
        super.setLocationRelativeTo(null);
        add.setVisible(false);
        CHECK_IN.setVisible(true);
        accueille.setVisible(false);
        jLabel1.setForeground(Color.white);
        jLabel17.setForeground(Color.white);
        lbl_1.setIcon(ResizeImage(lbl_1, "src/itc-2.png", null));

        d = (DefaultTableModel) jTable1.getModel();

        try {
            c = getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        }

        d.setRowCount(0);
        String req2 = "select * from Persons";
        //JOptionPane.showMessageDialog(null, "succes");
        try {
            re = st.executeQuery(req2);
            while (re.next()) {
                String t[] = new String[]{re.getString("id"), re.getString("nom"), re.getString("prenom"), re.getString("email"),
                    re.getString("wilaya"), re.getString("day_1"), re.getString("day_2"), re.getString("id") + "_" + re.getString("nom") + "_" + re.getString("prenom")};
                d.addRow(t);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error :" + e.toString());
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

        pdff = new javax.swing.JDialog();
        panel = new javax.swing.JPanel();
        lbl_5 = new javax.swing.JLabel();
        lbl_6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_7 = new javax.swing.JLabel();
        t_prenom = new javax.swing.JTextField();
        t_name = new javax.swing.JTextField();
        t_num = new javax.swing.JTextField();
        printt = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        add = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lbl_4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        CHECK_IN = new javax.swing.JPanel();
        p = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        accueille = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Send_Email = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Send_Email1 = new javax.swing.JButton();
        Send_Email2 = new javax.swing.JButton();

        pdff.setSize(new java.awt.Dimension(640, 610));
        pdff.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setBackground(new java.awt.Color(204, 204, 204));

        lbl_5.setBackground(new java.awt.Color(255, 255, 255));

        lbl_6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("City Diar EL Azzhar Frer Djilali,Alger,Algerie");

        jLabel10.setFont(new java.awt.Font("Agency FB", 1, 77)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Formation");

        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Nom :");

        jLabel12.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Prenom :");

        jLabel13.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("ID_C");

        t_prenom.setBackground(new java.awt.Color(204, 204, 204));
        t_prenom.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        t_prenom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_prenom.setBorder(null);

        t_name.setBackground(new java.awt.Color(204, 204, 204));
        t_name.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        t_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_name.setBorder(null);
        t_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_nameActionPerformed(evt);
            }
        });

        t_num.setBackground(new java.awt.Color(204, 204, 204));
        t_num.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        t_num.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_num.setBorder(null);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(lbl_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(t_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(53, 53, 53)
                        .addComponent(t_name, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_num, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(t_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t_name)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(t_num, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_7, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pdff.getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, -1));

        javax.swing.GroupLayout printtLayout = new javax.swing.GroupLayout(printt);
        printt.setLayout(printtLayout);
        printtLayout.setHorizontalGroup(
            printtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        printtLayout.setVerticalGroup(
            printtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        pdff.getContentPane().add(printt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 640, 60));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));

        jLabel1.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        jLabel1.setText("ITC_CHECK_IN");

        jLabel17.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("X");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lbl_1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 676, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(lbl_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, -1));

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Email");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(53, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addGap(9, 9, 9)))
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CHECK_IN");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(53, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addGap(9, 9, 9)))
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 230, -1));

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("ADD");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(53, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addGap(9, 9, 9)))
        );

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 230, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 230, 480));

        add.setBackground(new java.awt.Color(153, 153, 153));

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nome :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Prenom :");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Email :");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("QR_CODE :");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Wilaya :");

        lbl_4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton2.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jButton2.setText("Ajouter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add);
        add.setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addLayout.createSequentialGroup()
                            .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(48, 48, 48))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(46, 46, 46)))
                    .addGroup(addLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)))
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addLayout.createSequentialGroup()
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(addLayout.createSequentialGroup()
                                    .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(130, 130, 130))
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(130, Short.MAX_VALUE))
                    .addGroup(addLayout.createSequentialGroup()
                        .addComponent(lbl_4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addLayout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );

        jPanel1.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 720, 470));

        CHECK_IN.setBackground(new java.awt.Color(153, 153, 153));

        p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pActionPerformed(evt);
            }
        });
        p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Participantt :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nom", "Prenom", "Email", "Wilaya", "1er Jour", "2eme Jour", "Result"
            }
        ));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("WEBCAM");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CHECK_INLayout = new javax.swing.GroupLayout(CHECK_IN);
        CHECK_IN.setLayout(CHECK_INLayout);
        CHECK_INLayout.setHorizontalGroup(
            CHECK_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addGroup(CHECK_INLayout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel15)
                .addGap(37, 37, 37)
                .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CHECK_INLayout.setVerticalGroup(
            CHECK_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CHECK_INLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(CHECK_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CHECK_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(CHECK_IN, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 720, 470));

        accueille.setBackground(new java.awt.Color(153, 153, 153));

        jButton1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jButton1.setText("Ajouter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Send_Email.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        Send_Email.setText("Send_Email");
        Send_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_EmailActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jButton4.setText("1er_J");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Send_Email1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        Send_Email1.setText("1er & 2eme");
        Send_Email1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_Email1ActionPerformed(evt);
            }
        });

        Send_Email2.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        Send_Email2.setText("2eme_J");
        Send_Email2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_Email2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout accueilleLayout = new javax.swing.GroupLayout(accueille);
        accueille.setLayout(accueilleLayout);
        accueilleLayout.setHorizontalGroup(
            accueilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accueilleLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Send_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(accueilleLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(Send_Email2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(Send_Email1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        accueilleLayout.setVerticalGroup(
            accueilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accueilleLayout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(accueilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Send_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129)
                .addGroup(accueilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Send_Email1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Send_Email2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(123, 123, 123))
        );

        jPanel1.add(accueille, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 720, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int x = 0;
        try {
            // TODO add your handling code here:
            //String ids= JOptionPane.showInputDialog(null,"wach tnahi??");
            String req = "select * from Persons";

            re = st.executeQuery(req);

            d.setRowCount(0);

            while (re.next()) {

                try {
                    // TODO add your handling code here:

                    t_prenom.setText(re.getString("prenom"));
                    t_num.setText(re.getString("id"));
                    t_name.setText(re.getString("nom"));

                    pdff.setVisible(true);
                    lbl_6.setIcon(ResizeImage(lbl_6, "src/itc-2.png", null));
                    lbl_5.setIcon(ResizeImage(lbl_5, "src/ict.png", null));

                    if (t_name.getText().length() == 0) {
                        return;
                    } else {

                        String myCodeText;
                        myCodeText = t_num.getText() + "_" + t_name.getText() + "_" + t_prenom.getText();
                        String filePath = "src/QQR.png";
                        int size = 220;
                        String fileType = "png";
                        File myFile = new File(filePath);
                        try {

                            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
                            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                            QRCodeWriter qrCodeWriter = new QRCodeWriter();
                            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                                    size, hintMap);
                            int CrunchifyWidth = byteMatrix.getWidth();
                            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                                    BufferedImage.TYPE_INT_RGB);
                            image.createGraphics();

                            Graphics2D graphics = (Graphics2D) image.getGraphics();
                            graphics.setColor(Color.WHITE);
                            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
                            graphics.setColor(Color.BLACK);

                            for (int i = 0; i < CrunchifyWidth; i++) {
                                for (int j = 0; j < CrunchifyWidth; j++) {
                                    if (byteMatrix.get(i, j)) {
                                        graphics.fillRect(i, j, 1, 1);
                                    }
                                }
                            }
                            Graphics g = lbl_7.getGraphics();
                            g.drawImage(image, WIDTH, WIDTH, lbl_7);
                            ImageIO.write(image, fileType, myFile);
                            lbl_7.setIcon(ResizeImage(lbl_7, "src/QQR.png", null));

                        } catch (WriterException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("\n\nYou have successfully created QR Code.");
                    }

                    savePic(panel, "src/ppdf/" + t_num.getText().toString() + "_" + t_name.getText().toString() + "_" + t_prenom.getText().toString() + ".png");

                    //creation d'un pdf
                    Document doc = new Document();
                    try {
                        PdfWriter.getInstance(doc, new FileOutputStream("src/ppdf/" + t_num.getText().toString() + "_" + t_name.getText().toString() + "_" + t_prenom.getText().toString() + ".pdf"));
                    } catch (DocumentException ex) {
                        Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    doc.open();
                    com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("src/ppdf/" + t_num.getText().toString() + "_" + t_name.getText().toString() + "_" + t_prenom.getText().toString() + ".png");
                    img.scaleAbsolute(525, 475);
                    doc.add(img);
                    doc.close();
                    System.out.println("Done " + x);
                    x++;

                } catch (BadElementException ex) {
                    Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        window.setVisible(false);
        Webcam webcam = Webcam.getDefault(); // non-default (e.g. USB) webcam can be used too
        webcam.close();
        dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void Send_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_EmailActionPerformed
        // TODO add your handling code here:

        int w = 0;
        try {
            // TODO add your handling code here:
            String req = "select * from Persons";

            re = st.executeQuery(req);

            d.setRowCount(0);

            while (re.next()) {
                String t[] = new String[]{re.getString("id"), re.getString("nom"), re.getString("prenom"), re.getString("email"),
                    re.getString("wilaya"), re.getString("day_1"), re.getString("day_2"), re.getString("id") + "_" + re.getString("nom") + "_" + re.getString("prenom")};
                d.addRow(t);
                System.out.println("X");
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("itcscool7@gmail.com", "ITC12345");
                    }
                });
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("itcscool7@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(re.getString("email")));
                    message.setSubject("Email Testing");
                    message.setText("Test Test,"
                            + "\n\n No spam to my email, please!");

                    BodyPart messageBodyPart = new MimeBodyPart();

                    // Now set the actual message
                    messageBodyPart.setText("Som Text..Som Text..Som Text..Som Text");
                    // Create a multipar message
                    Multipart multipart = new MimeMultipart();
                    // Set text message part
                    multipart.addBodyPart(messageBodyPart);

                    // Part two is attachment
                    messageBodyPart = new MimeBodyPart();
                    String filename = "src/ppdf/" + re.getString("id") + "_" + re.getString("nom") + "_" + re.getString("prenom") + ".pdf";
                    DataSource source = new FileDataSource(filename);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(filename);
                    multipart.addBodyPart(messageBodyPart);

                    // Send the complete message parts
                    message.setContent(multipart);

                    //message.setFileName("TUTORIEL-CREATION-ACTIVITE.pdf");
                    System.out.println("Y");
                    System.out.println(w);
                    w++;
                    Transport.send(message);

                    System.out.println("Done");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "n'existe pas");
        }


    }//GEN-LAST:event_Send_EmailActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        if (jTextField2.getText().length() == 0) {
            return;
        } else {

            String myCodeText;
            myCodeText = jTextField1.getText() + "_" + jTextField2.getText();
            String filePath = "C:/Users/Djimmy/Desktop/I.T.C/ITC_check_in/QR_imgCrunchifyQR.png";
            int size = 210;
            String fileType = "png";
            File myFile = new File(filePath);
            try {

                Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
                hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

                // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
                //hintMap.put(EncodeHintType.MARGIN, 1);  default = 4 
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                        size, hintMap);
                int CrunchifyWidth = byteMatrix.getWidth();
                BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                        BufferedImage.TYPE_INT_RGB);
                image.createGraphics();

                Graphics2D graphics = (Graphics2D) image.getGraphics();
                graphics.setColor(Color.WHITE);
                graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
                graphics.setColor(Color.BLACK);

                for (int i = 0; i < CrunchifyWidth; i++) {
                    for (int j = 0; j < CrunchifyWidth; j++) {
                        if (byteMatrix.get(i, j)) {
                            graphics.fillRect(i, j, 1, 1);
                        }
                    }
                }
                Graphics g = lbl_4.getGraphics();
                g.drawImage(image, WIDTH, WIDTH, lbl_4);
                ImageIO.write(image, fileType, myFile);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\n\nYou have successfully created QR Code.");
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        if (jTextField1.getText().length() == 0) {
            return;
        } else {

            String myCodeText;
            myCodeText = jTextField1.getText() + "_" + jTextField2.getText();
            String filePath = "C:/Users/Djimmy/Desktop/I.T.C/ITC_check_in/QR_imgCrunchifyQR.png";
            int size = 210;
            String fileType = "png";
            File myFile = new File(filePath);
            try {

                Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
                hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

                // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
                //hintMap.put(EncodeHintType.MARGIN, 1);  default = 4 
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                        size, hintMap);
                int CrunchifyWidth = byteMatrix.getWidth();
                BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                        BufferedImage.TYPE_INT_RGB);
                image.createGraphics();

                Graphics2D graphics = (Graphics2D) image.getGraphics();
                graphics.setColor(Color.WHITE);
                graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
                graphics.setColor(Color.BLACK);

                for (int i = 0; i < CrunchifyWidth; i++) {
                    for (int j = 0; j < CrunchifyWidth; j++) {
                        if (byteMatrix.get(i, j)) {
                            graphics.fillRect(i, j, 1, 1);
                        }
                    }
                }
                Graphics g = lbl_4.getGraphics();
                g.drawImage(image, WIDTH, WIDTH, lbl_4);
                ImageIO.write(image, fileType, myFile);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\n\nYou have successfully created QR Code.");
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        add.setVisible(false);
        CHECK_IN.setVisible(true);
        accueille.setVisible(false);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void pKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pKeyReleased
        // TODO add your handling code here:

        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            Webcam webcam = Webcam.getDefault(); // non-default (e.g. USB) webcam can be used too
            webcam.open();

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {

                while ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
            }

            if (result != null) {
                try {
                    JOptionPane.showMessageDialog(null, "QR code data is: " + result.getText());
                    System.out.println("QR code data is: " + result.getText());
                    /**
                     * *
                     */
                    p.setText(result.getText());
                    String search = p.getText();
                    TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(d);
                    jTable1.setRowSorter(tr);
                    tr.setRowFilter(RowFilter.regexFilter(search));
                    String s = "", r;
                    r = result.getText();
                    int i = 0;
                    while ((i < r.length())) {
                        if ((r.charAt(i) != '_')) {
                            s = s + String.valueOf(r.charAt(i));
                        } else {
                            i = r.length();
                        }

                        i++;
                    }
                    System.out.println(s);

                    String date_a;
                    Date dd = null;
                    dd = new Date();
                    SimpleDateFormat dttt = new SimpleDateFormat("yyyy-MM-dd");
                    date_a = dttt.format(dd);

                    if (date_a.equals("2019-03-10")) {

                        String req = "update Persons set day_1=1"
                                + "where id='" + s + "' ";
                        int test = st.executeUpdate(req);
                        if (test == 1) {
                            JOptionPane.showMessageDialog(null, "Modifier avec succes");
                        } else {
                            JOptionPane.showMessageDialog(null, "n'est pas modifier");
                        }
                    }

                    if (date_a.equals("2019-03-11")) {

                        String req = "update Persons set day_2=1"
                                + "where id='" + s + "' ";
                        int test = st.executeUpdate(req);
                        if (test == 1) {
                            JOptionPane.showMessageDialog(null, "Modifier avec succes");
                        } else {
                            JOptionPane.showMessageDialog(null, "n'est pas modifier");
                        }
                    }

                    d.setRowCount(0);
                    String req2 = "select * from Persons";
                    try {
                        re = st.executeQuery(req2);
                        while (re.next()) {
                            String t[] = new String[]{re.getString("id"), re.getString("nom"), re.getString("prenom"), re.getString("email"),
                                re.getString("wilaya"), re.getString("day_1"), re.getString("day_2"), re.getString("id") + "_" + re.getString("nom") + "_" + re.getString("prenom")};
                            d.addRow(t);
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "error :" + e.toString());
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else {
            String search = p.getText();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(d);
            jTable1.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(search));
        }


    }//GEN-LAST:event_pKeyReleased

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        add.setVisible(false);
        CHECK_IN.setVisible(false);
        accueille.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void t_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_nameActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_t_nameActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        add.setVisible(true);
        CHECK_IN.setVisible(false);
        accueille.setVisible(false);

    }//GEN-LAST:event_jLabel14MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String nom, prenom, email, wilaya, day_1, day_2;

        nom = jTextField1.getText();
        prenom = jTextField2.getText();
        email = jTextField3.getText();
        wilaya = jTextField5.getText();

        try {
            c = getConnection();
            ps = c.prepareStatement("INSERT INTO Persons(nom, prenom,email, wilaya)values(?,?,?,?) ");
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, wilaya);
            /*InputStream img = new FileInputStream(new File(ImgPath));
                    ps.setBlob(7, img);*/
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "ajouter avec succes");
            d.setRowCount(0);
            String req2 = "select * from Persons";
            //JOptionPane.showMessageDialog(null, "succes");
            try {
                re = st.executeQuery(req2);
                while (re.next()) {
                    String t[] = new String[]{re.getString("id"), re.getString("nom"), re.getString("prenom"), re.getString("email"),
                        re.getString("wilaya"), re.getString("day_1"), re.getString("day_2"), re.getString("id") + "_" + re.getString("nom") + "_" + re.getString("prenom")};
                    d.addRow(t);
                    t_num.setText(re.getString("id"));

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error :" + e.toString());
            }

            int x = 0;

            /**
             * ***************
             */
            t_prenom.setText(prenom);
            //t_num.setText(re.getString("id"));
            t_name.setText(nom);

            pdff.setVisible(true);
            lbl_6.setIcon(ResizeImage(lbl_6, "src/itc-2.png", null));
            lbl_5.setIcon(ResizeImage(lbl_5, "src/ict.png", null));

            if (t_name.getText().length() == 0) {
                return;
            } else {

                String myCodeText;
                myCodeText = t_num.getText() + "_" + t_name.getText() + "_" + t_prenom.getText();
                String filePath = "src/QQR.png";
                int size = 220;
                String fileType = "png";
                File myFile = new File(filePath);

                try {

                    Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
                    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

                    // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
                    //hintMap.put(EncodeHintType.MARGIN, 1);  default = 4;
                    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                    QRCodeWriter qrCodeWriter = new QRCodeWriter();
                    BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                            size, hintMap);
                    int CrunchifyWidth = byteMatrix.getWidth();
                    BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                            BufferedImage.TYPE_INT_RGB);
                    image.createGraphics();

                    Graphics2D graphics = (Graphics2D) image.getGraphics();
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
                    graphics.setColor(Color.BLACK);

                    for (int i = 0; i < CrunchifyWidth; i++) {
                        for (int j = 0; j < CrunchifyWidth; j++) {
                            if (byteMatrix.get(i, j)) {
                                graphics.fillRect(i, j, 1, 1);
                            }
                        }
                    }
                    Graphics g = lbl_7.getGraphics();
                    g.drawImage(image, WIDTH, WIDTH, lbl_7);
                    ImageIO.write(image, fileType, myFile);
                    lbl_7.setIcon(ResizeImage(lbl_7, "src/QQR.png", null));

                } catch (WriterException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("\n\nYou have successfully created QR Code.");
            }

            System.out.println("save_1");
            savePic(panel, "src/ppdf/" + t_num.getText().toString() + "_" + t_name.getText().toString() + "_" + t_prenom.getText().toString() + ".png");
            System.out.println("save_2");

            //creation d'un pdf
            Document doc = new Document();
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("src/ppdf/" + t_num.getText().toString() + "_" + t_name.getText().toString() + "_" + t_prenom.getText().toString() + ".pdf"));
            } catch (DocumentException ex) {
                Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
            }
            doc.open();
            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("src/ppdf/" + t_num.getText().toString() + "_" + t_name.getText().toString() + "_" + t_prenom.getText().toString() + ".png");
            img.scaleAbsolute(525, 475);
            doc.add(img);

            doc.close();
            System.out.println("Done " + x);
            x++;

            int w = 0;

            System.out.println("X");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("djimy8855@gmail.com", "elbahri88");
                }
            });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("djimy8855@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
                message.setSubject("Subject");
                message.setText("SOM TEXT SOM TEXT SOM TEXT....");

                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setText("BODY TEXT BODY TEXT BODY TEXT");
                // Create a multipar message
                Multipart multipart = new MimeMultipart();
                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                String filename = "src/ppdf/" + t_num.getText().toString() + "_" + nom + "_" + prenom + ".pdf";
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);

                message.setContent(multipart);

                System.out.println("Y");
                System.out.println(w);
                w++;
                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField5.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + " no :/");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        JPanel panel = new WebcamPanel(Webcam.getDefault());

        window.setAlwaysOnTop(true);
        window.add(panel);
        window.pack();
        window.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:
            String req = "select nom,prenom from Persons where day_1=1 group by nom , prenom";
            //where day_1=1
            re = st.executeQuery(req);

            d.setRowCount(0);

            Document doc = new Document();
                    try {
                        PdfWriter.getInstance(doc, new FileOutputStream("src/ppdf/liste 1er_j.pdf"));
                    } catch (DocumentException ex) {
                        Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    doc.open();
                    com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("src/itc-3.png");
                    img.scaleAbsolute(525, 75);
                    doc.add(img);
                    
            while (re.next()) {
                
                doc.add(new Paragraph( re.getString("nom").toUpperCase()+"   "+re.getString("prenom").toUpperCase()));
            }
            doc.close();
            /*MessageFormat header = new MessageFormat("heloo");
            //MessageFormat footer = new MessageFormat("Page{0,numbre,integer}");
            try {
                
            jTable1.print(JTable.PrintMode.NORMAL,header,header);
            } catch (Exception e) {
                System.err.println("eeee"+e.getMessage());
            }*/
            
        } catch (SQLException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void Send_Email1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_Email1ActionPerformed
        // TODO add your handling code here:
         try {
            String req = "select nom,prenom from Persons where day_1=1 and day_2=1 group by nom , prenom";
            re = st.executeQuery(req);

            Document doc = new Document();
                    try {
                        PdfWriter.getInstance(doc, new FileOutputStream("src/ppdf/liste.pdf"));
                    } catch (DocumentException ex) {
                        Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    doc.open();
                    com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("src/itc-3.png");
                    img.scaleAbsolute(525, 75);
                    doc.add(img);
                    
            while (re.next()) {
                
                doc.add(new Paragraph( re.getString("nom").toUpperCase()+"   "+re.getString("prenom").toUpperCase()));
            }
            doc.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Send_Email1ActionPerformed

    private void Send_Email2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_Email2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String req = "select nom, prenom from Persons where day_1=0 and day_2=1 group by nom , prenom ";
            //where day_1=1
            re = st.executeQuery(req);

            Document doc = new Document();
                    try {
                        PdfWriter.getInstance(doc, new FileOutputStream("src/ppdf/liste 2eme_j.pdf"));
                    } catch (DocumentException ex) {
                        Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    doc.open();
                    com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("src/itc-3.png");
                    img.scaleAbsolute(525, 75);
                    doc.add(img);
                    
            while (re.next()) {
                
                doc.add(new Paragraph( re.getString("nom").toUpperCase()+"   "+re.getString("prenom").toUpperCase()));
            }
            doc.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(check_in.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Send_Email2ActionPerformed

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
            java.util.logging.Logger.getLogger(check_in.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(check_in.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(check_in.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(check_in.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new check_in().setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(check_in.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CHECK_IN;
    private javax.swing.JButton Send_Email;
    private javax.swing.JButton Send_Email1;
    private javax.swing.JButton Send_Email2;
    private javax.swing.JPanel accueille;
    private javax.swing.JPanel add;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel lbl_1;
    private javax.swing.JLabel lbl_4;
    private javax.swing.JLabel lbl_5;
    private javax.swing.JLabel lbl_6;
    private javax.swing.JLabel lbl_7;
    private javax.swing.JTextField p;
    private javax.swing.JPanel panel;
    private javax.swing.JDialog pdff;
    private javax.swing.JPanel printt;
    private javax.swing.JTextField t_name;
    private javax.swing.JTextField t_num;
    private javax.swing.JTextField t_prenom;
    // End of variables declaration//GEN-END:variables
}
