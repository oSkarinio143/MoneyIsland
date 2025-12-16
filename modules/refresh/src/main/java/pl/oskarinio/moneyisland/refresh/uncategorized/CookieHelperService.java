//package pl.oskarinio.moneyisland.auth.uncategorized;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;
//import pl.oskarinio.moneyisland.auth.domain.port.Token;
//import pl.oskarinio.moneyisland.auth.infrastructure.service.CookieManager;
//
//@Slf4j
//@Service
//public class CookieHelperService implements CookieHelper {
//    private final CookieManager cookieManager;
//
//    public CookieHelperService(Token token,
//                               @Value("${token.access.seconds}") long accessSeconds,
//                               @Value("${token.refresh.seconds}") long refreshSeconds) {
//        this.cookieManager = new CookieManager(token, accessSeconds, refreshSeconds);
//    }
//
//    @Override
//    public void setCookieTokens(UserServiceData loginServiceData, HttpServletResponse response) {
//        log.debug("Ustawiam tokeny w cookie dla uzytkownika. Nazwa = {}", loginServiceData.getUsername());
//        cookieManager.setCookieTokens(loginServiceData,response);
//    }
//
//    @Override
//    public String getUsernameFromCookie(HttpServletRequest request) {
//        String username = cookieManager.getUsernameFromCookie(request);
//        log.trace("Pobieram nazwe uzytkownika z cookies. Nazwa = {}", username);
//        return username;
//    }
//
//    @Override
//    public void removeAccessCookie(HttpServletResponse response) {
//        log.debug("Usuwam access cookie");
//        cookieManager.removeAccessCookie(response);
//    }
//
//    @Override
//    public void removeRefreshCookie(HttpServletResponse response) {
//        log.debug("Usuwam refresh cookie");
//        cookieManager.removeRefreshCookie(response);
//    }
//
//    @Override
//    public void clearCookies(HttpServletResponse response, HttpServletRequest request) {
//        log.debug("Czyszcze wszystkie cookies");
//        cookieManager.clearCookies(response,request);
//    }
//}
