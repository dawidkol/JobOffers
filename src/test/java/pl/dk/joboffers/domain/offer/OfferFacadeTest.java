package pl.dk.joboffers.domain.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dk.joboffers.domain.offer.dto.OfferDto;
import pl.dk.joboffers.domain.offer.exceptions.NoNewOfferToSaveException;
import pl.dk.joboffers.domain.offer.exceptions.OfferNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferFacadeTest {

    private OfferFacade offerFacade;
    private OfferRepository offerRepository;
    private OfferFetcher offerFetcher;
    private OfferDtoMapper offerDtoMapper;
    private RetrievedOfferValidator retrievedOfferValidator;

    @BeforeEach
    void init() {
        offerRepository = new CustomInMemoryOfferDatabaseService();
        offerFetcher = new CustomOfferProvider();
        offerDtoMapper = new OfferDtoMapper();
        retrievedOfferValidator = new RetrievedOfferValidator();
        offerFacade = new OfferFacade(offerRepository, offerFetcher, offerDtoMapper, retrievedOfferValidator);
    }


    @Test
    void shouldReturnOfferDtoObject() {
        //given
        OfferDto offerDto = new OfferDto(
                "abc",
                "developer",
                "7000 - 15000",
                "www.jobOffer.pl/oferta1"
        );

        //when
        OfferDto savedOffer = offerFacade.save(offerDto);

        //then
        assertInstanceOf(OfferDto.class, savedOffer);
    }


    @Test
    void shouldReturnTenOffers() {
        //given
        List<OfferDto> offerDtos = offerFacade.fetchAllOfferAndSaveIfNotExists();

        //when
        int size = offerDtos.size();

        //then
        assertEquals(10, size);
    }

    @Test
    void shouldThrowNoNewOfferToSaveException() {
        //given

        //when
        List<OfferDto> offerDtos1 = offerFacade.fetchAllOfferAndSaveIfNotExists();

        //then

        Assertions.assertThrows(NoNewOfferToSaveException.class, () -> {
                    offerFacade.fetchAllOfferAndSaveIfNotExists();
                }
        );
    }


    @Test
    void shouldSaveOfferToDb() {
        //given
        OfferDto offerToSave = new OfferDto("Appul",
                "Software Engineer",
                "7000 - 15000",
                "https://www.appul.com/offer");

        //when
        OfferDto savedOffer = offerFacade.save(offerToSave);
        int dbSize = offerRepository.getDatabaseSize();

        //then
        assertEquals(1, dbSize);

    }

    @Test
    void shouldSaveTenOffersToDb() {
        //given
        List<OfferDto> offerDtos = offerFacade.fetchAllOfferAndSaveIfNotExists();

        //when
        int databaseSize = offerRepository.getDatabaseSize();

        //then
        assertEquals(10, databaseSize);
    }

    @Test
    void shouldFindOfferById() {
        //given
        OfferDto offerToSave = new OfferDto("Appul",
                "Software Engineer",
                "7000 - 15000",
                "https://www.appul.com/offer");

        OfferDto save = offerFacade.save(offerToSave);
        OfferDto offerFound = offerFacade.findOfferById(1L);

        assertAll(
                () -> assertEquals(offerToSave, save),
                () -> assertEquals(offerToSave, offerFound),
                () -> assertNotNull(offerFound)
        );
    }

    @Test
    void shouldThrowOfferNotFoundException() {
        //given
        Long id = 1L;

        //when then
        Assertions.assertThrows(OfferNotFoundException.class, () -> {
            offerFacade.findOfferById(id);
        });
    }


    @Test
    void shouldReturnTenOfferFromDatabase() {
        //given
        offerFacade.fetchAllOfferAndSaveIfNotExists();

        //when
        List<OfferDto> allOffers = offerFacade.findAllOffers();
        int databaseSize = offerRepository.getDatabaseSize();

        assertAll(
                () -> assertEquals(10, allOffers.size()),
                () -> assertEquals(10, databaseSize)
        );


    }


}