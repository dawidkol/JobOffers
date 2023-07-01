package pl.dk.joboffers.domain.loginandregister;

class RegistrationNotPossibleException extends RuntimeException {
    public RegistrationNotPossibleException(String message) {
        super(message);
    }
}
