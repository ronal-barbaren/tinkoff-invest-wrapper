package ronal.barbaren.tinkoff.invest.wrapper.api.ext.cache;

import ronal.barbaren.instant.utils.InstantUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

class Cache {
    private final ConcurrentHashMap<String, CacheMethod> map = new ConcurrentHashMap<>();

    private <T> T update(String key, Supplier<T> func, Instant now) {
        T result = func.get();
        map.put(key, new CacheMethod(result, now));
        return result;
    }

    @SuppressWarnings("unchecked")
    protected <T> T get(String key, Supplier<T> func, ChronoUnit unit, int periods, Instant now) {
        CacheMethod cacheMethod = map.get(key);
        if (Objects.isNull(cacheMethod))
            return update(key, func, now);
        switch (unit) {
            case MINUTES, HOURS, DAYS -> {
                if (!isTimeChanged(cacheMethod.created, unit, periods, now))
                    return (T) cacheMethod.out;
                return update(key, func, now);
            }
            case MONTHS -> {
                if (!isMonthChanged(cacheMethod.created, periods, now))
                    return (T) cacheMethod.out;
                return update(key, func, now);
            }
            default -> throw new UnsupportedOperationException("supported only minutes/hours/days/month");
        }
    }

    protected boolean isTimeChanged(Instant time, ChronoUnit unit, int periods, Instant now) {
        if (Objects.isNull(time))
            return true;
        return !now.minus(periods, unit).isBefore(time);
    }

    protected boolean isMonthChanged(Instant time, int periods, Instant now) {
        if (Objects.isNull(time))
            return true;
        return !InstantUtils.minusMonth(now, periods).isBefore(time);
    }

    private record CacheMethod(Object out, Instant created) {
    }
}
