package pl.dk.joboffers.infrastructure.offer.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.dk.joboffers.domain.offer.OfferFacade;
import pl.dk.joboffers.domain.offer.dto.OfferDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@AllArgsConstructor
@Log4j2
@Component
public class HttpSchedulerOffers {

    private final OfferFacade offerFacade;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedDelayString = "${infrastructure.offer.scheduler.request.delay}")
    public List<OfferDto> fetchAllOfferAndSaveIfNotExists() {
        String formattedTime = LocalDateTime.now().format(dateTimeFormatter);
        log.info("Started fetching {}", formattedTime);
        return offerFacade.fetchAllOfferAndSaveIfNotExists();
    }
}
