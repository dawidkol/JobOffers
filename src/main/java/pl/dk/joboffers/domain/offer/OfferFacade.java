package pl.dk.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import pl.dk.joboffers.domain.offer.dto.OfferDto;
import pl.dk.joboffers.domain.offer.dto.OfferToSaveDto;
import pl.dk.joboffers.domain.offer.exceptions.OfferExistsInDatabaseException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Log4j2
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

    @Cacheable(cacheNames = "jobOffers")
    public List<OfferDto> findAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(offerDtoMapper::map)
                .toList();
    }

    public OfferDto save(OfferToSaveDto offerToSaveDto) {
        if (saveOfferValidator.validate(offerToSaveDto, offerRepository, offerDtoMapper)) {
            Offer offerToSave = offerDtoMapper.map(offerToSaveDto);
            Offer savedOffer = offerRepository.save(offerToSave);
            return offerDtoMapper.map(savedOffer);
        } else
            throw new OfferExistsInDatabaseException();
    }

    public List<OfferDto> fetchAllOfferAndSaveIfNotExists() {
        List<OfferDto> newOffers = retrievedOfferValidator
                .validateFetchedOffers(offerFetcher, offerDtoMapper, offerRepository)
                .stream()
                .toList();
        if (!newOffers.isEmpty()) {
            log.info("Numbers of new offers to save: " + newOffers.size());
            List<Offer> offersToSave = newOffers.stream()
                    .map(offerDtoMapper::map)
                    .toList();
            List<Offer> savedOffers = offerRepository.saveAll(offersToSave);
            return savedOffers.stream()
                    .map(offerDtoMapper::map)
                    .toList();
        } else {
            log.warn("No new offers to save in external sever");
            return newOffers;
        }
    }
}









