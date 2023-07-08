package pl.dk.joboffers.domain.offer;


import pl.dk.joboffers.domain.offer.dto.OfferDto;


class OfferDtoMapper {

    OfferDto map(Offer offer) {
        return OfferDto.builder()
                .company(offer.getCompany())
                .title(offer.getTitle())
                .salary(offer.getSalary())
                .offerUrl(offer.getOfferUrl())
                .build();
    }


    Offer map(OfferDto offerDto) {
        return Offer.builder()
                .company(offerDto.getCompany())
                .title(offerDto.getTitle())
                .salary(offerDto.getSalary())
                .offerUrl(offerDto.getOfferUrl())
                .build();
    }
}
