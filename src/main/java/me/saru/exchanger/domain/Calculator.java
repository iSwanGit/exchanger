package me.saru.exchanger.domain;

import me.saru.exchanger.data.command.CalcCommand;

import java.math.BigDecimal;

// domain
public class Calculator {

    public BigDecimal calculate(CalcCommand command) {
        return command.getCurrencyAmount().multiply(command.getFromAmount());
    }
}
