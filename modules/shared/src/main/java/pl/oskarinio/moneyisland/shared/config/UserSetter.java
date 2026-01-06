package pl.oskarinio.moneyisland.shared.config;

import io.jsonwebtoken.Claims;
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
import pl.oskarinio.moneyisland.shared.domain.Role;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;

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
        extractRole(accessTokenCookie);

        if(username == null || username.isEmpty())
            return;

        model.addAttribute("currentUsername", username);
        model.addAttribute("role", getPriorRole(accessTokenCookie));
    }

    @ModelAttribute
    public void setCurrentUrl(HttpServletRequest request, Model model){
        String requestURI = request.getRequestURI();
        System.out.println("ustawiam reque" + requestURI);
        model.addAttribute("requestUrl", requestURI);
    }

    private String extractUsername(String token) {
        String username = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }

    private String getPriorRole(String token){
        List<String> rolesList = extractRole(token);
        if(rolesList.contains(Role.ROLE_ADMIN.toString()))
            return Role.ROLE_ADMIN.toString();
        return Role.ROLE_USER.toString();
    }

    private List<String> extractRole(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles", List.class);
    }
}
