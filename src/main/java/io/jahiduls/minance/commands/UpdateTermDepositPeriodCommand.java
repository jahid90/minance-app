package io.jahiduls.minance.commands;

import io.jahiduls.minance.models.InvestmentPeriod;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@ToString
@EqualsAndHashCode
public class UpdateTermDepositPeriodCommand {
    @TargetAggregateIdentifier public final UUID id;
    public final InvestmentPeriod period;
}
