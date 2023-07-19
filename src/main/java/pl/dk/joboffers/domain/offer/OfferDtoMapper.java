package pl.dk.joboffers.domain.offer;


import pl.dk.joboffers.domain.offer.dto.OfferDto;


class OfferDtoMapper {

    OfferDto map(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .company(offer.getCompany())
                .salary(offer.getSalary())
                .offerUrl(offer.getOfferUrl())
                .build();
    }
    Offer map(OfferDto offerDto) {
        return Offer.builder()
                .title(offerDto.getTitle())
                .company(offerDto.getCompany())
                .salary(offerDto.getSalary())
                .offerUrl(offerDto.getOfferUrl())
                .build();
    }
}
