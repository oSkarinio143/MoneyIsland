package pl.oskarinio.moneyisland.finance.GoalBlock.repository;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oskarinio.moneyisland.finance.UserEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "user_target")
@Data
@NoArgsConstructor
public class TargetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private BigDecimal incomeTarget;
    private BigDecimal expenseTarget;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
