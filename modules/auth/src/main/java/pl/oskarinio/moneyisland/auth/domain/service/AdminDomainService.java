package pl.oskarinio.moneyisland.auth.domain.service;


import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.shared.domain.Role;
import pl.oskarinio.moneyisland.shared.domain.exception.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class AdminDomainService {
    private final UserRepository userRepository;

    public AdminDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList(){
        return userRepository.findAll();
    }

    public User deleteUser(String username){
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isEmpty())
            throw new UsernameNotFoundException();

        User user = userOpt.get();

        if (!user.getUsername().equals(System.getenv("ADMIN_USERNAME")))
            userRepository.delete(user);
        return user;
    }

    public void grantAdminRole(String username){
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            if(!user.getRoles().contains(Role.ROLE_ADMIN)) {
                user.addRole(Role.ROLE_ADMIN);
            }
            userRepository.save(user);
        }
    }
}
