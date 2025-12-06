package pl.oskarinio.moneyisland.auth.admin;

import pl.oskarinio.moneyisland.shared.uncategorized.User;

import java.util.List;

public interface Admin {
    List<User> getUserList();
    void deleteUser(String username);
    void grantAdminRole(String username);
}
