package ronal.barbaren.tinkoff.invest.wrapper.dto.operation;

import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.trade.OperationTrade;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public interface Operation {
    String getId();

    BigDecimal getPrice();

    long getLots();

    boolean isExecuted();

    boolean isCancelled();

    boolean isProgress();

    boolean isPartial();

    boolean isBuy();

    boolean isSell();

    Instant getDate();

    Set<OperationTrade> getTrades();
}
