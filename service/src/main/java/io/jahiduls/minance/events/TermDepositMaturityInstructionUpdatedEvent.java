package io.jahiduls.minance.events;

import io.jahiduls.minance.models.MaturityInstruction;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class TermDepositMaturityInstructionUpdatedEvent {
    public final UUID id;
    public final MaturityInstruction maturityInstruction;
}
