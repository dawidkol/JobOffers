package pl.dk.joboffers.domain.offer;

import pl.dk.joboffers.domain.offer.dto.OfferDto;

import java.util.List;

class CustomOfferProvider implements OfferFetcher {

    List<OfferDto> customJobOfferList = List.of(
            OfferDto.builder()
                    .company("Company A")
                    .title("Software Engineer")
                    .salary("50000 - 100000")
                    .offerUrl("https://www.companya.com/offer")
                    .build(),
            OfferDto.builder().company("Company B")
                    .title("Data Analyst")
                    .salary("60000 - 120000")
                    .offerUrl("https://www.companyb.com/offer")
                    .build(),
            OfferDto.builder().company("Company C")
                    .title("Product Manager")
                    .salary("70000 - 140000")
                    .offerUrl("https://www.companyc.com/offer")
                    .build(),
            OfferDto.builder()
                    .company("Company D")
                    .title("UX Designer")
                    .salary("55000 - 110000")
                    .offerUrl("https://www.companyd.com/offer")
                    .build(),
            OfferDto.builder()
                    .company("Company E")
                    .title("Frontend Developer")
                    .salary("45000 - 90000")
                    .offerUrl("https://www.companye.com/offer")
                    .build(),
            OfferDto.builder().
                    company("Company F")
                    .title("Data Scientist")
                    .salary("65000 - 130000")
                    .offerUrl("https://www.companyf.com/offer")
                    .build(),
            OfferDto.builder()
                    .company("Company G")
                    .title("Project Manager")
                    .salary("60000 - 120000")
                    .offerUrl("https://www.companyg.com/offer")
                    .build(),
            OfferDto.builder().company("Company H")
                    .title("Backend Developer")
                    .salary("55000 - 110000")
                    .offerUrl("https://www.companyh.com/offer")
                    .build(),
            OfferDto.builder().
                    company("Company I")
                    .title("Business Analyst")
                    .salary("50000 - 100000")
                    .offerUrl("https://www.companyi.com/offer")
                    .build(),
            OfferDto.builder()
                    .company("Company J")
                    .title("Software Tester")
                    .salary("45000 - 90000")
                    .offerUrl("https://www.companyj.com/offer")
                    .build()
    );


    @Override
    public List<OfferDto> fetchAllOffers() {
        return customJobOfferList;
    }
}
