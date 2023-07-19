package pl.dk.joboffers.domain.offer;

import pl.dk.joboffers.domain.offer.dto.OfferDto;

import java.util.List;

class SaveOfferValidator {

    public boolean validate(OfferDto offerDto, OfferRepository offerRepository, OfferDtoMapper offerDtoMapper) {
        List<OfferDto> allOffers = existingOffersInDb(offerRepository, offerDtoMapper);
        List<OfferDto> list = allOffers.stream().filter(offer -> offer.equals(offerDto)).toList();
        return list.isEmpty();
    }

    private List<OfferDto> existingOffersInDb (OfferRepository offerRepository, OfferDtoMapper offerDtoMapper){
        return offerRepository.findAll()
                .stream()
                .map(offerDtoMapper::map)
                .toList();
    }
}
