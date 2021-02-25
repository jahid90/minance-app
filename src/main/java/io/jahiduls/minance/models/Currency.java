package io.jahiduls.minance.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Currency {

    INR("INR", "\u20B9");

    @JsonValue public final String value;
    public final String symbol;

}
