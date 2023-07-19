package pl.dk.joboffers.infrastructure.validation;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
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

        list.forEach(constraint -> log.error("field: " + constraint.getField() + " " + constraint.getMessage()));

        return list;
    }
}
