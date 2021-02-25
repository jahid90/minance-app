package io.jahiduls.minance.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class Amount {

    @RequiredArgsConstructor
    enum Currency {

        INR("INR", "\u20B9");

        private final String name;
        @Getter private final String symbol;

    }

    private static final String SEPARATOR = ".";
    private static final int MULTIPLIER = 100;

    public final int fixed;
    public final int decimal;
    public final Currency currency;

    // lossy; no currency information
    static Amount fromInt(int amount) {
        return Amount.builder()
                .fixed(amount / MULTIPLIER)
                .decimal(amount % MULTIPLIER)
                .build();
    }

    public static Amount fromString(String amount) {
        String[] vals = amount.split(SEPARATOR);
        int val = Integer.parseInt(vals[1], 10);
        return Amount.builder()
                .currency(Currency.valueOf(vals[0]))
                .fixed(val / MULTIPLIER)
                .decimal(val % MULTIPLIER)
                .build();
    }

    // lossy; currency information will be lost
    int asInt() {
        return this.fixed * MULTIPLIER + this.decimal;
    }

    public String asString() {
        return this.currency.name + SEPARATOR + (this.fixed * MULTIPLIER + this.decimal);
    }

    @Converter(autoApply = true)
    static final class AmountConverter implements AttributeConverter<Amount, String> {

        @Override
        public String convertToDatabaseColumn(Amount amount) {
            return amount.asString();
        }

        @Override
        public Amount convertToEntityAttribute(String dbAmount) {
            return Amount.fromString(dbAmount);
        }
    }
}
