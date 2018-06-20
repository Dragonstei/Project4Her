package nl.hro.stkb.client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import nl.hro.stkb.api.*;



public class SerialPortListener implements SerialPortEventListener{

    SerialPort serialPort;

    public MainFrame mainFrame;
    public ScanPasPanel scanPasPanel;
    public PinInvoerPanel pinInvoerPanel;
    public MainMenuPanel mainMenuPanel;
    public BiljettenKiezen biljettenKiezenPanel;
    public SaldoInfo saldoInfoPanel;
    public LoadingPanel loadingPanel;
    public SerialPortListener serialPortListener;
    public VraagOmBon vraagOmBon;
    public EigenBedragInvoer eigenBedragInvoer;
    public String inputline= "";

    OnbekendPasNotificatie onbekendPasNotificatie;

    public String uid;
    public String iban;
    private long bedragGepint;
    public int tnummer = 3214;
    public int biljet50 = 10;
    public int biljet20 = 10;
    public int biljet10 = 10;
    public int biljet100 = 10;

    public int gekozen10 =0;
    public int gekozen20 = 0;
    public int gekozen50 = 0;
    public int gekozen100 = 0;


    public SerialPortListener() {
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
        this.initialize();

        mainFrame = new MainFrame();
       scanPasPanel = new ScanPasPanel();
        mainFrame.add(scanPasPanel);


    }

    /**
     * The port we're normally going to use.
     */

    private static final String PORT_NAMES[] = {

            "COM7", "COM6", "COM5", "COM4", "COM3", "COM2", "COM1", "/dev/ttyACM0", "/dev/ttyACM1", "/dev/ttyACM2","/dev/ttyACM3","/dev/ttyACM4", "/dev/ttyUSB0"// Windows

    };




    private BufferedReader input;

    /**
     * The output stream to the port
     */

    private OutputStream output;

    /**
     * Milliseconds to block while waiting for port open
     */

    private static final int TIME_OUT = 2000;

    /**
     * Default bits per second for COM port.
     */

    private static final int DATA_RATE = 9600;

    public void initialize() {

        CommPortIdentifier portId = null;

        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.

        while (portEnum.hasMoreElements()) {

            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

            for (String portName : PORT_NAMES) {

                if (currPortId.getName().equals(portName)) {

                    portId = currPortId;

                    break;

                }

            }

        }

        if (portId == null) {

            System.out.println("Could not find COM port.");

            return;

        }

        try {

            // open serial port, and use class name for the appName.

            serialPort = (SerialPort) portId.open(this.getClass().getName(),

                    TIME_OUT);

            // set port parameters

            serialPort.setSerialPortParams(DATA_RATE,

                    SerialPort.DATABITS_8,

                    SerialPort.STOPBITS_1,

                    SerialPort.PARITY_NONE);

            // open the streams

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

            output = serialPort.getOutputStream();

            // add event listeners

            serialPort.addEventListener(this);

            serialPort.notifyOnDataAvailable(true);

        } catch (Exception e) {

            System.err.println(e.toString());

        }

    }

    public void serialEvent(SerialPortEvent spe) {
        try {
            inputline = input.readLine();

        } catch (Exception e) {

        }
        if (spe.getEventType() == SerialPortEvent.DATA_AVAILABLE) {

            if (scanPasPanel != null && inputline.length() >= 15) {

                try {


                    uid = inputline.substring(0, 8);
                    iban = BankClient.hexToAscii(inputline.substring(8, 30));
                    System.out.println(uid);
                    System.out.println(iban);


                    pas();
                }
                catch (Exception e){
                    iban = "";
                    uid= "";
                }



            }
            if (pinInvoerPanel != null) {
                long timestamp = System.currentTimeMillis();


                    if (inputline.equals("1") || inputline.equals("2") || inputline.equals("3") || inputline.equals("4") || inputline.equals("5") ||
                            inputline.equals("6") || inputline.equals("7") || inputline.equals("8") || inputline.equals("9") || inputline.equals("0")) {
                        pinInvoerPanel.getPasswordField().setText(pinInvoerPanel.getPasswordField().getText() + inputline);
                    }
                    if (pinInvoerPanel.getPasswordField().getText().length() > 4) {
                        pinInvoerPanel.getPasswordField().setText("");
                    }
                    if (inputline.equals("*")) {
                        pinInvoerPanel.getPasswordField().setText("");
                    }
                    if (inputline.equals("B")) {
                        scanPasPanel = new ScanPasPanel();
                        mainFrame.remove(pinInvoerPanel);
                        mainFrame.add(scanPasPanel);
                        scanPasPanel.updateUI();
                        pinInvoerPanel = null;
                        inputline = "";
                    }
                    if (inputline.equals("A")) {

                        System.out.println(pinInvoerPanel.getPasswordField().
                                getText().toLowerCase());
                        VerifyPinResponse verifyPinResponse = BankClient.verifypin(uid.toString(), pinInvoerPanel.getPasswordField().getText());

                        if (verifyPinResponse.getGeblokeerdpas() == 1) {
                            blockPas();
                            pinInvoerPanel.getPasswordField().setText("");
                        }
                        if (verifyPinResponse.isPin()) {
                            mainMenuPanel = new MainMenuPanel();
                            mainFrame.remove(pinInvoerPanel);
                            mainFrame.add(mainMenuPanel);
                            mainMenuPanel.updateUI();
                            pinInvoerPanel = null;
                        }
                        if (verifyPinResponse.isPin() == false && verifyPinResponse.getGeblokeerdpas() != 1) {
                            inputline = "";
                            pinInvoerPanel.getPasswordField().setText("");
                            IncorrectPin(verifyPinResponse);

                        }
                        inputline = "";
                    }




            }
            if(mainMenuPanel != null) {

                if (inputline.equals("1")) {
                        bedragGepint = 10;

                        biljettenKiezenPanel = new BiljettenKiezen(bedragGepint);
                        mainFrame.remove(mainMenuPanel);
                        mainFrame.add(biljettenKiezenPanel);
                        biljettenKiezenPanel.updateUI();
                        mainMenuPanel = null;
                        inputline = "";
                }

                if (inputline.equals("4")) {
                    bedragGepint = 20;

                    biljettenKiezenPanel = new BiljettenKiezen(bedragGepint);
                    mainFrame.remove(mainMenuPanel);
                    mainFrame.add(biljettenKiezenPanel);
                    biljettenKiezenPanel.updateUI();
                    mainMenuPanel = null;
                    inputline = "";
                }

                if (inputline.equals("7")) {
                   bedragGepint = 50;

                    biljettenKiezenPanel = new BiljettenKiezen(bedragGepint);
                    mainFrame.remove(mainMenuPanel);
                    mainFrame.add(biljettenKiezenPanel);
                    biljettenKiezenPanel.updateUI();
                    mainMenuPanel = null;
                    inputline = "";
                }


                if (inputline.equals("A") ){
                    saldoInfoPanel = new SaldoInfo(BankClient.getBalance(iban).getBalans());
                    mainFrame.remove(mainMenuPanel);
                    mainFrame.add(saldoInfoPanel);
                    saldoInfoPanel.updateUI();
                    mainMenuPanel = null;
                    inputline = "";

                }
                if (inputline.equals("B")) {
                    eigenBedragInvoer = new EigenBedragInvoer();
                    mainFrame.remove(mainMenuPanel);
                    mainFrame.add(eigenBedragInvoer);
                    eigenBedragInvoer.updateUI();
                    mainMenuPanel = null;
                    inputline = "";
                }

                if (inputline.equals("C")) {
                    scanPasPanel = new ScanPasPanel();
                    mainFrame.remove(mainMenuPanel);
                    mainFrame.add(scanPasPanel);
                    scanPasPanel.updateUI();
                    mainMenuPanel = null;
                    inputline = "";
                }

            }
            if (saldoInfoPanel != null) {

                if (inputline.equals("A") ){
                    eigenBedragInvoer = new EigenBedragInvoer();
                    mainFrame.remove(saldoInfoPanel);
                    mainFrame.add(eigenBedragInvoer);
                    eigenBedragInvoer.updateUI();
                    saldoInfoPanel = null;
                    inputline = "";
                }

                if (inputline.equals("B")) {
                    mainMenuPanel = new MainMenuPanel();
                    mainFrame.remove(saldoInfoPanel);
                    mainFrame.add(mainMenuPanel);
                    mainMenuPanel.updateUI();
                    saldoInfoPanel = null;
                    inputline = "";
                }
                if (inputline.equals("C")) {
                    scanPasPanel = new ScanPasPanel();
                    mainFrame.remove(saldoInfoPanel);
                    mainFrame.add(scanPasPanel);
                    scanPasPanel.updateUI();
                    saldoInfoPanel = null;
                    inputline = "";

                }
            }
            if (eigenBedragInvoer != null){

                if (inputline.equals("1") || inputline.equals("2") || inputline.equals("3") || inputline.equals("4") || inputline.equals("5") ||
                        inputline.equals("6") || inputline.equals("7") || inputline.equals("8") || inputline.equals("9") || inputline.equals("0")) {
                    eigenBedragInvoer.getEigenbedrag().setText(eigenBedragInvoer.getEigenbedrag().getText() + inputline);
                }
                if (eigenBedragInvoer.getEigenbedrag().getText().length() > 4) {
                    eigenBedragInvoer.getEigenbedrag().setText("");
                }
                if (inputline.equals("*")) {
                   eigenBedragInvoer.getEigenbedrag().setText("");
                }


                if (inputline.equals("A")){
                    saldoInfoPanel = new SaldoInfo(BankClient.getBalance(iban).getBalans());
                    mainFrame.remove(eigenBedragInvoer);
                    mainFrame.add(saldoInfoPanel);
                    saldoInfoPanel.updateUI();
                    eigenBedragInvoer = null;
                    inputline = "";
                }
                if (inputline.equals("B")){
                    mainMenuPanel = new MainMenuPanel();
                    mainFrame.remove(eigenBedragInvoer);
                    mainFrame.add(mainMenuPanel);
                    mainMenuPanel.updateUI();
                    eigenBedragInvoer = null;
                    inputline = "";

                }
                if (inputline.equals("C")){
                    scanPasPanel = new ScanPasPanel();
                    mainFrame.remove(eigenBedragInvoer);
                    mainFrame.add(scanPasPanel);
                    scanPasPanel.updateUI();
                    eigenBedragInvoer = null;
                    inputline = "";
                }
                if (inputline.equals("D")){
                    System.out.println(eigenBedragInvoer.getEigenbedrag().getText());
                    if ((Integer.parseInt(eigenBedragInvoer.getEigenbedrag().getText().toString()) % 10) == 0){

                        bedragGepint = Long.valueOf(Integer.parseInt(eigenBedragInvoer.getEigenbedrag().getText().toString()));
                        System.out.println(bedragGepint);
                        biljettenKiezenPanel = new BiljettenKiezen(bedragGepint);
                        mainFrame.remove(eigenBedragInvoer);
                        mainFrame.add(biljettenKiezenPanel);
                        biljettenKiezenPanel.updateUI();
                        eigenBedragInvoer = null;
                        inputline = "";
                    }
                    else {
                        tientallen();
                        eigenBedragInvoer.getEigenbedrag().setText("");
                    }
                }
            }
            if (biljettenKiezenPanel != null){
                if (inputline.equals("1")){

                    if (biljet10 == 0){
                        afwezigBiljet();
                    }
                    else {
                        biljettenKiezenPanel.addBill(10);
                        biljettenKiezenPanel.updateUI();
                        gekozen10+=1;
                        biljet10-=1;
                    }
                }
                if (inputline.equals("4")){

                    if (biljet20 == 0) {
                       afwezigBiljet();
                    }
                    else {
                        biljettenKiezenPanel.addBill(20);
                        biljettenKiezenPanel.updateUI();
                        biljet20-=1;
                        gekozen20+=1;
                    }
                }
                if (inputline.equals("7")){

                    if (biljet50 == 0) {
                      afwezigBiljet();
                    }
                    else {
                        biljettenKiezenPanel.addBill(50);
                        biljettenKiezenPanel.updateUI();
                        biljet50-=1;
                        gekozen50+=1;
                    }
                }
                if (inputline.equals("*")){

                    if (biljet100 == 0) {
                        afwezigBiljet();
                    }
                    else {
                        biljettenKiezenPanel.addBill(100);
                        biljettenKiezenPanel.updateUI();
                        biljet100-=1;
                        gekozen100+=1;
                    }
                }
                if (inputline.equals("A")){
                    biljettenKiezenPanel.eraseBills();
                    biljettenKiezenPanel.updateUI();

                    biljet50+=gekozen50;
                    gekozen50 = 0;

                    biljet10+=gekozen10;
                    gekozen10=0;

                    biljet20+=gekozen20;
                    gekozen20=0;

                    biljet100+=gekozen100;
                    gekozen100=0;

                }
                if (inputline.equals("B")){
                    biljet50+=gekozen50;
                    gekozen50 = 0;

                    biljet10+=gekozen10;
                    gekozen10=0;

                    biljet20+=gekozen20;
                    gekozen20=0;

                    biljet100+=gekozen100;
                    gekozen100=0;

                    mainMenuPanel = new MainMenuPanel();
                    mainFrame.remove(biljettenKiezenPanel);
                    mainFrame.add(mainMenuPanel);
                    mainMenuPanel.updateUI();
                    biljettenKiezenPanel = null;
                    inputline = "";

                }
                if (inputline.equals("C")){
                    biljet50+=gekozen50;
                    gekozen50 = 0;

                    biljet10+=gekozen10;
                    gekozen10=0;

                    biljet20+=gekozen20;
                    gekozen20=0;

                    biljet100+=gekozen100;
                    gekozen100=0;

                    scanPasPanel = new ScanPasPanel();
                    mainFrame.remove(biljettenKiezenPanel);
                    mainFrame.add(scanPasPanel);
                    scanPasPanel.updateUI();
                    biljettenKiezenPanel = null;
                    inputline = "";
                }
                if (inputline.equals("D")){
                    if (bedragGepint != biljettenKiezenPanel.getSom()){
                        geenMatch();
                    }
                    else {
                        WithdrawResponse response =BankClient.withdraw(iban, bedragGepint);
                        bedragGepint = response.getBedragGepint();
                        iban = response.getIban();
                        tnummer++;
                        if (response.isResponse() == false){
                            geenSaldo();
                        }
                        else {
                            vraagOmBon = new VraagOmBon();
                            mainFrame.remove(biljettenKiezenPanel);
                            mainFrame.add(vraagOmBon);
                            vraagOmBon.updateUI();
                            biljettenKiezenPanel = null;

                        }
                        inputline = "";
                    }
                }

            }
            if (vraagOmBon != null){
                if (inputline.equals("A")) {
                    Printer printer = new Printer(iban, Long.toString(bedragGepint), tnummer);
                    loadingPanel = new LoadingPanel();
                    mainFrame.remove(vraagOmBon);
                    mainFrame.add(loadingPanel);
                    loadingPanel.updateUI();
                    vraagOmBon = null;
                }

                if (inputline.equals("1")) {
                    loadingPanel = new LoadingPanel();
                    mainFrame.remove(vraagOmBon);
                    mainFrame.add(loadingPanel);
                    loadingPanel.updateUI();
                    vraagOmBon = null;
                }
                }

            }
            if(loadingPanel != null){


                System.out.println("Aantal brieven 10: "+gekozen10);
                System.out.println("Aantal brieven 20: "+gekozen20);
                System.out.println("Aantal brieven 50: "+gekozen50);
                System.out.println("Aantal brieven 100: "+gekozen100);
              //  dispencer.getBills(gekozen10, gekozen20, gekozen50);
                try {
                    OutputStream outputStream = serialPort.getOutputStream();
                    outputStream.write(("{"+Integer.toString(gekozen10)+""+Integer.toString(gekozen20)+""+Integer.toString(gekozen50)+""+gekozen100+"}").getBytes());
                    System.out.println("{"+(Integer.toString(gekozen10)+""+Integer.toString(gekozen20)+""+Integer.toString(gekozen50)+""+gekozen100+"}"));
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                gekozen20 = 0;
                gekozen10 = 0;
                gekozen50 = 0;
                gekozen100 = 0;
                long timestamp = System.currentTimeMillis();

                while (timestamp + 10000 >= System.currentTimeMillis()){

                }

                loadingPanel.getWachten().setText("Bedankt en tot ziens!!");
                loadingPanel.updateUI();

                timestamp = System.currentTimeMillis();

                while (timestamp + 5000 >= System.currentTimeMillis()){

                }

                scanPasPanel = new ScanPasPanel();
                mainFrame.remove(loadingPanel);
                mainFrame.add(scanPasPanel);
                scanPasPanel.updateUI();
                loadingPanel = null;
            }





        }



public void pas(){
    if (BankClient.verifycard(uid, iban)) {
        pinInvoerPanel = new PinInvoerPanel();
        mainFrame.remove(scanPasPanel);
        mainFrame.add(pinInvoerPanel);
        pinInvoerPanel.updateUI();
        scanPasPanel = null;


    }
    else {

        long timestamp = System.currentTimeMillis();
        onbekendPasNotificatie = new OnbekendPasNotificatie();
        while (timestamp + 2000 >= System.currentTimeMillis()){

        }
        onbekendPasNotificatie.getOnbekendpas().setVisible(false);
        onbekendPasNotificatie = null;


    }
}

public void IncorrectPin(VerifyPinResponse verifyPinResponse){

    long timestamp = System.currentTimeMillis();
    IncorrectPincodeNotificatie incorrectPincodeNotificatie =
            new IncorrectPincodeNotificatie(Integer.toString(3 - verifyPinResponse.getFailedAttemps()));
    while (timestamp + 2000 >= System.currentTimeMillis()){

    }
    incorrectPincodeNotificatie.getIncrrectPIn().setVisible(false);
    incorrectPincodeNotificatie = null;

}

public void blockPas(){
    long timestamp = System.currentTimeMillis();
    BlockPasNotificatie blockPasNotificatie = new BlockPasNotificatie();
    while (timestamp + 2000 >= System.currentTimeMillis()){

    }
    blockPasNotificatie.getBlockpas().setVisible(false);
    blockPasNotificatie = null;
}

public void geenSaldo(){
    long timestamp = System.currentTimeMillis();
    OntoereikendSaldoNotificatie ontoereikendSaldoNotificatie = new OntoereikendSaldoNotificatie();
    while (timestamp + 2000 >= System.currentTimeMillis()){

    }
    ontoereikendSaldoNotificatie.getGeenSaldo().setVisible(false);
    ontoereikendSaldoNotificatie = null;

}

public void tientallen(){
    long timestamp = System.currentTimeMillis();
    TientallenNotificatie tientallenNotificatie = new TientallenNotificatie();
    while (timestamp + 2000 >= System.currentTimeMillis()){

    }
   tientallenNotificatie.getTientallen().setVisible(false);
    tientallenNotificatie = null;
}

public void afwezigBiljet(){
    long timestamp = System.currentTimeMillis();
    BiljetNietAanwezigNotificiatie biljetNietAanwezigNotificiatie = new BiljetNietAanwezigNotificiatie();
    while (timestamp + 2000 >= System.currentTimeMillis()){

    }
    biljetNietAanwezigNotificiatie.getBiljetNietAanwezig().setVisible(false);
    biljetNietAanwezigNotificiatie = null;
}

public void geenMatch(){

    long timestamp = System.currentTimeMillis();
    BedragNietMatchendNotificatie bedragNietMatchendNotificatie = new BedragNietMatchendNotificatie();
    while (timestamp + 2000 >= System.currentTimeMillis()){

    }
    bedragNietMatchendNotificatie.getGeenMatch().setVisible(false);
    bedragNietMatchendNotificatie = null;

}


    public String getInputline() {
        return inputline;
    }

    public BufferedReader getInput() {
        return input;
    }

    public String getIban() {
        return iban;
    }
}

