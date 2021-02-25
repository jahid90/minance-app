package io.jahiduls.minance.model;

import io.jahiduls.minance.aggregates.TermDepositAggregate;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class TermDepositView {
    public final UUID id;
    public final LocalDate createdOn;
    public final InvestmentPeriod period;
    public final Amount amount;
    public final MaturityInstruction maturityInstruction;

    public static TermDepositView from(TermDepositAggregate aggregate) {
        return TermDepositView.builder()
                .id(aggregate.getUuid())
                .createdOn(aggregate.getCreatedOn())
                .period(aggregate.getPeriod())
                .amount(aggregate.getAmount())
                .maturityInstruction(aggregate.getMaturityInstruction())
                .build();
    }
}
