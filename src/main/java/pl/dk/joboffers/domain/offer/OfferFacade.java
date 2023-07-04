package pl.dk.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.dk.joboffers.domain.offer.dto.OfferDto;
import pl.dk.joboffers.domain.offer.exceptions.NoNewOfferToSaveException;
import pl.dk.joboffers.domain.offer.exceptions.OfferNotFoundException;

import java.util.List;

@AllArgsConstructor
@Component
public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferFetcher offerFetcher;
    private final OfferDtoMapper offerDtoMapper;
    private final RetrievedOfferValidator retrievedOfferValidator;


    public OfferDto findOfferById(Long id) {
        return offerRepository.findOfferById(id).map(offerDtoMapper::map)
                .orElseThrow(OfferNotFoundException::new);
    }

    public List<OfferDto> findAllOffers() {
        return offerRepository.findAllOffers()
                .stream()
                .map(offerDtoMapper::map)
                .toList();
    }

    public OfferDto save(OfferDto offerDto) {
        Offer offer = offerDtoMapper.map(offerDto);
        Offer savedOffer = offerRepository.save(offer);
        return offerDtoMapper.map(savedOffer);
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
