package pl.oskarinio.moneyisland.auth.infrastructure.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.port.Token;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class CookieManager {
    private final Token token;

    @Value("${token.access.seconds}")
    private long tokenAccessSeconds;

    @Value("${token.refresh.seconds}")
    private long tokenRefreshSeconds;

    public CookieManager(Token token) {
        this.token = token;
    }

    public void setCookieTokens(UserServiceData loginServiceData, HttpServletResponse response){
        log.debug("Ustawiam tokeny w cookie dla uzytkownika. Nazwa = {}", loginServiceData.getUsername());
        ResponseCookie accessCookie = createAccessCookie(loginServiceData.getAccessToken());
        ResponseCookie refreshCookie = createRefreshCookie(loginServiceData.getRefreshToken());
        setCookies(accessCookie, refreshCookie, response);
    }

    private ResponseCookie createAccessCookie(String token){
        return ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .path("/")
                .maxAge(tokenAccessSeconds)
                .secure(true)
                .sameSite("Lax")
                .build();
    }

    private ResponseCookie createRefreshCookie(String token){
        return ResponseCookie.from("refreshToken", token)
                .httpOnly(true)
                .path("/")
                .maxAge(tokenRefreshSeconds)
                .secure(true)
                .sameSite("Strict")
                .build();
    }

    private void setCookies(ResponseCookie accessCookie, ResponseCookie refreshCookie, HttpServletResponse response){
        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    public String getUsernameFromCookie(HttpServletRequest request){
        log.trace("Pobieram nazwe uzytkownika z cookies");
        Cookie cookie = WebUtils.getCookie(request, "refreshToken");
        String refreshToken = cookie.getValue();
        return token.extractUsername(refreshToken);
    }

    public void removeAccessCookie(HttpServletResponse response){
        log.debug("Usuwam access cookie");
        Cookie cookie = new Cookie("accessToken", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void removeRefreshCookie(HttpServletResponse response){
        log.debug("Usuwam refresh cookie");
        Cookie cookie = new Cookie("refreshToken", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void clearCookies(HttpServletResponse response, HttpServletRequest request){
        log.debug("Czyszcze wszystkie cookies");
        List<Cookie> cookies = Arrays.asList(request.getCookies());
        cookies.forEach(cookie -> {
            Cookie toDelete = new Cookie(cookie.getName(), "");
            toDelete.setPath("/");
            toDelete.setMaxAge(0);
            response.addCookie(toDelete);
        });
    }
}
