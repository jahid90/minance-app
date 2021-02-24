package io.jahiduls.minance.projectors;

import io.jahiduls.minance.events.TermDepositAmountUpdatedEvent;
import io.jahiduls.minance.events.TermDepositCreatedEvent;
import io.jahiduls.minance.events.TermDepositMaturityInstructionUpdatedEvent;
import io.jahiduls.minance.events.TermDepositPeriodUpdatedEvent;
import io.jahiduls.minance.model.Amount;
import io.jahiduls.minance.model.InvestmentPeriod;
import io.jahiduls.minance.model.MaturityInstruction;
import io.jahiduls.minance.model.TermDepositView;
import io.jahiduls.minance.queries.GetAllTermDepositsQuery;
import io.jahiduls.minance.queries.GetTermDepositQuery;
import io.jahiduls.minance.repository.TermDepositRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TermDepositProjector {

    @EventHandler
    private void on(TermDepositCreatedEvent event, @Autowired TermDepositRepository repository) {

        log.debug("Handling event: {}", event);
        final TermDepositView view = TermDepositView.builder()
                .id(event.id)
                .createdOn(event.createdOn)
                .amount(Amount.builder().rupee(0).paise(0).build())
                .period(InvestmentPeriod.builder().years(0).months(0).days(0).build())
                .maturityInstruction(MaturityInstruction.NONE)
                .build();

        repository.save(view);
    }

    @EventHandler
    private void on(TermDepositAmountUpdatedEvent event, @Autowired TermDepositRepository repository) {

        log.debug("Handling event: {}", event);
        final TermDepositView deposit = repository.findById(event.id)
                .orElseThrow(() -> new RuntimeException("No term deposit found with id: " + event.id));
        final TermDepositView updatedDeposit = TermDepositView.builder()
                .id(deposit.getId())
                .createdOn(deposit.getCreatedOn())
                .period(deposit.getPeriod())
                .maturityInstruction(deposit.getMaturityInstruction())
                .amount(event.amount)
                .build();

        repository.save(updatedDeposit);
    }

    @EventHandler
    private void on(TermDepositPeriodUpdatedEvent event, @Autowired TermDepositRepository repository) {

        log.debug("Handling event: {}", event);
        final TermDepositView deposit = repository.findById(event.id)
                .orElseThrow(() -> new RuntimeException("No term deposit found with id: " + event.id));
        final TermDepositView updatedDeposit = TermDepositView.builder()
                .id(deposit.getId())
                .createdOn(deposit.getCreatedOn())
                .amount(deposit.getAmount())
                .maturityInstruction(deposit.getMaturityInstruction())
                .period(event.period)
                .build();

        repository.save(updatedDeposit);
    }

    @EventHandler
    private void on(TermDepositMaturityInstructionUpdatedEvent event, @Autowired TermDepositRepository repository) {

        log.debug("Handling event: {}", event);
        final TermDepositView deposit = repository.findById(event.id).orElseThrow();
        final TermDepositView updatedDeposit = TermDepositView.builder()
                .id(deposit.getId())
                .createdOn(deposit.getCreatedOn())
                .period(deposit.getPeriod())
                .amount(deposit.getAmount())
                .maturityInstruction(event.maturityInstruction)
                .build();

        repository.save(updatedDeposit);
    }

    @QueryHandler
    private List<UUID> on(GetAllTermDepositsQuery query, @Autowired TermDepositRepository repository) {

        log.debug("Handling query: {}", query);
        return repository.findAll().stream().map(TermDepositView::getId).collect(Collectors.toList());
    }

    @QueryHandler
    private TermDepositView on(GetTermDepositQuery query, @Autowired TermDepositRepository repository) {

        log.debug("Handling query: {}", query);
        return repository.findById(query.id)
                .orElseThrow(() -> new RuntimeException("No term deposits found with id: " + query.id));
    }

}
