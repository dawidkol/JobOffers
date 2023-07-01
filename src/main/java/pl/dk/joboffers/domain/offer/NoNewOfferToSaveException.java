package pl.dk.joboffers.domain.offer;

class NoNewOfferToSaveException extends RuntimeException {
    public NoNewOfferToSaveException(String message) {
        super(message);
    }
}
