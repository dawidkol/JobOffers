package pl.dk.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;
import pl.dk.joboffers.domain.loginandregister.dto.UserDto;
import pl.dk.joboffers.domain.loginandregister.dto.UserDtoWithoutPassword;

@AllArgsConstructor
class UserDtoMapper {

    User map(UserDto userDto) {
        return User.builder()
                .username(userDto.username())
                .password(userDto.password())
                .build();
    }

    UserDtoWithoutPassword mapToUserWithoutPassword(User user) {
        return UserDtoWithoutPassword.builder()
                .id(user.id())
                .username(user.username())
                .build();
    }

    UserDto map(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }




}
