package ronal.barbaren.tinkoff.invest.wrapper.api;

import ronal.barbaren.tinkoff.invest.wrapper.dto.amount.Amount;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;
import ronal.barbaren.tinkoff.invest.wrapper.dto.order.Order;
import ronal.barbaren.tinkoff.invest.wrapper.dto.position.Position;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

public interface Api {

    String buy(long lots, BigDecimal price);

    String sell(long lots, BigDecimal price);

    void cancelOrder(String orderId);

    Set<Order> getNotExecutedOrders();

    Position getPosition();

    Set<Operation> getExecutedOperations(Instant from, Instant to);

    BigDecimal getLastPrice();

    boolean isTradable();

    BigDecimal getMinPriceIncrement();

    Set<Amount> getAmounts();

    List<Candle> getCandles1Min(Instant from, Instant to);

    List<Candle> getCandles5Min(Instant from, Instant to);

    List<Candle> getCandles15Min(Instant from, Instant to);

    List<Candle> getCandles1Hour(Instant from, Instant to);

    List<Candle> getCandles1Day(Instant from, Instant to);

}
