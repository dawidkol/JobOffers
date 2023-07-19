package pl.dk.joboffers.domain.loginandregister;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dk.joboffers.domain.loginandregister.exceptions.UserNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class LoginAndRegisterFacadeTest {

    private LoginAndRegisterFacade loginAndRegisterFacade;
    @BeforeEach
    void init() {
        UserRepository userRepository = new CustomInMemoryUserDatabaseServiceForUnitTests();
        UserDtoMapper userDtoMapper = new UserDtoMapper();
        loginAndRegisterFacade = new LoginAndRegisterFacade(userRepository, userDtoMapper);
    }
    @Test
    void shouldRegisterUser() {
        //given
        UserRegistrationDto userRegistrationDto = UserRegistrationDto.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .username("JK23")
                .password("hardPass")
                .build();


        //when
        UserDto registeredUser = loginAndRegisterFacade.registerUser(userRegistrationDto);

        //then
        assertAll(
                () -> assertThat(registeredUser).isNotNull(),
                () -> assertThat(registeredUser).isInstanceOfAny(UserDto.class)
        );
    }
    @Test
    void shouldFindUserByUsername() {
        UserRegistrationDto userRegistrationDto = UserRegistrationDto.builder()
                .firstName("Adam")
                .lastName("Malinowski")
                .username("ADMA321")
                .password("hardPass")
                .build();
        String usernameNotInDb = "Jan";

        UserDto registeredUser = loginAndRegisterFacade.registerUser(userRegistrationDto);
        UserDto findByUsername = loginAndRegisterFacade.findByUsername(userRegistrationDto.getUsername());

        assertAll(
                () -> assertThat(findByUsername).isNotNull(),
                () -> assertThat(findByUsername).isEqualTo(registeredUser),
                () -> Assertions.assertThrows(UserNotFoundException.class, () -> {
                    loginAndRegisterFacade.findByUsername(usernameNotInDb);
                }
                )
        );
    }





}