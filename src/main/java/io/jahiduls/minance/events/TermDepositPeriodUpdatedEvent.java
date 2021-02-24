package io.jahiduls.minance.events;

import io.jahiduls.minance.model.InvestmentPeriod;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class TermDepositPeriodUpdatedEvent {
    public final UUID id;
    public final InvestmentPeriod period;
}
