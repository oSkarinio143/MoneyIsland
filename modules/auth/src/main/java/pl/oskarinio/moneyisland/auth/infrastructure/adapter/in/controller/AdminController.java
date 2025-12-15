package pl.oskarinio.moneyisland.auth.infrastructure.adapter.in.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarinio.moneyisland.auth.application.port.DeleteUserUseCase;
import pl.oskarinio.moneyisland.auth.application.port.GrantAdminRoleUseCase;
import pl.oskarinio.moneyisland.auth.application.port.GetUserListUseCase;
import pl.oskarinio.moneyisland.shared.config.Route;
import pl.oskarinio.moneyisland.shared.uncategorized.CookieHelper;
import pl.oskarinio.moneyisland.shared.uncategorized.User;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(Route.MAIN + Route.ADMIN)
public class AdminController {
    private final GetUserListUseCase showUserList;
    private final GrantAdminRoleUseCase grantAdminRole;
    private final DeleteUserUseCase deleteUser;
    private final CookieHelper cookieHelper;

    @Value("${app.security.admin-username:}")
    private String adminUsername;

    public AdminController(GetUserListUseCase showUserList, GrantAdminRoleUseCase grantAdminRole, DeleteUserUseCase deleteUserUseCase, CookieHelper cookieHelper) {
        this.showUserList = showUserList;
        this.grantAdminRole = grantAdminRole;
        this.deleteUser = deleteUserUseCase;
        this.cookieHelper = cookieHelper;
    }

    //Dostarcza liste uzytkownikow kazdej metodzie w klasie
    @ModelAttribute(Route.USERS_LIST)
    public List<User> addUsersListModel(){
        List<User> userList = showUserList.getUsersList();
        return showUserList.getUsersList();
    }

    @GetMapping
    public String choseAdminOption(){
        log.info("Uzytkownik administrator w panelu admina");
        return Route.PACKAGE_ADMIN + Route.ADMIN;
    }

    @GetMapping(Route.SHOW)
    public String showUsers(Model model){
        log.info("Admin wyswietla liste uzytkownikow");
        return Route.PACKAGE_ADMIN + Route.VIEW_SHOW_USERS;
    }

    @GetMapping(Route.DELETE)
    public String deleteUserView(Model model, HttpServletRequest request){
        log.info("Admin wybiera uzytkownika do usuniecia");
        model.addAttribute("adminUsername", adminUsername);
        model.addAttribute("thisUsername", cookieHelper.getUsernameFromCookie(request));
        return Route.PACKAGE_ADMIN + Route.VIEW_DELETE_USER;
    }

    @PostMapping(Route.DELETE)
    public String deleteUser(@RequestParam String username){
        deleteUser.deleteUser(username);
        log.info("Admin usunal uzytkownika");
        return Route.REDIRECT + Route.ADMIN + Route.DELETE;
    }

    @GetMapping(Route.GRANT)
    public String grantAdmin(){
        log.info("Admin wybiera uzytkownika do nadania uprawnien administracyjnych");
        return Route.PACKAGE_ADMIN + Route.VIEW_GRANT_ADMIN;
    }

    @PostMapping(Route.GRANT)
    public String grantAdmin(@RequestParam String username){
        grantAdminRole.grantAdminRole(username);
        log.info("Admin nadal uzytkownikowi uprawnienia administracyjne");
        return Route.REDIRECT + Route.ADMIN + Route.GRANT;
    }
}
