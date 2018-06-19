package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;

public class IncorrectPincodeNotificatie {
    private JFrame incrrectPIn;
    
    public IncorrectPincodeNotificatie(String m){
        incrrectPIn = new JFrame("Incorrecte Pin!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        incrrectPIn.setBounds((screenSize.width-600)/2, (screenSize.height-600)/2, 600, 600);
        incrrectPIn.setSize(500, 300);
        incrrectPIn.setResizable(false);
        incrrectPIn.setUndecorated(true);


        incrrectPIn.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("Incorrecte pincode u heeft nog "+m+" pogingen over!!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(50, 58, 500, 28);
        incrrectPIn.getContentPane().add(lblNewLabel);

        incrrectPIn.setVisible(true);
    }

    public JFrame getIncrrectPIn() {
        return incrrectPIn;
    }
}
