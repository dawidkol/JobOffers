package pl.dk.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;
import pl.dk.joboffers.domain.loginandregister.exceptions.UserNotFoundException;


@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserDto findByUsername(String username) {
       return userRepository.findByUsername(username)
               .map(userDtoMapper::mapFromUser)
               .orElseThrow(UserNotFoundException::new);
    }

    public UserDto registerUser(UserRegistrationDto userRegistrationDto) {
        User userToSave = userDtoMapper.mapFromUserRegistrationDto(userRegistrationDto);
        User savedUser = userRepository.save(userToSave);
        return userDtoMapper.mapFromUser(savedUser);
    }
}




