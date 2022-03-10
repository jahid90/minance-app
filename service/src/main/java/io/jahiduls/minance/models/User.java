package io.jahiduls.minance.models;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public String name;

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
