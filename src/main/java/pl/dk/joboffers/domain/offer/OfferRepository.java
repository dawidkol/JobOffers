package pl.dk.joboffers.domain.offer;

import java.util.List;
import java.util.Optional;

interface OfferRepository {


    Optional<Offer> findOfferById(Long id);

    List<Offer> findAllOffers();

    Offer save(Offer offer);

    int getDatabaseSize();


}
