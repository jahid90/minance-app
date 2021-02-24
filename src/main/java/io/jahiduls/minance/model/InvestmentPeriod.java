package io.jahiduls.minance.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class InvestmentPeriod {
    public final int years;
    public final int months;
    public final int days;

    @Converter(autoApply = true)
    static final class InvestmentPeriodConverter implements AttributeConverter<InvestmentPeriod, Integer> {

        private static final int MULTIPLIER = 100;

        @Override
        public Integer convertToDatabaseColumn(InvestmentPeriod period) {
            return period.years * MULTIPLIER * MULTIPLIER + period.months * MULTIPLIER + period.days;
        }

        @Override
        public InvestmentPeriod convertToEntityAttribute(Integer dbPeriod) {
            return InvestmentPeriod.builder()
                    .years(dbPeriod / (MULTIPLIER * MULTIPLIER))
                    .months((dbPeriod / MULTIPLIER) % MULTIPLIER)
                    .days(dbPeriod % MULTIPLIER)
                    .build();
        }
    }
}
