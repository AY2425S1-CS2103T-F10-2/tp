package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class AllergyTest {

    @Test
    public void constructor_invalidAllergy_throwsIllegalArgumentException() {
        String invalidAllergy = "";
        assertThrows(IllegalArgumentException.class, () -> new Allergy(invalidAllergy));
    }
    @Test
    public void isValidAllergy() {
        // null allergy
        assertThrows(NullPointerException.class, () -> Allergy.isValidAllergy(null));

        // invalid allergy
        assertFalse(Allergy.isValidAllergy("")); // empty string
        assertFalse(Allergy.isValidAllergy(" ")); // spaces only

        // valid addresses
        assertTrue(Allergy.isValidAllergy("Nuts"));
        assertTrue(Allergy.isValidAllergy("-")); // one character
        assertTrue(Allergy.isValidAllergy("Shellfish, Gluten"));
    }
    @Test
    public void equals() {
        Allergy allergy = new Allergy("Valid Allergy");

        // same values -> returns true
        assertTrue(allergy.equals(new Allergy("Valid Allergy")));

        // same object -> returns true
        assertTrue(allergy.equals(allergy));

        // null -> returns false
        assertFalse(allergy.equals(null));

        // different types -> returns false
        assertFalse(allergy.equals(5.0f));

        // different values -> returns false
        assertFalse(allergy.equals(new Address("Other Valid Allergy")));
    }
}
