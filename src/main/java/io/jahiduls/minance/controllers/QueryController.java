package io.jahiduls.minance.controllers;

import io.jahiduls.minance.model.TermDepositView;
import io.jahiduls.minance.queries.GetAllTermDepositsQuery;
import io.jahiduls.minance.queries.GetTermDepositQuery;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QueryController {

    private final QueryGateway queryGateway;

    @GetMapping("/deposits/term")
    private CompletableFuture<List<UUID>> getAllTermDeposits() {

        log.debug("Received get all query");
        return queryGateway.query(GetAllTermDepositsQuery.builder().build(),
                ResponseTypes.multipleInstancesOf(UUID.class));
    }

    @GetMapping("/deposits/term/{depositId}")
    private CompletableFuture<TermDepositView> getOne(@PathVariable UUID depositId) {

        log.debug("Received get one query for id: {}", depositId);
        return queryGateway.query(GetTermDepositQuery.builder().id(depositId).build(),
                ResponseTypes.instanceOf(TermDepositView.class));
    }

}
