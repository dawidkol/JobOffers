package pl.dk.joboffers.infrastructure.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.dk.joboffers.domain.loginandregister.LoginAndRegisterFacade;
import pl.dk.joboffers.domain.loginandregister.dto.UserDto;

@Service
@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginAndRegisterFacade.findCredentialsByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new BadCredentialsException("Username not exists in database"));
    }

    private UserDetails createUserDetails(UserDto userDto) {
        return User.builder()
                .username(userDto.username())
                .password(userDto.password())
                .build();
    }
}
