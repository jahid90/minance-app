package io.jahiduls.minance.models;

import io.jahiduls.minance.builders.steps.InterestComputeStep;
import org.hibernate.cfg.NotYetImplementedException;

public interface Amount {
    int asInt();
    InterestComputeStep computeInterest();

    static Amount fromInt(int amount) {
        throw new NotYetImplementedException("Should be overridden by sub-classes");
    }
}
