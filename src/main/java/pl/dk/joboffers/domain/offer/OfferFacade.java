package pl.dk.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import pl.dk.joboffers.domain.offer.dto.OfferDto;
import pl.dk.joboffers.domain.offer.exceptions.NoNewOfferToSaveException;
import pl.dk.joboffers.domain.offer.exceptions.OfferExistsInDatabaseException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferFetcher offerFetcher;
    private final OfferDtoMapper offerDtoMapper;
    private final RetrievedOfferValidator retrievedOfferValidator;
    private final SaveOfferValidator saveOfferValidator;


    public Optional<OfferDto> findOfferById(String id) {
        return offerRepository.findById(id)
                .map(offerDtoMapper::map);

    }

    public List<OfferDto> findAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(offerDtoMapper::map)
                .toList();
    }

    public OfferDto save(OfferDto offerDto) {
        if (saveOfferValidator.validate(offerDto, offerRepository, offerDtoMapper)) {
            Offer offer = offerDtoMapper.map(offerDto);
            Offer savedOffer = offerRepository.save(offer);
            return offerDtoMapper.map(savedOffer);
        } else {
            throw new OfferExistsInDatabaseException();
        }

        /*Offer offer = offerDtoMapper.map(offerDto);
        Offer savedOffer = offerRepository.save(offer);
        return offerDtoMapper.map(savedOffer);*/

    }
    public List<OfferDto> fetchAllOfferAndSaveIfNotExists() {
        List<OfferDto> newOffers = retrievedOfferValidator
                .validate(offerFetcher, offerDtoMapper, offerRepository)
                .stream()
                .toList();

        if (!newOffers.isEmpty()) {
            return newOffers.stream()
                    .map(this::save)
                    .toList();
        } else {
            throw new NoNewOfferToSaveException();
        }
    }


}









