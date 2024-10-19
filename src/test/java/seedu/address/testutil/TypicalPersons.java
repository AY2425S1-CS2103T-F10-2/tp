package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withNric("T0181280Z")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withHealthServices("blood test", "vaccination").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withNric("T0101280Z")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withHealthServices("Dental", "General Checkup").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withNric("T0481580Z")
            .withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withHealthServices("General Checkup").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withNric("T0381280Z")
            .withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withHealthServices("General Checkup").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withNric("T0081280F")
            .withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withHealthServices("General Checkup").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withNric("T0100280Z")
            .withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withHealthServices("General Checkup").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withNric("T0181003Z")
            .withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withHealthServices("General Checkup").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withNric("S1234567H")
            .withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withHealthServices("General Checkup").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withHealthServices("General Checkup").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withNric(VALID_NRIC_AMY)
            .withSex(VALID_SEX_AMY).withBirthdate(VALID_BIRTHDATE_AMY)
            .withHealthServices("General Checkup").build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withNric(VALID_NRIC_BOB)
            .withSex(VALID_SEX_BOB).withBirthdate(VALID_BIRTHDATE_BOB)
            .withHealthServices("General Checkup").build();

    public static final Person JAKE = new PersonBuilder().withName("Jake Tio").withPhone("9982131")
            .withEmail("jakers@example.com").withAddress("nus drive")
            .withHealthServices("Blood Test").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
