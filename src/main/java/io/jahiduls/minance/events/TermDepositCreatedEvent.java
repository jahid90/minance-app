package io.jahiduls.minance.events;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class TermDepositCreatedEvent {
    public final UUID id;
    public final LocalDate createdOn;
}
