package pl.dk.joboffers.infrastructure.loginandregister.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dk.joboffers.domain.loginandregister.dto.UserDto;
import pl.dk.joboffers.infrastructure.security.jwt.JwtAuthenticatorFacade;
import pl.dk.joboffers.infrastructure.security.jwt.JwtTokenDto;

@RestController
@AllArgsConstructor
public class TokenController {

    private final JwtAuthenticatorFacade jwtAuthenticator;

    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JwtTokenDto> getToken (@Valid @RequestBody UserDto userDto) {
        JwtTokenDto jwtTokenDto = jwtAuthenticator.authenticateAndGenerateToken(userDto);
        return ResponseEntity.ok(jwtTokenDto);
    }



}
