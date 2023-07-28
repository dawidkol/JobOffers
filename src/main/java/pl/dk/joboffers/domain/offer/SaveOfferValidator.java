package pl.dk.joboffers.domain.offer;

import lombok.extern.log4j.Log4j2;
import pl.dk.joboffers.domain.offer.dto.OfferToSaveDto;

import java.util.List;

@Log4j2
class SaveOfferValidator {

    public boolean validate(OfferToSaveDto offerToSaveDto, OfferRepository offerRepository, OfferDtoMapper offerDtoMapper) {
        List<OfferToSaveDto> existingOffersInDb = existingOffersInDb(offerRepository, offerDtoMapper);
        List<OfferToSaveDto> list = existingOffersInDb.stream()
                .filter(offer -> offer.equals(offerToSaveDto)).toList();
        return list.isEmpty();
    }

    private List<OfferToSaveDto> existingOffersInDb (OfferRepository offerRepository, OfferDtoMapper offerDtoMapper){
        return offerRepository.findAll()
                .stream()
                .map(offerDtoMapper::mapToOfferToSaveDto)
                .toList();
    }
}
