package pl.dk.joboffers.infrastructure.offer.http;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;


class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        final HttpStatusCode statusCode = httpResponse.getStatusCode();

        if (statusCode.is5xxServerError()) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Error while using client");
        } else if (statusCode.is4xxClientError()) {
            if (statusCode.isSameCodeAs(NOT_FOUND)) {
                throw new ResponseStatusException(NOT_FOUND);
            } else if (statusCode.isSameCodeAs(UNAUTHORIZED)) {
                throw new ResponseStatusException(UNAUTHORIZED);
            } else if (statusCode.isSameCodeAs(NO_CONTENT)) {
                throw new ResponseStatusException(NO_CONTENT);
            }
        }
    }

}
