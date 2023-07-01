package pl.dk.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class LoginAndRegisterFacade {
    public static final String USER_NOT_FOUND = "User not found";


    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;


    public UserDto findByUsername(String username) {
       return userRepository.findByUsername(username)
               .map(userDtoMapper::mapFromUser)
               .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }


    public UserDto registerUser(UserRegistrationDto userRegistrationDto) {
        User userToSave = userDtoMapper.mapFromUserRegistrationDto(userRegistrationDto);
        User savedUser = userRepository.save(userToSave);
        return userDtoMapper.mapFromUser(savedUser);
    }


}




