package ronal.barbaren.tinkoff.invest.wrapper.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.wrapper.dto.DtoDate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Instant;
import java.util.*;

@UtilityClass
public class DtoDateUtils {
    @Nullable
    public static <T extends DtoDate> T filterLast(Collection<T> dto) {
        if (CollectionUtils.isEmpty(dto))
            return null;
        return dto.stream()
                .filter(Objects::nonNull)
                .filter(a -> Objects.nonNull(a.getDate()))
                .max(Comparator.comparing(T::getDate))
                .orElse(null);
    }

    @Nullable
    public static <T extends DtoDate> T filterLastBefore(Collection<T> dto, Instant endDate) {
        if (CollectionUtils.isEmpty(dto))
            return null;
        return dto.stream()
                .filter(Objects::nonNull)
                .filter(a -> Objects.nonNull(a.getDate()))
                .filter(a -> a.getDate().isBefore(endDate))
                .max(Comparator.comparing(T::getDate))
                .orElse(null);
    }

    @Nonnull
    public static <T extends DtoDate> List<T> filterBetween(Collection<T> dto, Instant from, Instant to) {
        if (CollectionUtils.isEmpty(dto))
            return Collections.emptyList();
        return dto.stream()
                .filter(Objects::nonNull)
                .filter(a -> Objects.nonNull(a.getDate()))
                .filter(a -> a.getDate().isAfter(from))
                .filter(a -> a.getDate().isBefore(to))
                .toList();
    }

    @Nonnull
    public static <T extends DtoDate> List<T> filterAfter(Collection<T> dto, Instant from) {
        if (CollectionUtils.isEmpty(dto))
            return Collections.emptyList();
        return dto.stream()
                .filter(Objects::nonNull)
                .filter(a -> Objects.nonNull(a.getDate()))
                .filter(a -> a.getDate().isAfter(from))
                .toList();
    }

    @Nonnull
    public static <T extends DtoDate> List<T> filterBefore(Collection<T> dto, Instant to) {
        if (CollectionUtils.isEmpty(dto))
            return Collections.emptyList();
        return dto.stream()
                .filter(Objects::nonNull)
                .filter(a -> Objects.nonNull(a.getDate()))
                .filter(a -> a.getDate().isBefore(to))
                .toList();
    }
}
