package ronal.barbaren.tinkoff.invest.wrapper.dto.order.stage;

import java.math.BigDecimal;

public interface OrderStage {
    String getId();

    long getLots();

    BigDecimal getPrice();
}
