package io.jahiduls.minance.builders;

import io.jahiduls.minance.model.Amount;

public interface InterestComputeStep {
    Amount fromAmount(Amount amount);
}
