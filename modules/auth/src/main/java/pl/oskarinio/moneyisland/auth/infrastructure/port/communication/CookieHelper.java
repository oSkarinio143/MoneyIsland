package pl.oskarinio.moneyisland.auth.infrastructure.port.communication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.oskarinio.moneyisland.auth.domain.model.user.UserServiceData;

public interface CookieHelper {
    void setCookieTokens(UserServiceData loginServiceData, HttpServletResponse response);
    String getUsernameFromCookie(HttpServletRequest request);
    void removeAccessCookie(HttpServletResponse response);
    void removeRefreshCookie(HttpServletResponse response);
    void clearCookies(HttpServletResponse response, HttpServletRequest request);
}
