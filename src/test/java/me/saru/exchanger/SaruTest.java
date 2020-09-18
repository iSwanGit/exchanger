package me.saru.exchanger;

import me.saru.exchanger.data.CurrencyData;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class SaruTest {

    private final String TEST_URL = "http://api.currencylayer.com/live?access_key=0515a49dfab221e7ab4317ca087a516e&currencies=KRW,JPY,PHP&format=1";
    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void apiTest() throws Exception {
        ResponseEntity<CurrencyData.Res> entity = restTemplate.getForEntity(TEST_URL, CurrencyData.Res.class);
        CurrencyData.Res body = entity.getBody();
        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isTrue();
        assertThat(body.getJpy()).isNotNull();
        assertThat(body.getKrw()).isNotNull();
        assertThat(body.getPhp()).isNotNull();
    }
}
