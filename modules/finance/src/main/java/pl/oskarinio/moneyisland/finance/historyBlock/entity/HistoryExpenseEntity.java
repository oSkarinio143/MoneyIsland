package pl.oskarinio.moneyisland.finance.historyBlock.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oskarinio.moneyisland.finance.UserEntity;
import pl.oskarinio.moneyisland.finance.historyBlock.Month;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

@Entity
@Table(name = "user_expense_history")
@Data
@NoArgsConstructor
public class HistoryExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @ElementCollection
    @CollectionTable(
            name="month_expense_values",
            joinColumns = @JoinColumn(name="expense_id"))
    @MapKeyColumn(name = "month")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "value")
    private Map<Month, BigDecimal> monthValue = new EnumMap<>(Month.class);

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
