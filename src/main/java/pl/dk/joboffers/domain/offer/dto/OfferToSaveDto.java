package pl.dk.joboffers.domain.offer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;

@Builder
public record OfferToSaveDto(@NotNull
                             @NotEmpty
                             String title,
                             @NotNull
                             @NotEmpty
                             String company,
                             String salary,
                             @NotNull
                             @NotEmpty
                             @URL String offerUrl) {
}
