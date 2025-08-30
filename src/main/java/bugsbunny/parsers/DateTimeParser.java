package bugsbunny.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    // date time formatter to convert input String to LocalDateTime
    public static final DateTimeFormatter INPUT_TO_DATE_TIME =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // date time formatter to convert LocalDateTime to bugsbunny.storage.Storage String and vice versa
    public static final DateTimeFormatter DATE_TIME_STRING_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy, EEE h.mma"); // Aug 30 2025, Sat 1.00pm

    // convert string to LocalDateTime
    public static LocalDateTime parseStringToDateTime(String s, DateTimeFormatter formatter) {
        s = s.trim();

        try {
            return LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unrecognized date time format: " + s
                    + "\nAccepted date time example: 2025-08-30 1300");
        }
    }
}