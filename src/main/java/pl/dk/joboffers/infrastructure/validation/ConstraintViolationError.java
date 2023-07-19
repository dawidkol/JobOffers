package pl.dk.joboffers.infrastructure.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ConstraintViolationError {
    private String field;
    private String message;
}
