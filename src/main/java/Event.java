import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String convertToStorageFormat() {
        return String.format(
                "E | %s | %s | %s",
                super.convertToStorageFormat(),
                this.from.format(DateTimeParser.DATE_TIME_FORMATTER),
                this.to);
    }

    @Override
    public String toString() {
        return String.format(
                "[%s]%s (from: %s to: %s)",
                "E",
                super.toString(),
                this.from.format(DateTimeParser.DATE_TIME_FORMATTER),
                this.to.format(DateTimeParser.DATE_TIME_FORMATTER));
    }
}
