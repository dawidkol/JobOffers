package pl.dk.features;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.BaseIntegrationTest;
import pl.dk.joboffers.domain.offer.OfferFetcher;
import pl.dk.joboffers.domain.offer.dto.OfferDto;
import pl.dk.joboffers.domain.offer.exceptions.NoNewOfferToSaveException;
import pl.dk.joboffers.infrastructure.offer.scheduler.HttpSchedulerOffers;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log4j2
class TypicalScenarioUserWantToSeeOffersIntegrationTest extends BaseIntegrationTest implements SampleJobOfferResponse {

    @Autowired
    OfferFetcher offerHttpClient;

    @Autowired
    HttpSchedulerOffers schedulerOffers;

    @Test
    public void userWantToSeeOffersButHaveToBeLoggedInAndExternalServerShouldHaveSomeOffers() throws Exception {
        //  step 1:there are no offers in external HTTP server
        setResponse(bodyWithZeroJobOfferJson());

        //  step 2:scheduler ran 1 st time and made GET to external server and system added 0 offers to database
        Assert.assertThrows(NoNewOfferToSaveException.class, () -> {
            schedulerOffers.fetchAllOfferAndSaveIfNotExists();
        });

        //  step 3:user tried to get JWT token by requesting POST / token with username = someUser, password = somepassword and system returned UNAUTHORIZED(401)
        //  step 4:user made GET / offers with no wt token and system returned UNAUTHORIZED (401)
        //  step 5:user made POST / register with username = someUser, password = somepassword and system registered user with status OK (200)
        //  step 6:user tried to get JWT token by requesting POST / token with username = someUser, password = somePassword and system returned OK (200) and jwttoken = AAAA.BBBB.CCC
        //  step 7:user made GET / offers with header "Authorization: Bearer AAAA.BBBB.CCC" and system returned OK (200) with 0 offers
        //given
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/offers")).andReturn();

        //when
        int responseStatusCode = mvcResult.getResponse().getStatus();
        String responseBody = mvcResult.getResponse().getContentAsString();

        //then
        assertAll(
                () -> Assertions.assertEquals(HttpStatus.OK.value(), responseStatusCode),
                () -> assertThat(responseBody).isEqualTo("[]")
        );
        //  step 8:there are 2 new offers in external HTTP, server
        setResponse(bodyWithTwoJobOffersJson());

        //  step 9:scheduler ran 2 nd time and made GET to external server and system added 2 new offers with ids:1000 and 2000 to database ids:1000 and 2000
        List<OfferDto> firstTwoNewOffers = schedulerOffers.fetchAllOfferAndSaveIfNotExists();

        //  step 10:user made GET / offers with header "Authorization: Bearer AAAA.BBBB. CCC" and system returned OK (200) with 2 offers with
        //given
        ResultActions performTwoOffers = mockMvc.perform(MockMvcRequestBuilders.get("/offers").contentType(APPLICATION_JSON_VALUE));

        //when
        String contentTwoOffersString = performTwoOffers.andReturn().getResponse().getContentAsString();
        List<OfferDto> offerDtos = objectMapper.readValue(contentTwoOffersString, new TypeReference<List<OfferDto>>() {
        });

        //then
        assertThat(offerDtos).hasSize(2);

        //  step 11:user made GET / offers / 9999 and system returned NOT_FOUND (404) with message "Offer with id 9999 not found"
        //given
        String id = "9999";

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/offers/" + id)
                .contentType(APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isNotFound()).andExpect(content().json(offerNotFoundJson(id)));

        //  step 12:user made GET / offers / 1000 and system returned OK (200) with offer
        //given
        String firstOfferId = firstTwoNewOffers.get(0).getId();
        OfferDto firstOfferDto = firstTwoNewOffers.get(0);

        //when
        ResultActions performWithOneOffer = mockMvc.perform(MockMvcRequestBuilders.get("/offers/" + firstOfferId)
                .contentType(APPLICATION_JSON_VALUE));
        String firstOfferString = performWithOneOffer.andReturn().getResponse().getContentAsString();
        OfferDto offerDto = objectMapper.readValue(firstOfferString, OfferDto.class);

        //then
        performWithOneOffer.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertAll(
                () -> assertThat(offerDto).isEqualTo(firstOfferDto)
        );

        //  step 13:there are 2 new offers in external HTTP server
        setResponse(bodyWithTwoNewOffers());
        //  step 14:scheduler ran 3 rd time and made GET to external server and system added 2 new offers with ids:3000 and 4000 to database
        //given

        //when
        List<OfferDto> twoNewOffers = schedulerOffers.fetchAllOfferAndSaveIfNotExists();

        //then
        assertThat(twoNewOffers).hasSize(2);

        //  step 15:user made GET / offers with header "Authorization: Bearer AAAA. BBBB.CCC" and system returned OK (200) with 4 offers with ids:1000, 2000, 3000 and 4000
        //given
        ResultActions performForFourOffersInDatabase = mockMvc.perform(MockMvcRequestBuilders.get("/offers").contentType(APPLICATION_JSON_VALUE));

        //when
        String fourOffersInDatabase = performForFourOffersInDatabase.andReturn().getResponse().getContentAsString();
        List<OfferDto> fourOffers = objectMapper.readValue(fourOffersInDatabase, new TypeReference<List<OfferDto>>() {
        });

        //then
        assertThat(fourOffers).hasSize(4);

        // step 16: user made POST /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and offer as body and system returned CREATED(201) with saved offer
        //given
        String jobOfferToSave = bodyWithOfferToSave();

        OfferDto jobOfferToSaveObject = objectMapper.readValue(jobOfferToSave, OfferDto.class);

        //when
        ResultActions performOneJobOffer = mockMvc.perform(post("/offers/save")
                .contentType(APPLICATION_JSON_VALUE).content(jobOfferToSave));

        //then
        String contentAsString = performOneJobOffer
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        OfferDto savedOffer = objectMapper.readValue(contentAsString, OfferDto.class);

        assertAll(
                () -> assertThat(savedOffer.getId()).isNotNull(),
                () -> assertThat(savedOffer.getTitle()).isEqualTo(jobOfferToSaveObject.getTitle()),
                () -> assertThat(savedOffer.getCompany()).isEqualTo(jobOfferToSaveObject.getCompany()),
                () -> assertThat(savedOffer.getSalary()).isEqualTo(jobOfferToSaveObject.getSalary()),
                () -> assertThat(savedOffer.getOfferUrl()).isEqualTo(jobOfferToSaveObject.getOfferUrl())
        );
        // step 17: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 3 offer
        //given

        //when
        ResultActions performThreeOffers = mockMvc.perform(MockMvcRequestBuilders.get("/offers").contentType(APPLICATION_JSON_VALUE));

        String returnedStringContent = performThreeOffers.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<OfferDto> returnedOffers = objectMapper.readValue(returnedStringContent, new TypeReference<List<OfferDto>>() {
        });

        assertAll(
                () -> assertThat(returnedOffers).hasSize(5)
        );
    }

    private StubMapping setResponse(String responseMethod) {
        return wireMockServer.stubFor(get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(responseMethod)));
    }


}
