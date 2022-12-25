package ronal.barbaren.tinkoff.invest.wrapper.api.ext;

import lombok.RequiredArgsConstructor;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;
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

@RequiredArgsConstructor
public abstract class BaseExtApi implements Api {
    private final Api api;

    @Nonnull
    @Override
    public String buy(long lots, BigDecimal price) {
        return api.buy(lots, price);
    }

    @Nonnull
    @Override
    public String sell(long lots, BigDecimal price) {
        return api.sell(lots, price);
    }

    @Override
    public void cancelOrder(String orderId) {
        api.cancelOrder(orderId);
    }

    @Nonnull
    @Override
    public Set<Order> getNotExecutedOrders() {
        return api.getNotExecutedOrders();
    }

    @Nullable
    @Override
    public Position getPosition() {
        return api.getPosition();
    }

    @Nonnull
    @Override
    public Set<Operation> getExecutedOperations(Instant from, Instant to) {
        return api.getExecutedOperations(from, to);
    }

    @Nullable
    @Override
    public BigDecimal getLastPrice() {
        return api.getLastPrice();
    }

    @Override
    public boolean isTradable() {
        return api.isTradable();
    }

    @Nonnull
    @Override
    public BigDecimal getMinPriceIncrement() {
        return api.getMinPriceIncrement();
    }

    @Nonnull
    @Override
    public Set<Amount> getAmounts() {
        return api.getAmounts();
    }

    @Nonnull
    @Override
    public List<Candle> getCandles1Min(Instant from, Instant to) {
        return api.getCandles1Min(from, to);
    }

    @Nonnull
    @Override
    public List<Candle> getCandles5Min(Instant from, Instant to) {
        return api.getCandles5Min(from, to);
    }

    @Nonnull
    @Override
    public List<Candle> getCandles15Min(Instant from, Instant to) {
        return api.getCandles15Min(from, to);
    }

    @Nonnull
    @Override
    public List<Candle> getCandles1Hour(Instant from, Instant to) {
        return api.getCandles1Hour(from, to);
    }

    @Nonnull
    @Override
    public List<Candle> getCandles1Day(Instant from, Instant to) {
        return api.getCandles1Day(from, to);
    }
}
