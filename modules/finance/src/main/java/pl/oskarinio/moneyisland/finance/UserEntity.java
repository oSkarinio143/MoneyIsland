package pl.oskarinio.moneyisland.finance;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long authUserId;

    @NonNull
    @Column(unique = true)
    private String username;

    @NonNull
    @Column
    private String email;

    @Column
    private BigDecimal balance;
}
