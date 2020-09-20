package me.saru.exchanger.service;

import me.saru.exchanger.data.command.CalcCommand;
import me.saru.exchanger.domain.Calculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculateService {
    private final Calculator calculator = new Calculator();

    public BigDecimal calculateDelegate(CalcCommand command) {
        return calculator.calculate(command);
    }
}
