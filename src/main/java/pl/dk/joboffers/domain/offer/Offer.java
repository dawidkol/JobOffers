package pl.dk.joboffers.domain.offer;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Document
@NoArgsConstructor
class Offer {

    @Id
    private String id;
    private String title;
    private String company;
    private String salary;
    @Indexed(unique = true)
    private String offerUrl;

}
