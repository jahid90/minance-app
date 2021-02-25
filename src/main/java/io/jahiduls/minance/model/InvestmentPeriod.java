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

    private static final int MULTIPLIER = 100;

    public final int years;
    public final int months;
    public final int days;

    public static InvestmentPeriod fromInt(int period) {
        return InvestmentPeriod.builder()
                .years(period / MULTIPLIER / MULTIPLIER)
                .months((period / MULTIPLIER) % MULTIPLIER)
                .days(period % MULTIPLIER)
                .build();
    }

    public int asInt() {
        return years * MULTIPLIER * MULTIPLIER + months * MULTIPLIER + days;
    }

    @Converter(autoApply = true)
    static final class InvestmentPeriodConverter implements AttributeConverter<InvestmentPeriod, Integer> {

        @Override
        public Integer convertToDatabaseColumn(InvestmentPeriod period) {
            return period.asInt();
        }

        @Override
        public InvestmentPeriod convertToEntityAttribute(Integer dbPeriod) {
            return InvestmentPeriod.fromInt(dbPeriod);
        }
    }
}
