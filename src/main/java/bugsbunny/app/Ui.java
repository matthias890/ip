package bugsbunny.app;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        // Art by Shanaka Dias, obtained from https://www.asciiart.eu/cartoons/looney-tunes
        System.out.printf("              , ,\n");
        System.out.printf("             /| |\\\n");
        System.out.printf("            / | | \\\n");
        System.out.printf("            | | | |     Neeaah, Whats up Doc !?!\n");
        System.out.printf("            \\ | | /\n");
        System.out.printf("             \\|w|/    /\n");
        System.out.printf("             /_ _\\   /      ,\n");
        System.out.printf("  /\\       _:()_():_       /]\n");
        System.out.printf("  ||_     : ._=Y=_  :     / /\n");
        System.out.printf(" [)(_\\,   ',__\\W/ _,'    /  \\\n");
        System.out.printf(" [) \\_/\\    _/'='\\      /-/\\)\n");
        System.out.printf("  [_| \\ \\  ///  \\ '._  / /\n");
        System.out.printf("  :;   \\ \\///   / |  '` /\n");
        System.out.printf("  ;::   \\ `|:   : |',_.'\n");
        System.out.printf("  \"\"\"    \\_|:   : |   \n");
        System.out.printf("           |:   : |'\".\n");
        System.out.printf("           /`._.'  \\/\n");
        System.out.printf("          /  /|   /\n");
        System.out.printf("         |  \\ /  /\n");
        System.out.printf("          '. '. /\n");
        System.out.printf("            '. '\n");
        System.out.printf("            / \\ \\\n");
        System.out.printf("           / / \\'=,\n");
        System.out.printf("     .----' /   \\ (\\__\n");
        System.out.printf("snd (((____/     \\ \\  )\n");
        System.out.printf("                  '.\\_)\n");
    }

    public void showCommandGuide() {
        System.out.println("You can chat with me using the following commands:");
        System.out.println(" help: Show this guide. Syntax: help");
        System.out.println(" list: List all the tasks you have created so far. Syntax: list");
        System.out.println(" mark: Mark a task as completed. Syntax: mark <task index>");
        System.out.println(" unmark: Mark a task as not completed. Syntax: unmark <task index>");
        System.out.println(" delete: Delete a task. Syntax: delete <task index>");
        System.out.println(" todo: Create a todo task. Syntax: todo <task name>");
        System.out.println(" deadline: Create a deadline task. Syntax: deadline <task name> /by <yyyy-mm-dd hhmm>");
        System.out.println(" event: Create an event task. " +
                "Syntax: event <task name> /from <yyyy-mm-dd hhmm> /to <yyyy-mm-dd hhmm>");
        System.out.println(" due: Shows the tasks that are due by this date and time. Syntax: due <yyyy-mm-dd hhmm>");
        System.out.println(" bye: Exit the chat");
    }

    public void showLine() {
        System.out.println("________________________________________________________________");
    }

    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    public void showError(String s) {
        System.out.println("Neeah, there's a problem Doc:");
        System.out.println(" " + s);
    }

    public void showLoadingError() {
        System.out.println("Neeah, I can't load from the hard disk Doc");
    }

    public void showSavingError() {
        System.out.println("Neeah, I can't save to the hard disk Doc");
    }

    public void showGoodbye() {
        System.out.println("So long, Doc!");
        this.scanner.close();
    }
}

