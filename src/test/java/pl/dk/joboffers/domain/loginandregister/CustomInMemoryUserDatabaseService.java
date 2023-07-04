package pl.dk.joboffers.domain.loginandregister;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class CustomInMemoryUserDatabaseService implements UserRepository{

    Long userId = 1L;
    Set<User> users = new HashSet<>();

    public User save(User user) {
        User savedUser = User.builder()
                .id(userId)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        users.add(savedUser);
        userId++;
        return savedUser;
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }
}
