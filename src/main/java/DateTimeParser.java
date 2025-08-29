import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    // date time formatters
    public static final DateTimeFormatter[] DATE_TIME_FORMATTERS = {
            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM d yyyy HHmm"),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm")
    };

    // date time formatter to convert back to string
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    // convert string to LocalDateTime
    public static LocalDateTime parseDateTime(String s) {
        s = s.trim();

        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(s, formatter);
            } catch (DateTimeParseException e) {
                // ignored
            }
        }

        throw new IllegalArgumentException(
                "Unrecognized date time format: " + s
                        + "\nAccepted date time examples: 2025-08-01 12:00, 2025-08-01 1230," +
                        " 30/8/2025 12:00, 1/8/2025 0800, Jan 1 00:00, Jan 2 1200");
    }
}