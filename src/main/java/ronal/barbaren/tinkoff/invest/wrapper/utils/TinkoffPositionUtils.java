package ronal.barbaren.tinkoff.invest.wrapper.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ru.tinkoff.piapi.core.models.Money;
import ru.tinkoff.piapi.core.models.Position;
import ru.tinkoff.piapi.core.models.Positions;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class TinkoffPositionUtils {

    @Nullable
    public static BigDecimal getAmount(String currency, Positions positions) {
        List<Money> all = positions.getMoney();
        List<Money> monies = all.stream().filter(a -> currency.equals(a.getCurrency())).toList();
        if (CollectionUtils.isEmpty(monies))
            return null;
        if (monies.size() > 1)
            throw new IllegalStateException("amount more than 1, currency: " + currency);
        Money currencyAmount = monies.get(0);
        return currencyAmount.getValue();
    }

    @Nullable
    public static BigDecimal getPositionAmount(Position position) {
        if (Objects.isNull(position))
            return null;
        BigDecimal price = position.getCurrentPrice().getValue();
        BigDecimal lots = position.getQuantityLots();
        return price.multiply(lots);
    }
}
