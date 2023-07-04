package pl.dk.joboffers.domain.offer;

import pl.dk.joboffers.domain.offer.dto.OfferDto;

import java.util.List;

class CustomOfferProvider implements OfferFetcher {

    List<OfferDto> customJobOfferList = List.of(
            new OfferDto("Company A", "Software Engineer", "50000 - 100000", "https://www.companya.com/offer"),
            new OfferDto("Company B", "Data Analyst", "60000 - 120000", "https://www.companyb.com/offer"),
            new OfferDto("Company C", "Product Manager", "70000 - 140000", "https://www.companyc.com/offer"),
            new OfferDto("Company D", "UX Designer", "55000 - 110000", "https://www.companyd.com/offer"),
            new OfferDto("Company E", "Frontend Developer", "45000 - 90000", "https://www.companye.com/offer"),
            new OfferDto("Company F", "Data Scientist", "65000 - 130000", "https://www.companyf.com/offer"),
            new OfferDto("Company G", "Project Manager", "60000 - 120000", "https://www.companyg.com/offer"),
            new OfferDto("Company H", "Backend Developer", "55000 - 110000", "https://www.companyh.com/offer"),
            new OfferDto("Company I", "Business Analyst", "50000 - 100000", "https://www.companyi.com/offer"),
            new OfferDto("Company J", "Software Tester", "45000 - 90000", "https://www.companyj.com/offer")
    );


    @Override
    public List<OfferDto> fetchAllOffers() {
        return customJobOfferList;
    }
}
