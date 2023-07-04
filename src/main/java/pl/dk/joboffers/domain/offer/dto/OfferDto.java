package pl.dk.joboffers.domain.offer.dto;


import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OfferDto {

    private String title;
    private String company;
    private String salary;
    private String offerUrl;
}
