import javax.swing.*;
import java.awt.*;


public class OntoereikendSaldoNotificatie {
    private JFrame geenSaldo;
    
    public OntoereikendSaldoNotificatie(){
        geenSaldo = new JFrame("Saldo!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        geenSaldo.setBounds((screenSize.width-600)/2, (screenSize.height-600)/2, 600, 600);
        geenSaldo.setSize(500, 300);
        geenSaldo.setResizable(false);
        geenSaldo.setUndecorated(true);


        geenSaldo.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("U heeft niet genoeg saldo up uw rekening!!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(50, 58, 500, 28);
        geenSaldo.getContentPane().add(lblNewLabel);

        geenSaldo.setVisible(true);
    }

    public JFrame getGeenSaldo() {
        return geenSaldo;
    }
}
