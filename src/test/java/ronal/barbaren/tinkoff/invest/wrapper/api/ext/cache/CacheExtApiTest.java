package ronal.barbaren.tinkoff.invest.wrapper.api.ext.cache;

import org.junit.jupiter.api.Test;
import ronal.barbaren.instant.utils.InstantUtils;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

class CacheExtApiTest {
    private static class MockedNow {
        private Instant now;
    }

    @Test
    void cache1Minute() {
        MockedNow mockedNow = new MockedNow();
        Api api = mock(Api.class);
        CacheExtApi cacheExtApi = new CacheExtApi(api) {
            @Override
            public Instant now() {
                return mockedNow.now;
            }
        };

        Instant now = InstantUtils.truncateMinute(Instant.now());
        List<Instant> seconds = IntStream.range(0, 6000)
                .mapToObj(i -> now.minus(i, ChronoUnit.SECONDS))
                .sorted()
                .toList();
        for (Instant s : seconds) {
            mockedNow.now = s;
            cacheExtApi.getCurrentDay1DayCandle();
        }
        verify(api, times(100)).getCandles1Day(any(), any());
    }
}