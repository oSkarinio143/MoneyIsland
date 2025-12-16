//package pl.oskarinio.moneyisland.gateway.routes.unused.refresh;
//
//import lombok.NonNull;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.WebUtils;
//import pl.oskarinio.moneyisland.gateway.routes.controller.Route;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
////Zadanie tego filtra to podstawowe sprawdzenie które przepuści 99% przypadków, gdy nieprzepuści, przekierowanie do prawdziwego refeshFilter
//@Component
//public class BasicRefreshFilter extends OncePerRequestFilter {
//    private final Token token;
//
//    private static final long TOKEN_ACCESS_SECONDS = 900;
//    private static final long TOKEN_REFRESH_SECONDS = 604800;
//
//    private static final List<String> PUBLIC_PATHS = new ArrayList<>(List.of(
//            Route.MAIN + Route.LOGIN,
//            Route.MAIN + Route.REGISTER));
//
//    public BasicRefreshFilter(Token token){
//        this.token = token;
//    }
//
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
//        if (continueIfPublicPath(request, response, filterChain))
//            return;
//
//        Cookie cookieAccess = WebUtils.getCookie(request, "accessToken");
//        String tokenAccess = getTokenAccess(cookieAccess);
//
//        if (continueIfAccessTokenGood(cookieAccess, tokenAccess, request, response, filterChain))
//            return;
