import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Delta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("./data/delta.txt");
        TaskList taskList;
        try {
            taskList = storage.load();
        } catch (IOException e) {
            System.out.println("Unable to load from hard disk: " + e.getMessage());
            taskList = new TaskList();
        }

        printLine();
        System.out.println("Hello! I'm Delta");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            if (!scanner.hasNextLine()) {
                break;
            }
            String input = scanner.nextLine().trim();
            printLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            try {
                handleInput(input, taskList, storage);
            } catch (DeltaException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            printLine();
        }
    }

    private static void printLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    private static void handleInput(String input, TaskList taskList, Storage storage) throws DeltaException {
        String trimmedInput = input.trim();

        if (trimmedInput.isEmpty()) {
            throw new DeltaException("Your input cannot be empty.");
        }

        String[] splitInput = trimmedInput.split(" ", 2);
        Command command = Command.getCommand(splitInput[0]);

        switch (command) {
            case BYE: {
                break;
            }

            case LIST: {
                taskList.printTasks();
                break;
            }

            case MARK:
            case UNMARK:
            case DELETE: {
                // No task number error
                if (splitInput.length < 2) {
                    if (command == Command.MARK || command == Command.UNMARK) {
                        throw new DeltaException("Usage: mark/unmark <number>");
                    } else {
                        throw new DeltaException("Usage: delete <number>");
                    }
                }
                int index;
                // Invalid Task number
                try {
                    index = Integer.parseInt(splitInput[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new DeltaException("'" + splitInput[1] + "' is not a valid task number. Please try again.");
                }
                // Task number out of range
                if (index < 0 || index >= taskList.getNumberOfTasks()) {
                    throw new DeltaException("The task number is out of range.");
                }

                if (command == Command.MARK) {
                    taskList.markTask(index);
                } else if (command == Command.UNMARK) {
                    taskList.unmarkTask(index);
                } else {
                    taskList.deleteTask(index);
                }
                try {
                    storage.save(taskList);
                } catch (IOException e) {
                    throw new DeltaException("Unable to save to hard disk: " + e.getMessage());
                }
                break;
            }

            case TODO: {
                if (splitInput.length < 2 || splitInput[1].trim().isEmpty()) {
                    throw new DeltaException("Usage: todo <description>");
                }
                taskList.addTask(new ToDo(splitInput[1].trim()));

                try {
                    storage.save(taskList);
                } catch (IOException e) {
                    throw new DeltaException("Unable to save to hard disk: " + e.getMessage());
                }
                break;
            }

            case DEADLINE: {
                // no /by included
                if (!trimmedInput.contains("/by")) {
                    throw new DeltaException("Usage: deadline <description> /by <date>");
                }
                String[] firstSplit = splitInput[1].split("/by", 2);
                String description = firstSplit[0].trim();

                // no description included
                if (firstSplit.length < 2 || description.isEmpty()) {
                    throw new DeltaException("Usage: deadline <description> /by <date>");
                }
                String by = firstSplit[1].trim();

                // nothing after /by
                if (by.isEmpty()) {
                    throw new DeltaException("Usage: deadline <description> /by <date>");
                }

                try {
                    LocalDateTime byDateTime = DateTimeParser.parseDateTime(by);
                    taskList.addTask(new Deadline(description, byDateTime));
                } catch (IllegalArgumentException e) {
                    throw new DeltaException(e.getMessage());
                }

                try {
                    storage.save(taskList);
                } catch (IOException e) {
                    throw new DeltaException("Unable to save to hard disk: " + e.getMessage());
                }
                break;
            }

            case EVENT: {
                if (!trimmedInput.contains("/from") || !trimmedInput.contains("/to")) {
                    throw new DeltaException("Usage: event <description> /from <start> /to <end>");
                }
                String[] firstSplit = splitInput[1].split("/from", 2);
                String description = firstSplit[0].trim();

                // no description included
                if (firstSplit.length < 2 || description.isEmpty()) {
                    throw new DeltaException("Usage: event <description> /from <start> /to <end>");
                }

                String[] secondSplit = firstSplit[1].trim().split("/to", 2);
                String from = secondSplit[0].trim();

                // nothing after /from
                if (secondSplit.length < 2 || from.isEmpty()) {
                    throw new DeltaException("Usage: event <description> /from <start> /to <end>");
                }
                String to = secondSplit[1].trim();

                // nothing after /to
                if (to.isEmpty()) {
                    throw new DeltaException("Usage: event <description> /from <start> /to <end>");
                }

                LocalDateTime fromDateTime;
                LocalDateTime toDateTime;

                try {
                    fromDateTime = DateTimeParser.parseDateTime(from);
                } catch (IllegalArgumentException e) {
                    throw new DeltaException(e.getMessage());
                }

                try {
                    toDateTime = DateTimeParser.parseDateTime(to);
                } catch (IllegalArgumentException e) {
                    throw new DeltaException(e.getMessage());
                }
                taskList.addTask(new Event(description, fromDateTime, toDateTime));

                try {
                    storage.save(taskList);
                } catch (IOException e) {
                    throw new DeltaException("Unable to save to hard disk: " + e.getMessage());
                }
                break;
            }
        }
    }
}
