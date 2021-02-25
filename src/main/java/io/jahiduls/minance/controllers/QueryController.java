package io.jahiduls.minance.controllers;

import com.google.common.cache.CacheStats;
import io.jahiduls.minance.models.User;
import io.jahiduls.minance.queries.GetAllTermDepositsByUserQuery;
import io.jahiduls.minance.queries.GetCacheStatsQuery;
import io.jahiduls.minance.queries.GetAllTermDepositsQuery;
import io.jahiduls.minance.queries.GetTermDepositQuery;
import io.jahiduls.minance.views.TermDepositView;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/deposits/term/filter")
    private CompletableFuture<List<UUID>> getAllTermDepositsByUser(@RequestParam String user) {

        log.debug("Received get all by user query");
        return queryGateway.query(GetAllTermDepositsByUserQuery.builder().user(User.fromString(user)).build(),
                ResponseTypes.multipleInstancesOf(UUID.class));
    }

    @GetMapping("/deposits/term/details/{depositId}")
    private CompletableFuture<TermDepositView> getOne(@PathVariable UUID depositId) {

        log.debug("Received get one query for id: {}", depositId);
        return queryGateway.query(GetTermDepositQuery.builder().id(depositId).build(),
                ResponseTypes.instanceOf(TermDepositView.class));
    }

    @GetMapping("/cache/stats")
    private CompletableFuture<CacheStats> cacheStats() {

        log.debug("Received cache stats query");
        return queryGateway.query(GetCacheStatsQuery.builder().build(), ResponseTypes.instanceOf(CacheStats.class));
    }

    @ExceptionHandler
    private void handleException(RuntimeException e) {

        log.error("{}", e);
        throw e;
    }

}
