package ronal.barbaren.tinkoff.invest.wrapper.dto.order.stage;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.tinkoff.piapi.core.utils.MapperUtils;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(of = "id")
public class TinkoffOrderStage implements OrderStage {
    private final long lots;
    private final BigDecimal price;
    private final String id;

    public TinkoffOrderStage(ru.tinkoff.piapi.contract.v1.OrderStage stage) {
        this.lots = stage.getQuantity();
        this.price = MapperUtils.moneyValueToBigDecimal(stage.getPrice());
        this.id = stage.getTradeId();
    }

}
