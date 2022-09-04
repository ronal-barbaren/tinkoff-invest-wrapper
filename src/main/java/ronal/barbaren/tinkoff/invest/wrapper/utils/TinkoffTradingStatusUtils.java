package ronal.barbaren.tinkoff.invest.wrapper.utils;

import lombok.experimental.UtilityClass;
import ru.tinkoff.piapi.contract.v1.GetTradingStatusResponse;
import ru.tinkoff.piapi.contract.v1.SecurityTradingStatus;

@UtilityClass
public class TinkoffTradingStatusUtils {

    public static boolean isTradable(GetTradingStatusResponse tradingStatus) {
        return tradingStatus.getTradingStatus() == SecurityTradingStatus.SECURITY_TRADING_STATUS_NORMAL_TRADING;
    }
}
