import java.util.Scanner;

public class Delta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        printLine();
        System.out.println(" Hello! I'm Delta");
        System.out.println(" What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ");
            printLine();

            String command = splitInput[0];

            if (command.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (command.equals("list")) {
                taskList.printTasks();
            } else if (command.equals("mark") || command.equals("unmark")) {
                int index = Integer.parseInt(splitInput[1]) - 1;
                if (command.equals("mark")) {
                    taskList.markTask(index);
                } else {
                    taskList.unmarkTask(index);
                }
            } else {
                // Add Tasks
                if (command.equals("todo")) {
                    String description = input.split(" ", 2)[1];
                    taskList.addTask(new ToDo(description));
                } else if (command.equals("deadline")) {
                    String[] firstSplit = input.split("/by");
                    String description = firstSplit[0].trim().split(" ", 2)[1];
                    String by = firstSplit[1].trim();
                    taskList.addTask(new Deadline(description, by));
                } else if (command.equals("event")) {
                    String[] firstSplit = input.split("/from");
                    String[] secondSplit = firstSplit[1].trim().split("/to");
                    String description = firstSplit[0].trim().split(" ", 2)[1];
                    String from = secondSplit[0].trim();
                    String to = secondSplit[1].trim();
                    taskList.addTask(new Event(description, from, to));
                } else {
                    System.out.println("Sorry, I do not recognise this command. Please enter another command.");
                }
            }
            printLine();
        }
    }

    public static void printLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println();
    }
}
