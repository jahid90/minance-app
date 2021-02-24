package io.jahiduls.minance.model;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class User {
    private final UUID id;
    private final String name;
    private final String[] roles;
}
