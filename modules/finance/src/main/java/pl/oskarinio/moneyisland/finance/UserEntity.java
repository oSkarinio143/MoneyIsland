package pl.oskarinio.moneyisland.finance;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.oskarinio.moneyisland.finance.GoalBlock.repository.TargetEntity;
import pl.oskarinio.moneyisland.finance.historyBlock.entity.HistoryExpenseEntity;
import pl.oskarinio.moneyisland.finance.historyBlock.entity.HistoryIncomeEntity;

@Entity
@Table(name = "user_app")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @NonNull
    private Long authUserId;
    @Column(unique = true)
    @NonNull
    private String username;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private TargetEntity target;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private HistoryIncomeEntity historyIncome;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private HistoryExpenseEntity historyExpense;
}

