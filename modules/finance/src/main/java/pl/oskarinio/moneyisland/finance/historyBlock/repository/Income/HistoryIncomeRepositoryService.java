package pl.oskarinio.moneyisland.finance.historyBlock.repository.Income;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.BalanceBlock.MapStruct;
import pl.oskarinio.moneyisland.finance.historyBlock.Dto.HistoryIncome;
import pl.oskarinio.moneyisland.finance.historyBlock.Month;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

@Service
public class HistoryIncomeRepositoryService implements HistoryIncomeRepository {
    private final HistoryIncomeRepositoryJpa historyIncomeRepositoryJpa;
    private final MapStruct mapper;

    public HistoryIncomeRepositoryService(HistoryIncomeRepositoryJpa historyIncomeRepositoryJpa, MapStruct mapper) {
        this.historyIncomeRepositoryJpa = historyIncomeRepositoryJpa;
        this.mapper = mapper;
    }

    public void save(HistoryIncome historyIncome){
        historyIncomeRepositoryJpa.save(mapper.toHistoryIncomeEntity(historyIncome));
    }

    public HistoryIncome findByUsername(String username){
        HistoryIncome historyIncome = mapper.toHistoryIncome(historyIncomeRepositoryJpa.findByUsername(username));
        if(historyIncome != null)
            return setSortMap(historyIncome);
        return historyIncome;
    }

    private HistoryIncome setSortMap(HistoryIncome historyIncome){
        Map<Month, BigDecimal> historyIncomeMonthValues = historyIncome.getMonthValue();

        EnumMap<Month, BigDecimal> sortedMonthValues = historyIncomeMonthValues.entrySet()
                .stream()
                .collect(
                        () -> new EnumMap<>(Month.class),
                        (month, entry) -> month.put(entry.getKey(), entry.getValue()),
                        EnumMap::putAll
                );
        historyIncome.setMonthValue(sortedMonthValues);
        return historyIncome;
    }
}
