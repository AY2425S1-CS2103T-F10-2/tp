package seedu.address.logic.commands;

/**
 * Represents the keywords that are used in the command line.
 */
public enum CommandKeywords {
    add,
    addf,
    appt,
    clear,
    delete,
    edit,
    exit,
    find,
    help,
    list;

    /**
     * Checks if the given keyword is a valid command keyword.
     *
     * @param keyword The keyword to check.
     * @return True if the keyword is a valid command keyword, false otherwise.
     */
    public static boolean isValidCommandKeyword(String keyword) {
        for (CommandKeywords command : CommandKeywords.values()) {
            if (command.name().equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }
}
