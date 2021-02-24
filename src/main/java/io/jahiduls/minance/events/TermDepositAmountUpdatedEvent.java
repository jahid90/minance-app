package io.jahiduls.minance.events;

import io.jahiduls.minance.model.Amount;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class TermDepositAmountUpdatedEvent {
    public final UUID id;
    public final Amount amount;
}
