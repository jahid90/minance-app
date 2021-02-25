package io.jahiduls.minance.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MaturityInstruction {

    NONE("none"),
    REINVEST_PRINCIPLE_AND_INTEREST("reinvest-principle-and-interest"),
    REINVEST_PRINCIPLE_AND_PAYOUT_INTEREST("reinvest-principle-and-payout-interest"),
    PAYOUT_PRINCIPLE_AND_INTEREST("payout-principle-and-interest"),
    PAYOUT_INTEREST_QUARTERLY("payout-interest-quarterly");

    @JsonValue @Getter
    private final String value;

}
