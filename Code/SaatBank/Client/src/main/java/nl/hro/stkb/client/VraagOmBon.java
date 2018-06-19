package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;

public class VraagOmBon extends JPanel {
    private JButton jaKnop;
    private JButton neeKnop;
    private JLabel background;
    private JLabel vraagBon;
    
    
    
    public VraagOmBon(){
        setLayout(null);

        vraagBon = new JLabel();
        vraagBon.setHorizontalAlignment(SwingConstants.CENTER);
        vraagBon.setForeground(Color.white);
        vraagBon.setFont(new Font("Tahoma", Font.BOLD, 51));
        vraagBon.setText("Wilt u de transactiebon?");
        vraagBon.setBounds(12, 100, 1300, 100);
        this.add(vraagBon);
        
        jaKnop = new JButton("Ja[A]");
        jaKnop.setBounds(975, 350, 326, 74);
        jaKnop.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(jaKnop);

        neeKnop = new JButton("Afbreken[1]");
        neeKnop.setBounds(25, 350, 326, 74);
        neeKnop.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(neeKnop);
        
        background = new JLabel();
        background.setBounds(0, 0, 1920, 1080);
        this.add(background);
        ImageIcon imgThisImg = new ImageIcon("Client/background.jpg");

        background.setIcon(imgThisImg);

        this.setVisible(true);
    }
}
