package io.jahiduls.minance.events;

import io.jahiduls.minance.models.Date;
import io.jahiduls.minance.models.User;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class TermDepositCreatedEvent {
    public final UUID id;
    public final User user;
    public final Date createdOn;
}
