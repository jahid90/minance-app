package io.jahiduls.minance.handlers;

import com.google.common.cache.CacheStats;
import io.jahiduls.minance.helpers.TermDepositCache;
import io.jahiduls.minance.queries.GetCacheStatsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheQueriesHandler {

    @QueryHandler
    private CacheStats on(GetCacheStatsQuery query, @Autowired TermDepositCache cache) {
        return cache.stats();
    }

}
