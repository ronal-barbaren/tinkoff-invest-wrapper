package ronal.barbaren.tinkoff.invest.wrapper.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.wrapper.dto.DtoBuySell;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class DtoBuySellUtils {
    public static <T extends DtoBuySell> List<T> filterBuy(Collection<T> dto) {
        if (CollectionUtils.isEmpty(dto))
            return Collections.emptyList();
        return dto.stream().filter(DtoBuySell::isBuy).toList();
    }

    public static <T extends DtoBuySell> List<T> filterSell(Collection<T> dto) {
        if (CollectionUtils.isEmpty(dto))
            return Collections.emptyList();
        return dto.stream().filter(DtoBuySell::isSell).toList();
    }
}
