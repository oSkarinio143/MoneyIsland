package pl.oskarinio.moneyisland.gateway.web;

import lombok.Getter;

@Getter
public class Route {
    public static final String REDIRECT = "redirect:http://localhost:8080/oskarinio143/moneyisland";
    public static final String MAIN = "/oskarinio143/moneyisland";
    public static final String USER = "/user";
    public static final String ADMIN = "/admin";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String ERROR = "/error";
    public static final String FINANCE = "/finance";
    public static final String INVESTMENT = "/investment";

}
