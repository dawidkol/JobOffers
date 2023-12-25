package pl.dk.joboffers.domain.offer;

import lombok.extern.log4j.Log4j2;
import pl.dk.joboffers.domain.offer.dto.OfferDto;

import java.util.List;

@Log4j2
class RetrievedOfferValidator {

    public List<OfferDto> validateFetchedOffers(OfferFetcher offerFetcher, OfferDtoMapper offerDtoMapper, OfferRepository offerRepository) {
        return offerFetcher.fetchAllOffers()
                .stream()
                .filter(offer -> !existingOffersInDb(offerRepository, offerDtoMapper).contains(offer))
                .toList();
    }

    private List<OfferDto> existingOffersInDb (OfferRepository offerRepository, OfferDtoMapper offerDtoMapper){
        return offerRepository.findAll()
                .stream()
                .map(offerDtoMapper::map)
                .toList();
    }
}
