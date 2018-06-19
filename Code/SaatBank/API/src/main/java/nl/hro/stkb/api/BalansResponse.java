package nl.hro.stkb.api;

import org.codehaus.jackson.annotate.JsonProperty;


public class BalansResponse {
    @JsonProperty
    private long balans;
    @JsonProperty
    private String iban;


    public void setBalans(long balans) {
        this.balans = balans;
    }

    public long getBalans() {
        return balans;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
