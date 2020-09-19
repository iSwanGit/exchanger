package me.saru.exchanger.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "exchange")
@ConstructorBinding
@Getter
public class ConfigProperties {

    @NotEmpty
    private final String accessKey;

    @NotEmpty
    @Min(1)
    private final String[] currencies;

    public ConfigProperties(String accessKey, String[] currencies) {
        this.accessKey = accessKey;
        this.currencies = currencies;
    }
}
