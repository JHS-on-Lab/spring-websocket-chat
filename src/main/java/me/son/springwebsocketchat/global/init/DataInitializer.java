package me.son.springwebsocketchat.global.init;

import lombok.RequiredArgsConstructor;
import me.son.springwebsocketchat.user.domain.entity.User;
import me.son.springwebsocketchat.user.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .role("ROLE_USER")
                    .build();

            userRepository.save(admin);
        }
    }
}
