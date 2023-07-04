package pl.dk.joboffers.domain.offer;


import org.springframework.stereotype.Service;
import pl.dk.joboffers.domain.offer.dto.OfferDto;

import java.util.List;

@Service
class RetrievedOfferValidator {




    public List<OfferDto> validate(OfferFetcher offerFetcher, OfferDtoMapper offerDtoMapper, OfferRepository offerRepository) {
        return offerFetcher.fetchAllOffers()
                .stream()
                .filter(offer -> !existingOffersInDb(offerRepository, offerDtoMapper).contains(offer))
                .toList();
    }


    public List<OfferDto> existingOffersInDb (OfferRepository offerRepository, OfferDtoMapper offerDtoMapper){
        return offerRepository.findAllOffers()
                .stream()
                .map(offerDtoMapper::map)
                .toList();
    }



}
