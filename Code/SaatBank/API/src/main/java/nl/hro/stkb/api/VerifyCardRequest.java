package nl.hro.stkb.api;

import org.codehaus.jackson.annotate.JsonProperty;

public class VerifyCardRequest {
    @JsonProperty
    private String uid;

    @JsonProperty
    private String iban;

    public String getIban() {
        return iban;
    }

    public String getUid() {
        return uid;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
