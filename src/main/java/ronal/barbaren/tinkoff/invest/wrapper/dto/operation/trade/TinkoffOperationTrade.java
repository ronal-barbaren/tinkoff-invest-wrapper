package ronal.barbaren.tinkoff.invest.wrapper.dto.operation.trade;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.wrapper.utils.TinkoffTimestampUtils;
import ru.tinkoff.piapi.core.utils.MapperUtils;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@EqualsAndHashCode(of = "id")
public class TinkoffOperationTrade implements OperationTrade {
    private final String id;
    private final BigDecimal price;
    private final long lots;
    private final Instant date;

    public TinkoffOperationTrade(ru.tinkoff.piapi.contract.v1.OperationTrade operationTrade) {
        this.id = operationTrade.getTradeId();
        this.price = MapperUtils.moneyValueToBigDecimal(operationTrade.getPrice());
        this.lots = operationTrade.getQuantity();
        this.date = TinkoffTimestampUtils.toInstant(operationTrade.getDateTime());
    }
}
