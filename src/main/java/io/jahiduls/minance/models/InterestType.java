package io.jahiduls.minance.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;

@RequiredArgsConstructor
public enum InterestType {

    SIMPLE("simple") {
        @Override
        public int computeInterest(int amount, int rate, int timeInYears) {
            return amount * timeInYears * rate;
        }
    },
    COMPOUND("compound") {
        @Override
        public int computeInterest(int amount, int rate, int timeInYears) {
            return -1;
        }
    };

    @JsonValue
    public final String value;

    public int computeInterest(int amount, int rate, int timeInYears) {
        throw new NotYetImplementedException("Each entry must implement their own logic");
    }
}
