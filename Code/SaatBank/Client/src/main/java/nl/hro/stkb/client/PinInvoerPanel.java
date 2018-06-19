package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;


public class PinInvoerPanel extends JPanel {
    private  JPasswordField passwordField;
    private JLabel pinInvoer;
    private JButton okButton;
    private JButton breekaf;
    private JButton correctie;
    private JLabel background;

    public PinInvoerPanel(){
        setLayout(null);

        pinInvoer = new JLabel();
        pinInvoer.setHorizontalAlignment(SwingConstants.CENTER);
        pinInvoer.setForeground(Color.white);
        pinInvoer.setFont(new Font("Tahoma", Font.BOLD, 51));
        pinInvoer.setText("Voer uw pincode in");
        pinInvoer.setBounds(12, 100, 1300, 232);
        this.add(pinInvoer);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 99));
        passwordField.setBounds(550, 350, 235, 109);
        this.add(passwordField);

        okButton = new JButton("OK[A]");
        okButton.setBounds(975, 350, 326, 74);
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(okButton);

        breekaf = new JButton("Afbreken[B]");
        breekaf.setBounds(975, 446, 326, 74);
        breekaf.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(breekaf);

        correctie = new JButton("Correctie[*]");
        correctie.setBounds(25, 350, 326,74);
        correctie.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(correctie);

        background = new JLabel();
        background.setBounds(0, 0, 1920, 1080);
        this.add(background);
        ImageIcon imgThisImg = new ImageIcon("Client/background.jpg");

        background.setIcon(imgThisImg);

        this.setVisible(true);

    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getBreekaf() {
        return breekaf;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JLabel getPinInvoer() {
        return pinInvoer;
    }



}
