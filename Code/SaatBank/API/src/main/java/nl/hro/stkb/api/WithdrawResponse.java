package nl.hro.stkb.api;

import org.codehaus.jackson.annotate.JsonProperty;

public class WithdrawResponse {
    @JsonProperty
    private boolean response;
    @JsonProperty
    private long newSaldo;
    @JsonProperty
    private long bedragGepint;
    @JsonProperty
    private String iban;
    @JsonProperty
    private int transactieNr;

    public void setResponse(boolean response) {
        this.response = response;
    }

    public boolean isResponse() {
        return response;
    }

    public long getNewSaldo() {
        return newSaldo;
    }

    public void setNewSaldo(long newSaldo) {
        this.newSaldo = newSaldo;
    }

    public void setTransactieNr(int transactieNr) {
        this.transactieNr = transactieNr;
    }

    public int getTransactieNr() {
        return transactieNr;
    }

    public void setBedragGepint(long bedragGepint) {
        this.bedragGepint = bedragGepint;
    }

    public long getBedragGepint() {
        return bedragGepint;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
