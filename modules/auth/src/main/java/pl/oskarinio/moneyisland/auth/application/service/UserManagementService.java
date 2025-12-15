package pl.oskarinio.moneyisland.auth.application.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.port.Token;
import pl.oskarinio.moneyisland.auth.domain.port.UserManagement;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.auth.domain.service.UserManagementDomainService;
import pl.oskarinio.moneyisland.auth.domain.dto.RefreshToken;

import java.time.Clock;
import java.util.List;

@Service
@Slf4j
public class UserManagementService implements UserManagement {
    private final UserManagementDomainService userManagementDomainService;

    public UserManagementService(UserRepository userRepository,
                                 Token token,
                                 Clock clock,
                                 @Value("${token.access.seconds}") long accessSeconds,
                                 @Value("${token.refresh.seconds}") long tokenSeconds){
        this.userManagementDomainService = new UserManagementDomainService(userRepository, token, clock, accessSeconds, tokenSeconds);
    }

    @Override
    public void generateAndSetTokens(UserServiceData userServiceData) {
        log.debug("Generuję i ustawiam tokeny dla użytkownika. Nazwa = {}", userServiceData.getUsername());
        userManagementDomainService.generateAndSetTokens(userServiceData);
    }

    @Override
    public RefreshToken getRefreshToken(String refreshTokenString) {
        log.debug("Pobieram refresh token");
        return userManagementDomainService.getRefreshToken(refreshTokenString);
    }

    @Override
    public String prepareErrorMessage(List<String> errorsMessageList) {
        log.debug("Przygotowuję wiadomość błędu dla użytkownika");
        return userManagementDomainService.prepareErrorMessage(errorsMessageList);
    }

    @Override
    public User getUserByUsernameOrThrow(String username) {
        log.trace("Pobieram użytkownika z bazy. Nazwa = {}", username);
        return userManagementDomainService.getUserByUsernameOrThrow(username);
    }

    @Transactional
    @Override
    public void deleteToken(String username) {
        log.debug("Usuwam token użytkownika. Nazwa = {}", username);
        userManagementDomainService.deleteToken(username);
    }

    @Transactional
    @Override
    public void setRefreshToken(User user, RefreshToken refreshToken) {
        log.debug("Ustawiam refresh token dla użytkownika. Nazwa = {}", user.getUsername());
        userManagementDomainService.setRefreshToken(user, refreshToken);
    }
}
