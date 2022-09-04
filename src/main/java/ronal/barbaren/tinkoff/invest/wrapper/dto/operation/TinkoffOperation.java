package ronal.barbaren.tinkoff.invest.wrapper.dto.operation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.wrapper.utils.TinkoffTimestampUtils;
import ru.tinkoff.piapi.contract.v1.OperationState;
import ru.tinkoff.piapi.contract.v1.OperationType;
import ru.tinkoff.piapi.core.utils.MapperUtils;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@EqualsAndHashCode(of = "id")
public class TinkoffOperation implements Operation {
    private final String id;
    private final BigDecimal price;
    private final long lots;
    private final boolean executed;
    private final boolean buy;
    private final boolean sell;
    private final Instant date;

    public TinkoffOperation(ru.tinkoff.piapi.contract.v1.Operation operation) {
        this.id = operation.getId();
        this.price = MapperUtils.moneyValueToBigDecimal(operation.getPrice());
        this.lots = operation.getQuantity();
        this.executed = operation.getState() == OperationState.OPERATION_STATE_EXECUTED;
        this.buy = operation.getOperationType() == OperationType.OPERATION_TYPE_BUY;
        this.sell = operation.getOperationType() == OperationType.OPERATION_TYPE_SELL;
        this.date = TinkoffTimestampUtils.toInstant(operation.getDate());
    }

}
