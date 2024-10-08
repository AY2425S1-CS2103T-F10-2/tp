package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private Allergy allergy;
    private BloodType bloodType;
    private HealthCondition healthCondition;
    private HealthRecord pastHealthRecord;
    private Note additionalNote;
    private Name nokName;
    private Phone nokPhone;
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Only Name, NRIC, Sex, BirthDate, HealthServices field need to be present.
     * The other fields can be null
     */
    public Person(Name name, Phone phone, Email email, Address address, Allergy allergy, BloodType bloodType,
        HealthCondition healthCondition, HealthRecord healthRecord, Note note, Name nokName, Phone nokPhone) {
        requireAllNonNull(name, phone, email);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.allergy = allergy;
        this.bloodType = bloodType;
        this.healthCondition = healthCondition;
        this.pastHealthRecord = healthRecord;
        this.additionalNote = note;
        this.nokName = nokName;
        this.nokPhone = nokPhone;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Name getNokName() {
        return nokName;
    }

    public Phone getNokPhone() {
        return nokPhone;
    }

    public Allergy getAllergies() {
        return allergy;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public HealthCondition getHealthCondition() {
        return healthCondition;
    }

    public Note getAdditionalNote() {
        return additionalNote;
    }

    public HealthRecord getPastHealthRecord() {
        return pastHealthRecord;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
