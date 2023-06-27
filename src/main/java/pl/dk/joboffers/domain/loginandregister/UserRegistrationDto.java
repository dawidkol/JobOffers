package pl.dk.joboffers.domain.loginandregister;

import lombok.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Builder
@Getter
@AllArgsConstructor
class UserRegistrationDto {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
