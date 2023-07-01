package pl.dk.joboffers.domain.offer;



class OfferDtoMapper {

    OfferDto map(Offer offer) {
        return OfferDto.builder()
                .companyName(offer.getCompanyName())
                .position(offer.getPosition())
                .minSalary(offer.getMinSalary())
                .maxSalary(offer.getMaxSalary())
                .offerUrl(offer.getOfferUrl())
                .build();
    }


    Offer map(OfferDto offerDto) {
        return Offer.builder()
                .companyName(offerDto.getCompanyName())
                .position(offerDto.getPosition())
                .minSalary(offerDto.getMinSalary())
                .maxSalary(offerDto.getMaxSalary())
                .offerUrl(offerDto.getOfferUrl())
                .build();
    }
}
