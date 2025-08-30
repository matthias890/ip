import java.time.LocalDateTime;

abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void updateStatus(boolean bool) {
        this.isDone = bool;
    }

    public String convertToStorageFormat() {
        String done = isDone ? "1" : "0";
        return String.format("%s | %s", done, this.description);
    }

    public static Task convertFromStorageFormat(String fileTask) throws BugsBunnyException {
        String[] parts = fileTask.split(" \\| "); // split on ' | '

        if (parts.length < 3) {
            throw new BugsBunnyException("Bad record: " + fileTask);
        }
        
        String taskType = parts[0];
        boolean isTaskDone = parts[1].equals("1");
        String taskDescription = parts[2];

        switch(taskType) {
        case "T":
            return new ToDo(taskDescription, isTaskDone);

        case "D":
            if (parts.length < 4) {
                throw new BugsBunnyException("Bad deadline record: " + fileTask);
            }
            return new Deadline(
                    taskDescription,
                    isTaskDone,
                    DateTimeParser.parseStringToDateTime(parts[3], DateTimeParser.DATE_TIME_STRING_FORMATTER));

        case "E":
            if (parts.length < 5) {
                throw new BugsBunnyException("Bad event record: " + fileTask);
            }
            return new Event(
                    taskDescription,
                    isTaskDone,
                    DateTimeParser.parseStringToDateTime(parts[3], DateTimeParser.DATE_TIME_STRING_FORMATTER),
                    DateTimeParser.parseStringToDateTime(parts[4], DateTimeParser.DATE_TIME_STRING_FORMATTER));

        default:
            throw new BugsBunnyException("Unknown task type: " + taskType);
        }
    }

    public abstract boolean isDueBy(LocalDateTime dateTime);

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Task)) {
            return false;
        }

        Task task = (Task) object;

        return this.description.equals(task.description);
    }
}
