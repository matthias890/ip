import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String convertToStorageFormat() {
        return String.format(
                "D | %s | %s",
                super.convertToStorageFormat(),
                this.by.format(DateTimeParser.DATE_TIME_STRING_FORMATTER));
    }

    @Override
    public boolean isDueBy(LocalDateTime dateTime) {
        return !this.by.isAfter(dateTime);
    }

    @Override
    public String toString() {
        return String.format(
                "[%s]%s (by: %s)",
                "D", super.toString(),
                this.by.format(DateTimeParser.DATE_TIME_STRING_FORMATTER));
    }
}
