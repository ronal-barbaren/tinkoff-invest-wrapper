package ronal.barbaren.tinkoff.invest.wrapper.dto.candle;

import java.math.BigDecimal;
import java.time.Instant;

public interface Candle {
    Instant getDate();

    BigDecimal getMinPrice();

    BigDecimal getMaxPrice();

    BigDecimal getOpenPrice();

    BigDecimal getClosePrice();

    long getLots();
}
