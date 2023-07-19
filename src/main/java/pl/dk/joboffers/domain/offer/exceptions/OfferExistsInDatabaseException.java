package pl.dk.joboffers.domain.offer.exceptions;

public class OfferExistsInDatabaseException extends RuntimeException {

    private static final String MESSAGE = "Current offer exists in database";
    public OfferExistsInDatabaseException() {
        super(MESSAGE);
    }
}
