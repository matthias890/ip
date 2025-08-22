public class Delta {
    public static void main(String[] args) {
        printLine();
        System.out.println(" Hello! I'm Delta");
        System.out.println(" What can I do for you?");
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println();
    }
}
