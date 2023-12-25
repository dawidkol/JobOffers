package pl.dk.joboffers.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserDtoWithoutPassword(String id, String username) {
}
