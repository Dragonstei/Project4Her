package nl.hro.stkb.server;

import nl.hro.stkb.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/")
public class BankEndpoint
{
    private int nr = 23483;
    public static final Logger logger = LoggerFactory.getLogger(BankEndpoint.class);

    @GET
    @Path("/balance/{rekeningNr}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public BalansResponse getSaldo(@PathParam("rekeningNr") String rekeningNr){
        Database db = Server.getDatabase();
        BalansResponse response = new BalansResponse();
        logger.trace("in de bankendpoint::getSaldo()");
        response.setBalans(db.getBalance(rekeningNr));
        response.setIban(rekeningNr);

        return response;
    }


    @POST
    @Path("/withdraw")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public WithdrawResponse withdraw(WithdrawRequest request){
        WithdrawResponse response = new WithdrawResponse();
        Database db = Server.getDatabase();
        boolean b = db.withdraw(request.getIban(), request.getAmount());
       if (b == false){
           response.setResponse(false);
           response.setNewSaldo(db.getBalance(request.getIban()));
           response.setTransactieNr(0);
           logger.info("pinnen is mislukt "+ response.isResponse());


           return response;
       }
       else {
           response.setResponse(true);
           response.setNewSaldo(db.getBalance(request.getIban()));
           response.setBedragGepint(request.getAmount());
           response.setIban(request.getIban());
           response.setTransactieNr(nr);
           nr++;
           return response;


       }
    }

    @POST
    @Path("/verifycard")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public VerifyCardResponse verifycard(VerifyCardRequest request){
        VerifyCardResponse response = new VerifyCardResponse();

        Database db = Server.getDatabase();

        if (db.verifyCard(request.getUid(), request.getIban()) == true){
            response.setCardExists(true);
            return response;
        }
        else {
            response.setCardExists(false);
            return response;
        }
    }

    @POST
    @Path("/verifypin")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public VerifyPinResponse verifypin(VerifyPinRequest request){
        VerifyPinResponse response = new VerifyPinResponse();
        Database db = Server.getDatabase();

       if(db.verifyPin(request.getUid(), request.getPin()) == true){
           response.setPin(true);
           return response;
       }
       else {
           response.setFailedAttemps(db.getAttemps(request.getUid()));
           response.setGeblokeerdpas(db.getblokkade(request.getUid()));
           return response;
       }


    }


}
