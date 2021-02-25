package io.jahiduls.minance.projectors;

import io.jahiduls.minance.events.TermDepositCreatedEvent;
import io.jahiduls.minance.helpers.TermDepositCache;
import io.jahiduls.minance.model.TermDepositIdsView;
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
        final TermDepositIdsView view = TermDepositIdsView.builder().id(event.id).build();
        repository.save(view);
    }

    @QueryHandler
    private List<UUID> on(GetAllTermDepositsQuery query, @Autowired TermDepositRepository repository) {

        log.debug("Handling query: {}", query);
        return repository.findAll().stream().map(TermDepositIdsView::getId).collect(Collectors.toList());
    }

    @QueryHandler
    private TermDepositView on(GetTermDepositQuery query, @Autowired TermDepositCache cache) {

        log.debug("Handling query: {}", query);
        return cache.lookup(query.id);

    }

}
