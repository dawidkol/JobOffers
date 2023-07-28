package pl.dk.joboffers.infrastructure.validation;

record ConstraintViolationError(String field, String message) {
}
