package ronal.barbaren.tinkoff.invest.wrapper.api.ext;

import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;

import static ronal.barbaren.instant.utils.InstantUtils.*;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoDateUtils.*;

interface CandleApi extends Api {
    @Nullable
    default Candle get1DayCandle(Instant day) {
        Instant from = truncateDay(day);
        Instant to = plusDay(from);
        List<Candle> candles1Day = getCandles1Day(to, from);
        if (CollectionUtils.isEmpty(candles1Day))
            return null;
        return filterLast(candles1Day);
    }

    @Nullable
    default Candle getLastTrade1DayCandleExcludeEndDay(Instant endDay) {
        List<Candle> candles = getLastYear1DayCandlesIncludeEndDay(endDay);
        return filterLastBefore(candles, truncateDay(endDay));
    }

    @Nonnull
    default List<Candle> getLastYear1DayCandlesIncludeEndDay(Instant endDay) {
        Instant to = truncateDay(plusDay(endDay));
        Instant from = minusDay(to, 365);
        return getCandles1Day(from, endDay);
    }

    @Nonnull
    default List<Candle> getLast1Month1DayCandlesIncludeEndDay(Instant endDay) {
        List<Candle> candles = getLastYear1DayCandlesIncludeEndDay(endDay);
        Instant from = minusMonth(endDay);
        return filterAfter(candles, from);
    }

    @Nonnull
    default List<Candle> getLast5Days1DayCandlesIncludeEndDay(Instant endDay) {
        List<Candle> candles = getLast1Month1DayCandlesIncludeEndDay(endDay);
        Instant from = minusDay(endDay, 5);
        return filterAfter(candles, from);
    }

    @Nonnull
    default List<Candle> getLast10Days1DayCandlesIncludeEndDay(Instant endDay) {
        List<Candle> candles = getLast1Month1DayCandlesIncludeEndDay(endDay);
        Instant from = minusDay(endDay, 10);
        return filterAfter(candles, from);
    }

    @Nonnull
    default List<Candle> getLast20Days1DayCandlesIncludeEndDay(Instant endDay) {
        List<Candle> candles = getLast1Month1DayCandlesIncludeEndDay(endDay);
        Instant from = minusDay(endDay, 20);
        return filterAfter(candles, from);
    }

    @Nonnull
    default List<Candle> getCurrentDay1MinuteCandlesIncludeEndMinute(Instant endDate) {
        Instant from = truncateDay(endDate);
        Instant to = plusDay(endDate);
        return getCandles1Min(from, to);
    }

    @Nonnull
    default List<Candle> getLastHour1MinuteCandlesIncludeEndMinute(Instant endDate) {
        List<Candle> day = getCurrentDay1MinuteCandlesIncludeEndMinute(endDate);
        Instant hour = minusHour(endDate);
        return filterAfter(day, hour);
    }

    @Nonnull
    default List<Candle> getLast5Minute1MinuteCandlesIncludeEndMinute(Instant endDate) {
        List<Candle> candles = getLastHour1MinuteCandlesIncludeEndMinute(endDate);
        Instant from = minusMinute(endDate, 5);
        return filterAfter(candles, from);
    }

    @Nonnull
    default List<Candle> getLast10Minute1MinuteCandles(Instant endDate) {
        List<Candle> candles = getLastHour1MinuteCandlesIncludeEndMinute(endDate);
        Instant from = minusMinute(endDate, 10);
        return filterAfter(candles, from);
    }

    @Nonnull
    default List<Candle> getLast20Minute1MinuteCandles(Instant endDate) {
        List<Candle> candles = getLastHour1MinuteCandlesIncludeEndMinute(endDate);
        Instant from = minusMinute(endDate, 20);
        return filterAfter(candles, from);
    }

    @Nonnull
    default List<Candle> getLast30Minute1MinuteCandles(Instant endDate) {
        List<Candle> candles = getLastHour1MinuteCandlesIncludeEndMinute(endDate);
        Instant from = minusMinute(endDate, 30);
        return filterAfter(candles, from);
    }
}
