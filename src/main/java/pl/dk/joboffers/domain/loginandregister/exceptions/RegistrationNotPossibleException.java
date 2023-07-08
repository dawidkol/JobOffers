package pl.dk.joboffers.domain.loginandregister.exceptions;

public class RegistrationNotPossibleException extends RuntimeException {
    public RegistrationNotPossibleException(String message) {
        super(message);
    }
}
