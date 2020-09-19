package me.saru.exchanger.controller.api;

import me.saru.exchanger.config.ConfigProperties;
import me.saru.exchanger.data.CurrencyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ExchangeController {
    private final ConfigProperties configProperties;
    private final RestTemplate restTemplate;
    private final String apiUrl;

    @Autowired
    public ExchangeController(ConfigProperties configProperties, RestTemplate restTemplate) {
        this.configProperties = configProperties;
        this.restTemplate = restTemplate;
        this.apiUrl = "http://api.currencylayer.com/live?access_key=" + configProperties.getAccessKey() + "&currencies=" +
                String.join(",", configProperties.getCurrencies()) + "&format=1";
    }

    @GetMapping("/currencies")
    public CurrencyData.Res currencyList() {
        return restTemplate.getForEntity(apiUrl, CurrencyData.Res.class)
                .getBody();
    }
}
