package pl.dk.joboffers.domain.loginandregister;

class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);

    }
}
