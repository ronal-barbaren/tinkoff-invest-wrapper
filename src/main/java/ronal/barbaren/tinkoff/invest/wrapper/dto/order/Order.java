package ronal.barbaren.tinkoff.invest.wrapper.dto.order;

import ronal.barbaren.tinkoff.invest.wrapper.dto.DtoBuySell;
import ronal.barbaren.tinkoff.invest.wrapper.dto.DtoDate;
import ronal.barbaren.tinkoff.invest.wrapper.dto.order.stage.OrderStage;

import java.math.BigDecimal;
import java.util.Set;

public interface Order extends DtoDate, DtoBuySell {
    String getId();

    long getLotsExecuted();

    long getLotsRequested();

    /*
     * start lot-price * lots
     */
    BigDecimal getPriceRequested();

    /*
     * avg lot-price * lots
     */
    BigDecimal getPriceExecuted();

    boolean isExecuted();

    boolean isCancelled();

    boolean isRejected();

    boolean isPartial();

    Set<OrderStage> getStages();
}
