package io.jahiduls.minance.handlers;

import io.jahiduls.minance.helpers.TermDepositCache;
import io.jahiduls.minance.queries.GetAllTermDepositsByUserQuery;
import io.jahiduls.minance.queries.GetAllTermDepositsQuery;
import io.jahiduls.minance.queries.GetTermDepositQuery;
import io.jahiduls.minance.repositories.TermDepositRepository;
import io.jahiduls.minance.views.TermDepositView;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TermDepositQueriesHandler {

    @QueryHandler
    private List<UUID> on(GetAllTermDepositsQuery query, @Autowired TermDepositRepository repository) {

        log.debug("Handling query: {}", query);
        return repository.findAll()
                .stream()
                .map(it -> it.id)
                .collect(Collectors.toList());
    }

    @QueryHandler
    private List<UUID> on(GetAllTermDepositsByUserQuery query, @Autowired TermDepositRepository repository) {

        log.debug("Handling query: {}", query);
        return repository.findAll()
                .stream()
                .filter(it -> it.user.equals(query.user))
                .map(it -> it.id)
                .collect(Collectors.toList());
    }

    @QueryHandler
    private TermDepositView on(GetTermDepositQuery query, @Autowired TermDepositCache cache) {

        log.debug("Handling query: {}", query);
        return cache.lookup(query.id);
    }

}
