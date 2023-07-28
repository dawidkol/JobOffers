package pl.dk.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;
import pl.dk.joboffers.domain.loginandregister.dto.UserDto;
import pl.dk.joboffers.domain.loginandregister.dto.UserDtoWithoutPassword;

import java.util.Optional;


@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public Optional<UserDtoWithoutPassword> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userDtoMapper::mapToUserWithoutPassword);
    }

    public Optional<UserDto> findCredentialsByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userDtoMapper::map);
    }

    public UserDtoWithoutPassword registerUser(UserDto userDto) {
        User userToSave = userDtoMapper.map(userDto);
        User savedUser = userRepository.save(userToSave);
        return userDtoMapper.mapToUserWithoutPassword(savedUser);
    }
}




