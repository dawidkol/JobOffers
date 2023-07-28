package pl.dk.joboffers.infrastructure.offer.controller.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;
@Builder
public record ErrorResponse(HttpStatus status, String message) {
}
