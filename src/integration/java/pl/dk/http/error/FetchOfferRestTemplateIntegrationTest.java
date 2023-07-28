package pl.dk.http.error;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.dk.joboffers.domain.offer.OfferFetcher;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FetchOfferRestTemplateIntegrationTest {

    @RegisterExtension
    public static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().port(1000))
            .build();

    OfferFetcher offerFetcher = new FetchOfferRestTemplateIntegrationTestConfig().remoteOfferFetcherClient(
            wireMockServer.getPort(),
            1000,
            1000
    );

    @Test
    void shouldThrowInternalServerErrorWhenConnectionResetByPeer() {
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse().
                        withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.CONNECTION_RESET_BY_PEER)));

        Exception exception = catchException(() -> offerFetcher.fetchAllOffers());

        assertThat(exception.getMessage()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }

    @Test
    void shouldThrowInternalServerErrorWhenMalformedResponseChunk() {
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse().
                        withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.MALFORMED_RESPONSE_CHUNK)));

        Exception exception = catchException(() -> offerFetcher.fetchAllOffers());

        assertAll(
                () -> assertThrows(RuntimeException.class, () -> offerFetcher.fetchAllOffers()),
                () -> assertThat(exception.getMessage()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        );

    }

    @Test
    void shouldThrowInternalServerErrorWhenRandomDataThenClose() {
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse().
                        withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.RANDOM_DATA_THEN_CLOSE)));

        Exception exception = catchException(() -> offerFetcher.fetchAllOffers());

        assertAll(
                () -> assertThrows(ResponseStatusException.class, () -> offerFetcher.fetchAllOffers()),
                () -> assertThat(exception.getMessage()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        );
    }

    @Test
    void shouldThorInternalServerErrorWhenEmptyResponse() {
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse().
                        withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.EMPTY_RESPONSE)));

        Exception exception = catchException(() -> offerFetcher.fetchAllOffers());

        assertAll(
                () -> assertThrows(ResponseStatusException.class, () -> offerFetcher.fetchAllOffers()),
                () -> assertThat(exception.getMessage()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        );
    }

    @Test
    void shouldThrowHttpStatusUnauthorized() {
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse().
                        withStatus(HttpStatus.UNAUTHORIZED.value())
                        .withHeader("Content-Type", "application/json")));

        //when
        Exception exception = catchException(() -> offerFetcher.fetchAllOffers());

        //then
        assertAll(
                () -> assertThrows(ResponseStatusException.class, () -> offerFetcher.fetchAllOffers()),
                () -> assertThat(exception.getMessage()).isEqualTo(HttpStatus.UNAUTHORIZED.toString())
        );
    }

    @Test
    void shouldThrowHttpStatusNotFound() {
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader("Content-Type", "application-json")));

        Exception exception = catchException(() -> offerFetcher.fetchAllOffers());

        assertAll(
                () -> assertThrows(ResponseStatusException.class, () -> offerFetcher.fetchAllOffers()),
                () -> assertThat(exception.getMessage()).isEqualTo(HttpStatus.NOT_FOUND.toString())
        );
    }

    @Test
    void shouldThrowHttStatusForbidden() {
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.FORBIDDEN.value())
                        .withHeader("Content-Type", "application-json")));

        Exception exception = catchException(() -> offerFetcher.fetchAllOffers());

        assertAll(
                () -> assertThrows(ResponseStatusException.class, () -> offerFetcher.fetchAllOffers()),
                () -> assertThat(exception.getMessage()).isEqualTo(HttpStatus.FORBIDDEN.toString())
        );
    }




}
