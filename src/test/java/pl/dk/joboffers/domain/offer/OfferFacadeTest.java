package pl.dk.joboffers.domain.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dk.joboffers.domain.offer.dto.OfferDto;
import pl.dk.joboffers.domain.offer.dto.OfferToSaveDto;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class OfferFacadeTest {

    private OfferFacade offerFacade;
    private OfferRepository offerRepository;
    private OfferFetcher offerFetcher;
    private OfferDtoMapper offerDtoMapper;
    private RetrievedOfferValidator retrievedOfferValidator;
    private SaveOfferValidator saveOfferValidator;

    @BeforeEach
    void init() {
        offerRepository = new CustomInMemoryOfferDatabaseServiceForUnitTests();
        offerFetcher = new CustomOfferProvider();
        offerDtoMapper = new OfferDtoMapper();
        retrievedOfferValidator = new RetrievedOfferValidator();
        saveOfferValidator = new SaveOfferValidator();
        offerFacade = new OfferFacade(offerRepository, offerFetcher, offerDtoMapper, retrievedOfferValidator, saveOfferValidator);
    }


    @Test
    void shouldReturnOfferDtoObject() {
        //given
        OfferToSaveDto offerDto = OfferToSaveDto.builder()
                .title("abc")
                .company("Apple")
                .salary("7000 - 15000")
                .offerUrl("www.jobOffer.pl/oferta1")
                .build();

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
    void shouldNotBeAnyNewOffersToSave() {
        //given
        offerFacade.fetchAllOfferAndSaveIfNotExists();

        //when
        List<OfferDto> offerDtos1 = offerFacade.fetchAllOfferAndSaveIfNotExists();

        //then
        assertEquals(0, offerDtos1.size());
    }


    @Test
    void shouldSaveOfferToDb() {
        OfferToSaveDto offerToSave = OfferToSaveDto.builder()
                .title("Software Engineer")
                .company("Appul")
                .salary("7000 - 15000")
                .offerUrl("https://www.appul.com/offer")
                .build();

        //when
        OfferDto savedOffer = offerFacade.save(offerToSave);
        long dbSize = offerRepository.count();

        //then
        assertEquals(1, dbSize);
    }

    @Test
    void shouldSaveTenOffersToDb() {
        //given
        List<OfferDto> offerDtos = offerFacade.fetchAllOfferAndSaveIfNotExists();

        //when
        long databaseSize = offerRepository.count();

        //then
        assertEquals(10, databaseSize);
    }

    @Test
    void shouldFindOfferById() {
        //given
        OfferToSaveDto offerToSave = OfferToSaveDto.builder()
                .title("Software Engineer")
                .company("Appul")
                .salary("7000 - 15000")
                .offerUrl("https://www.appul.com/offer")
                .build();

        OfferDto savedJobOffers = offerFacade.save(offerToSave);
        OfferDto offerFound = offerFacade.findOfferById(String.valueOf(1L)).get();


        assertAll(
                () -> assertEquals(offerToSave.title(), offerFound.title()),
                () -> assertEquals(offerToSave.company(), offerFound.company()),
                () -> assertEquals(offerToSave.salary(), offerFound.salary()),
                () -> assertEquals(offerToSave.offerUrl(), offerFound.offerUrl())
        );
    }

    @Test
    void shouldThrowEmptyOptional() {
        //given
        Long id = 1L;
        //when
        Optional<OfferDto> offerById = offerFacade.findOfferById(String.valueOf(id));
        //then
        assertTrue(offerById.isEmpty());
    }


    @Test
    void shouldReturnTenOfferFromDatabase() {
        //given
        offerFacade.fetchAllOfferAndSaveIfNotExists();

        //when
        List<OfferDto> allOffers = offerFacade.findAllOffers();
        long databaseSize = offerRepository.count();

        assertAll(
                () -> assertEquals(10, allOffers.size()),
                () -> assertEquals(10, databaseSize)
        );


    }


}