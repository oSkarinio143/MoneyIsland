//package pl.oskarinio.moneyisland.auth.infrastructure.usecase.database;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import pl.oskarinio.moneyisland.auth.domain.port.out.UserRepository;
//
//import java.time.Clock;
//
//@Component
//@Profile("!h2")
//class MySQLAdminInitializerUseCase implements CommandLineRunner {
//    private final MySQLAdminInitializer mySQLAdminInitializer;
//
//    public MySQLAdminInitializerUseCase(UserRepository userRepository,
//                                        PasswordEncoder passwordEncoder,
//                                        Clock clock,
//                                        @Value("${app.security.admin-username:}") String adminUsername,
//                                        @Value("${app.security.admin-password:}") String adminPassword) {
//        this.mySQLAdminInitializer = new MySQLAdminInitializer(userRepository, passwordEncoder, clock, adminUsername, adminPassword);
//    }
//
//    @Override
//    public void run(String... args) {
//        mySQLAdminInitializer.initializeProfile();
//    }
//}
