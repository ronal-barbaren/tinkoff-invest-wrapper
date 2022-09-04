package ronal.barbaren.tinkoff.invest.wrapper.dto.position;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(of = "figi")
public class TinkoffPosition implements Position {
    private final BigDecimal averagePrice;
    private final BigDecimal currentPrice;
    private final long lots;
    private final BigDecimal expectedYield;
    private final String figi;

    public TinkoffPosition(ru.tinkoff.piapi.core.models.Position position) {
        this.averagePrice = position.getAveragePositionPrice().getValue();
        this.currentPrice = position.getCurrentPrice().getValue();
        this.lots = position.getQuantityLots().longValue();
        this.expectedYield = position.getExpectedYield();
        this.figi = position.getFigi();
    }
}
