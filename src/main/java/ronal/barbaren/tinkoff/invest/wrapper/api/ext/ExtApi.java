package ronal.barbaren.tinkoff.invest.wrapper.api.ext;

import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Objects;

import static ronal.barbaren.instant.utils.InstantUtils.truncateDay;

public interface ExtApi extends CandleApi, OperationApi {
    @Nullable
    default Instant getLastTradeDayExcludeEndDay(Instant endDay) {
        Candle candle = getLastTrade1DayCandleExcludeEndDay(endDay);
        if (Objects.isNull(candle))
            return null;
        return truncateDay(candle.getDate());
    }
}
