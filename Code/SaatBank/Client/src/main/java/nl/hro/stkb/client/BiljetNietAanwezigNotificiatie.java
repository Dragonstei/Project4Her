package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;


public class BiljetNietAanwezigNotificiatie {
    private JFrame biljetNietAanwezig;
    
    public BiljetNietAanwezigNotificiatie(){
        biljetNietAanwezig = new JFrame("Afwezig biljet!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        biljetNietAanwezig.setBounds((screenSize.width-600)/2, (screenSize.height-600)/2, 600, 600);
        biljetNietAanwezig.setSize(500, 300);
        biljetNietAanwezig.setResizable(false);
        biljetNietAanwezig.setUndecorated(true);


        biljetNietAanwezig.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("Dit biljet is nietmeer op vooraad!!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(50, 58, 500, 28);
        biljetNietAanwezig.getContentPane().add(lblNewLabel);

        biljetNietAanwezig.setVisible(true);
        
    }

    public JFrame getBiljetNietAanwezig() {
        return biljetNietAanwezig;
    }
}
