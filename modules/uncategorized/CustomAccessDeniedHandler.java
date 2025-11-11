package pl.oskarinio.moneyisland.shared.uncategorized;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

//Automatyczne przekierowania dla użytkówników próbujących wejść na endpoint dla admina
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException{
        request.setAttribute("adminErrorMessage", "Nie posiadasz uprawnień administratorskich");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.sendRedirect(Route.MAIN);
    }
}