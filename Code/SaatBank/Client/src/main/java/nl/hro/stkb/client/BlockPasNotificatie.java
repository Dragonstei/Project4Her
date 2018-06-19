package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;


public class BlockPasNotificatie {
    private JFrame blockpas;
    
    public BlockPasNotificatie(){
        blockpas = new JFrame("Uw pas is geblokkeerd!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        blockpas.setBounds((screenSize.width-600)/2, (screenSize.height-600)/2, 600, 600);
        blockpas.setSize(500, 300);
        blockpas.setResizable(false);
        blockpas.setUndecorated(true);


        blockpas.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("Uw pas is geblokkeerd!!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(50, 58, 500, 28);
        blockpas.getContentPane().add(lblNewLabel);

        blockpas.setVisible(true);
    }

    public JFrame getBlockpas() {
        return blockpas;
    }
}
