package pl.dk.joboffers.domain.offer;

import java.math.BigDecimal;
import java.util.List;

class CustomOfferProvider implements OfferFetcher {

    List<OfferDto> customJobOfferList = List.of(
            new OfferDto("Company A", "Software Engineer", new BigDecimal(50000), new BigDecimal(100000), "https://www.companya.com/offer"),
            new OfferDto("Company B", "Data Analyst", new BigDecimal(60000), new BigDecimal(120000), "https://www.companyb.com/offer"),
            new OfferDto("Company C", "Product Manager", new BigDecimal(70000), new BigDecimal(140000), "https://www.companyc.com/offer"),
            new OfferDto("Company D", "UX Designer", new BigDecimal(55000), new BigDecimal(110000), "https://www.companyd.com/offer"),
            new OfferDto("Company E", "Frontend Developer", new BigDecimal(45000), new BigDecimal(90000), "https://www.companye.com/offer"),
            new OfferDto("Company F", "Data Scientist", new BigDecimal(65000), new BigDecimal(130000), "https://www.companyf.com/offer"),
            new OfferDto("Company G", "Project Manager", new BigDecimal(60000), new BigDecimal(120000), "https://www.companyg.com/offer"),
            new OfferDto("Company H", "Backend Developer", new BigDecimal(55000), new BigDecimal(110000), "https://www.companyh.com/offer"),
            new OfferDto("Company I", "Business Analyst", new BigDecimal(50000), new BigDecimal(100000), "https://www.companyi.com/offer"),
            new OfferDto("Company J", "Software Tester", new BigDecimal(45000), new BigDecimal(90000), "https://www.companyj.com/offer")
    );

    @Override
    public List<OfferDto> fetchAllOffers() {
        return customJobOfferList;
    }
}
