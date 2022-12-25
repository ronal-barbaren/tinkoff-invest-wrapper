package ronal.barbaren.tinkoff.invest.wrapper.dto.operation;

import ronal.barbaren.tinkoff.invest.wrapper.dto.DtoBuySell;
import ronal.barbaren.tinkoff.invest.wrapper.dto.DtoDate;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.trade.OperationTrade;

import java.math.BigDecimal;
import java.util.Set;

public interface Operation extends DtoDate, DtoBuySell {
    String getId();

    BigDecimal getPrice();

    long getLots();

    boolean isExecuted();

    boolean isCancelled();

    boolean isProgress();

    boolean isPartial();

    Set<OperationTrade> getTrades();
}
