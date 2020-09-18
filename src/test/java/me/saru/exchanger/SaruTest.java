package me.saru.exchanger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class SaruTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void apiTest() throws Exception {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://api.currencylayer.com/live?access_key=0515a49dfab221e7ab4317ca087a516e&currencies=KRW,JPY,PHP&format=1", String.class);
        System.out.println(entity);

    }
}
