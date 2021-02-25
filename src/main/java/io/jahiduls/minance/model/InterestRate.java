package io.jahiduls.minance.model;

import io.jahiduls.minance.builders.InterestComputeStep;
import org.hibernate.cfg.NotYetImplementedException;

public interface InterestRate {
    int asInt();
    InterestComputeStep computeInterest();

    static InterestRate fromInt(int rate) {
        throw new NotYetImplementedException("Should be overridden by sub-classes");
    }
}
