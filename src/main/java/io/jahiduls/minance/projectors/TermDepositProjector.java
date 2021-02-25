package io.jahiduls.minance.projectors;

import io.jahiduls.minance.aggregates.TermDepositAggregate;
import io.jahiduls.minance.events.TermDepositAmountUpdatedEvent;
import io.jahiduls.minance.events.TermDepositCreatedEvent;
import io.jahiduls.minance.events.TermDepositMaturityInstructionUpdatedEvent;
import io.jahiduls.minance.events.TermDepositPeriodUpdatedEvent;
import io.jahiduls.minance.helpers.Strings;
import io.jahiduls.minance.model.TermDepositIdsView;
import io.jahiduls.minance.model.TermDepositView;
import io.jahiduls.minance.queries.GetAllTermDepositsQuery;
import io.jahiduls.minance.queries.GetTermDepositQuery;
import io.jahiduls.minance.repository.TermDepositRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TermDepositProjector {

    @EventHandler
    private void on(TermDepositCreatedEvent event, @Autowired TermDepositRepository repository) {

        log.debug("Handling event: {}", event);
        final TermDepositIdsView view = TermDepositIdsView.builder().id(event.id).build();
        repository.save(view);
    }

    @QueryHandler
    private List<UUID> on(GetAllTermDepositsQuery query, @Autowired TermDepositRepository repository) {

        log.debug("Handling query: {}", query);
        return repository.findAll().stream().map(TermDepositIdsView::getId).collect(Collectors.toList());
    }

    @QueryHandler
    private TermDepositView on(GetTermDepositQuery query, @Autowired EventStore store) {

        log.debug("Handling query: {}", query);
        DomainEventStream eventStream = store.readEvents(query.id.toString());
        if (!eventStream.hasNext()) {
            throw new RuntimeException(Strings.format("[{}] No such term deposit found.", query.id));
        }

        TermDepositAggregate aggregate = new TermDepositAggregate();
        while (eventStream.hasNext()) {
            DomainEventMessage<?> event = eventStream.next();
            Class<?> eventClass = event.getPayloadType().getNestHost();

            log.trace("[{}] Processing event message: {}", query.id, event);

            if (TermDepositCreatedEvent.class.equals(eventClass)) {
                aggregate.on(((TermDepositCreatedEvent) event.getPayload()));
            } else if (TermDepositPeriodUpdatedEvent.class.equals(eventClass)) {
                aggregate.on(((TermDepositPeriodUpdatedEvent) event.getPayload()));
            } else if (TermDepositAmountUpdatedEvent.class.equals(eventClass)) {
                aggregate.on(((TermDepositAmountUpdatedEvent) event.getPayload()));
            } else if (TermDepositMaturityInstructionUpdatedEvent.class.equals(eventClass)) {
                aggregate.on(((TermDepositMaturityInstructionUpdatedEvent) event.getPayload()));
            } else {
                log.warn("[{}] Ignoring unknown event type: {}", query.id, eventClass);
            }
        }

        return TermDepositView.from(aggregate);
    }

}
