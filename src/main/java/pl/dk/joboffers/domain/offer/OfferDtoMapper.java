package pl.dk.joboffers.domain.offer;


import pl.dk.joboffers.domain.offer.dto.OfferDto;
import pl.dk.joboffers.domain.offer.dto.OfferToSaveDto;


class OfferDtoMapper {

    OfferDto map(Offer offer) {
        return OfferDto.builder()
                .id(offer.id())
                .title(offer.title())
                .company(offer.company())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }

    Offer map(OfferDto offerDto) {
        return Offer.builder()
                .title(offerDto.title())
                .company(offerDto.company())
                .salary(offerDto.salary())
                .offerUrl(offerDto.offerUrl())
                .build();
    }

     Offer map(OfferToSaveDto offerToSaveDto) {
        return Offer.builder()
                .title(offerToSaveDto.title())
                .company(offerToSaveDto.company())
                .salary(offerToSaveDto.salary())
                .offerUrl(offerToSaveDto.offerUrl())
                .build();
    }

    OfferToSaveDto mapToOfferToSaveDto(Offer offerDto) {
        return OfferToSaveDto.builder()
                .title(offerDto.title())
                .company(offerDto.company())
                .salary(offerDto.salary())
                .offerUrl(offerDto.offerUrl())
                .build();
    }
}
