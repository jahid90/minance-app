package io.jahiduls.minance.commands;

import io.jahiduls.minance.model.InterestRate;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@ToString
@EqualsAndHashCode
public class UpdateTermDepositInterestRateCommand {
    @TargetAggregateIdentifier public final UUID id;
    public final InterestRate interestRate;
}
