package io.jahiduls.minance.helpers;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import io.jahiduls.minance.aggregates.TermDepositAggregate;
import io.jahiduls.minance.events.TermDepositAmountUpdatedEvent;
import io.jahiduls.minance.events.TermDepositCreatedEvent;
import io.jahiduls.minance.events.TermDepositDepositorUpdatedEvent;
import io.jahiduls.minance.events.TermDepositInterestRateUpdatedEvent;
import io.jahiduls.minance.events.TermDepositMaturityInstructionUpdatedEvent;
import io.jahiduls.minance.events.TermDepositPeriodUpdatedEvent;
import io.jahiduls.minance.model.TermDepositView;
import io.jahiduls.minance.repository.TermDepositRepository;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TermDepositCache {

    private final TermDepositRepository repository;
    private final EventStore store;

    private LoadingCache<UUID, TermDepositView> cache;
    private CacheLoader<UUID, TermDepositView> cacheLoader;

    @PostConstruct
    public void init() {

        cacheLoader = new CacheLoader<>() {
            @Override
            public TermDepositView load(UUID id) {

                log.debug("[{}] Loading from store", id);

                if (repository.findById(id).isEmpty()) {
                    throw new RuntimeException(Strings.format("[{}] No such term deposit found.", id));
                }

                DomainEventStream eventStream = store.readEvents(id.toString());
                if (!eventStream.hasNext()) {
                    log.warn("[{}] No events found for aggregate id", id);
                    return null;
                }

                TermDepositAggregate aggregate = new TermDepositAggregate();
                while (eventStream.hasNext()) {
                    DomainEventMessage<?> event = eventStream.next();
                    Class<?> eventClass = event.getPayloadType().getNestHost();

                    log.trace("[{}] Processing event message: {}", id, event);

                    if (TermDepositCreatedEvent.class.equals(eventClass)) {
                        aggregate.on(((TermDepositCreatedEvent) event.getPayload()));
                    } else if (TermDepositPeriodUpdatedEvent.class.equals(eventClass)) {
                        aggregate.on(((TermDepositPeriodUpdatedEvent) event.getPayload()));
                    } else if (TermDepositAmountUpdatedEvent.class.equals(eventClass)) {
                        aggregate.on(((TermDepositAmountUpdatedEvent) event.getPayload()));
                    } else if (TermDepositMaturityInstructionUpdatedEvent.class.equals(eventClass)) {
                        aggregate.on(((TermDepositMaturityInstructionUpdatedEvent) event.getPayload()));
                    } else if (TermDepositDepositorUpdatedEvent.class.equals(eventClass)) {
                        aggregate.on(((TermDepositDepositorUpdatedEvent) event.getPayload()));
                    } else if (TermDepositInterestRateUpdatedEvent.class.equals(eventClass)) {
                        aggregate.on(((TermDepositInterestRateUpdatedEvent) event.getPayload()));
                    } else {
                        log.warn("[{}] Ignoring unknown event type: {}", id, eventClass);
                    }
                }

                return TermDepositView.from(aggregate);
            }
        };

        cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .recordStats()
                .build(cacheLoader);
    }

    public TermDepositView lookup(UUID id) {
        return cache.getUnchecked(id);
    }

    public CacheStats stats() {
        return cache.stats();
    }
}
