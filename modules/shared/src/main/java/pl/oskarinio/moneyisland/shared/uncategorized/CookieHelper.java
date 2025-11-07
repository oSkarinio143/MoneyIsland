package pl.oskarinio.moneyisland.shared.uncategorized;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CookieHelper {
    void setCookieTokens(UserServiceData loginServiceData, HttpServletResponse response);
    String getUsernameFromCookie(HttpServletRequest request);
    void removeAccessCookie(HttpServletResponse response);
    void removeRefreshCookie(HttpServletResponse response);
    void clearCookies(HttpServletResponse response, HttpServletRequest request);
}
