package pl.oskarinio.moneyisland.auth.domain.dto;

import lombok.*;
import pl.oskarinio.moneyisland.shared.config.Role;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Instant registrationDate;
    private Set<Role> roles = new HashSet<>();
    private RefreshToken refreshToken;

    public void addRole(Role role){
        roles.add(role);
    }
}
