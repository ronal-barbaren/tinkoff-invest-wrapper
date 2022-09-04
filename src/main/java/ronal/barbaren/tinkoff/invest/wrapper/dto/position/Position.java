package ronal.barbaren.tinkoff.invest.wrapper.dto.position;

import java.math.BigDecimal;

public interface Position {
    BigDecimal getAveragePrice();

    BigDecimal getCurrentPrice();

    long getLots();

    BigDecimal getExpectedYield();

    String getFigi();
}
