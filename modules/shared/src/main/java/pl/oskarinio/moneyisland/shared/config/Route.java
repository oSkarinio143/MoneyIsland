package pl.oskarinio.moneyisland.shared.config;

import lombok.Getter;

@Getter
public class Route {
    public static final String VIEW_MAIN_PANEL = "/mainPanel";
    public static final String VIEW_SHOW_USERS = "/showUsers";
    public static final String VIEW_DELETE_USER = "/deleteUser";
    public static final String VIEW_GRANT_ADMIN = "/grantAdmin";

    public static final String REDIRECT = "redirect:http://localhost:8080/oskarinio143/moneyisland";
    public static final String MAIN = "/oskarinio143/moneyisland";
    public static final String FAVICON = "/favicon.ico";
    public static final String USER = "/user";
    public static final String ADMIN = "/admin";

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String REFRESH ="/refresh";

    public static final String USERS_LIST = "users";
    public static final String SHOW_USERS_LIST = "/users";
    public static final String GRANT = "/grant";
    public static final String DELETE = "/delete";

    public static final String FINANCE = "/finance";
    public static final String INVESTMENT = "/investment";
    public static final String BALANCE = "/balance";
    public static final String ADD = "/add";
    public static final String MODIFY = "/modify";
    public static final String HISTORY = "/history";
    public static final String GOAL = "/goal";



    public static final String VIEW_BALANCE = "balance";
    public static final String VIEW_FINANCE = "finance";
    public static final String VIEW_HISTORY = "history";
    public static final String VIEW_GOAL = "goal";

    public static final String ERROR = "/error";
    public static final String SHOW = "/show";
}
