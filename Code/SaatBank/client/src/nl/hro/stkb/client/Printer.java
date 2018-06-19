package nl.hro.stkb.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Printer {
    private PageFormat mPageFormat;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    public String company = "Saatbank";
    public String line = "________________";
    public String datum = dateFormat.format(date);
    public String naam = "S. Boer";
    public String[] reknr = {"Rek. Nummer:", "..."};
    public String bedrag = "Opgenomen: ...";
    public String bedankt = "Bedankt en tot ziens!";
    public String transacnmr = "Transactienummer: ...";

    static boolean example = true;

    public static void main(String[] args) {
        Printer ps = new Printer();
        if (example) {
            ps.preview();
        }
    }

    public void preview() {
        JFrame frame = new JFrame();
        frame.add(new OnScreen());
        frame.setTitle("Preview van label 99014");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Printer() {
        if (!example) {
            PrinterJob pj = PrinterJob.getPrinterJob();
            mPageFormat = new PageFormat();
            Paper paper = new Paper();
            mPageFormat.setOrientation(mPageFormat.LANDSCAPE);
            paper.setImageableArea(0, 0, 160, 290);
            paper.setSize(160, 290);
            mPageFormat.setPaper(paper);

            pj.setPrintable(new OutPrintable(), mPageFormat);
            if (pj.printDialog())
            {
                try {
                    pj.print();
                } catch (PrinterException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void makeGraphics(Graphics2D g3) {
        g3.setColor(Color.white);
        g3.fillRect(0, 0, 160, 280);
        g3.setColor(Color.black);
        g3.setFont(new Font("Monospaced", Font.BOLD, 14));
        g3.drawString(company, 20, 30);
        g3.drawString(line, 10, 50);
        g3.setFont(new Font("Monospaced", Font.BOLD, 12));
        g3.drawString(transacnmr, 10, 75);
        g3.drawString(datum, 10, 90);
        g3.setFont(new Font("Monospaced", Font.BOLD, 14));
        g3.drawString(line, 10, 100);
        g3.setFont(new Font("Monospaced", Font.BOLD, 12));
        g3.drawString(naam, 10, 120);
        g3.drawString(reknr[0], 10, 135);
        g3.drawString(reknr[1], 10, 150);
        g3.drawString(bedrag, 10, 180);
        g3.drawString(bedankt, 10, 240);
    }

    class OutPrintable implements Printable {

        public int print(Graphics g, PageFormat pf, int pageIndex) {
            if (pageIndex != 0) {
                return NO_SUCH_PAGE;
            }
            Graphics2D g2 = (Graphics2D) g;
            makeGraphics(g2);
            return PAGE_EXISTS;
        }
    }

    class OnScreen extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            makeGraphics(g2);
        }
    }
}