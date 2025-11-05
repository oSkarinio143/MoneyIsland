package pl.oskarinio.moneyisland.auth.domain.port.in;


import pl.oskarinio.moneyisland.auth.domain.model.user.User;

import java.util.List;

public interface Admin {
    List<User> getUserList();
    void deleteUser(String username);
    void grantAdminRole(String username);
}
