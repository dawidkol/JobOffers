package pl.dk.features;

import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pl.BaseIntegrationTest;
import pl.dk.joboffers.domain.offer.OfferFetcher;
import pl.dk.joboffers.domain.offer.dto.OfferDto;
import pl.dk.joboffers.domain.offer.exceptions.NoNewOfferToSaveException;
import pl.dk.joboffers.infrastructure.offer.scheduler.HttpSchedulerOffers;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
class TypicalScenarioUserWantToSeeOffersIntegrationTest extends BaseIntegrationTest implements SampleJobOfferResponse {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String URL = "/offers";

    @Autowired
    OfferFetcher offerHttpClient;

    @Autowired
    HttpSchedulerOffers schedulerOffers;

    @Test
    public void userWantToSeeOffersButHaveToBeLoggedInAndExternalServerShouldHaveSomeOffers() throws InterruptedException {

//  step 1:there are no offers in external HTTP server
        wireMockServer.stubFor(WireMock.get(URL)
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(bodyWithZeroJobOfferJson())));

//  step 2:scheduler ran 1 st time and made GET to external server and system added 0 offers to database
        /*try {
            List<OfferDto> offersToSave = schedulerOffers.fetchAllOfferAndSaveIfNotExists();
            *//*Assertions.assertEquals(0, offersToSave);*//*
        } catch (NoNewOfferToSaveException e) {
            log.error(e.getMessage());
        }*/

        Assert.assertThrows(NoNewOfferToSaveException.class, () -> {
            List<OfferDto> offersToSave = schedulerOffers.fetchAllOfferAndSaveIfNotExists();
        });



//  step 3:user tried to get JWT token by requesting POST / token with username = someUser, password = somepassword and system returned UNAUTHORIZED(401)
//  step 4:user made GET / offers with no wt token and system returned UNAUTHORIZED (401)
//  step 5:user made POST / register with username = someUser, password = some assword and system registered user with status OK (200)
//  step 6:user tried to get JWT token by requesting POST / token with username = someUser, password = somePassword and system returned OK (200) and jwttoken = AAAA.BBBB.CCC
//  step 7:user made GET / offers with header "Authorization: Bearer AAAA.BBBB.CCC" and system returned OK (200) with 0 offers
//  step 8:there are 2 new offers in external HTTP, server
//  step 9:scheduler ran 2 nd time and made GET to external server and system added 2 new offers with ids:1000 and 2000 to database ids:1000 and 2000
//  step 10:user made GET / offers with header "Authorization: Bearer AAAA.BBBB. CCC" and system returned OK (200) with 2 offers with
//  step 11:user made GET / offers / 9999 and system returned NOT_FOUND (404) with message "Offer with id 9999 not found"
//  step 12:user made GET / offers / 1000 and system returned OK (200) with offer
//  step 13:there are 2 new offers in external HTTP server
//  step 14:scheduler ran 3 rd time and made GET to external server and system added 2 new offers with ids:3000 and 4000 to database
//  step 15:user made GET / offers with header "Authorization: Bearer AAAA. BBBB.CCC" and system returned OK (200) with 4 offers with ids:1000, 2000, 3000 and 4000


    }

}
