package pl.oskarinio.moneyisland.auth.domain.port;


import pl.oskarinio.moneyisland.shared.uncategorized.RefreshToken;
import pl.oskarinio.moneyisland.shared.uncategorized.User;
import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

import java.util.List;

public interface UserManagement {
    void generateAndSetTokens(UserServiceData userServiceData);
    RefreshToken getRefreshToken(String refreshTokenString);
    String prepareErrorMessage(List<String> errorsMessageList);
    User getUserByUsernameOrThrow(String username);
    void deleteToken(String username);
    void setRefreshToken(User user, RefreshToken refreshToken);
}
