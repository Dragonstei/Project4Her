package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;

public class TientallenNotificatie {
    private JFrame tientallen;
    
    public TientallenNotificatie(){
        tientallen = new JFrame("Saldo!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        tientallen.setBounds((screenSize.width-600)/2, (screenSize.height-600)/2, 600, 600);
        tientallen.setSize(500, 300);
        tientallen.setResizable(false);
        tientallen.setUndecorated(true);


        tientallen.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("U kunt alleen bedragen in tientallen opnemen!!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(50, 58, 500, 28);
        tientallen.getContentPane().add(lblNewLabel);

        tientallen.setVisible(true);
    }

    public JFrame getTientallen() {
        return tientallen;
    }
}
