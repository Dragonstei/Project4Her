package nl.hro.stkb.api;

import org.codehaus.jackson.annotate.JsonProperty;

public class VerifyPinResponse {
    @JsonProperty
    private boolean pin;

    @JsonProperty
    private int failedAttemps;

    @JsonProperty
    private int geblokkeerdpas;

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

    public void setGeblokkeerdpas(int geblokkeerdpas) {
        this.geblokkeerdpas = geblokkeerdpas;
    }

    public int getGeblokkeerdpas() {
        return geblokkeerdpas;
    }

}
