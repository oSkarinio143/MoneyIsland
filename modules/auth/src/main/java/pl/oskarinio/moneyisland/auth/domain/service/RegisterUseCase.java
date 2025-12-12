package pl.oskarinio.moneyisland.auth.domain.service;


import pl.oskarinio.moneyisland.auth.domain.port.PasswordEncoderPort;
import pl.oskarinio.moneyisland.auth.application.port.different.UserManagement;
import pl.oskarinio.moneyisland.zzzzzzzz.auth.RegisterForm;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.shared.uncategorized.RefreshToken;
import pl.oskarinio.moneyisland.shared.uncategorized.Role;
import pl.oskarinio.moneyisland.shared.uncategorized.User;
import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class RegisterUseCase {
    private final UserRepository userRepository;
    private final UserManagement userManagement;
    private final PasswordEncoderPort passwordEncoderPort;
    private final Clock clock;
    private final long TOKEN_REFRESH_SECONDS;

    public RegisterUseCase(UserRepository userRepository, UserManagement userManagement, PasswordEncoderPort passwordEncoderPort, Clock clock, long refreshSeconds) {
        this.userRepository = userRepository;
        this.userManagement = userManagement;
        this.passwordEncoderPort = passwordEncoderPort;
        this.clock = clock;
        TOKEN_REFRESH_SECONDS = refreshSeconds;
    }

    public UserServiceData registerUser(RegisterForm registerForm){
        UserServiceData userServiceData = getUserServiceData(registerForm);
        userManagement.generateAndSetTokens(userServiceData);
        saveData(userServiceData);
        return userServiceData;
    }

    private UserServiceData getUserServiceData(RegisterForm registerForm){
        String hashedPassword = passwordEncoderPort.encode(registerForm.getPassword());
        UserServiceData userServiceData = new UserServiceData(registerForm.getUsername(), registerForm.getEmail(), hashedPassword);
        userServiceData.addRole(Role.ROLE_USER);
        return userServiceData;
    }

    private void saveData(UserServiceData userServiceData){
        Instant now = Instant.now(clock);
        RefreshToken refreshToken = new RefreshToken(userServiceData.getRefreshToken(), now, now.plus(TOKEN_REFRESH_SECONDS, ChronoUnit.SECONDS));
        User user = new User(userServiceData.getUsername(), userServiceData.getEmail(), userServiceData.getPassword(), now);

        user.setRoles(userServiceData.getRoles());
        userManagement.setRefreshToken(user, refreshToken);
        userRepository.save(user);
    }
}
