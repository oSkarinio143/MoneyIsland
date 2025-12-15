package pl.oskarinio.moneyisland.auth.application.port;


import pl.oskarinio.moneyisland.auth.domain.dto.User;

import java.util.List;

public interface GetUserListUseCase {
    List<User> getUsersList();
}
