package io.jahiduls.minance.builders.steps;

import io.jahiduls.minance.models.InterestRate;

public interface InterestComputeStep {
    RateAddedStep at(InterestRate rate);
}
