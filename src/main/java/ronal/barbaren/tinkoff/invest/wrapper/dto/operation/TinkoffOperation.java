package ronal.barbaren.tinkoff.invest.wrapper.dto.operation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.trade.OperationTrade;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.trade.TinkoffOperationTrade;
import ronal.barbaren.tinkoff.invest.wrapper.utils.TinkoffTimestampUtils;
import ru.tinkoff.piapi.contract.v1.OperationState;
import ru.tinkoff.piapi.contract.v1.OperationType;
import ru.tinkoff.piapi.core.utils.MapperUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(of = "id")
public class TinkoffOperation implements Operation {
    private final String id;
    private final BigDecimal price;
    private final long lots;
    private final boolean executed;
    private final boolean cancelled;
    private final boolean progress;
    private final boolean buy;
    private final boolean sell;
    private final Instant date;
    private final Set<OperationTrade> trades;

    public TinkoffOperation(ru.tinkoff.piapi.contract.v1.Operation operation) {
        this.id = operation.getId();
        this.price = MapperUtils.moneyValueToBigDecimal(operation.getPrice());
        this.lots = operation.getQuantity();
        this.executed = operation.getState() == OperationState.OPERATION_STATE_EXECUTED;
        this.cancelled = operation.getState() == OperationState.OPERATION_STATE_CANCELED;
        this.progress = operation.getState() == OperationState.OPERATION_STATE_PROGRESS;
        this.buy = operation.getOperationType() == OperationType.OPERATION_TYPE_BUY;
        this.sell = operation.getOperationType() == OperationType.OPERATION_TYPE_SELL;
        this.date = TinkoffTimestampUtils.toInstant(operation.getDate());
        if (operation.getTradesCount() != 0)
            this.trades = operation.getTradesList().stream().map(TinkoffOperationTrade::new).collect(Collectors.toSet());
        else this.trades = Collections.emptySet();
    }

}
