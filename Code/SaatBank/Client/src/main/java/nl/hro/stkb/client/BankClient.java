package nl.hro.stkb.client;

import nl.hro.stkb.api.*;
import org.glassfish.jersey.jackson.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;



public class BankClient {
    private  SerialPortListener serialPortListener;



    private static Client client = ClientBuilder.newClient().register(JacksonFeature.class);
    private static WebTarget target = client.target("http://145.24.222.79:8025");

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    SerialPortListener serialPortListener = new SerialPortListener();


                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });
    }



    public static BalansResponse getBalance(String IBAN) {
        BalansResponse response = target
                .path("/balance/" + IBAN)
                .request(MediaType.APPLICATION_JSON)
                .get(BalansResponse.class);

        return response;
    }

    public static WithdrawResponse withdraw(String iban, long amount) {
        WithdrawRequest request = new WithdrawRequest();
        request.setAmount(amount);
        request.setIban(iban);

        WithdrawResponse response = target
                .path("/withdraw")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON), WithdrawResponse.class);

        return response;
    }

    public static boolean verifycard(String uid, String iban){
        VerifyCardRequest request = new VerifyCardRequest();
        request.setIban(iban);
        request.setUid(uid);

        VerifyCardResponse response = target
                .path("/verifycard").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON), VerifyCardResponse.class);

        return response.isCardExists();
    }

    public static VerifyPinResponse verifypin(String uid, String pin){
        VerifyPinRequest request = new VerifyPinRequest();
        request.setPin(pin);
        request.setUid(uid);

        VerifyPinResponse response = target
                .path("/verifypin").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON), VerifyPinResponse.class);

        return response;
    }

    public static String hexToAscii(String h){
        String hex = h;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i+=2) {
            String str = hex.substring(i, i+2);
            output.append((char)Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    public  SerialPortListener getSerialPortListener() {
        return serialPortListener;
    }


    public static void setTarget(WebTarget target) {
        BankClient.target = target;
    }

    public static Client getClient() {
        return client;
    }
}

