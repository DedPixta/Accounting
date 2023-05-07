package ru.test.task.driverservice.util;

import java.math.BigDecimal;

public enum CurrencyType {
    RED(BigDecimal.valueOf(0.4)),
    GREEN(BigDecimal.ONE),
    BLUE(BigDecimal.valueOf(0.6));

    private final BigDecimal rate;

    CurrencyType(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
