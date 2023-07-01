package pl.dk.joboffers.domain.offer;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@EqualsAndHashCode
class OfferDto {
    private String companyName;
    private String position;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String offerUrl;
}
