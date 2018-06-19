import sun.applet.Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PinProces {
    public MainFrame mainFrame;
    public ScanPasPanel scanPasPanel;
    public PinInvoerPanel pinInvoerPanel;
    public MainMenuPanel mainMenuPanel;
    public BiljettenKiezen biljettenKiezenPanel;
    public SaldoInfo saldoInfoPanel;
    public SerialPortListener serialPortListener;
    public VraagOmBon vraagOmBon;
    public String inputline = "";

    private String uid;
    private String iban;


    public PinProces() {

        serialPortListener.initialize();

        mainFrame = new MainFrame();
        scanPasPanel = new ScanPasPanel();
        mainFrame.add(scanPasPanel);


    }

    public void run() {
        while (true) {
            try {
                inputline = serialPortListener.getInput().readLine();
                System.out.println(inputline);
            } catch (Exception e) {

            }


        }
    }
}