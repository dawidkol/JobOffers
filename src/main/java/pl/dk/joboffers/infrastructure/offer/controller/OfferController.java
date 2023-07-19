package pl.dk.joboffers.infrastructure.offer.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.dk.joboffers.domain.offer.OfferFacade;
import pl.dk.joboffers.domain.offer.dto.OfferDto;
import pl.dk.joboffers.infrastructure.offer.controller.error.OfferControllerErrorHandler;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



@RestController
@AllArgsConstructor
@Log4j2
public class OfferController {

    private final OfferFacade offerFacade;
    private final OfferControllerErrorHandler offerControllerErrorHandler;

    @GetMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferDto>> getAllOffers() {
        List<OfferDto> allOffers = offerFacade.findAllOffers();
        if (allOffers.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(allOffers);
        }
    }

    @GetMapping(value = "/offers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOfferById(@PathVariable String id) throws JsonProcessingException {
        Optional<OfferDto> offerById = offerFacade.findOfferById(id);
        if (offerById.isEmpty()) {
            String offerNotFound = offerControllerErrorHandler.offerNotFoundResponse(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(offerNotFound);
        } else {
            return ResponseEntity.ok(offerById);
        }
    }

    @PostMapping(value = "/offers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDto> saveOffer(@Valid @RequestBody OfferDto offerDto) {
        OfferDto savedOffer = offerFacade.save(offerDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().replacePath("/offers/{id}")
                .buildAndExpand(savedOffer)
                .toUri();
        log.info(uri);
        return ResponseEntity.created(uri).body(savedOffer);
    }
}
