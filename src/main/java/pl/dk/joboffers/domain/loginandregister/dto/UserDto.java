package pl.dk.joboffers.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserDto (String username, String password){
}
