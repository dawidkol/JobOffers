package pl.dk.joboffers.domain.offer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OfferFacadeConfig {
    @Bean
    OfferFacade offerFacade(OfferRepository offerRepository, OfferFetcher offerFetcher) {
        OfferDtoMapper offerDtoMapper = new OfferDtoMapper();
        RetrievedOfferValidator retrievedOfferValidator = new RetrievedOfferValidator();
        SaveOfferValidator saveOfferValidator = new SaveOfferValidator();
        return new OfferFacade(offerRepository, offerFetcher, offerDtoMapper, retrievedOfferValidator, saveOfferValidator);
    }
}
