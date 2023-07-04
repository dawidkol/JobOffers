package pl.dk.joboffers.domain.offer.exceptions;

public class OfferNotFoundException extends RuntimeException {

    private static final String OFFER_NOT_FOUND = "Offer not found";

    public OfferNotFoundException() {
        super(OFFER_NOT_FOUND);
    }
}
