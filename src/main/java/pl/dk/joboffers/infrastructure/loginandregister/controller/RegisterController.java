package pl.dk.joboffers.infrastructure.loginandregister.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.dk.joboffers.domain.loginandregister.LoginAndRegisterFacade;
import pl.dk.joboffers.domain.loginandregister.dto.UserDto;
import pl.dk.joboffers.domain.loginandregister.dto.UserDtoWithoutPassword;

import java.net.URI;

@RestController
@AllArgsConstructor
public class RegisterController {
    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDtoWithoutPassword> register(@Valid @RequestBody UserDto userDto) {
        UserDto userToRegister = createUserToSaveWithEncodedPassword(userDto);
        UserDtoWithoutPassword registeredUserDto = loginAndRegisterFacade.registerUser(userToRegister);
        URI savedUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/user/{username}")
                .buildAndExpand(registeredUserDto)
                .toUri();
        return ResponseEntity.created(savedUri).body(registeredUserDto);

    }

    private UserDto createUserToSaveWithEncodedPassword(UserDto userDto) {
        return UserDto.builder()
                .username(userDto.username())
                .password(passwordEncoder.encode(userDto.password()))
                .build();
    }

}
