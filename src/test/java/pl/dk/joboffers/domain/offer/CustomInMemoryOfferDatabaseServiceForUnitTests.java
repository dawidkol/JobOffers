package pl.dk.joboffers.domain.offer;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
class CustomInMemoryOfferDatabaseServiceForUnitTests implements OfferRepository {

    Map<String, Offer> jobOffersList = new HashMap<>();
    private Long jobOfferId = 1L;

    @Override
    public <S extends Offer> S save(S entity) {
        Offer offerToSave = Offer.builder()
                .company(entity.company())
                .title(entity.title())
                .salary(entity.salary())
                .offerUrl(entity.offerUrl())
                .build();
        jobOffersList.put(String.valueOf(jobOfferId), offerToSave);
        jobOfferId++;
        return (S) offerToSave;
    }


    @Override
    public long count() {
        return jobOffersList.size();
    }

    @Override
    public List<Offer> findAll() {
        return jobOffersList.values().stream().toList();
    }

    @Override
    public Optional<Offer> findById(String s) {
        return jobOffersList.entrySet().stream()
                .filter(x -> x.getKey().equals(s)).findFirst()
                .map(Map.Entry::getValue);
    }

    @Override
    public <S extends Offer> List<S> saveAll(Iterable<S> entities) {
        Iterator<S> iterator = entities.iterator();
        while (iterator.hasNext()) {
            jobOffersList.put(String.valueOf(jobOfferId), iterator.next());
            jobOfferId++;
        }
        System.out.println(jobOffersList.size());
        return (List<S>) new ArrayList<>(jobOffersList.values());

    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Offer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Offer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Offer> entities) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public <S extends Offer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Offer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Offer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Offer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Offer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Offer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return null;
    }
}





