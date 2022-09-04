package ronal.barbaren.tinkoff.invest.wrapper.dto.order;

import java.math.BigDecimal;
import java.time.Instant;

public interface Order {

    Instant getDate();

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

    boolean isBuy();

    boolean isSell();
}
