package pl.dk.joboffers.infrastructure.offer.controller.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dk.joboffers.domain.offer.exceptions.OfferExistsInDatabaseException;
import pl.dk.joboffers.infrastructure.offer.controller.OfferController;

@RestControllerAdvice(basePackageClasses = OfferController.class)
@RequiredArgsConstructor
public class OfferControllerErrorHandler {

    private final ObjectMapper objectMapper;


    public String offerNotFoundResponse(String id) throws JsonProcessingException {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, String.format("offer with id = %s not found", id));
        return objectMapper.writeValueAsString(errorResponse);
    }

    @ExceptionHandler(OfferExistsInDatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String offerExistsInDatabaseResponse() throws JsonProcessingException {
        String message = "offer you want to save exists in database";
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message);
        return objectMapper.writeValueAsString(errorResponse);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String offerWithCurrentUrlExistsInDatabase() throws JsonProcessingException {
        String message = "offer with current URL exists in database";
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, message);
        return objectMapper.writeValueAsString(errorResponse);
    }


}
