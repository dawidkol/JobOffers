package pl.dk.joboffers.domain.offer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
record Offer(
        @Id
        String id,
        String title,
        String company,
        String salary,
        @Indexed(unique = true)
        String offerUrl
)  {
}
