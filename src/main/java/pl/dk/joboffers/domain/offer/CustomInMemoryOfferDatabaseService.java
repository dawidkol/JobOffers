package pl.dk.joboffers.domain.offer;

import java.util.*;

class CustomInMemoryOfferDatabaseService implements OfferRepository{
    /*List<Offer> jobOffersList = new ArrayList<>();*/
    Map<Long, Offer> jobOffersList = new HashMap<>();
    private Long jobOfferId = 1L;

    @Override
    public Optional<Offer> findOfferById(Long id) {


        return jobOffersList.entrySet().stream()
                .filter(x -> x.getKey().equals(id)).findFirst()
                .map(Map.Entry::getValue);

        /*return Optional.of(jobOffersList.get(id.intValue() -1));*/
    }

    @Override
    public List<Offer> findAllOffers() {
        return jobOffersList.values().stream().toList();
    }

    @Override
    public Offer save(Offer offer) {
        Offer offerToSave = Offer.builder()
                .id(jobOfferId)
                .companyName(offer.getCompanyName())
                .position(offer.getPosition())
                .minSalary(offer.getMinSalary())
                .maxSalary(offer.getMaxSalary())
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
