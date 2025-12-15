package pl.oskarinio.moneyisland.auth.domain.service;


import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.shared.exception.UsernameNotFoundException;
import pl.oskarinio.moneyisland.shared.config.Role;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AdminDomainService {
    private final UserRepository userRepository;
    private final List<String> activeProfiles;

    public AdminDomainService(UserRepository userRepository, String[] activeProfiles) {
        this.userRepository = userRepository;
        this.activeProfiles = Arrays.asList(activeProfiles);
    }

    public List<User> getUserList(){
        return userRepository.findAll();
    }

    //PRZY KOŃCZENIU PROJEKTU PAMIĘTAĆ O ROZWAŻENIU DRUGIEGO PROFILU DLA NOWYCH UŻYTKOWNIKÓW
    public void deleteUser(String username){
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isEmpty())
            throw new UsernameNotFoundException();

        User user = userOpt.get();
        if(activeProfiles.contains("h2")) {
            if(!user.getUsername().equals("adminUser"))
                userRepository.delete(user);
        }
        else{
            if (!user.getUsername().equals(System.getenv("ADMIN_USERNAME")))
                userRepository.delete(user);
        }
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
