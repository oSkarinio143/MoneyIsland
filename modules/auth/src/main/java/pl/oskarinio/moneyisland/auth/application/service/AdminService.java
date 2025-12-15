package pl.oskarinio.moneyisland.auth.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.auth.application.port.DeleteUserUseCase;
import pl.oskarinio.moneyisland.auth.application.port.GetUserListUseCase;
import pl.oskarinio.moneyisland.auth.application.port.GrantAdminRoleUseCase;
import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.auth.domain.service.AdminDomainService;

import java.util.List;

@Service
@Slf4j
public class AdminService implements GetUserListUseCase, GrantAdminRoleUseCase, DeleteUserUseCase {
    private final AdminDomainService adminDomainService;

    public AdminService(UserRepository userRepository, @Value("${spring.profiles.active:}") String[] activeProfiles) {
        this.adminDomainService = new AdminDomainService(userRepository, activeProfiles);
    }

    @Override
    public List<User> getUsersList() {
        log.trace("Zwracam listę użytkowników");
        return adminDomainService.getUserList();
    }

    public void deleteUser(String username) {
        log.debug("Usuwanie użytkownika. Nazwa = {}", username);
        adminDomainService.deleteUser(username);
    }

    @Override
    public void grantAdminRole(String username) {
        log.debug("Nadawanie roli administratora użytkownikowi. Nazwa = {}", username);
        adminDomainService.grantAdminRole(username);
    }
}
