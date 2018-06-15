package nl.hro.stkb.api;

import org.codehaus.jackson.annotate.JsonProperty;


public class VerifyCardResponse {
    @JsonProperty
    private boolean cardExists;

    public boolean isCardExists() {
        return cardExists;
    }

    public void setCardExists(boolean cardExists) {
        this.cardExists = cardExists;
    }
}
