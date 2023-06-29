package pl.dk.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
class Offer {

    private Long id;
    private String companyName;
    private String position;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String offerUrl;


}
