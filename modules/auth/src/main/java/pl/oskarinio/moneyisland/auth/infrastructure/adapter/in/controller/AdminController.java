package pl.oskarinio.moneyisland.auth.infrastructure.adapter.in.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarinio.moneyisland.auth.application.port.DeleteUserUseCase;
import pl.oskarinio.moneyisland.auth.application.port.GetUserListUseCase;
import pl.oskarinio.moneyisland.auth.application.port.GrantAdminRoleUseCase;
import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.infrastructure.service.CookieManager;
import pl.oskarinio.moneyisland.shared.config.Route;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(Route.MAIN + Route.ADMIN)
public class AdminController {
    private final GetUserListUseCase showUserList;
    private final GrantAdminRoleUseCase grantAdminRole;
    private final DeleteUserUseCase deleteUser;

    @Value("${app.security.admin-username:}")
    private String adminUsername;

    public AdminController(GetUserListUseCase showUserList, GrantAdminRoleUseCase grantAdminRole, DeleteUserUseCase deleteUserUseCase, CookieManager cookieManager) {
        this.showUserList = showUserList;
        this.grantAdminRole = grantAdminRole;
        this.deleteUser = deleteUserUseCase;
    }

    @ModelAttribute(Route.USERS_LIST)
    public List<User> addUsersListModel(){
        List<User> userList = showUserList.getUsersList();
        return showUserList.getUsersList();
    }

    @GetMapping
    public String choseAdminOption(){
        log.info("Uzytkownik administrator w panelu admina");
        return Route.ADMIN;
    }

    @GetMapping(Route.SHOW_USERS_LIST)
    public String showUsers(Model model){
        log.info("Admin wyswietla liste uzytkownikow");
        return Route.USERS_LIST;
    }

    @PostMapping(Route.DELETE)
    public String deleteUser(@RequestParam String username){
        deleteUser.deleteUser(username);
        log.info("Admin usunal uzytkownika");
        return Route.REDIRECT + Route.ADMIN;
    }

    @PostMapping(Route.GRANT)
    public String grantAdmin(@RequestParam String username){
        grantAdminRole.grantAdminRole(username);
        log.info("Admin nadal uzytkownikowi uprawnienia administracyjne");
        return Route.REDIRECT + Route.ADMIN;
    }
}
