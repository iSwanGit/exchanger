package me.saru.exchanger;

import me.saru.exchanger.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = ConfigProperties.class)
public class ExchangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangerApplication.class, args);
	}

}
