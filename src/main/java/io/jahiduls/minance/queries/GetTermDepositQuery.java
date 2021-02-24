package io.jahiduls.minance.queries;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class GetTermDepositQuery {
    public final UUID id;
}
