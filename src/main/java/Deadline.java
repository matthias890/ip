public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;;
    }

    @Override
    public String convertToStorageFormat() {
        return String.format("D | %s | %s", super.toString(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", "D", super.toString(), this.by);
    }
}
