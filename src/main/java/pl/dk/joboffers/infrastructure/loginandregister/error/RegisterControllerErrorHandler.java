package pl.dk.joboffers.infrastructure.loginandregister.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoWriteException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dk.joboffers.infrastructure.offer.controller.error.ErrorResponse;

@RestControllerAdvice
@RequiredArgsConstructor
class RegisterControllerErrorHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(MongoWriteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userWithCurrentDataExistsInDatabase() throws JsonProcessingException {
        String message = "User you want to register already exists in database";
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, message);
        return objectMapper.writeValueAsString(errorResponse);
    }
}
