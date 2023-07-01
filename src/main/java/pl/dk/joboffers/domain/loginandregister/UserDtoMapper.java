package pl.dk.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class UserDtoMapper {

     User mapFromUserRegistrationDto(UserRegistrationDto userRegistrationDto) {
        return User.builder()
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .username(userRegistrationDto.getUsername())
                .password(userRegistrationDto.getPassword())
                .build();
    }

    UserDto mapFromUser(User user) {
         return UserDto.builder()
                 .firstName(user.getFirstName())
                 .lastName(user.getLastName())
                 .username(user.getUsername())
                 .build();
    }

}
