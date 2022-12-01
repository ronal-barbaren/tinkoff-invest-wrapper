package ronal.barbaren.tinkoff.invest.wrapper.utils;

import com.google.protobuf.Timestamp;
import lombok.experimental.UtilityClass;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Objects;

@UtilityClass
public class TinkoffTimestampUtils {

    @Nullable
    public static Instant toInstant(Timestamp date) {
        if (Objects.isNull(date))
            return null;
        return Instant.ofEpochSecond(date.getSeconds(), date.getNanos());
    }
}