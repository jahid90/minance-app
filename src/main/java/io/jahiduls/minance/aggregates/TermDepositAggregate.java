package io.jahiduls.minance.aggregates;

import io.jahiduls.minance.commands.CreateTermDepositCommand;
import io.jahiduls.minance.commands.UpdateTermDepositAmountCommand;
import io.jahiduls.minance.commands.UpdateTermDepositDepositorCommand;
import io.jahiduls.minance.commands.UpdateTermDepositInterestRateCommand;
import io.jahiduls.minance.commands.UpdateTermDepositMaturityInstructionCommand;
import io.jahiduls.minance.commands.UpdateTermDepositPeriodCommand;
import io.jahiduls.minance.events.TermDepositAmountUpdatedEvent;
import io.jahiduls.minance.events.TermDepositCreatedEvent;
import io.jahiduls.minance.events.TermDepositDepositorUpdatedEvent;
import io.jahiduls.minance.events.TermDepositInterestRateUpdatedEvent;
import io.jahiduls.minance.events.TermDepositMaturityInstructionUpdatedEvent;
import io.jahiduls.minance.events.TermDepositPeriodUpdatedEvent;
import io.jahiduls.minance.models.AmountImpl;
import io.jahiduls.minance.models.Date;
import io.jahiduls.minance.models.InterestRate;
import io.jahiduls.minance.models.InvestmentPeriod;
import io.jahiduls.minance.models.MaturityInstruction;
import io.jahiduls.minance.models.User;
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
    private User user;
    private Date createdOn;
    private String depositor;
    private AmountImpl amount;
    private InvestmentPeriod period;
    private InterestRate interestRate;
    private MaturityInstruction maturityInstruction;

    private void logAndDispatch(Object event) {

        log.debug("Dispatching event: {}", event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public TermDepositAggregate(CreateTermDepositCommand command) {

        final TermDepositCreatedEvent event = TermDepositCreatedEvent.builder()
                .id(command.id)
                .user(command.user)
                .createdOn(command.createdOn)
                .build();

        logAndDispatch(event);
    }

    @CommandHandler
    private void handle(UpdateTermDepositAmountCommand command) {

        final TermDepositAmountUpdatedEvent event = TermDepositAmountUpdatedEvent.builder()
                .id(command.id)
                .amount(command.amount)
                .build();

        logAndDispatch(event);
    }

    @CommandHandler
    private void handle(UpdateTermDepositPeriodCommand command) {

        final TermDepositPeriodUpdatedEvent event = TermDepositPeriodUpdatedEvent.builder()
                .id(command.id)
                .period(command.period)
                .build();

        logAndDispatch(event);
    }

    @CommandHandler
    private void handle(UpdateTermDepositMaturityInstructionCommand command) {

        final TermDepositMaturityInstructionUpdatedEvent event = TermDepositMaturityInstructionUpdatedEvent.builder()
                .id(command.id)
                .maturityInstruction(command.maturity)
                .build();

        logAndDispatch(event);
    }

    @CommandHandler
    private void handle(UpdateTermDepositDepositorCommand command) {

        final TermDepositDepositorUpdatedEvent event = TermDepositDepositorUpdatedEvent.builder()
                .id(command.id)
                .depositor(command.depositor)
                .build();

        logAndDispatch(event);
    }

    @CommandHandler
    private void handle(UpdateTermDepositInterestRateCommand command) {

        final TermDepositInterestRateUpdatedEvent event = TermDepositInterestRateUpdatedEvent.builder()
                .id(command.id)
                .interestRate(command.interestRate)
                .build();

        logAndDispatch(event);
    }

    @EventSourcingHandler
    public void on(TermDepositCreatedEvent event) {

        log.debug("Handling event: {}", event);
        this.uuid = event.id;
        this.user = event.user;
        this.createdOn = event.createdOn;
    }

    @EventSourcingHandler
    public void on(TermDepositAmountUpdatedEvent event) {

        log.debug("Handling event: {}", event);
        this.amount = event.amount;
    }

    @EventSourcingHandler
    public void on(TermDepositPeriodUpdatedEvent event) {

        log.debug("Handling event: {}", event);
        this.period = event.period;
    }

    @EventSourcingHandler
    public void on(TermDepositMaturityInstructionUpdatedEvent event) {

        log.debug("Handling event: {}", event);
        this.maturityInstruction = event.maturityInstruction;
    }

    @EventSourcingHandler
    public void on(TermDepositDepositorUpdatedEvent event) {

        log.debug("Handling event: {}", event);
        this.depositor = event.depositor;
    }

    @EventSourcingHandler
    public void on(TermDepositInterestRateUpdatedEvent event) {

        log.debug("Handling event: {}", event);
        this.interestRate = event.interestRate;
    }
}
