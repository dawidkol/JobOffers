package pl.dk.http.error;

import org.springframework.web.client.RestTemplate;
import pl.dk.joboffers.domain.offer.OfferFetcher;
import pl.dk.joboffers.infrastructure.offer.http.OfferHttpClientConfig;

class FetchOfferRestTemplateIntegrationTestConfig extends OfferHttpClientConfig {

    public OfferFetcher remoteOfferFetcherClient(int port, int connectionTimeout, int readTimeout) {
        RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return remoteOfferClient(restTemplate, "http://localhost", port);
    }
}
