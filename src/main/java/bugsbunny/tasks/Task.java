package bugsbunny.tasks;

import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Base type for all tasks. Contains shared state (description, done flag)
 * and storage/formatting helpers.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Used when creating a new task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Used only by {@link #convertFromStorageFormat(String)}.
     *
     * @param description Description of task.
     * @param isDone Status completion.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Updates the completion status.
     */
    public void updateStatus(boolean bool) {
        this.isDone = bool;
    }

    /**
     * @return Storage-friendly string like {@code "1 | submit report"}.
     */
    public String convertToStorageFormat() {
        String done = isDone ? "1" : "0";
        return String.format("%s | %s", done, this.description);
    }

    /**
     * Parses a line from storage into a typed task.
     *
     * @param fileTask The line from the save file.
     * @return The reconstructed task.
     * @throws BugsBunnyException If the line is malformed or has an unknown type.
     */
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

    /**
     * @param dateTime Cutoff date-time.
     * @return Whether this task is considered "due by" the given time.
     */
    public abstract boolean isDueBy(LocalDateTime dateTime);

    /**
     * Checks if the description of the task contains the keyword.
     *
     * @param keyword Object of interest.
     * @return {@code true} if the task's description contains the keyword, else {@code false}.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
