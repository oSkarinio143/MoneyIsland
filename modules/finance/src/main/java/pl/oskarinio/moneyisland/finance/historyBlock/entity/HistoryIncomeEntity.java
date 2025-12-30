package pl.oskarinio.moneyisland.finance.historyBlock.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import pl.oskarinio.moneyisland.finance.historyBlock.Month;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

@Entity
@Table(name = "user_income_history")
@Data
@NoArgsConstructor
public class HistoryIncomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    @ElementCollection
    @CollectionTable(name="income_month_values",
            joinColumns = @JoinColumn(name="income_id"))
    @MapKeyColumn(name = "month")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "value")
    private Map<Month, BigDecimal> monthValue = new EnumMap<>(Month.class);
}
