package pl.dk.joboffers.infrastructure.security.jwt;

import lombok.Builder;

@Builder
public record JwtTokenDto(String username, String token) {
}
