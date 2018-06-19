package nl.hro.stkb.api;

import org.codehaus.jackson.annotate.JsonProperty;

public class WithdrawRequest {
    @JsonProperty
    private String iban;
    @JsonProperty
    private long amount;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }
}
