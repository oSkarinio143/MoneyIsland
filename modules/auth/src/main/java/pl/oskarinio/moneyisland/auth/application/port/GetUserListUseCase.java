package pl.oskarinio.moneyisland.auth.application.port;

import pl.oskarinio.moneyisland.shared.uncategorized.User;

import java.util.List;

public interface GetUserListUseCase {
    List<User> getUsersList();
}
