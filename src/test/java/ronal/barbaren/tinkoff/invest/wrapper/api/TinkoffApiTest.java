package ronal.barbaren.tinkoff.invest.wrapper.api;

import org.junit.jupiter.api.Test;
import ronal.barbaren.tinkoff.invest.wrapper.BaseTest;
import ronal.barbaren.tinkoff.invest.wrapper.dto.amount.Amount;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;
import ronal.barbaren.tinkoff.invest.wrapper.dto.order.Order;
import ronal.barbaren.tinkoff.invest.wrapper.dto.position.TinkoffPosition;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TinkoffApiTest extends BaseTest {


    @Test
    void getNotExecutedOrders() throws IOException {
        TinkoffApi api = api();
        Set<Order> o = api.getNotExecutedOrders();
        assertTrue(o.isEmpty());
    }

    @Test
    void getCandles1Day() throws IOException {
        TinkoffApi api = api();
        Instant from = Instant.parse("2021-11-30T18:35:24.00Z");
        Instant to = Instant.parse("2021-12-30T18:35:24.00Z");
        List<Candle> candles = api.getCandles1Day(from, to);
        assertEquals(23, candles.size());
    }

    @Test
    void getCandles1Hour() throws IOException {
        TinkoffApi api = api();
        Instant from = Instant.parse("2021-12-20T18:35:24.00Z");
        Instant to = Instant.parse("2021-12-21T18:35:24.00Z");
        List<Candle> candles = api.getCandles1Hour(from, to);
        assertEquals(9, candles.size());
    }

    @Test
    void getCandles15Min() throws IOException {
        TinkoffApi api = api();
        Instant from = Instant.parse("2021-12-20T18:35:24.00Z");
        Instant to = Instant.parse("2021-12-21T18:35:24.00Z");
        List<Candle> candles = api.getCandles15Min(from, to);
        assertEquals(35, candles.size());
    }

    @Test
    void getCandles5Min() throws IOException {
        TinkoffApi api = api();
        Instant from = Instant.parse("2021-12-20T18:35:24.00Z");
        Instant to = Instant.parse("2021-12-21T18:35:24.00Z");
        List<Candle> candles = api.getCandles5Min(from, to);
        assertEquals(105, candles.size());
    }

    @Test
    void getCandles1Min() throws IOException {
        TinkoffApi api = api();
        Instant from = Instant.parse("2021-12-20T18:35:24.00Z");
        Instant to = Instant.parse("2021-12-21T18:35:24.00Z");
        List<Candle> candles = api.getCandles1Min(from, to);
        assertEquals(525, candles.size());
    }

    @Test
    void getAmounts() throws IOException {
        TinkoffApi api = api();
        Set<Amount> amounts = api.getAmounts();
        assertTrue(amounts.stream().map(Amount::getCurrency).anyMatch("rub"::equals));
        assertTrue(amounts.stream().map(Amount::getCurrency).anyMatch("usd"::equals));
    }

    @Test
    void getPosition() throws IOException {
        TinkoffApi api = api();
        TinkoffPosition position = api.getPosition();
        assertNotNull(position);
        assertEquals("BBG333333333", position.getFigi());
    }

    @Test
    void getLastPrice() throws IOException {
        TinkoffApi api = api();
        BigDecimal lastPrice = api.getLastPrice();
        assertNotNull(lastPrice);
    }

    @Test
    void getMinPriceIncrement() throws IOException {
        TinkoffApi api = api();
        BigDecimal price = api.getMinPriceIncrement();
        assertNotNull(price);
    }

    @Test
    void name() throws IOException {
        TinkoffApi api = api();
        Instant from = Instant.parse("2021-12-20T18:35:24.00Z");
        Instant to = Instant.parse("2021-12-21T18:35:24.00Z");
        Set<Operation> o = api.getExecutedOperations(from, to);
        assertTrue(o.isEmpty());
    }
}