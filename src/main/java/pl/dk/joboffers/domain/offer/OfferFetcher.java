package pl.dk.joboffers.domain.offer;

import java.util.List;

interface OfferFetcher {
    List<OfferDto> fetchAllOffers();
}
