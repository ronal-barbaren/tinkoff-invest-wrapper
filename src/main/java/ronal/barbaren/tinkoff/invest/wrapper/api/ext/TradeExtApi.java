package ronal.barbaren.tinkoff.invest.wrapper.api.ext;

import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import static ronal.barbaren.instant.utils.InstantUtils.*;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoDateUtils.filterLast;

public class TradeExtApi extends BaseExtApi {

    @Nullable
    protected Candle get1DayCandle(Instant day) {
        Instant from = truncateDay(day);
        Instant to = plusDay(from);
        List<Candle> candles1Day = getCandles1Day(from, to);
        if (CollectionUtils.isEmpty(candles1Day))
            return null;
        return filterLast(candles1Day);
    }

    @Nonnull
    protected List<Candle> getLast1Year1DayCandlesExcludeEndDay(Instant endDay) {
        Instant to = truncateDay(endDay).minusSeconds(1L);
        Instant from = minusDay(to, 365);
        return getCandles1Day(from, endDay);
    }

    @Nonnull
    protected List<Candle> getCurrentDay1MinuteCandlesIncludeEndMinute(Instant endDate) {
        Instant from = truncateDay(endDate);
        Instant to = plusDay(endDate);
        return getCandles1Min(from, to);
    }

    @Nonnull
    protected Set<Operation> getLast1MonthExecutedOperationsIncludeEndDay(Instant endDay) {
        Instant to = truncateDay(plusDay(endDay));
        Instant from = minusMonth(to);
        return getExecutedOperations(from, to);
    }

    public TradeExtApi(Api api) {
        super(api);
    }
}
