public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return String.format("[%s]%s", "T", super.toString());
    }
}