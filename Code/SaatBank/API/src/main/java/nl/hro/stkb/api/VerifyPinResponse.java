package nl.hro.stkb.api;

import org.codehaus.jackson.annotate.JsonProperty;

public class VerifyPinResponse {
    @JsonProperty
    private boolean pin;

    @JsonProperty
    private int failedAttemps;

    @JsonProperty
    private int geblokeerdpas;

    public boolean isPin() {
        return pin;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }

    public int getFailedAttemps() {
        return failedAttemps;
    }

    public void setFailedAttemps(int failedAttemps) {
        this.failedAttemps = failedAttemps;
    }

    public void setGeblokeerdpas(int geblokeerdpas) {
        this.geblokeerdpas = geblokeerdpas;
    }

    public int getGeblokeerdpas() {
        return geblokeerdpas;
    }

}
