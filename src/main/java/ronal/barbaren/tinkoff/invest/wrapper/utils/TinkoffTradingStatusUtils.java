package ronal.barbaren.tinkoff.invest.wrapper.utils;

import lombok.experimental.UtilityClass;
import ru.tinkoff.piapi.contract.v1.GetTradingStatusResponse;
import ru.tinkoff.piapi.contract.v1.SecurityTradingStatus;

import java.util.Objects;

@UtilityClass
public class TinkoffTradingStatusUtils {

    public static boolean isTradable(GetTradingStatusResponse tradingStatus) {
        if (Objects.isNull(tradingStatus))
            throw new NullPointerException("tradingStatus can't be null");
        return tradingStatus.getTradingStatus() == SecurityTradingStatus.SECURITY_TRADING_STATUS_NORMAL_TRADING;
    }
}
