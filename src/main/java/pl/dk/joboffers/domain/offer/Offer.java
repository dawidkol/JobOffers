package pl.dk.joboffers.domain.offer;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
class Offer {

    private Long id;
    private String company;
    private String title;
    private String salary;
    private String offerUrl;

}
