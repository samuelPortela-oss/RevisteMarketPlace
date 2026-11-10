package app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class FechaUtil {

    // Formato compatible con columnas DATETIME de MySQL
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private FechaUtil() {
    }

    public static String ahora() {
        return LocalDateTime.now().format(FORMATO);
    }
}
