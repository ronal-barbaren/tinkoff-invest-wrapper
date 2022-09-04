package ronal.barbaren.tinkoff.invest.wrapper.dto.candle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.wrapper.utils.TinkoffTimestampUtils;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;
import ru.tinkoff.piapi.core.utils.MapperUtils;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@EqualsAndHashCode(of = "date")
public class TinkoffCandle implements Candle {
    private final Instant date;
    private final BigDecimal minPrice;
    private final BigDecimal maxPrice;
    private final BigDecimal openPrice;
    private final BigDecimal closePrice;
    private final long lots;

    public TinkoffCandle(HistoricCandle candle) {
        this.date = TinkoffTimestampUtils.toInstant(candle.getTime());
        this.maxPrice = MapperUtils.quotationToBigDecimal(candle.getHigh());
        this.minPrice = MapperUtils.quotationToBigDecimal(candle.getLow());
        this.openPrice = MapperUtils.quotationToBigDecimal(candle.getOpen());
        this.closePrice = MapperUtils.quotationToBigDecimal(candle.getClose());
        this.lots = candle.getVolume();
    }
}
