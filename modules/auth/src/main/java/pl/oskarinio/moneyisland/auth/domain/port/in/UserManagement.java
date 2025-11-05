package pl.oskarinio.moneyisland.auth.domain.port.in;


import pl.oskarinio.moneyisland.auth.domain.model.user.RefreshToken;
import pl.oskarinio.moneyisland.auth.domain.model.user.User;
import pl.oskarinio.moneyisland.auth.domain.model.user.UserServiceData;

import java.util.List;

public interface UserManagement {
    void generateAndSetTokens(UserServiceData userServiceData);
    RefreshToken getRefreshToken(String refreshTokenString);
    String prepareErrorMessage(List<String> errorsMessageList);
    User getUserByUsernameOrThrow(String username);
    void deleteToken(String username);
    void setRefreshToken(User user, RefreshToken refreshToken);
}
