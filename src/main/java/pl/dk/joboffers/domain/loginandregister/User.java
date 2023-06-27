package pl.dk.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
