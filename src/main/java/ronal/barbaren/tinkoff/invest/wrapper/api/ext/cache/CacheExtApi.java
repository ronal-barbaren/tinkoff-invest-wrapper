package ronal.barbaren.tinkoff.invest.wrapper.api.ext.cache;

import ronal.barbaren.tinkoff.invest.wrapper.api.Api;
import ronal.barbaren.tinkoff.invest.wrapper.api.ext.TradeExtApi;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class CacheExtApi extends TradeExtApi {
    private final Cache cache = new Cache();

    @Nullable
    public Instant getLastTradeDayExcludeCurrentDay() {
        return cache1Day("getLastTradeDayExcludeCurrentDay", this::getLastTradeDayExcludeEndDay);
    }

    @Nullable
    public Candle getCurrentDay1DayCandle() {
        return cache1Minute("getCurrentDay1DayCandle", this::get1DayCandle);
    }

    @Nullable
    public Candle getLastTrade1DayCandleExcludeCurrentDay() {
        return cache1Day("getLastTrade1DayCandleExcludeCurrentDay", this::getLastTrade1DayCandleExcludeEndDay);
    }

    @Nonnull
    public List<Candle> getLast1Year1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast1Year1DayCandlesExcludeCurrentDay", this::getLast1Year1DayCandlesExcludeEndDay);
    }

    @Nonnull
    public List<Candle> getLast1Month1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast1Month1DayCandlesExcludeCurrentDay", this::getLast1Month1DayCandlesExcludeEndDay);
    }

    @Nonnull
    public List<Candle> getLast5Days1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast5Days1DayCandlesExcludeCurrentDay", this::getLast5Days1DayCandlesExcludeEndDay);
    }

    @Nonnull
    public List<Candle> getLast10Days1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast10Days1DayCandlesExcludeCurrentDay", this::getLast10Days1DayCandlesExcludeEndDay);
    }

    @Nonnull
    public List<Candle> getLast20Days1DayCandlesExcludeCurrentDay() {
        return cache1Day("getLast20Days1DayCandlesExcludeCurrentDay", this::getLast20Days1DayCandlesExcludeEndDay);
    }

    @Nonnull
    public List<Candle> getCurrentDay1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getCurrentDay1MinuteCandlesIncludeCurrentMinute", this::getCurrentDay1MinuteCandlesIncludeEndMinute);
    }

    @Nonnull
    public List<Candle> getLastHour1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLastHour1MinuteCandlesIncludeCurrentMinute", this::getLastHour1MinuteCandlesIncludeEndMinute);
    }

    @Nonnull
    public List<Candle> getLast5Minute1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLast5Minute1MinuteCandlesIncludeCurrentMinute", this::getLast5Minute1MinuteCandlesIncludeEndMinute);
    }

    @Nonnull
    public List<Candle> getLast10Minute1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLast10Minute1MinuteCandlesIncludeCurrentMinute", this::getLast10Minute1MinuteCandlesIncludeEndMinute);
    }

    @Nonnull
    public List<Candle> getLast20Minute1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLast20Minute1MinuteCandlesIncludeCurrentMinute", this::getLast20Minute1MinuteCandlesIncludeEndMinute);
    }

    @Nonnull
    public List<Candle> getLast30Minute1MinuteCandlesIncludeCurrentMinute() {
        return cache1Minute("getLast30Minute1MinuteCandlesIncludeCurrentMinute", this::getLast30Minute1MinuteCandlesIncludeEndMinute);
    }

    @Nonnull
    public Set<Operation> getLast1MonthExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1MonthExecutedOperationsIncludeCurrentDay", this::getLast1MonthExecutedOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast1MonthExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1MonthExecutedBuyOperationsIncludeCurrentDay", this::getLast1MonthExecutedBuyOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast1MonthExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1MonthExecutedSellOperationsIncludeCurrentDay", this::getLast1MonthExecutedSellOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast20DaysExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast20DaysExecutedOperationsIncludeCurrentDay", this::getLast20DaysExecutedOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast20DaysExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast20DaysExecutedBuyOperationsIncludeCurrentDay", this::getLast20DaysExecutedBuyOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast20DaysExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast20DaysExecutedSellOperationsIncludeCurrentDay", this::getLast20DaysExecutedSellOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast10DaysExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast10DaysExecutedOperationsIncludeCurrentDay", this::getLast10DaysExecutedOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast10DaysExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast10DaysExecutedBuyOperationsIncludeCurrentDay", this::getLast10DaysExecutedBuyOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast10DaysExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast10DaysExecutedSellOperationsIncludeCurrentDay", this::getLast10DaysExecutedSellOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast5DaysExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast5DaysExecutedOperationsIncludeCurrentDay", this::getLast5DaysExecutedOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast5DaysExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast5DaysExecutedBuyOperationsIncludeCurrentDay", this::getLast5DaysExecutedBuyOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast5DaysExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast5DaysExecutedSellOperationsIncludeCurrentDay", this::getLast5DaysExecutedSellOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast1DayExecutedOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1DayExecutedOperationsIncludeCurrentDay", this::getLast1DayExecutedOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast1DaysExecutedBuyOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1DaysExecutedBuyOperationsIncludeCurrentDay", this::getLast1DaysExecutedBuyOperationsIncludeEndDay);
    }

    @Nonnull
    public List<Operation> getLast1DaysExecutedSellOperationsIncludeCurrentDay() {
        return cache1Minute("getLast1DaysExecutedSellOperationsIncludeCurrentDay", this::getLast1DaysExecutedSellOperationsIncludeEndDay);
    }

    @Nullable
    public Operation getLast1MonthExecutedOperationIncludeCurrentDay() {
        return cache1Minute("getLast1MonthExecutedOperationIncludeCurrentDay", this::getLast1MonthExecutedOperationIncludeEndDay);
    }

    @Nullable
    public Operation getLast1MonthExecutedBuyOperationIncludeCurrentDay() {
        return cache1Minute("getLast1MonthExecutedBuyOperationIncludeCurrentDay", this::getLast1MonthExecutedBuyOperationIncludeEndDay);
    }

    @Nullable
    public Operation getLast1MonthExecutedSellOperationIncludeCurrentDay() {
        return cache1Minute("getLast1MonthExecutedSellOperationIncludeCurrentDay", this::getLast1MonthExecutedSellOperationIncludeEndDay);
    }

    protected <T> T cache(String key, Function<Instant, T> func, ChronoUnit unit, int periods) {
        Instant now = now();
        return cache.get(key, () -> func.apply(now), unit, periods, now);
    }

    protected <T> T cache1Day(String key, Function<Instant, T> func) {
        return cache(key, func, ChronoUnit.DAYS, 1);
    }

    protected <T> T cache1Minute(String key, Function<Instant, T> func) {
        return cache(key, func, ChronoUnit.MINUTES, 1);
    }

    protected <T> T cache1Hour(String key, Function<Instant, T> func, Instant now) {
        return cache(key, func, ChronoUnit.HOURS, 1);
    }

    protected <T> T cache1Day(String key, Supplier<T> func, Instant now) {
        return cache.get(key, func, ChronoUnit.DAYS, 1, now);
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
