package io.jahiduls.minance.builders.steps;

import io.jahiduls.minance.models.AmountImpl;
import io.jahiduls.minance.models.InvestmentPeriod;

public interface RateAddedStep {
    AmountImpl forPeriod(InvestmentPeriod period);
}
