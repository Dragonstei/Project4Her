package nl.hro.stkb.api;

import org.codehaus.jackson.annotate.JsonProperty;


public class VerifyPinRequest {
    @JsonProperty
    private String pin;
    @JsonProperty
    private String uid;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUid() {
        return uid;
    }

    public String getPin() {
        return pin;
    }
}
