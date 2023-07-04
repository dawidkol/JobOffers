package pl.dk.joboffers.domain.offer;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
class CustomInMemoryOfferDatabaseService implements OfferRepository{
    /*List<Offer> jobOffersList = new ArrayList<>();*/
    Map<Long, Offer> jobOffersList = new HashMap<>();
    private Long jobOfferId = 1L;

    @Override
    public Optional<Offer> findOfferById(Long id) {


        return jobOffersList.entrySet().stream()
                .filter(x -> x.getKey().equals(id)).findFirst()
                .map(Map.Entry::getValue);


    }

    @Override
    public List<Offer> findAllOffers() {
        return jobOffersList.values().stream().toList();
    }

    @Override
    public Offer save(Offer offer) {
        Offer offerToSave = Offer.builder()
                .id(jobOfferId)
                .company(offer.getCompany())
                .title(offer.getTitle())
                .salary(offer.getSalary())
                .offerUrl(offer.getOfferUrl())
                .build();
        jobOffersList.put(jobOfferId, offerToSave);
        jobOfferId++;
        return offerToSave;
    }

    @Override
    public int getDatabaseSize() {
        return jobOffersList.size();
    }

}
