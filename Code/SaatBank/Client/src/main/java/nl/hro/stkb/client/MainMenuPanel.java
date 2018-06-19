package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;


public class MainMenuPanel extends JPanel {
    private JButton breekaf;
    private JButton snelpin10;
    private JButton snelpin20;
    private JButton snelpin50;
    private JButton saldoOpvragen;
    private JButton bedragInvoeren;
    
    private JLabel welkom;
    private JLabel maakKeuze;
    private JLabel background;
    
    public MainMenuPanel(){
        this.setLayout(null);

        welkom = new JLabel();
        welkom.setHorizontalAlignment(SwingConstants.CENTER);
        welkom.setForeground(Color.white);
        welkom.setFont(new Font("Tahoma", Font.BOLD, 60));
        welkom.setText("Welkom");
        welkom.setBounds(12, 100, 1300, 100);
        this.add(welkom);

        maakKeuze = new JLabel();
        maakKeuze.setHorizontalAlignment(SwingConstants.CENTER);
        maakKeuze.setForeground(Color.white);
        maakKeuze.setFont(new Font("Tahoma", Font.BOLD, 51));
        maakKeuze.setText("Maak uw keuze");
        maakKeuze.setBounds(12, 200, 1300, 100);
        this.add(maakKeuze);


        saldoOpvragen = new JButton("Saldo[A]");
        saldoOpvragen.setBounds(975, 350, 326, 74);
        saldoOpvragen.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(saldoOpvragen);

        bedragInvoeren = new JButton("Eigen bedrag[B]");
        bedragInvoeren.setBounds(975, 446, 326, 74);
        bedragInvoeren.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(bedragInvoeren);

        breekaf = new JButton("Afbreken[C]");
        breekaf.setBounds(975, 540, 326, 74);
        breekaf.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(breekaf);

        snelpin10 = new JButton("10 euros[1]");
        snelpin10.setBounds(25, 350, 326,74);
        snelpin10.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(snelpin10);

        snelpin20 = new JButton("20 euro[4]");
        snelpin20.setBounds(25, 446, 326,74);
        snelpin20.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(snelpin20);

        snelpin50 = new JButton("50 euro[7]");
        snelpin50.setBounds(25, 540, 326, 74);
        snelpin50.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(snelpin50);

        background = new JLabel();
        background.setBounds(0, 0, 1920, 1080);
        this.add(background);
        ImageIcon imgThisImg = new ImageIcon("Client/background.jpg");

        background.setIcon(imgThisImg);

        this.setVisible(true);
    }

    public JButton getSaldoButton() {
        return saldoOpvragen;
    }
    public JButton getBedragButton() { return bedragInvoeren; }
    public JButton getBreekafButton() {
        return breekaf;
    }
    public JButton get10Button() {
        return snelpin10;
    }
    public JButton get20Button() {
        return snelpin20;
    }
    public JButton get50Button() {
        return snelpin50;
    }



}
