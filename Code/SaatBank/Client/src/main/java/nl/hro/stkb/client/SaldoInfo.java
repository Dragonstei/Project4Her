import nl.hro.grnd.TheGrandExchange.api.BalansResponse;

import javax.swing.*;
import java.awt.*;


public class SaldoInfo extends JPanel {
    private JButton breekaf;
    private JButton eigenBedrag;
    private JButton mainMenu;

    private JLabel saldo;
    private JLabel uwsaldois;
    private JLabel background;



    public SaldoInfo(long saldoGebruiker){

        this.setLayout(null);




        uwsaldois = new JLabel();
        uwsaldois.setHorizontalAlignment(SwingConstants.CENTER);
        uwsaldois.setForeground(Color.white);
        uwsaldois.setFont(new Font("Tahoma", Font.BOLD, 60));
        uwsaldois.setText("Uw saldo ");
        uwsaldois.setBounds(12, 150, 1890, 100);
        this.add(uwsaldois);

        saldo = new JLabel();
        saldo.setHorizontalAlignment(SwingConstants.CENTER);
        saldo.setForeground(Color.white);
        saldo.setFont(new Font("Tahoma", Font.BOLD, 51));
        saldo.setText(saldoGebruiker+" Euro");
        saldo.setBounds(12, 300, 1890, 100);
        this.add(saldo);



        eigenBedrag = new JButton("Eigen bedrag [A]");
        eigenBedrag.setBounds(1300, 463, 326, 74);
        eigenBedrag.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(eigenBedrag);

        mainMenu = new JButton("Terug naar menu[B]");
        mainMenu.setBounds(1300, 557, 326, 74);
        mainMenu.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(mainMenu);

        breekaf = new JButton("Afbreken[C]");
        breekaf.setBounds(1300, 651, 326, 74);
        breekaf.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.add(breekaf);

        background = new JLabel();
        background.setBounds(0, 0, 1920, 1080);
        this.add(background);
        ImageIcon imgThisImg = new ImageIcon("C:/Users/Raber/Documents/Project-4/background/background.jpg");

        background.setIcon(imgThisImg);

        saldo = new JLabel();
        saldo.setHorizontalAlignment(SwingConstants.CENTER);
        saldo.setForeground(Color.white);
        saldo.setFont(new Font("Tahoma", Font.BOLD, 51));
        saldo.setText(saldoGebruiker+" Euro");
        saldo.setBounds(12, 300, 1890, 100);
        this.add(saldo);


        this.updateUI();

    }

    public JLabel getSaldo() {
        return saldo;
    }
    public JButton getMainMenuButton() {
        return mainMenu;
    }
    public JButton getBedragButton() { return eigenBedrag; }
    public JButton getBreekafButton() {
        return breekaf;
    }



    public void popup(String m){
        JOptionPane.showMessageDialog(this,
                "U heeft niet genoeg saldo!",
                "",
                JOptionPane.PLAIN_MESSAGE);
    }
}
