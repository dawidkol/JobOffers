package pl.dk.joboffers.infrastructure.loginandregister.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dk.joboffers.infrastructure.loginandregister.controller.TokenController;

@RestControllerAdvice(basePackageClasses = TokenController.class)
class TokenControllerErrorHandler {

    public static final String BAD_CREDENTIALS = "Bad Credentials";
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public String badCredentialErrorhandler() throws JsonProcessingException {
        TokenErrorResponse tokenErrorResponse = new TokenErrorResponse(HttpStatus.UNAUTHORIZED.toString(), BAD_CREDENTIALS);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(tokenErrorResponse);
    }


}
