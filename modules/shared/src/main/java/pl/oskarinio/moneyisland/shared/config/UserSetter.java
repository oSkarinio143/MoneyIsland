package pl.oskarinio.moneyisland.shared.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.util.Base64;

@ControllerAdvice
public class UserSetter {

    @Value("${jwt.secret.base64}")
    private String secretKeyString;
    private SecretKey secretKey;
    private final String COOKIE_ACCESS_TOKEN = "accessToken";

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyString));
    }

    @ModelAttribute
    public void setLoggedUser(HttpServletRequest request, Model model){
        Cookie cookieAccess = WebUtils.getCookie(request, COOKIE_ACCESS_TOKEN);
        if(cookieAccess == null || cookieAccess.getValue().isEmpty())
            return;

        String accessTokenCookie = cookieAccess.getValue();
        String username = extractUsername(accessTokenCookie);

        if(username == null || username.isEmpty())
            return;

        model.addAttribute("currentUsername", username);
    }

    public String extractUsername(String token) {
        String username = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }
}
