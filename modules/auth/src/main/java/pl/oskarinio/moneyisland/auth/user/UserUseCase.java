package pl.oskarinio.moneyisland.auth.user;


//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.oskarinio.moneyisland.shared.uncategorized.RefreshToken;
import pl.oskarinio.moneyisland.shared.uncategorized.Token;
import pl.oskarinio.moneyisland.shared.uncategorized.User;
import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class UserUseCase {

    private final UserRepository userRepository;
    private final Token token;
    private final Clock clock;
    private final long TOKEN_ACCESS_SECONDS;
    private final long TOKEN_REFRESH_SECONDS;

    public UserUseCase(UserRepository userRepository, Token token, Clock clock, long accessSeconds, long refreshSeconds) {
        this.userRepository = userRepository;
        this.token = token;
        this.clock = clock;
        this.TOKEN_ACCESS_SECONDS = accessSeconds;
        this.TOKEN_REFRESH_SECONDS = refreshSeconds;
    }

    public void generateAndSetTokens(UserServiceData userServiceData){
        userServiceData.setAccessToken(token.generateToken(userServiceData, TOKEN_ACCESS_SECONDS));
        userServiceData.setRefreshToken(token.generateToken(userServiceData, TOKEN_REFRESH_SECONDS));
    }

    public RefreshToken getRefreshToken(String refreshTokenString){
        Instant now = Instant.now(this.clock);
        return new RefreshToken(refreshTokenString, now, now.plus(TOKEN_REFRESH_SECONDS, ChronoUnit.SECONDS));
    }

    public String prepareErrorMessage(List<String> errorsMessageList){
        StringBuilder errorMessage = new StringBuilder();
        errorsMessageList.forEach(message -> errorMessage.append(message + "<br>"));
        return errorMessage.toString();
    }

    public User getUserByUsernameOrThrow(String username){
//        return userRepository.findByUsername(username)
//                .orElseThrow(() ->new UsernameNotFoundException(username));
        return new User();
    }

    public void deleteToken(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.ifPresent(user -> setRefreshToken(user, null));
        userRepository.save(userOptional.get());
    }

    public void setRefreshToken(User user, RefreshToken refreshToken){
        if(refreshToken == null && user.getRefreshToken() != null) {
            user.setRefreshToken(null);
        }
        else if (user.getRefreshToken() != refreshToken) {
            user.setRefreshToken(refreshToken);
        }
    }
}
