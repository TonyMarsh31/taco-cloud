package rsocket;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RSocketClientConfiguration {

    @Bean
    public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder) {
        return args -> {
            String stockSymbol = "XYZ";

            RSocketRequester tcp = requesterBuilder.tcp("localhost", 7777);
            tcp
                    .route("stock/{symbol}", stockSymbol)
                    .retrieveFlux(StockQuote.class)
                    .doOnNext(stockQuote ->
                            log.info(
                                    "Price of {} : {} (at {})",
                                    stockQuote.getSymbol(),
                                    stockQuote.getPrice(),
                                    stockQuote.getTimestamp())
                    )
                    .subscribe();
        };
    }

}
