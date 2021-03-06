package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Raber on 6/6/2017.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class BiljettenKiezen extends JPanel {
    private JLabel kiesbrieven;
    private JLabel background;
    private JButton biljet10;
    private JButton biljet20;
    private JButton biljet50;
    private JButton biljet100;
    private JButton verwijderBedrag;
    private JButton breekaf;
    private JButton mainMenu;
    private JButton ok;
    private JLabel totaal;
    private JLabel amount;
    private long som = 0;
    private int ten;
    private int twenty;
    private int fifty;
    private int onehundred;



    public BiljettenKiezen(long am){
        setLayout(null);

        kiesbrieven = new JLabel();
        kiesbrieven.setHorizontalAlignment(SwingConstants.CENTER);
        kiesbrieven.setForeground(Color.white);
        kiesbrieven.setFont(new Font("Tahoma", Font.BOLD, 51));
        kiesbrieven.setText("Kies welke biljetten u wilt");
        kiesbrieven.setBounds(12, 100, 1300, 100);
        this.add(kiesbrieven);

        totaal = new JLabel();
        totaal.setHorizontalAlignment(SwingConstants.LEFT);
        totaal.setForeground(Color.white);
        totaal.setFont(new Font("Tahoma", Font.BOLD, 35));
        totaal.setBounds(25, 200, 1000, 100);
        this.add(totaal);

        amount = new JLabel();
        amount.setHorizontalAlignment(SwingConstants.CENTER);
        amount.setForeground(Color.WHITE);
        amount.setFont(new Font("Tahoma", Font.BOLD, 35));
        amount.setBounds(650, 200, 700, 100);
        amount.setText("bedrag opnemen: "+am);
        this.add(amount);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totaal.setText("Gekozen biljetten totaal: "+som+" Euro");
            }
        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();

        biljet10 = new JButton("10 euro biljet[1]");
        biljet10.setBounds(25, 350, 326,74);
        biljet10.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(biljet10);

        biljet20 = new JButton("20 euro biljet[4]");
        biljet20.setBounds(25, 446, 326,74);
        biljet20.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(biljet20);

        biljet50 = new JButton("50 euro biljet[7]");
        biljet50.setBounds(25, 540, 326, 74);
        biljet50.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(biljet50);

        biljet50 = new JButton("100 euro biljet[*]");
        biljet50.setBounds(25, 634, 326, 74);
        biljet50.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(biljet50);


        verwijderBedrag = new JButton("Verwijder Bedrag[A]");
        verwijderBedrag.setBounds(975, 350, 326, 74);
        verwijderBedrag.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(verwijderBedrag);

        breekaf = new JButton("Afbreken[C]");
        breekaf.setBounds(975, 540, 326, 74);
        breekaf.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(breekaf);

        mainMenu = new JButton("Terug naar menu[B]");
        mainMenu.setBounds(975, 446, 326, 74);
        mainMenu.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(mainMenu);

        ok = new JButton("Bevestig[D]");
        ok.setBounds(975, 634, 326, 74);
        ok.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(ok);

        background = new JLabel();
        background.setBounds(0, 0, 1920, 1080);
        this.add(background);
        ImageIcon imgThisImg = new ImageIcon("Client/background.jpg");

        background.setIcon(imgThisImg);

        this.setVisible(true);

    }

    public JButton getBreekafButton() { return breekaf; }
    public JButton getMainMenu() { return mainMenu; }
    public JButton get10Button() { return biljet10; }
    public JButton get20Button() { return biljet20; }
    public JButton get50Button() { return biljet50; }
    public int GetTens(){return ten;}
    public int GetTwentys(){return twenty;}
    public int GetFiftys(){return fifty;}

    public void SetTotaaltext() {
        totaal.setText(Long.toString(som)+" Euro");
    }

    public void addBill(int bill){
        som += bill;

        switch(bill){
            case 10: {ten++;break;}
            case 20: {twenty++;break;}
            case 50: {fifty++;break;}
            case 100: {onehundred++;break;}
        }
    }

    public void eraseBills(){
        som = 0;
    }

    public long getSom() {
        return som;
    }
}

