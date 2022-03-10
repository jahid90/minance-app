package io.jahiduls.minance.helpers;

import lombok.experimental.UtilityClass;
import org.slf4j.helpers.MessageFormatter;

@UtilityClass
public final class Strings {

    public static String format(String message, Object... params) {
        return MessageFormatter.arrayFormat(message, params).getMessage();
    }

}
