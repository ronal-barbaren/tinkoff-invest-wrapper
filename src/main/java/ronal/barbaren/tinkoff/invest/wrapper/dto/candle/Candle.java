package ronal.barbaren.tinkoff.invest.wrapper.dto.candle;

import ronal.barbaren.tinkoff.invest.wrapper.dto.DtoDate;

import java.math.BigDecimal;
import java.time.Instant;

public interface Candle extends DtoDate {
    BigDecimal getMinPrice();

    BigDecimal getMaxPrice();

    BigDecimal getOpenPrice();

    BigDecimal getClosePrice();

    long getLots();
}
