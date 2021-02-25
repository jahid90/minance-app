package io.jahiduls.minance.model;

import io.jahiduls.minance.builders.InterestComputeStep;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class InterestRateImpl implements InterestRate, InterestComputeStep {

    private static final int MULTIPLIER = 100;

    public final int fixed;
    public final int decimal;
    public final InterestType type;

    // Overridden static interface method
    public static InterestRate fromInt(int rate) {
        return InterestRateImpl.builder()
                .fixed(rate / MULTIPLIER)
                .decimal(rate % MULTIPLIER)
                .build();
    }

    @Override
    public int asInt() {
        // Assumes 2 digits in decimal; TODO - add validation
        return fixed * MULTIPLIER + decimal;
    }

    @Override
    public InterestComputeStep computeInterest() {
        return this;
    }

    @Override
    public Amount fromAmount(Amount amount) {

        int val = amount.asInt();
        int interest = type.computeInterest(val);

        return Amount.fromInt(interest);
    }

    @Converter(autoApply = true)
    static final class RateOfInterestConverter implements AttributeConverter<InterestRate, Integer> {

        @Override
        public Integer convertToDatabaseColumn(InterestRate interestRate) {
            return interestRate.asInt();
        }

        @Override
        public InterestRate convertToEntityAttribute(Integer dbRate) {
            return InterestRate.fromInt(dbRate);
        }
    }
}
