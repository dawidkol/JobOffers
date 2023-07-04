package pl.dk.joboffers.domain.offer.exceptions;

public class NoNewOfferToSaveException extends RuntimeException {

    private static final String NO_NEW_OFFER_TO_SAVE = "No new offer to save";
    public NoNewOfferToSaveException() {
        super(NO_NEW_OFFER_TO_SAVE);
    }
}
