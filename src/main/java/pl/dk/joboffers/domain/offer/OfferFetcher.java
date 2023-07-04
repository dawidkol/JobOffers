package pl.dk.joboffers.domain.offer;

import pl.dk.joboffers.domain.offer.dto.OfferDto;

import java.util.List;

public interface OfferFetcher {
    List<OfferDto> fetchAllOffers();
}
