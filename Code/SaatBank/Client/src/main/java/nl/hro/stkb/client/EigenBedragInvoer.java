package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;

public class EigenBedragInvoer extends JPanel {
    private JLabel invoerBedrag;
    private JTextArea eigenbedrag;
    private JButton bevestig;
    private JLabel background;
    private JButton mainMenu;
    private JButton saldo;
    private JButton breekaf;
    public EigenBedragInvoer(){
        this.setLayout(null);

        invoerBedrag = new JLabel();
        invoerBedrag.setHorizontalAlignment(SwingConstants.CENTER);
        invoerBedrag.setFont(new Font("Tahoma", Font.BOLD, 45));
        invoerBedrag.setText("Voer uw bedrag in. Let op beperkt tot tientallen!");
        invoerBedrag.setForeground(Color.WHITE);
        invoerBedrag.setBounds(12, 100, 1300, 232);
        this.add(invoerBedrag);

        eigenbedrag = new JTextArea();
        eigenbedrag.setFont(new Font("Tahoma", Font.BOLD, 99));
        eigenbedrag.setBounds(450, 350, 350, 109);
        this.add(eigenbedrag);

        bevestig = new JButton("Bevestig[D]");
        bevestig.setBounds(470, 540, 326, 74);
        bevestig.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(bevestig);

        saldo = new JButton("Saldo[A]");
        saldo.setBounds(975, 350, 326, 74);
        saldo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(saldo);

        mainMenu = new JButton("Terug naar menu[B]");
        mainMenu.setBounds(975, 446, 326, 74);
        mainMenu.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(mainMenu);

        breekaf = new JButton("Afbreken[C]");
        breekaf.setBounds(975, 540, 326, 74);
        breekaf.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(breekaf);

        background = new JLabel();
        background.setBounds(0, 0, 1920, 1080);
        this.add(background);
        ImageIcon imgThisImg = new ImageIcon("Client/background.jpg");

        background.setIcon(imgThisImg);

        this.setVisible(true);



    }

    public JTextArea getEigenbedrag() {
        return eigenbedrag;
    }
}
