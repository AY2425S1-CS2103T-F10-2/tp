package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Appt;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;

/**
 * Deletes an appointment from a patient in the address book.
 * The appointment is identified by the NRIC of the patient and the date and time of the appointment.
 *
 */
public class DeleteApptCommand extends Command {
    public static final String COMMAND_WORD = "deleteappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified appointment for the identified patient\n"
            + "Input \"help " + COMMAND_WORD + "\" for description and usage of this command";

    public static final String MESSAGE_DELETE_APPT_SUCCESS = "Deleted Appointment: %1$s";

    private final LocalDateTime apptDateTime;
    private final Nric nric;

    /**
     * Creates a DeleteApptCommand to delete the specified {@code Appt}
     * @param apptDateTime
     * @param nric
     */
    public DeleteApptCommand(Nric nric, LocalDateTime apptDateTime) {
        this.apptDateTime = apptDateTime;
        this.nric = nric;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> lastShownList = model.getFilteredPatientList();

        Patient patientToDeleteAppt = lastShownList.stream()
                .filter(patient -> patient.getNric().equals(nric))
                .findFirst()
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_PATIENT_NRIC));
        if (patientToDeleteAppt == null) {
            throw new CommandException(Messages.MESSAGE_PATIENT_NOT_FOUND);
        }

        Appt apptToDelete = patientToDeleteAppt.getAppts().stream()
                .filter(appt -> appt.getDateTime().equals(apptDateTime))
                .findFirst()
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_APPT_DATETIME));
        if (apptToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPT_DATETIME);
        }

        patientToDeleteAppt.deleteAppt(apptToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_APPT_SUCCESS, apptToDelete));
    }

    /**
     * Returns true if both DeleteApptCommands have the same appointment date and time and NRIC.
     * This defines a stronger notion of equality between two DeleteApptCommands.
     * @param other DeleteApptCommand
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteApptCommand)) {
            return false;
        }

        DeleteApptCommand otherDeleteApptCommand = (DeleteApptCommand) other;
        return apptDateTime.equals(otherDeleteApptCommand.apptDateTime)
                && nric.equals(otherDeleteApptCommand.nric);
    }

    /**
     * Returns the string representation of the DeleteApptCommand.
     * @return String representation of the DeleteApptCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("appointmentDateTime", apptDateTime)
                .add("targetNric", nric)
                .toString();
    }
}
