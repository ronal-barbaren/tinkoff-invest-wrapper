package ronal.barbaren.tinkoff.invest.wrapper;

import lombok.Builder;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.wrapper.api.TinkoffApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {

    @Getter
    @Builder
    protected static class AppProperties {
        private String token;
        private String accountId;
        private String figi;
    }

    protected String getPropertiesPath() {
        return "app.properties";
    }

    protected Properties load(String path) throws IOException {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = loader.getResourceAsStream(path)) {
            properties.load(stream);
            return properties;
        }
    }

    protected AppProperties load() throws IOException {
        String path = getPropertiesPath();
        Properties properties = load(path);
        return AppProperties.builder()
                .accountId(properties.getProperty("account-id"))
                .token(properties.getProperty("token"))
                .figi(properties.getProperty("figi"))
                .build();
    }

    protected TinkoffApi api() throws IOException {
        AppProperties p = load();
        return new TinkoffApi(p.getToken(), p.getAccountId(), p.getFigi());
    }
}
