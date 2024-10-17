package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.healthservice.HealthService;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Sex;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Appt EMPTY_APPT = new Appt(LocalDateTime.of(2020, 12, 25, 12, 0));
    public static final List<Appt> EMPTY_APPT_LIST = Arrays.asList(EMPTY_APPT);
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Nric("T0123456A"), new Birthdate("2001-05-30"),
                    new Sex("M"), getHealthServiceSet("Blood Test")),
            new Person(new Name("Bernice Yu"), new Nric("S0123456B"), new Birthdate("1965-08-09"),
                new Sex("F"), getHealthServiceSet("Vaccination", "Mammogram ")),
            new Person(new Name("Charlotte Oliveiro"), new Nric("M1234457A"), new Birthdate("1990-12-20"),
                new Sex("F"), getHealthServiceSet("Cancer Screening")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<HealthService> getHealthServiceSet(String... strings) {
        return Arrays.stream(strings)
                .map(HealthService::new)
                .collect(Collectors.toSet());
    }

}
