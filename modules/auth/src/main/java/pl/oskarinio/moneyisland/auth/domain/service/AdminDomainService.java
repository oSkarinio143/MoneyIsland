package pl.oskarinio.moneyisland.auth.domain.service;


import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.auth.infrastructure.kafka.KafkaEventPublisher;
import pl.oskarinio.moneyisland.shared.domain.exception.UsernameNotFoundException;
import pl.oskarinio.moneyisland.shared.domain.Role;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AdminDomainService {
    private final UserRepository userRepository;
    private final List<String> activeProfiles;
    private final KafkaEventPublisher kafkaEventPublisher;

    public AdminDomainService(UserRepository userRepository, String[] activeProfiles, KafkaEventPublisher kafkaEventPublisher) {
        this.userRepository = userRepository;
        this.activeProfiles = Arrays.asList(activeProfiles);
        this.kafkaEventPublisher = kafkaEventPublisher;
    }

    public List<User> getUserList(){
        return userRepository.findAll();
    }

    public void deleteUser(String username){
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isEmpty())
            throw new UsernameNotFoundException();

        User user = userOpt.get();

        if (!user.getUsername().equals(System.getenv("ADMIN_USERNAME")))
            userRepository.delete(user);
        kafkaEventPublisher.publishUserDeleted(username, user.getId());
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
