package io.jahiduls.minance.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class Amount {
    public final int rupee;
    public final int paise;

    @Converter(autoApply = true)
    static final class AmountConverter implements AttributeConverter<Amount, Integer> {

        private static final int MULTIPLIER = 100;

        @Override
        public Integer convertToDatabaseColumn(Amount amount) {
            return amount.rupee * MULTIPLIER + amount.paise;
        }

        @Override
        public Amount convertToEntityAttribute(Integer dbAmount) {
            return Amount.builder().rupee(dbAmount / MULTIPLIER).paise(dbAmount % MULTIPLIER).build();
        }
    }
}
