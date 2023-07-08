package pl.dk.joboffers.domain.loginandregister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoginAndFacadeConfig {
    @Bean
    LoginAndRegisterFacade loginAndRegisterFacade(UserRepository userRepository) {
        UserDtoMapper userDtoMapper = new UserDtoMapper();
        return new LoginAndRegisterFacade(userRepository, userDtoMapper);
    }
}
