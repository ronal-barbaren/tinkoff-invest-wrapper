package ronal.barbaren.tinkoff.invest.wrapper.dto.amount;

import java.math.BigDecimal;

public interface Amount {
    BigDecimal getValue();

    String getCurrency();
}
