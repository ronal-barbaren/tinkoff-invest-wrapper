package ronal.barbaren.tinkoff.invest.wrapper.dto.amount;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.tinkoff.piapi.core.models.Money;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(of = "currency")
public class TinkoffAmount implements Amount {
    private final String currency;
    private final BigDecimal value;

    public TinkoffAmount(Money money) {
        this.currency = money.getCurrency();
        this.value = money.getValue();
    }
}
