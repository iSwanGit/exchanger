package me.saru.exchanger.controller.api;

import me.saru.exchanger.config.ConfigProperties;
import me.saru.exchanger.data.CurrencyData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@EnableConfigurationProperties(value = ConfigProperties.class)
@ActiveProfiles("test")
public class ExternalApiTests {
    @Autowired
    private ConfigProperties configProperties;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void api_요청_정상_성공() throws Exception {
        String testUrl = "http://api.currencylayer.com/live?access_key=" + configProperties.getAccessKey() + "&currencies=" +
                String.join(",", configProperties.getCurrencies()) + "&format=1";

        ResponseEntity<CurrencyData.Res> entity = restTemplate.getForEntity(testUrl, CurrencyData.Res.class);
        CurrencyData.Res body = entity.getBody();
        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isTrue();
        assertThat(body.getJpy()).isNotNull();
        assertThat(body.getKrw()).isNotNull();
        assertThat(body.getPhp()).isNotNull();
    }

    @Test
    void 수취국가가_2개인_경우() {
        String testUrl = "http://api.currencylayer.com/live?access_key=" + configProperties.getAccessKey() + "&currencies=" +
                String.join(",", configProperties.getCurrencies()).replace("PHP", "") + "&format=1";

        ResponseEntity<CurrencyData.Res> entity = restTemplate.getForEntity(testUrl, CurrencyData.Res.class);
        CurrencyData.Res body = entity.getBody();
        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isTrue();
        assertThat(body.getJpy()).isNotNull();
        assertThat(body.getKrw()).isNotNull();
        assertThat(body.getPhp()).isNull();
    }
}
