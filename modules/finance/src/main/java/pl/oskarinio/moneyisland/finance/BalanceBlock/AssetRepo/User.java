package pl.oskarinio.moneyisland.finance.BalanceBlock.AssetRepo;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> assetNames = new ArrayList<>();
    private List<BigDecimal> assetValues = new ArrayList<>();

    public void addToNames(String name) {
        assetNames.add(name);
    }

    public void addToValues(BigDecimal value){
        assetValues.add(value);
    }

//    private String password;
//    private Instant registrationDate;
//    private Set<Role> roles = new HashSet<>();
//
//    public void addRole(Role role){
//        roles.add(role);
//    }
}
