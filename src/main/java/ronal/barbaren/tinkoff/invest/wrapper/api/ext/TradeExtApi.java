package ronal.barbaren.tinkoff.invest.wrapper.api.ext;

import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static ronal.barbaren.instant.utils.InstantUtils.*;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoBuySellUtils.filterBuy;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoBuySellUtils.filterSell;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoDateUtils.filterAfter;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoDateUtils.filterLast;

public class TradeExtApi extends BaseExtApi {
    @Nullable
    protected Instant getLastTradeDayExcludeEndDay(Instant endDay) {
        Candle candle = getLastTrade1DayCandleExcludeEndDay(endDay);
        if (Objects.isNull(candle))
            return null;
        return truncateDay(candle.getDate());
    }

    @Nullable
    protected Candle get1DayCandle(Instant day) {
        Instant from = truncateDay(day);
        Instant to = plusDay(from);
        List<Candle> candles1Day = getCandles1Day(to, from);
        if (CollectionUtils.isEmpty(candles1Day))
            return null;
        return filterLast(candles1Day);
    }

    @Nullable
    protected Candle getLastTrade1DayCandleExcludeEndDay(Instant endDay) {
        List<Candle> candles = getLast1Year1DayCandlesExcludeEndDay(endDay);
        return filterLast(candles);
    }

    @Nonnull
    protected List<Candle> getLast1Year1DayCandlesExcludeEndDay(Instant endDay) {
        Instant to = truncateDay(endDay).minusSeconds(1L);
        Instant from = minusDay(to, 365);
        return getCandles1Day(from, endDay);
    }

    @Nonnull
    protected List<Candle> getLast1Month1DayCandlesExcludeEndDay(Instant endDay) {
        List<Candle> candles = getLast1Year1DayCandlesExcludeEndDay(endDay);
        Instant from = minusMonth(endDay);
        return filterAfter(candles, from);
    }

    @Nonnull
    protected List<Candle> getLast5Days1DayCandlesExcludeEndDay(Instant endDay) {
        List<Candle> candles = getLast1Month1DayCandlesExcludeEndDay(endDay);
        Instant from = minusDay(endDay, 5);
        return filterAfter(candles, from);
    }

    @Nonnull
    protected List<Candle> getLast10Days1DayCandlesExcludeEndDay(Instant endDay) {
        List<Candle> candles = getLast1Month1DayCandlesExcludeEndDay(endDay);
        Instant from = minusDay(endDay, 10);
        return filterAfter(candles, from);
    }

    @Nonnull
    protected List<Candle> getLast20Days1DayCandlesExcludeEndDay(Instant endDay) {
        List<Candle> candles = getLast1Month1DayCandlesExcludeEndDay(endDay);
        Instant from = minusDay(endDay, 20);
        return filterAfter(candles, from);
    }

    @Nonnull
    protected List<Candle> getCurrentDay1MinuteCandlesIncludeEndMinute(Instant endDate) {
        Instant from = truncateDay(endDate);
        Instant to = plusDay(endDate);
        return getCandles1Min(from, to);
    }

    @Nonnull
    protected List<Candle> getLastHour1MinuteCandlesIncludeEndMinute(Instant endDate) {
        List<Candle> day = getCurrentDay1MinuteCandlesIncludeEndMinute(endDate);
        Instant hour = minusHour(endDate);
        return filterAfter(day, hour);
    }

    @Nonnull
    protected List<Candle> getLast5Minute1MinuteCandlesIncludeEndMinute(Instant endDate) {
        List<Candle> candles = getLastHour1MinuteCandlesIncludeEndMinute(endDate);
        Instant from = minusMinute(endDate, 5);
        return filterAfter(candles, from);
    }

    @Nonnull
    protected List<Candle> getLast10Minute1MinuteCandlesIncludeEndMinute(Instant endDate) {
        List<Candle> candles = getLastHour1MinuteCandlesIncludeEndMinute(endDate);
        Instant from = minusMinute(endDate, 10);
        return filterAfter(candles, from);
    }

    @Nonnull
    protected List<Candle> getLast20Minute1MinuteCandlesIncludeEndMinute(Instant endDate) {
        List<Candle> candles = getLastHour1MinuteCandlesIncludeEndMinute(endDate);
        Instant from = minusMinute(endDate, 20);
        return filterAfter(candles, from);
    }

    @Nonnull
    protected List<Candle> getLast30Minute1MinuteCandlesIncludeEndMinute(Instant endDate) {
        List<Candle> candles = getLastHour1MinuteCandlesIncludeEndMinute(endDate);
        Instant from = minusMinute(endDate, 30);
        return filterAfter(candles, from);
    }

    @Nonnull
    protected Set<Operation> getLast1MonthExecutedOperationsIncludeEndDay(Instant endDay) {
        Instant to = truncateDay(plusDay(endDay));
        Instant from = minusMonth(to);
        return getExecutedOperations(from, to);
    }

    @Nonnull
    protected List<Operation> getLast1MonthExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    protected List<Operation> getLast1MonthExecutedSellOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nonnull
    protected List<Operation> getLast20DaysExecutedOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        Instant from = minusDay(truncateDay(endDay), 20);
        return filterAfter(operations, from);
    }

    @Nonnull
    protected List<Operation> getLast20DaysExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast20DaysExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    protected List<Operation> getLast20DaysExecutedSellOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast20DaysExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nonnull
    protected List<Operation> getLast10DaysExecutedOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        Instant from = minusDay(truncateDay(endDay), 10);
        return filterAfter(operations, from);
    }

    @Nonnull
    protected List<Operation> getLast10DaysExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast10DaysExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    protected List<Operation> getLast10DaysExecutedSellOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast10DaysExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nonnull
    protected List<Operation> getLast5DaysExecutedOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        Instant from = minusDay(truncateDay(endDay), 5);
        return filterAfter(operations, from);
    }

    @Nonnull
    protected List<Operation> getLast5DaysExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast5DaysExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    protected List<Operation> getLast5DaysExecutedSellOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast5DaysExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nonnull
    protected List<Operation> getLast1DayExecutedOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        Instant from = minusDay(truncateDay(endDay));
        return filterAfter(operations, from);
    }

    @Nonnull
    protected List<Operation> getLast1DaysExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast1DayExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    protected List<Operation> getLast1DaysExecutedSellOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast1DayExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nullable
    protected Operation getLast1MonthExecutedOperationIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        return filterLast(operations);
    }

    @Nullable
    protected Operation getLast1MonthExecutedBuyOperationIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast1MonthExecutedBuyOperationsIncludeEndDay(endDay);
        return filterLast(operations);
    }

    @Nullable
    protected Operation getLast1MonthExecutedSellOperationIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast1MonthExecutedSellOperationsIncludeEndDay(endDay);
        return filterLast(operations);
    }

    public TradeExtApi(Api api) {
        super(api);
    }
}
