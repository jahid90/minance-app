package io.jahiduls.minance.commands;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@ToString
@EqualsAndHashCode
public class CreateTermDepositCommand {
    @TargetAggregateIdentifier public final UUID id;
    public final LocalDate createdOn;
}
