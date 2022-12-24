package ronal.barbaren.tinkoff.invest.wrapper.api;

import ronal.barbaren.tinkoff.invest.wrapper.dto.amount.Amount;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;
import ronal.barbaren.tinkoff.invest.wrapper.dto.order.Order;
import ronal.barbaren.tinkoff.invest.wrapper.dto.position.Position;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

public interface Api {
    @Nonnull
    String buy(long lots, BigDecimal price);

    @Nonnull
    String sell(long lots, BigDecimal price);

    void cancelOrder(String orderId);

    @Nonnull
    Set<Order> getNotExecutedOrders();

    @Nullable
    Position getPosition();

    /**
     * @param from first date
     * @param to   second date
     * @return operation.isExecuted = true
     */
    @Nonnull
    Set<Operation> getExecutedOperations(Instant from, Instant to);

    @Nullable
    BigDecimal getLastPrice();

    boolean isTradable();

    @Nonnull
    BigDecimal getMinPriceIncrement();

    @Nonnull
    Set<Amount> getAmounts();

    @Nonnull
    List<Candle> getCandles1Min(Instant from, Instant to);

    @Nonnull
    List<Candle> getCandles5Min(Instant from, Instant to);

    @Nonnull
    List<Candle> getCandles15Min(Instant from, Instant to);

    /**
     * @param from from date
     * @param to   to date
     * @return candles sorted by date
     */
    @Nonnull
    List<Candle> getCandles1Hour(Instant from, Instant to);

    @Nonnull
    List<Candle> getCandles1Day(Instant from, Instant to);

}
