

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;


public class NewClass extends JPanel implements Printable, ActionListener{

  /** **/

JPanel frameToPrint;

  public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

    if (page > 0) {
      return NO_SUCH_PAGE;
    }

    Graphics2D g2d = (Graphics2D) g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());

    frameToPrint.printAll(g);

    return PAGE_EXISTS;
   }

  public void actionPerformed(ActionEvent e) {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(this);
    boolean ok = job.printDialog();
    if (ok) {
      try {
        job.print();
      } catch (PrinterException ex) {
     }
    }
  }

  public NewClass(JPanel f) {
    frameToPrint = f;
  }

 }
