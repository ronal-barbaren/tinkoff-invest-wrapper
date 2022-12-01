package ronal.barbaren.tinkoff.invest.wrapper.dto.operation.trade;

import java.math.BigDecimal;
import java.time.Instant;

public interface OperationTrade {
    String getId();

    BigDecimal getPrice();

    Instant getDate();

    long getLots();
}
