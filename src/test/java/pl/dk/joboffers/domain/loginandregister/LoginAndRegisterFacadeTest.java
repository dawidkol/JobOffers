package pl.dk.joboffers.domain.loginandregister;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dk.joboffers.domain.loginandregister.dto.UserDto;
import pl.dk.joboffers.domain.loginandregister.dto.UserDtoWithoutPassword;

import java.util.Optional;

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
        UserDto userRegistrationDto = UserDto.builder()
                .username("JK23")
                .password("hardPass")
                .build();


        //when
        UserDtoWithoutPassword registeredUser = loginAndRegisterFacade.registerUser(userRegistrationDto);

        //then
        assertAll(
                () -> assertThat(registeredUser).isNotNull(),
                () -> assertThat(registeredUser).isInstanceOfAny(UserDtoWithoutPassword.class)
        );
    }
    @Test
    void shouldFindUserByUsername() {
        UserDto userRegistrationDto = UserDto.builder()
                .username("ADMA321")
                .password("hardPass")
                .build();
        String usernameNotInDb = "Jan";

        UserDtoWithoutPassword registeredUser = loginAndRegisterFacade.registerUser(userRegistrationDto);
        Optional<UserDtoWithoutPassword> findByUsername = loginAndRegisterFacade.findByUsername(userRegistrationDto.username());

        assertAll(
                () -> assertThat(findByUsername).isNotEmpty(),
                () -> assertThat(findByUsername.get()).isEqualTo(registeredUser)
        );
    }





}