package io.jahiduls.minance.queries;

import io.jahiduls.minance.models.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class GetAllTermDepositsByUserQuery {
    public final User user;
}
