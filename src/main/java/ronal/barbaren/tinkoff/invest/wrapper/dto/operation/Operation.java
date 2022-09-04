package ronal.barbaren.tinkoff.invest.wrapper.dto.operation;

import java.math.BigDecimal;
import java.time.Instant;

public interface Operation {
    String getId();

    BigDecimal getPrice();

    long getLots();

    boolean isExecuted();

    boolean isBuy();

    boolean isSell();

    Instant getDate();
}
