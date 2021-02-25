package io.jahiduls.minance.commands;

import io.jahiduls.minance.models.MaturityInstruction;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@ToString
@EqualsAndHashCode
public class UpdateTermDepositMaturityInstructionCommand {
    @TargetAggregateIdentifier public final UUID id;
    public final MaturityInstruction maturity;

}
