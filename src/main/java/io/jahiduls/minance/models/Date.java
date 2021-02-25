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
public class Date {

    private static final int MULTIPLIER = 100;

    public int year;
    public int month;
    public int day;

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
