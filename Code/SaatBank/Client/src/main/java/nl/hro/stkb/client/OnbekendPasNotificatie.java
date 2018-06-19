import javax.swing.*;
import java.awt.*;

public class OnbekendPasNotificatie {
    JFrame onbekendpas;
    public OnbekendPasNotificatie(){
        onbekendpas = new JFrame("Pas Onbekend!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        onbekendpas.setBounds((screenSize.width-600)/2, (screenSize.height-600)/2, 600, 600);
        onbekendpas.setSize(500, 300);
        onbekendpas.setResizable(false);
        onbekendpas.setUndecorated(true);


        onbekendpas.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("Uw pas is mogelijk beschadigd of onbekend!!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(50, 58, 500, 28);
        onbekendpas.getContentPane().add(lblNewLabel);

        onbekendpas.setVisible(true);
    }

    public JFrame getOnbekendpas() {
        return onbekendpas;
    }
}
