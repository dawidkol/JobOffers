package pl.dk.joboffers.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.dk.joboffers.domain.loginandregister.dto.UserDto;

import java.util.Date;


@RequiredArgsConstructor
@Service
@Log4j2
public class JwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long expiration;

    public JwtTokenDto authenticateAndGenerateToken(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password())
        );

        User user = (User) authentication.getPrincipal();
        String token = createToken(user);
        String username = user.getUsername();

        return JwtTokenDto.builder()
                .username(username)
                .token(token)
                .build();
    }

    private String createToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(algorithm);
    }
}
