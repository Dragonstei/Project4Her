import javax.swing.*;
import java.awt.*;


public class BedragNietMatchendNotificatie {
    private JFrame geenMatch;

    public BedragNietMatchendNotificatie(){
        geenMatch = new JFrame("Error!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        geenMatch.setBounds((screenSize.width-600)/2, (screenSize.height-600)/2, 600, 600);
        geenMatch.setSize(500, 300);
        geenMatch.setResizable(false);
        geenMatch.setUndecorated(true);


        geenMatch.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("Bedrag is niet gelijk aan wat u heeft gekozen!!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(50, 58, 500, 28);
        geenMatch.getContentPane().add(lblNewLabel);

        geenMatch.setVisible(true);
    }

    public JFrame getGeenMatch() {
        return geenMatch;
    }
}
