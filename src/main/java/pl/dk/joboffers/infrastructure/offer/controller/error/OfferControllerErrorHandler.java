package pl.dk.joboffers.infrastructure.offer.controller.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.servlet.annotation.HandlesTypes;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.dk.joboffers.domain.offer.exceptions.OfferExistsInDatabaseException;
import pl.dk.joboffers.infrastructure.offer.controller.OfferController;
import pl.dk.joboffers.infrastructure.validation.ConstraintViolationError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class OfferControllerErrorHandler {


    public String offerNotFoundResponse(String id) throws JsonProcessingException {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, String.format("offer with id = %s not found", id));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(errorResponse);
    }

    @ExceptionHandler(OfferExistsInDatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String offerExistsInDatabaseResponse() throws JsonProcessingException {
        String message = "offer you want to save exists in database";
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(errorResponse);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public String offerWithCurrentUrlExistsInDatabase() throws JsonProcessingException {
        String message = "offer with current URL exists in database";
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, message);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(errorResponse);
    }


}
