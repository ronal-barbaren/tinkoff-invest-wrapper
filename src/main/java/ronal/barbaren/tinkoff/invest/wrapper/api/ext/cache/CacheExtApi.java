package ronal.barbaren.tinkoff.invest.wrapper.api.ext.cache;

import ronal.barbaren.tinkoff.invest.wrapper.api.Api;
import ronal.barbaren.tinkoff.invest.wrapper.api.ext.TradeExtApi;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static ronal.barbaren.instant.utils.InstantUtils.*;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoBuySellUtils.filterBuy;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoBuySellUtils.filterSell;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoDateUtils.filterAfter;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoDateUtils.filterLast;

public class CacheExtApi extends TradeExtApi {
    private final Cache cache = new Cache();

    @Nullable
    public Instant getLastTradeDayExcludeCurrentDay() {
        return cache1Day("getLastTradeDayExcludeCurrentDay", () -> {
            Candle day = getLastTrade1DayCandleExcludeCurrentDay();
            if (Objects.isNull(day))
                return null;
            return day.getDate();
        });
    }

    @Nullable
    public Candle getCurrentDay1DayCandle() {
        return cache1Minute("getCurrentDay1DayCandle", this::get1DayCandle);
    }

    @Nullable
    public Candle getLastTrade1DayCandleExcludeCurrentDay() {
        return cache1Day("getLastTrade1DayCandleExcludeCurrentDay", () -> filterLast(getLast5Days1DayCandlesExcludeCurrentDay()));
    }

    @Nonnull
    public List<Candle> getLast1Year1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast1Year1DayCandlesExcludeCurrentDay", this::getLast1Year1DayCandlesExcludeEndDay);
    }

    @Nonnull
    public List<Candle> getLast1Month1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast1Month1DayCandlesExcludeCurrentDay", () -> {
            Instant day = truncateDay(now());
            List<Candle> candles = getLast1Year1DayCandlesExcludeCurrentDay();
            Instant from = minusMonth(day);
            return filterAfter(candles, from);
        });
    }

    @Nonnull
    public List<Candle> getLast5Days1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast5Days1DayCandlesExcludeCurrentDay", () -> {
            Instant day = truncateDay(now());
            List<Candle> candles = getLast10Days1DayCandlesExcludeCurrentDay();
            Instant from = minusDay(day, 5);
            return filterAfter(candles, from);
        });
    }

    @Nonnull
    public List<Candle> getLast10Days1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast10Days1DayCandlesExcludeCurrentDay", () -> {
            Instant day = truncateDay(now());
            List<Candle> candles = getLast20Days1DayCandlesExcludeCurrentDay();
            Instant from = minusDay(day, 10);
            return filterAfter(candles, from);
        });
    }

    @Nonnull
    public List<Candle> getLast20Days1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast20Days1DayCandlesExcludeCurrentDay", () -> {
            Instant day = truncateDay(now());
            List<Candle> candles = getLast1Month1DayCandlesExcludeCurrentDay();
            Instant from = minusDay(day, 20);
            return filterAfter(candles, from);
        });
    }

    @Nonnull
    public List<Candle> getCurrentDay1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getCurrentDay1MinuteCandlesIncludeCurrentMinute", this::getCurrentDay1MinuteCandlesIncludeEndMinute);
    }

    @Nonnull
    public List<Candle> getLastHour1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLastHour1MinuteCandlesIncludeCurrentMinute", () -> {
            Instant now = now();
            List<Candle> dayCandles = getCurrentDay1MinuteCandlesIncludeCurrentMinute();
            Instant hour = minusHour(now);
            return filterAfter(dayCandles, hour);
        });
    }

    @Nonnull
    public List<Candle> getLast5Minute1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLast5Minute1MinuteCandlesIncludeCurrentMinute", () -> {
            Instant now = now();
            List<Candle> dayCandles = getLast10Minute1MinuteCandlesIncludeCurrentMinute();
            Instant from = minusMinute(now, 5);
            return filterAfter(dayCandles, from);
        });
    }

    @Nonnull
    public List<Candle> getLast10Minute1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLast10Minute1MinuteCandlesIncludeCurrentMinute", () -> {
            Instant now = now();
            List<Candle> dayCandles = getLast20Minute1MinuteCandlesIncludeCurrentMinute();
            Instant from = minusMinute(now, 10);
            return filterAfter(dayCandles, from);
        });
    }

    @Nonnull
    public List<Candle> getLast20Minute1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLast20Minute1MinuteCandlesIncludeCurrentMinute", () -> {
            Instant now = now();
            List<Candle> dayCandles = getLast30Minute1MinuteCandlesIncludeCurrentMinute();
            Instant from = minusMinute(now, 20);
            return filterAfter(dayCandles, from);
        });
    }

    @Nonnull
    public List<Candle> getLast30Minute1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLast30Minute1MinuteCandlesIncludeCurrentMinute", () -> {
            Instant now = now();
            List<Candle> dayCandles = getLastHour1MinuteCandlesIncludeCurrentMinute();
            Instant from = minusMinute(now, 30);
            return filterAfter(dayCandles, from);
        });
    }

    @Nonnull
    public Set<Operation> getLast1MonthExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1MonthExecutedOperationsIncludeCurrentDay", this::getLast1MonthExecutedOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast1MonthExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1MonthExecutedBuyOperationsIncludeCurrentDay", () -> {
            Set<Operation> operations = getLast1MonthExecutedOperationsIncludeCurrentDay();
            return filterBuy(operations);
        });
    }

    @Nonnull
    public List<Operation> getLast1MonthExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1MonthExecutedSellOperationsIncludeCurrentDay", () -> {
            Set<Operation> operations = getLast1MonthExecutedOperationsIncludeCurrentDay();
            return filterSell(operations);
        });
    }

    @Nonnull
    public List<Operation> getLast20DaysExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast20DaysExecutedOperationsIncludeCurrentDay", () -> {
            Instant now = now();
            Set<Operation> operations = getLast1MonthExecutedOperationsIncludeCurrentDay();
            Instant from = minusDay(now, 20);
            return filterAfter(operations, from);
        });
    }

    @Nonnull
    public List<Operation> getLast20DaysExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast20DaysExecutedBuyOperationsIncludeCurrentDay", () -> {
            List<Operation> operations = getLast20DaysExecutedOperationsIncludeCurrentDay();
            return filterBuy(operations);
        });
    }

    @Nonnull
    public List<Operation> getLast20DaysExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast20DaysExecutedSellOperationsIncludeCurrentDay", () -> {
            List<Operation> operations = getLast20DaysExecutedOperationsIncludeCurrentDay();
            return filterSell(operations);
        });
    }

    @Nonnull
    public List<Operation> getLast10DaysExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast10DaysExecutedOperationsIncludeCurrentDay", () -> {
            Instant now = now();
            List<Operation> operations = getLast20DaysExecutedOperationsIncludeCurrentDay();
            Instant from = minusDay(now, 10);
            return filterAfter(operations, from);
        });
    }

    @Nonnull
    public List<Operation> getLast10DaysExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast10DaysExecutedBuyOperationsIncludeCurrentDay", () -> {
            List<Operation> operations = getLast10DaysExecutedOperationsIncludeCurrentDay();
            return filterBuy(operations);
        });
    }

    @Nonnull
    public List<Operation> getLast10DaysExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast10DaysExecutedSellOperationsIncludeCurrentDay", () -> {
            List<Operation> operations = getLast10DaysExecutedOperationsIncludeCurrentDay();
            return filterSell(operations);
        });
    }

    @Nonnull
    public List<Operation> getLast5DaysExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast5DaysExecutedOperationsIncludeCurrentDay", () -> {
            Instant now = now();
            List<Operation> operations = getLast10DaysExecutedOperationsIncludeCurrentDay();
            Instant from = minusDay(now, 5);
            return filterAfter(operations, from);
        });
    }

    @Nonnull
    public List<Operation> getLast5DaysExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast5DaysExecutedBuyOperationsIncludeCurrentDay", () -> {
            List<Operation> operations = getLast5DaysExecutedOperationsIncludeCurrentDay();
            return filterBuy(operations);
        });
    }

    @Nonnull
    public List<Operation> getLast5DaysExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast5DaysExecutedSellOperationsIncludeCurrentDay", () -> {
            List<Operation> operations = getLast5DaysExecutedOperationsIncludeCurrentDay();
            return filterSell(operations);
        });
    }

    @Nonnull
    public List<Operation> getLast1DayExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1DayExecutedOperationsIncludeCurrentDay", () -> {
            Instant now = now();
            List<Operation> operations = getLast5DaysExecutedOperationsIncludeCurrentDay();
            Instant from = minusDay(now);
            return filterAfter(operations, from);
        });
    }

    @Nonnull
    public List<Operation> getLast1DaysExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1DaysExecutedBuyOperationsIncludeCurrentDay", () -> {
            List<Operation> operations = getLast1DayExecutedOperationsIncludeCurrentDay();
            return filterBuy(operations);
        });
    }

    @Nonnull
    public List<Operation> getLast1DaysExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1DaysExecutedSellOperationsIncludeCurrentDay", () -> {
            List<Operation> operations = getLast1DayExecutedOperationsIncludeCurrentDay();
            return filterSell(operations);
        });
    }

    @Nullable
    public Operation getLast1DayExecutedOperationIncludeCurrentDay() {
        return cache1Minute("getLast1DayExecutedOperationIncludeCurrentDay", () -> {
            List<Operation> operations = getLast1DayExecutedOperationsIncludeCurrentDay();
            return filterLast(operations);
        });
    }

    @Nullable
    public Operation getLast1DayExecutedBuyOperationIncludeCurrentDay() {
        return cache1Minute("getLast1DayExecutedBuyOperationIncludeCurrentDay", () -> {
            List<Operation> operations = getLast1DaysExecutedBuyOperationsIncludeCurrentDay();
            return filterLast(operations);
        });
    }

    @Nullable
    public Operation getLast1DayExecutedSellOperationIncludeCurrentDay() {
        return cache1Minute("getLast1DayExecutedSellOperationIncludeCurrentDay", () -> {
            List<Operation> operations = getLast1DaysExecutedSellOperationsIncludeCurrentDay();
            return filterLast(operations);
        });
    }

    @Nullable
    public List<Operation> getLast1DayBuyOperationsAfterLastSell() {
        return cache1Minute("getLast1DayBuyOperationsAfterLastSell", () -> {
            List<Operation> sell = getLast1DaysExecutedSellOperationsIncludeCurrentDay();
            Operation lSell = filterLast(sell);
            List<Operation> buy = getLast1DaysExecutedBuyOperationsIncludeCurrentDay();
            if (Objects.isNull(lSell))
                return buy;
            return filterAfter(buy, lSell.getDate());
        });
    }

    @Nullable
    public List<Operation> getLast1DaySellOperationsAfterLastBuy() {
        return cache1Minute("getLast1DaySellOperationsAfterLastBuy", () -> {
            List<Operation> buy = getLast1DaysExecutedBuyOperationsIncludeCurrentDay();
            Operation lBuy = filterLast(buy);
            List<Operation> sell = getLast1DaysExecutedSellOperationsIncludeCurrentDay();
            if (Objects.isNull(lBuy))
                return sell;
            return filterAfter(sell, lBuy.getDate());
        });
    }

    @Nullable
    public BigDecimal getCurrentAndLastTradeDayPriceDelta() {
        return cache1Minute("getCurrentAndLastTradeDayPriceDelta", () -> {
            Candle currentDay = getCurrentDay1DayCandle();
            Candle lastTradeDay = getLastTrade1DayCandleExcludeCurrentDay();
            if (Objects.isNull(currentDay)) {
                if (Objects.isNull(lastTradeDay))
                    return null;
                return lastTradeDay.getMaxPrice().subtract(lastTradeDay.getMinPrice());
            }
            if (Objects.isNull(lastTradeDay))
                return currentDay.getMaxPrice().subtract(currentDay.getMinPrice());
            BigDecimal max = currentDay.getMaxPrice().max(lastTradeDay.getMaxPrice());
            BigDecimal min = currentDay.getMinPrice().min(lastTradeDay.getMinPrice());
            return max.subtract(min);
        });
    }

    protected <T> T cache(String key, Function<Instant, T> func, ChronoUnit unit, int periods) {
        Instant now = now();
        return cache.get(key, () -> func.apply(now), unit, periods, now);
    }

    protected <T> T cache(String key, Supplier<T> func, ChronoUnit unit, int periods) {
        Instant now = now();
        return cache.get(key, func, unit, periods, now);
    }

    protected <T> T cache1Day(String key, Supplier<T> func, Instant now) {
        return cache.get(key, func, ChronoUnit.DAYS, 1, now);
    }

    protected <T> T cache1Day(String key, Function<Instant, T> func) {
        return cache(key, func, ChronoUnit.DAYS, 1);
    }

    protected <T> T cache1Day(String key, Supplier<T> func) {
        return cache1Day(key, func, now());
    }

    protected <T> T cache1Minute(String key, Function<Instant, T> func) {
        return cache(key, func, ChronoUnit.MINUTES, 1);
    }

    protected <T> T cache1Minute(String key, Supplier<T> func) {
        return cache(key, func, ChronoUnit.MINUTES, 1);
    }

    protected <T> T cache1Hour(String key, Function<Instant, T> func) {
        return cache(key, func, ChronoUnit.HOURS, 1);
    }


    protected <T> T cache1Minute(String key, Supplier<T> func, Instant now) {
        return cache.get(key, func, ChronoUnit.MINUTES, 1, now);
    }

    protected <T> T cache1Hour(String key, Supplier<T> func, Instant now) {
        return cache.get(key, func, ChronoUnit.HOURS, 1, now);
    }

    public Instant now() {
        return Instant.now();
    }

    public CacheExtApi(Api api) {
        super(api);
    }
}
