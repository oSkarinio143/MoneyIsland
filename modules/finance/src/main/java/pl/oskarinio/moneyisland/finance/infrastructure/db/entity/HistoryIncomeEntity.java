package pl.oskarinio.moneyisland.finance.infrastructure.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oskarinio.moneyisland.finance.domain.dto.Month;

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
    private String username;
    @ElementCollection
    @CollectionTable(
            name="month_income_values",
            joinColumns = @JoinColumn(name="income_id"))
    @MapKeyColumn(name = "month")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "value")
    private Map<Month, BigDecimal> monthValue = new EnumMap<>(Month.class);

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
