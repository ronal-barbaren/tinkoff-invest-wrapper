package ronal.barbaren.tinkoff.invest.wrapper.utils;

import com.google.protobuf.Timestamp;
import lombok.experimental.UtilityClass;

import java.time.Instant;

@UtilityClass
public class TinkoffTimestampUtils {
    public static Instant toInstant(Timestamp date) {
        return Instant.ofEpochSecond(date.getSeconds(), date.getNanos());
    }
}