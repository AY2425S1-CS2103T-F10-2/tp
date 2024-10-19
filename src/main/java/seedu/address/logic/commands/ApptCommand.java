package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appt;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Changes the remark of an existing person in the address book.
 */
public class ApptCommand extends Command {

    public static final String MESSAGE_ARGUMENTS = "Appt: %2$s, Nric: %1$s";
    public static final String COMMAND_WORD = "appt";
    public static final String MESSAGE_APPT_ADDED_SUCCESS = "Appointment added successfully";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds an appointment to the patient with the given NRIC. "
        + "Format: appt dt/YYYY-MM-DDTHH:MM i/NRIC \n"
        + "Example: " + COMMAND_WORD + " dt/2022-12-31T14:00 i/S1234567A";

    private final LocalDateTime dateTime;
    private final Nric nric;

    /**
     * @param dateTime of the appointment
     * @param name of the person
     */
    public ApptCommand(LocalDateTime dateTime, Nric nric) {
        requireAllNonNull(dateTime, nric);
        this.dateTime = dateTime;
        this.nric = nric;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // Find the person with the given name
        Optional<Person> optionalPerson = lastShownList.stream()
            .filter(person -> person.getNric().equals(nric))
            .findFirst();

        if (!optionalPerson.isPresent()) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        Person person = optionalPerson.get();

        // Add the appointment to the person's list of appointments
        person.addAppt(new Appt(dateTime));

        return new CommandResult(generateSuccessMessage(person));
    }

    /**
     * Returns true if both appt commands have the same nric and dateTime.
     * This defines a stronger notion of equality between two appt commands.
     * @param other appt command
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApptCommand)) {
            return false;
        }

        ApptCommand e = (ApptCommand) other;
        return nric.equals(e.nric)
                && dateTime.equals(e.dateTime);
    }

    /**
     * Generates a command execution success message based on whether the remark is added or deleted.
     */
    private String generateSuccessMessage(Person person) {
        return String.format(MESSAGE_APPT_ADDED_SUCCESS, person.getName());
    }
}
