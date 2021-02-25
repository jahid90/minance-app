package io.jahiduls.minance.aggregates;

import io.jahiduls.minance.commands.CreateTermDepositCommand;
import io.jahiduls.minance.commands.UpdateTermDepositAmountCommand;
import io.jahiduls.minance.commands.UpdateTermDepositMaturityInstructionCommand;
import io.jahiduls.minance.commands.UpdateTermDepositPeriodCommand;
import io.jahiduls.minance.events.TermDepositAmountUpdatedEvent;
import io.jahiduls.minance.events.TermDepositCreatedEvent;
import io.jahiduls.minance.events.TermDepositMaturityInstructionUpdatedEvent;
import io.jahiduls.minance.events.TermDepositPeriodUpdatedEvent;
import io.jahiduls.minance.model.Amount;
import io.jahiduls.minance.model.InvestmentPeriod;
import io.jahiduls.minance.model.MaturityInstruction;
import java.time.LocalDate;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class TermDepositAggregate {

    @AggregateIdentifier private UUID uuid;
    private LocalDate createdOn;
    private InvestmentPeriod period;
    private Amount amount;
    private MaturityInstruction maturityInstruction;

    @CommandHandler
    public TermDepositAggregate(CreateTermDepositCommand command) {

        final TermDepositCreatedEvent event = TermDepositCreatedEvent.builder()
                .id(command.id)
                .createdOn(command.createdOn)
                .build();

        log.debug("Dispatching event: {}", event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    private void handle(UpdateTermDepositAmountCommand command) {

        final TermDepositAmountUpdatedEvent event = TermDepositAmountUpdatedEvent.builder()
                .id(command.id)
                .amount(command.amount)
                .build();

        log.debug("Dispatching event: {}", event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    private void handle(UpdateTermDepositPeriodCommand command) {

        final TermDepositPeriodUpdatedEvent event = TermDepositPeriodUpdatedEvent.builder()
                .id(command.id)
                .period(command.period)
                .build();

        log.debug("Dispatching event: {}", event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    private void handle(UpdateTermDepositMaturityInstructionCommand command) {

        final TermDepositMaturityInstructionUpdatedEvent event = TermDepositMaturityInstructionUpdatedEvent.builder()
                .id(command.id)
                .maturityInstruction(command.maturityInstruction)
                .build();

        log.debug("Dispatching event: {}", event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(TermDepositCreatedEvent event) {

        log.debug("Handling event: {}", event);
        log.debug("[BEFORE] {}", this);
        this.uuid = event.id;
        this.createdOn = event.createdOn;
        log.debug("[AFTER] {}", this);
    }

    @EventSourcingHandler
    public void on(TermDepositAmountUpdatedEvent event) {

        log.debug("Handling event: {}", event);
        log.debug("[BEFORE] {}", this);
        this.amount = event.amount;
        log.debug("[AFTER] {}", this);
    }

    @EventSourcingHandler
    public void on(TermDepositPeriodUpdatedEvent event) {

        log.debug("Handling event: {}", event);
        log.debug("[BEFORE] {}", this);
        this.period = event.period;
        log.debug("[AFTER] {}", this);
    }

    @EventSourcingHandler
    public void on(TermDepositMaturityInstructionUpdatedEvent event) {

        log.debug("Handling event: {}", event);
        log.debug("[BEFORE] {}", this);
        this.maturityInstruction = event.maturityInstruction;
        log.debug("[AFTER] {}", this);
    }
}
