package io.jahiduls.minance.handlers;

import io.jahiduls.minance.events.TermDepositCreatedEvent;
import io.jahiduls.minance.repositories.TermDepositRepository;
import io.jahiduls.minance.views.TermDepositIdsView;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TermDepositEventsHandler {

    @EventHandler
    private void on(TermDepositCreatedEvent event, @Autowired TermDepositRepository repository) {

        log.debug("Handling event: {}", event);
        final TermDepositIdsView view = TermDepositIdsView.builder()
                .id(event.id)
                .user(event.user)
                .build();

        repository.save(view);
    }

}
