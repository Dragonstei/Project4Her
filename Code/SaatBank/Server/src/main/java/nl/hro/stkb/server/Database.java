package nl.hro.stkb.server;


public interface Database
{
    public long getBalance(String rekeningNr);
    public boolean withdraw(String rekeningNr,long amount);
    public boolean verifyCard(String uid, String iban );
    public boolean verifyPin(String uid, String pin);
    public int getAttemps(String uid);
    public int getblokkade(String uid);
}
