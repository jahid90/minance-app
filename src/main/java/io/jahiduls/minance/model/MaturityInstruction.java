package io.jahiduls.minance.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum MaturityInstruction {

    NONE("none"),
    REINVEST_PRINCIPLE_AND_PAYOUT_INTEREST("reinvest-principle-and-payout-interest"),
    REINVEST_PRINCIPLE_AND_INTEREST("reinvest-principle-and-interest"),
    PAYOUT_PRINCIPLE_AND_INTEREST("payout-principle-and-interest");

    @JsonValue @Getter
    private final String value;

    MaturityInstruction(final String value) {
        this.value = value;
    }
}
