import java.util.Scanner;

public class Delta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] taskList = new String[100];
        int count = 0;

        printLine();
        System.out.println(" Hello! I'm Delta");
        System.out.println(" What can I do for you?");
        printLine();

        while (true) {
            String command = scanner.nextLine();
            printLine();

            if (command.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            if (command.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println(String.format(" %d. %s", i + 1, taskList[i]));
                }
            } else {
                taskList[count] = command;
                count++;
                System.out.println(" added: " + command);
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
