package io.jahiduls.minance.models;

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
public class InterestRate {

    private static final int MULTIPLIER = 100;

    public int fixed;
    public int decimal;
    public InterestType type;

    public static InterestRate fromInt(int rate) {
        return InterestRate.builder()
                .fixed(rate / MULTIPLIER)
                .decimal(rate % MULTIPLIER)
                .build();
    }

    public int asInt() {
        return fixed * MULTIPLIER + decimal;
    }


    @Converter(autoApply = true)
    static final class RateOfInterestConverter implements AttributeConverter<Amount, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Amount interestRate) {
            return interestRate.asInt();
        }

        @Override
        public Amount convertToEntityAttribute(Integer dbRate) {
            return Amount.fromInt(dbRate);
        }
    }
}
