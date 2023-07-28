package pl.dk.joboffers.infrastructure.validation;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dk.joboffers.infrastructure.offer.controller.OfferController;

import java.util.List;

@RestControllerAdvice(basePackageClasses = OfferController.class)
@Log4j2
class ApiValidationErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public List<ConstraintViolationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ConstraintViolationError> list = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ConstraintViolationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        list.forEach(constraint -> log.error("field: " + constraint.field() + " " + constraint.message()));

        return list;
    }
}
