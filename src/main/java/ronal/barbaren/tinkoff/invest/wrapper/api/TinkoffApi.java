package ronal.barbaren.tinkoff.invest.wrapper.api;

import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.wrapper.dto.amount.Amount;
import ronal.barbaren.tinkoff.invest.wrapper.dto.amount.TinkoffAmount;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.TinkoffCandle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.TinkoffOperation;
import ronal.barbaren.tinkoff.invest.wrapper.dto.order.Order;
import ronal.barbaren.tinkoff.invest.wrapper.dto.order.TinkoffOrder;
import ronal.barbaren.tinkoff.invest.wrapper.dto.position.TinkoffPosition;
import ronal.barbaren.tinkoff.invest.wrapper.utils.TinkoffTradingStatusUtils;
import ru.tinkoff.piapi.contract.v1.*;
import ru.tinkoff.piapi.core.InvestApi;
import ru.tinkoff.piapi.core.models.Portfolio;
import ru.tinkoff.piapi.core.models.Position;
import ru.tinkoff.piapi.core.models.Positions;
import ru.tinkoff.piapi.core.utils.MapperUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class TinkoffApi implements Api {
    private final String accountId;
    private final String figi;
    private final InvestApi api;

    public TinkoffApi(String token, String accountId, String figi) {
        this.accountId = accountId;
        this.figi = figi;
        this.api = InvestApi.create(token);
    }

    @Nonnull
    @Override
    public String buy(long lots, BigDecimal price) {
        return postLimitOrder(lots, price, OrderDirection.ORDER_DIRECTION_BUY);
    }

    @Nonnull
    @Override
    public String sell(long lots, BigDecimal price) {
        return postLimitOrder(lots, price, OrderDirection.ORDER_DIRECTION_SELL);
    }

    @Override
    public void cancelOrder(String orderId) {
        api.getOrdersService().cancelOrderSync(accountId, orderId);
    }

    @Nonnull
    @Override
    public Set<Order> getNotExecutedOrders() {
        List<OrderState> orders = api.getOrdersService().getOrdersSync(accountId);
        if (CollectionUtils.isEmpty(orders))
            return Collections.emptySet();
        return orders.stream()
                .filter(a -> figi.equals(a.getFigi()))
                .map(TinkoffOrder::new)
                .collect(Collectors.toSet());
    }

    @Override
    @Nullable
    public TinkoffPosition getPosition() {
        Portfolio response = api.getOperationsService().getPortfolioSync(accountId);
        List<Position> positions = response.getPositions().stream().filter(a -> figi.equals(a.getFigi())).toList();
        if (CollectionUtils.isEmpty(positions))
            return null;
        if (positions.size() > 1)
            throw new IllegalStateException("impossible, found more than one position by figi: " + figi);
        Position position = positions.get(0);
        return new TinkoffPosition(position);
    }

    @Override
    @Nonnull
    public Set<Operation> getExecutedOperations(Instant from, Instant to) {
        List<ru.tinkoff.piapi.contract.v1.Operation> operations = api.getOperationsService().getExecutedOperationsSync(accountId, from, to, figi);
        if (CollectionUtils.isEmpty(operations))
            return Collections.emptySet();
        return operations.stream()
                .map(TinkoffOperation::new)
                .collect(Collectors.toSet());
    }

    @Override
    @Nullable
    public BigDecimal getLastPrice() {
        List<LastPrice> lastPrices = api.getMarketDataService()
                .getLastPricesSync(Collections.singleton(figi));
        if (CollectionUtils.isEmpty(lastPrices))
            return null;
        if (lastPrices.size() > 1)
            throw new IllegalStateException("impossible, get more than one last price, figi: " + figi);
        return MapperUtils.quotationToBigDecimal(lastPrices.get(0).getPrice());
    }

    @Override
    public boolean isTradable() {
        GetTradingStatusResponse tradingStatus = api
                .getMarketDataService()
                .getTradingStatusSync(figi);
        return TinkoffTradingStatusUtils.isTradable(tradingStatus);
    }

    @Override
    @Nonnull
    public BigDecimal getMinPriceIncrement() {
        Instrument instrument = api.getInstrumentsService().getInstrumentByFigiSync(figi);
        return MapperUtils.quotationToBigDecimal(instrument.getMinPriceIncrement());
    }

    @Nonnull
    protected List<Candle> getCandles(Instant from, Instant to, CandleInterval interval) {
        return api.getMarketDataService()
                .getCandlesSync(figi, from, to, interval)
                .stream()
                .map(TinkoffCandle::new)
                .collect(Collectors.toList());
    }

    @Override
    @Nonnull
    public List<Candle> getCandles1Min(Instant from, Instant to) {
        return getCandles(from, to, CandleInterval.CANDLE_INTERVAL_1_MIN);
    }

    @Override
    @Nonnull
    public List<Candle> getCandles5Min(Instant from, Instant to) {
        return getCandles(from, to, CandleInterval.CANDLE_INTERVAL_5_MIN);
    }

    @Override
    @Nonnull
    public List<Candle> getCandles15Min(Instant from, Instant to) {
        return getCandles(from, to, CandleInterval.CANDLE_INTERVAL_15_MIN);
    }

    @Override
    @Nonnull
    public List<Candle> getCandles1Hour(Instant from, Instant to) {
        return getCandles(from, to, CandleInterval.CANDLE_INTERVAL_HOUR);
    }

    @Override
    @Nonnull
    public List<Candle> getCandles1Day(Instant from, Instant to) {
        return getCandles(from, to, CandleInterval.CANDLE_INTERVAL_DAY);
    }

    @Override
    @Nonnull
    public Set<Amount> getAmounts() {
        Positions positions = api.getOperationsService().getPositionsSync(accountId);
        return positions.getMoney().stream()
                .map(TinkoffAmount::new)
                .collect(Collectors.toSet());
    }

    @Nonnull
    protected String postLimitOrder(long lots, BigDecimal price, OrderDirection operation) {
        if (Objects.equals(0L, lots) || Objects.isNull(price))
            throw new IllegalArgumentException("lots = 0 or price is null");
        return api.getOrdersService()
                .postOrderSync(
                        figi,
                        lots,
                        MapperUtils.bigDecimalToQuotation(price),
                        operation,
                        accountId,
                        OrderType.ORDER_TYPE_LIMIT,
                        UUID.randomUUID().toString()
                ).getOrderId();
    }
}
