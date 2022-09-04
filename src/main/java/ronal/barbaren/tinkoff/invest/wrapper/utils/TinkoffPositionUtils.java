package ronal.barbaren.tinkoff.invest.wrapper.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ru.tinkoff.piapi.core.models.Money;
import ru.tinkoff.piapi.core.models.Position;
import ru.tinkoff.piapi.core.models.Positions;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class TinkoffPositionUtils {

    public static BigDecimal getAmount(String currency, Positions positions) {
        List<Money> all = positions.getMoney();
        List<Money> monies = all.stream().filter(a -> currency.equals(a.getCurrency())).toList();
        if (CollectionUtils.isEmpty(monies))
            throw new IllegalStateException("amount not found, currency: " + currency);
        if (monies.size() > 1)
            throw new IllegalStateException("amount more than 1, currency: " + currency);
        Money currencyAmount = monies.get(0);
        return currencyAmount.getValue();
    }

    public static BigDecimal getPositionAmount(Position position) {
        BigDecimal price = position.getCurrentPrice().getValue();
        BigDecimal lots = position.getQuantityLots();
        return price.multiply(lots);
    }
}
