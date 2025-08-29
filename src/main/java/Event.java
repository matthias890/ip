public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String convertToStorageFormat() {
        return String.format("E | %s | %s | %s", super.convertToStorageFormat(), this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", "E", super.toString(), this.from, this.to);
    }
}
