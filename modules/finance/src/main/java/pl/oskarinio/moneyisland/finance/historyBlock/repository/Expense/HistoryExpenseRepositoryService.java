package pl.oskarinio.moneyisland.finance.historyBlock.repository.Expense;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.map.MapStruct;
import pl.oskarinio.moneyisland.finance.historyBlock.Dto.HistoryExpense;
import pl.oskarinio.moneyisland.finance.historyBlock.Month;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

@Service
public class HistoryExpenseRepositoryService implements HistoryExpenseRepository{
    private final HistoryExpenseRepositoryJpa historyExpenseRepositoryJpa;
    private final MapStruct mapper;

    public HistoryExpenseRepositoryService(HistoryExpenseRepositoryJpa historyExpenseRepositoryJpa, MapStruct mapper) {
        this.historyExpenseRepositoryJpa = historyExpenseRepositoryJpa;
        this.mapper = mapper;
    }

    public void save(HistoryExpense historyExpense){
        historyExpenseRepositoryJpa.save(mapper.toHistoryExpenseEntity(historyExpense));
    }

    public HistoryExpense findByUsername(String username){
        HistoryExpense historyExpense = mapper.toHistoryExpense(historyExpenseRepositoryJpa.findByUsername(username));
        if(historyExpense != null)
            return setSortMap(historyExpense);
        return historyExpense;
    }

    private HistoryExpense setSortMap(HistoryExpense historyExpense){
        Map<Month, BigDecimal> historyIncomeMonthValues = historyExpense.getMonthValue();

        EnumMap<Month, BigDecimal> sortedMonthValues = historyIncomeMonthValues.entrySet()
                .stream()
                .collect(
                        () -> new EnumMap<>(Month.class),
                        (month, entry) -> month.put(entry.getKey(), entry.getValue()),
                        EnumMap::putAll
                );
        historyExpense.setMonthValue(sortedMonthValues);
        return historyExpense;
    }
}
