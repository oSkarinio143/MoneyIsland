package pl.oskarinio.moneyisland.finance.historyBlock.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oskarinio.moneyisland.finance.historyBlock.Month;

import java.math.BigDecimal;
import java.util.*;

@Data
@NoArgsConstructor
public class HistoryExpense {
    private Long id;
    private String username;
    private Map<Month, BigDecimal> monthValue = new EnumMap<>(Month.class);

    public HistoryExpense(String username){
        this.username = username;
        monthValue.put(Month.JANUARY, BigDecimal.ZERO);
        monthValue.put(Month.FEBRUARY, BigDecimal.ZERO);
        monthValue.put(Month.MARCH, BigDecimal.ZERO);
        monthValue.put(Month.APRIL, BigDecimal.ZERO);
        monthValue.put(Month.MAY, BigDecimal.ZERO);
        monthValue.put(Month.JUNE, BigDecimal.ZERO);
        monthValue.put(Month.JULY, BigDecimal.ZERO);
        monthValue.put(Month.AUGUST, BigDecimal.ZERO);
        monthValue.put(Month.SEPTEMBER, BigDecimal.ZERO);
        monthValue.put(Month.OCTOBER, BigDecimal.ZERO);
        monthValue.put(Month.NOVEMBER, BigDecimal.ZERO);
        monthValue.put(Month.DECEMBER, BigDecimal.ZERO);
    }

    public void addValuesToMonths(List<BigDecimal> values){
        List<BigDecimal> valuesWithoutNull = changeNullToZero(values);
        Iterator<BigDecimal> iterator = valuesWithoutNull.iterator();
        monthValue.replaceAll((k, v) -> iterator.next());
    }

    private List<BigDecimal> changeNullToZero(List<BigDecimal> values){
        values.forEach(v -> {
            if (v==null)
                values.set(values.indexOf(v), BigDecimal.ZERO);
        });
        return values;
    }

    public void setMonthValue(Map<Month, BigDecimal> monthValue) {
        if (monthValue == null || monthValue.isEmpty())
            return;
        this.monthValue = monthValue;
    }
}
