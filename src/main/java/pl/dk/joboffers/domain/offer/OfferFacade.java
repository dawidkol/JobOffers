package pl.dk.joboffers.domain.offer;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OfferFacade {

    private static final String OFFER_NOT_FOUND = "Offer not found";
    private static final String NO_OFFER_TO_SAVE = "No new offer to save";
    private final OfferRepository offerRepository;
    private final OfferFetcher offerFetcher;
    private final OfferDtoMapper offerDtoMapper;
    private final RetrievedOfferValidator retrievedOfferValidator;

    public OfferDto findOfferById(Long id) {
        return offerRepository.findOfferById(id).map(offerDtoMapper::map)
                .orElseThrow(() -> new OfferNotFoundException(OFFER_NOT_FOUND));
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
            throw new NoNewOfferToSaveException(NO_OFFER_TO_SAVE);
        }



    }









}
