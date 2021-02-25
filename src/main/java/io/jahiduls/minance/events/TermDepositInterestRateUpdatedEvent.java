package io.jahiduls.minance.events;

import io.jahiduls.minance.models.InterestRate;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class TermDepositInterestRateUpdatedEvent {
    public final UUID id;
    public final InterestRate interestRate;
}
