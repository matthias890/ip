import java.util.Scanner;

public class Delta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

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
                handleInput(input, taskList);
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

    private static void handleInput(String input, TaskList taskList) throws DeltaException {
        String trimmedInput = input.trim();

        if (trimmedInput.isEmpty()) {
            throw new DeltaException("Your input cannot be empty.");
        }

        String[] splitInput = trimmedInput.split(" ", 2);
        String command = splitInput[0];

        switch (command) {
            case "bye": {
                break;
            }

            case "list": {
                taskList.printTasks();
                break;
            }

            case "mark":
            case "unmark":
            case "delete": {
                // No task number error
                if (splitInput.length < 2) {
                    if (command.equals("mark") || command.equals("unmark")) {
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

                if (command.equals("mark")) {
                    taskList.markTask(index);
                } else if (command.equals("unmark")) {
                    taskList.unmarkTask(index);
                } else {
                    taskList.deleteTask(index);
                }
                break;
            }

            case "todo": {
                if (splitInput.length < 2 || splitInput[1].trim().isEmpty()) {
                    throw new DeltaException("Usage: todo <description>");
                }
                taskList.addTask(new ToDo(splitInput[1].trim()));
                break;
            }

            case "deadline": {
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
                taskList.addTask(new Deadline(description, by));
                break;
            }

            case "event": {
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
                taskList.addTask(new Event(description, from, to));
                break;
            }

            default:
                throw new DeltaException(
                        "Please enter a valid command. Example Usages:\n" +
                                " todo <description>\n" +
                                " deadline <description> /by <date>\n" +
                                " event <description> /from <start> /to <end>\n" +
                                " list\n" +
                                " mark/unmark <number>\n" +
                                " delete <number>\n" +
                                " bye");

        }
    }
}
