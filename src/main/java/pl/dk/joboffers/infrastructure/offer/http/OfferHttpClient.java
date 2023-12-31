package pl.dk.joboffers.infrastructure.offer.http;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import pl.dk.joboffers.domain.offer.OfferFetcher;
import pl.dk.joboffers.domain.offer.dto.OfferDto;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Log4j2
class OfferHttpClient implements OfferFetcher {

    public static final String SERVICE = "/offers";
    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    public OfferHttpClient(RestTemplate restTemplate, String uri, int port) {
        this.restTemplate = restTemplate;
        this.uri = uri;
        this.port = port;
    }

    @Override
    public List<OfferDto> fetchAllOffers() {
        log.info("Started fetching offers from http client");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        try {
            String httpUrl = getUrlService(SERVICE);
            final String url = UriComponentsBuilder.fromHttpUrl(httpUrl).toUriString(); //Wiremock na localHoście
            ResponseEntity<List<OfferDto>> responseBody = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
                    new ParameterizedTypeReference<>() {
                    });
            final List<OfferDto> body = responseBody.getBody();
            return (!body.isEmpty() ? body : Collections.emptyList());
        } catch (ResourceAccessException e) {
            log.error("Error while fetching offers using htttp client " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getUrlService(String service) {
        return uri + ":" + port + service;
    }
}
