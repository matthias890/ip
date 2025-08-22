import java.util.Scanner;

public class Delta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

            System.out.println(" " + command);
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
