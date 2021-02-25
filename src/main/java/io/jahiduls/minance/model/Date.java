package io.jahiduls.minance.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class Date {

    private static final int MULTIPLIER = 100;

    public final int year;
    public final int month;
    public final int day;

    public static Date fromInt(int date) {
        return Date.builder()
                .year(date / MULTIPLIER / MULTIPLIER)
                .month((date / MULTIPLIER) % MULTIPLIER)
                .day(date % MULTIPLIER)
                .build();
    }

    public int asInt() {
        // "YYYYMMDD" as 8-digit int
        return year * MULTIPLIER * MULTIPLIER + month * MULTIPLIER + day;
    }

    @Converter(autoApply = true)
    static final class DateConverter implements AttributeConverter<Date, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Date date) {
            return date.asInt();
        }

        @Override
        public Date convertToEntityAttribute(Integer dbDate) {
            return Date.fromInt(dbDate);
        }
    }
}
