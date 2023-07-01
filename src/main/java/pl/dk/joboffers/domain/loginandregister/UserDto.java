package pl.dk.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    /*private String password;*/
}
