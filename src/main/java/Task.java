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

    public static Task convertFromStorageFormat(String fileTask) throws DeltaException {
        String[] parts = fileTask.split(" \\| "); // split on ' | '

        if (parts.length < 3) {
            throw new DeltaException("Bad record: " + fileTask);
        }
        
        String taskType = parts[0];
        boolean isTaskDone = parts[1].equals("1");
        String taskDescription = parts[2];

        switch(taskType) {
        case "T":
            return new ToDo(taskDescription, isTaskDone);

        case "D":
            return new Deadline(taskDescription, isTaskDone, parts[3]);

        case "E":
            return new Event(taskDescription, isTaskDone, parts[3], parts[4]);

        default:
            throw new DeltaException("Unknown task type");
        }
    }

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
