package pl.dk.joboffers.domain.offer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository {


    Optional<Offer> findOfferById(Long id);

    List<Offer> findAllOffers();

    Offer save(Offer offer);

    int getDatabaseSize();


}
