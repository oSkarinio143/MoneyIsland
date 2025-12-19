package pl.oskarinio.moneyisland.auth.infrastructure.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.domain.port.PasswordEncoderPort;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.shared.domain.Role;

import java.time.Clock;
import java.time.Instant;

@Component
@Slf4j
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final Clock clock;
    private final String adminUsername;
    private final String adminEmail;
    private final String adminPassword;

    public AdminInitializer(UserRepository userRepository,
                            PasswordEncoderPort passwordEncoder,
                            Clock clock,
                            @Value("${app.security.admin-username:adminUser}") String adminUsername,
                            @Value("${app.security.admin-email:admin@pl}") String adminEmail,
                            @Value("${app.security.admin-password:1234}") String adminPassword) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.clock = clock;
        this.adminUsername = adminUsername;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }


    public void initializeProfile() {
        log.info("Inicjalizuję profil...");
        if (adminUsername == null || adminUsername.isBlank() || adminPassword == null || adminPassword.isBlank()) {
            log.warn("Brak danych administratora – pomijam inicjalizację.");
            return;
        }
        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            log.info("Tworzę konto administratora. Nazwa = {}", adminUsername);
            User adminUser = new User();
            adminUser.setUsername(adminUsername);
            adminUser.setEmail(adminEmail);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));
            adminUser.setRegistrationDate(Instant.now(clock));
            adminUser.addRole(Role.ROLE_USER);
            adminUser.addRole(Role.ROLE_ADMIN);
            userRepository.save(adminUser);
            log.info("Konto administratora utworzone. Nazwa = {}", adminUsername);
        } else {
            log.debug("Konto administratora już istnieje. Nazwa = {}", adminUsername);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        initializeProfile();
    }
}