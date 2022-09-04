package ronal.barbaren.tinkoff.invest.wrapper.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ru.tinkoff.piapi.contract.v1.Operation;

import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;

@UtilityClass
public class TinkoffOperationUtils {

    public static Operation getLast(Collection<Operation> operations) {
        if (CollectionUtils.isEmpty(operations))
            return null;
        return operations.stream()
                .max(Comparator.comparing(TinkoffOperationUtils::operationCompareByDate))
                .orElseThrow();
    }

    private static Instant operationCompareByDate(Operation a) {
        return TinkoffTimestampUtils.toInstant(a.getDate());
    }

}
