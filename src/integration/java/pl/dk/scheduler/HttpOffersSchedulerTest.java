package pl.dk.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pl.BaseIntegrationTest;
import pl.dk.joboffers.JobOffersApplication;
import pl.dk.joboffers.domain.offer.OfferFetcher;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = JobOffersApplication.class, properties = {"scheduler.enabler = true"})
class HttpOffersSchedulerTest extends BaseIntegrationTest {


    @SpyBean
    OfferFetcher offerFetcher;
    @Test
    void shouldRunHttpOffersFetchMethodGivenNumberOfTimes() {
        await().atMost(Duration.ofSeconds(2))
                .untilAsserted(() -> verify(offerFetcher, times(2)).fetchAllOffers());
    }
}
