import java.time.LocalDateTime;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String convertToStorageFormat() {
        return String.format("T | %s", super.convertToStorageFormat());
    }

    @Override
    public boolean isDueBy(LocalDateTime dateTime) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", "T", super.toString());
    }
}