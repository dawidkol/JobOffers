package pl.dk.joboffers.domain.offer.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;

import java.util.Objects;

@Builder
public record OfferDto(String id,
                       @NotNull
                       @NotEmpty
                       String title,
                       @NotNull
                       @NotEmpty
                       String company, String salary,
                       @NotNull
                       @NotEmpty
                       @URL String offerUrl) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferDto offerDto = (OfferDto) o;
        return Objects.equals(title, offerDto.title) && Objects.equals(company, offerDto.company) && Objects.equals(salary, offerDto.salary) && Objects.equals(offerUrl, offerDto.offerUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, company, salary, offerUrl);
    }
}
