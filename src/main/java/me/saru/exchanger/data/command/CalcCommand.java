package me.saru.exchanger.data.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CalcCommand {
    private final BigDecimal fromAmount;
    private final BigDecimal currencyAmount;
}
