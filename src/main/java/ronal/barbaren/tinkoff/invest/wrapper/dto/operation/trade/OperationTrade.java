package ronal.barbaren.tinkoff.invest.wrapper.dto.operation.trade;

import ronal.barbaren.tinkoff.invest.wrapper.dto.DtoDate;

import java.math.BigDecimal;

public interface OperationTrade extends DtoDate {
    String getId();

    BigDecimal getPrice();

    long getLots();
}
