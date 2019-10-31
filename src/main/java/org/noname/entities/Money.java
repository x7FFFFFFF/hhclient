package org.noname.entities;

import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {

    public static final String CURRENCY_UNKNOWN = "?";
    private final BigDecimal value;
    private final String currency;

    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money(Element element) {
        String text = element.text();
        if (text.isEmpty()) {
            value = BigDecimal.ZERO;
            currency = CURRENCY_UNKNOWN;
        } else {
            String[] strings = text.split(" ");
            if (strings.length != 2) {
                throw new IllegalStateException();
            }
            value = new BigDecimal(strings[0]).setScale(2, RoundingMode.HALF_UP);
            currency = strings[1];
        }
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return value + " " + currency;

    }
}
