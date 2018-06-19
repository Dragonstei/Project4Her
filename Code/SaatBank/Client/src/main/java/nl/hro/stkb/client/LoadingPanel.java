package nl.hro.stkb.client;
import javax.swing.*;
import java.awt.*;


public class LoadingPanel extends JPanel {
    private JLabel wachten;
    private JLabel background;
    public LoadingPanel(){

        this.setLayout(null);
        wachten = new JLabel();
        wachten.setHorizontalAlignment(SwingConstants.CENTER);
        wachten.setForeground(Color.white);
        wachten.setOpaque(false);
        wachten.setFont(new Font("Tahoma", Font.BOLD, 45));
        wachten.setText("Een ogenblik geduld alstubieft!");
        wachten.setBounds(12, 100, 1300, 100);
        this.add(wachten);

        background = new JLabel();
        background.setBounds(0, 0, 1920, 1080);
        this.add(background);
        ImageIcon imgThisImg = new ImageIcon("Client/background.jpg");
        background.setIcon(imgThisImg);

        setVisible(true);
        
    }

    public JLabel getWachten() {
        return wachten;
    }

}
