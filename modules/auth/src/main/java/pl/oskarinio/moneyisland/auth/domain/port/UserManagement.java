package pl.oskarinio.moneyisland.auth.domain.port;


import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.dto.RefreshToken;

import java.util.List;

public interface UserManagement {
    void generateAndSetTokens(UserServiceData userServiceData);
    RefreshToken getRefreshToken(String refreshTokenString);
    String prepareErrorMessage(List<String> errorsMessageList);
    User getUserByUsernameOrThrow(String username);
    void deleteToken(String username);
    void setRefreshToken(User user, RefreshToken refreshToken);
}
