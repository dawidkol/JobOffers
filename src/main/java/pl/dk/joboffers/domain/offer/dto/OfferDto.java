package pl.dk.joboffers.domain.offer.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OfferDto {

    private String id;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String company;
    private String salary;
    @NotNull
    @NotEmpty
    @URL
    private String offerUrl;
}
