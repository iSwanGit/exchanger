package me.saru.exchanger.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "exchange")
@ConstructorBinding
@Getter
public class ConfigProperties {

    private final String accessKey;
    private final String[] currencies;

    public ConfigProperties(String accessKey, String[] currencies) {
        this.accessKey = accessKey;
        this.currencies = currencies;
    }
}
