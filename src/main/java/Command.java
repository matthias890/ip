public enum Command {
    TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE;

    public static Command getCommand(String input) throws DeltaException {
        switch (input) {
            case "todo": return TODO;
            case "deadline": return DEADLINE;
            case "event": return EVENT;
            case "list": return LIST;
            case "mark": return MARK;
            case "unmark": return UNMARK;
            case "delete": return DELETE;
            case "bye": return BYE;
            default:
                throw new DeltaException(
                        "Unknown command. Example usages:\n" +
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
