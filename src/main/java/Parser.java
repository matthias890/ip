import java.time.LocalDateTime;

public class Parser {
    public static Command parse(String input) throws BugsBunnyException {
        if (input == null) {
            return new ExitCommand(); // null input shouldn't happen
        }

        if (input.isEmpty()) {
            throw new BugsBunnyException("Your input cannot be empty");
        }

        String[] firstSplit = input.split(" ", 2);
        String start = firstSplit[0].toLowerCase();

        switch (start) {
            case "bye": {
                return new ExitCommand();
            }

            case "help": {
                return new HelpCommand();
            }

            case "list": {
                return new ListCommand();
            }

            case "due": {
                // No date error
                if (firstSplit.length < 2) {
                    throw new BugsBunnyException("Usage: due <yyyy-mm-dd hhmm>");
                }

                try {
                    LocalDateTime dateTime =
                            DateTimeParser.parseStringToDateTime(
                                    firstSplit[1].trim(),
                                    DateTimeParser.INPUT_TO_DATE_TIME);

                    return new DueCommand(dateTime);
                } catch (IllegalArgumentException e) {
                    // Incorrect Date format error
                    throw new BugsBunnyException(e.getMessage());
                }
            }

            case "mark":
            case "unmark":
            case "delete": {
                // No task number error
                if (firstSplit.length < 2) {
                    if (start.equals("mark")) {
                        throw new BugsBunnyException("Usage: mark <task index>");
                    } else if (start.equals("unmark")) {
                        throw new BugsBunnyException("Usage: unmark <task index>");
                    } else {
                        throw new BugsBunnyException("Usage: delete <task index>");
                    }
                }

                int index;
                String indexString = firstSplit[1].trim();

                if (indexString.isEmpty()) {
                    throw new BugsBunnyException("You did not include a task index");
                }

                try {
                    index = Integer.parseInt(indexString) - 1;
                } catch (NumberFormatException e) {
                    // Invalid task index
                    throw new BugsBunnyException("'" + indexString + "' is not a valid task number. Please try again.");
                }

                if (start.equals("mark")) {
                    return new MarkCommand(index);
                } else if (start.equals("unmark")) {
                    return new UnmarkCommand(index);
                } else {
                    return new DeleteCommand(index);
                }
            }

            case "todo": {
                if (firstSplit.length < 2 || firstSplit[1].trim().isEmpty()) {
                    throw new BugsBunnyException("Usage: todo <description>");
                }
                return new AddToDoCommand(firstSplit[1].trim());
            }

            case "deadline": {
                // no /by included
                if (firstSplit.length < 2 || !input.contains("/by")) {
                    throw new BugsBunnyException("Usage: deadline <description> /by <date>");
                }
                String[] bySplit = firstSplit[1].trim().split("/by", 2);
                String taskName = bySplit[0].trim();

                // no taskName included
                if (bySplit.length < 2 || taskName.isEmpty()) {
                    throw new BugsBunnyException("Usage: deadline <description> /by <date>");
                }

                String by = firstSplit[1].trim();

                // nothing after /by
                if (by.isEmpty()) {
                    throw new BugsBunnyException("Usage: deadline <description> /by <date>");
                }

                try {
                    LocalDateTime byDateTime =
                            DateTimeParser.parseStringToDateTime(
                                    by,
                                    DateTimeParser.INPUT_TO_DATE_TIME);
                    return new AddDeadlineCommand(taskName, byDateTime);
                } catch (IllegalArgumentException e) {
                    // Invalid Date time format
                    throw new BugsBunnyException(e.getMessage());
                }
            }

            case "event": {
                if (firstSplit.length < 2 || !input.contains("/from") || !input.contains("/to")) {
                    throw new BugsBunnyException("Usage: event <description> /from <start> /to <end>");
                }
                String[] fromSplit = firstSplit[1].split("/from", 2);
                String taskName = fromSplit[0].trim();

                // no taskName included
                if (fromSplit.length < 2 || taskName.isEmpty()) {
                    throw new BugsBunnyException("Usage: event <description> /from <start> /to <end>");
                }

                String[] toSplit = fromSplit[1].trim().split("/to", 2);
                String from = toSplit[0].trim();

                // nothing after /from
                if (toSplit.length < 2 || from.isEmpty()) {
                    throw new BugsBunnyException("Usage: event <description> /from <start> /to <end>");
                }

                String to = toSplit[1].trim();

                // nothing after /to
                if (to.isEmpty()) {
                    throw new BugsBunnyException("Usage: event <description> /from <start> /to <end>");
                }

                LocalDateTime fromDateTime;
                LocalDateTime toDateTime;

                try {
                    fromDateTime =
                            DateTimeParser.parseStringToDateTime(
                                    from,
                                    DateTimeParser.INPUT_TO_DATE_TIME);
                } catch (IllegalArgumentException e) {
                    throw new BugsBunnyException(e.getMessage());
                }

                try {
                    toDateTime =
                            DateTimeParser.parseStringToDateTime(
                                    to,
                                    DateTimeParser.INPUT_TO_DATE_TIME);
                } catch (IllegalArgumentException e) {
                    throw new BugsBunnyException(e.getMessage());
                }
                return new AddEventCommand(taskName, fromDateTime, toDateTime);
            }
            default: throw new BugsBunnyException(String.format("I don't understand this command: %s\n" +
                    "You can type 'help' for the list of available commands", input));
        }
    }
}
