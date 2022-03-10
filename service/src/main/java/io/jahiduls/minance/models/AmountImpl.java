package io.jahiduls.minance.models;

import io.jahiduls.minance.builders.steps.RateAddedStep;
import io.jahiduls.minance.builders.steps.InterestComputeStep;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AmountImpl implements Amount, InterestComputeStep, RateAddedStep {

    private static final String SEPARATOR = ".";
    private static final int MULTIPLIER = 100;

    public int fixed;
    public int decimal;
    public Currency currency;

    // lossy; no currency information
    // Override interface static method
    public static AmountImpl fromInt(int amount) {
        return AmountImpl.builder()
                .fixed(amount / MULTIPLIER)
                .decimal(amount % MULTIPLIER)
                .build();
    }

    public static AmountImpl fromString(String amount) {
        String[] vals = amount.split(SEPARATOR);
        int val = Integer.parseInt(vals[1], 10);
        return AmountImpl.builder()
                .currency(Currency.valueOf(vals[0]))
                .fixed(val / MULTIPLIER)
                .decimal(val % MULTIPLIER)
                .build();
    }

    // lossy; currency information will be lost
    @Override
    public int asInt() {
        return this.fixed * MULTIPLIER + this.decimal;
    }

    public String asString() {
        return this.currency.value + SEPARATOR + this.asInt();
    }

    @Override
    public InterestComputeStep computeInterest() {
        return this;
    }

    @Override
    public RateAddedStep at(InterestRate rate) {
        return this;
    }

    public AmountImpl forPeriod(InvestmentPeriod period) {
        return AmountImpl.fromInt(0);
    }

    @Converter(autoApply = true)
    static final class AmountConverter implements AttributeConverter<AmountImpl, String> {

        @Override
        public String convertToDatabaseColumn(AmountImpl amount) {
            return amount.asString();
        }

        @Override
        public AmountImpl convertToEntityAttribute(String dbAmount) {
            return AmountImpl.fromString(dbAmount);
        }
    }
}
