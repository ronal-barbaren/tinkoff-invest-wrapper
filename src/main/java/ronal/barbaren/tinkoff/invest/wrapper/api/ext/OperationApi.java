package ronal.barbaren.tinkoff.invest.wrapper.api.ext;

import ronal.barbaren.tinkoff.invest.wrapper.api.Api;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import static ronal.barbaren.instant.utils.InstantUtils.*;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoBuySellUtils.filterBuy;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoBuySellUtils.filterSell;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoDateUtils.filterAfter;
import static ronal.barbaren.tinkoff.invest.wrapper.utils.DtoDateUtils.filterLast;

interface OperationApi extends Api {
    @Nonnull
    default Set<Operation> getLast1MonthExecutedOperationsIncludeEndDay(Instant endDay) {
        Instant to = truncateDay(plusDay(endDay));
        Instant from = minusMonth(to);
        return getExecutedOperations(from, to);
    }

    @Nonnull
    default List<Operation> getLast1MonthExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    default List<Operation> getLast1MonthExecutedSellOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nonnull
    default List<Operation> getLast20DaysExecutedOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        Instant from = minusDay(truncateDay(endDay), 20);
        return filterAfter(operations, from);
    }

    @Nonnull
    default List<Operation> getLast20DaysExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast20DaysExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    default List<Operation> getLast20DaysExecutedSellOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast20DaysExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nonnull
    default List<Operation> getLast10DaysExecutedOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        Instant from = minusDay(truncateDay(endDay), 10);
        return filterAfter(operations, from);
    }

    @Nonnull
    default List<Operation> getLast10DaysExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast10DaysExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    default List<Operation> getLast10DaysExecutedSellOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast10DaysExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nonnull
    default List<Operation> getLast5DaysExecutedOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        Instant from = minusDay(truncateDay(endDay), 5);
        return filterAfter(operations, from);
    }

    @Nonnull
    default List<Operation> getLast5DaysExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast5DaysExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    default List<Operation> getLast5DaysExecutedSellOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast5DaysExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nonnull
    default List<Operation> getLast1DayExecutedOperationsIncludeEndDay(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        Instant from = minusDay(truncateDay(endDay));
        return filterAfter(operations, from);
    }

    @Nonnull
    default List<Operation> getLast1DaysExecutedBuyOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast1DayExecutedOperationsIncludeEndDay(endDay);
        return filterBuy(operations);
    }

    @Nonnull
    default List<Operation> getLast1DaysExecutedSellOperationsIncludeEndDay(Instant endDay) {
        List<Operation> operations = getLast1DayExecutedOperationsIncludeEndDay(endDay);
        return filterSell(operations);
    }

    @Nullable
    default Operation getLast1MonthExecutedOperation(Instant endDay) {
        Set<Operation> operations = getLast1MonthExecutedOperationsIncludeEndDay(endDay);
        return filterLast(operations);
    }

    @Nullable
    default Operation getLast1MonthExecutedBuyOperation(Instant endDay) {
        List<Operation> operations = getLast1MonthExecutedBuyOperationsIncludeEndDay(endDay);
        return filterLast(operations);
    }

    @Nullable
    default Operation getLast1MonthExecutedSellOperation(Instant endDay) {
        List<Operation> operations = getLast1MonthExecutedSellOperationsIncludeEndDay(endDay);
        return filterLast(operations);
    }
}
