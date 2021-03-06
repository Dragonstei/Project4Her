package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;


public class ScanPasPanel extends JPanel{

    private JLabel beginscherm;
    private JLabel background;
    private JLabel welkom;

    private JButton okButton;

    public ScanPasPanel(){
        setLayout(null);

        beginscherm = new JLabel();
        beginscherm.setHorizontalAlignment(SwingConstants.CENTER);
        beginscherm.setForeground(Color.white);
        beginscherm.setFont(new Font("Tahoma", Font.BOLD, 51));
        beginscherm.setText("Houd uw pinpas voor de scanner");
        beginscherm.setBounds(12, 400, 1300, 100);
        this.add(beginscherm);

        welkom = new JLabel();
        welkom.setHorizontalAlignment(SwingConstants.CENTER);
        welkom.setForeground(Color.white);
        welkom.setFont(new Font("Tahoma", Font.BOLD, 60));
        welkom.setText("Welkom bij Saatbank");
        welkom.setBounds(12, 150, 1300, 100);
        this.add(welkom);

        background = new JLabel();
        background.setBounds(0, 0, 1366, 768);
        this.add(background);
        ImageIcon imgThisImg = new ImageIcon("Client/background.jpg");
        background.setIcon(imgThisImg);

        setVisible(true);
    }
    public void onbekendPasPopup(){
        JOptionPane.showMessageDialog(this,
                "Uw pas is mogelijk beschadigd of onbekend",
                "Pas Onbekend",
                JOptionPane.PLAIN_MESSAGE);

    }

}
