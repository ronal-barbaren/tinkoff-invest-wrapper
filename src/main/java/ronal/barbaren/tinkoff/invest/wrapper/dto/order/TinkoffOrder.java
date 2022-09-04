package ronal.barbaren.tinkoff.invest.wrapper.dto.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.wrapper.utils.TinkoffTimestampUtils;
import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderExecutionReportStatus;
import ru.tinkoff.piapi.contract.v1.OrderState;
import ru.tinkoff.piapi.core.utils.MapperUtils;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@EqualsAndHashCode(of = "id")
public class TinkoffOrder implements Order {
    private final String id;
    private final Instant date;
    private final long lotsExecuted;
    private final long lotsRequested;
    private final BigDecimal priceRequested;
    private final BigDecimal priceExecuted;
    private final boolean executed;
    private final boolean cancelled;
    private final boolean rejected;
    private final boolean buy;
    private final boolean sell;

    public TinkoffOrder(OrderState order) {
        this.id = order.getOrderId();
        this.date = TinkoffTimestampUtils.toInstant(order.getOrderDate());
        this.lotsExecuted = order.getLotsExecuted();
        this.lotsRequested = order.getLotsRequested();
        this.executed = order.getExecutionReportStatus() == OrderExecutionReportStatus.EXECUTION_REPORT_STATUS_FILL;
        this.cancelled = order.getExecutionReportStatus() == OrderExecutionReportStatus.EXECUTION_REPORT_STATUS_CANCELLED;
        this.rejected = order.getExecutionReportStatus() == OrderExecutionReportStatus.EXECUTION_REPORT_STATUS_REJECTED;
        this.priceRequested = MapperUtils.moneyValueToBigDecimal(order.getInitialOrderPrice());
        this.priceExecuted = MapperUtils.moneyValueToBigDecimal(order.getExecutedOrderPrice());
        this.buy = order.getDirection() == OrderDirection.ORDER_DIRECTION_BUY;
        this.sell = order.getDirection() == OrderDirection.ORDER_DIRECTION_SELL;
    }
}
