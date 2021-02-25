package io.jahiduls.minance.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class User {

    private final String name;

    public static User fromString(String name) {
        return User.builder().name(name).build();
    }

    public String asString() {
        return name;
    }

    @Converter(autoApply = true)
    static final class UserConverter implements AttributeConverter<User, String> {

        @Override
        public String convertToDatabaseColumn(User user) {
            return user.asString();
        }

        @Override
        public User convertToEntityAttribute(String dbUser) {
            return User.fromString(dbUser);
        }
    }
}
