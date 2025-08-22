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

            if (splitInput.length > 1) {
                if (command.equals("mark")) {
                    taskList.updateTaskStatus(Integer.parseInt(splitInput[1]), true);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + taskList.getTask(Integer.parseInt(splitInput[1]) - 1).toString());
                } else if (command.equals("unmark")) {
                    taskList.updateTaskStatus(Integer.parseInt(splitInput[1]), false);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + taskList.getTask(Integer.parseInt(splitInput[1]) - 1).toString());
                } else {
                    taskList.addTask(new Task(input));
                    System.out.println(" added: " + input);
                }

            } else {
                if (command.equals("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    printLine();
                    break;
                } else if (command.equals("list")) {
                    taskList.printTasks();
                } else {
                    taskList.addTask(new Task(input));
                    System.out.println(" added: " + input);
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
