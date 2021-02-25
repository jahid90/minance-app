package io.jahiduls.minance.events;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class TermDepositDepositorUpdatedEvent {
    public final UUID id;
    public final String depositor;
}
