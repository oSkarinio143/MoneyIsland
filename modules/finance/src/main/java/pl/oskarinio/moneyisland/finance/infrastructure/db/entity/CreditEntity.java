package pl.oskarinio.moneyisland.finance.infrastructure.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(
        name = "user_credit",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"credit_name", "username"})
        })
@Data
@NoArgsConstructor
public class CreditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "credit_name")
    private String creditName;
    private double percent;
    private BigDecimal months;
    private BigDecimal value;
    private BigDecimal finalValue;
    private BigDecimal payedValue;
    private BigDecimal notPayedValue;
    private BigDecimal repayment;
    private BigDecimal repaymentQuantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
