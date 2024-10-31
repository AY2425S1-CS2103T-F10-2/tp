package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.appointmentdatefilter.AppointmentDateFilter.isValidStartAndEndDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointmentdatefilter.AppointmentDateFilter;
import seedu.address.model.healthservice.HealthService;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Allergy;
import seedu.address.model.patient.AllergyMap;
import seedu.address.model.patient.Appt;
import seedu.address.model.patient.Birthdate;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.ExistingCondition;
import seedu.address.model.patient.HealthRisk;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Note;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Sex;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String nric} into a {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!Nric.isValidNric(trimmedNric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
    }

    /**
     * Parses a {@code String sex} into a {@code Sex}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sex} is invalid.
     */
    public static Sex parseSex(String sex) throws ParseException {
        requireNonNull(sex);
        String trimmedSex = sex.trim();
        if (!Sex.isValidSex(trimmedSex)) {
            throw new ParseException(Sex.MESSAGE_CONSTRAINTS);
        }
        return new Sex(sex);
    }

    /**
     * Parses a {@code String birthDate} into a {@code BirthDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthDate} is invalid.
     */
    public static Birthdate parseBirthDate(String birthDate) throws ParseException {
        requireNonNull(birthDate);
        String trimmedBirthDate = birthDate.trim();
        if (!Birthdate.isValidBirthdate(trimmedBirthDate)) {
            throw new ParseException(Birthdate.MESSAGE_CONSTRAINTS);
        }
        return new Birthdate(birthDate);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        if (address.isEmpty()) {
            return null;
        }
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!AppointmentDateFilter.isValidDate(trimmedDate)) {
            throw new ParseException(AppointmentDateFilter.ONE_DATE_MESSAGE_CONSTRAINTS);
        }
        return AppointmentDateFilter.parseDate(trimmedDate);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate} and checks if startDate is before endDate
     */
    public static LocalDate parseDateAndCheck(String startDateString, LocalDate endDate) throws ParseException {
        LocalDate startDate = parseDate(startDateString);
        if (!isValidStartAndEndDate(startDate, endDate)) {
            throw new ParseException(AppointmentDateFilter.TWO_DATE_MESSAGE_CONSTRAINTS);
        }
        return startDate;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String allergy} into a {@code Allergy}.
     * @throws ParseException if the given {@code allergy} is invalid.
     */
    public static Allergy parseAllergies(String allergy) throws ParseException {
        requireNonNull(allergy);
        String trimmedAllergy = allergy.trim();
        if (!Allergy.isValidAllergy(trimmedAllergy)) {
            throw new ParseException(Allergy.MESSAGE_CONSTRAINTS);
        }
        return new Allergy(trimmedAllergy);
    }

    /**
     * Parses {@code Collection<String> allergy} into a {@code Set<Allergy>}.
     */
    public static AllergyMap parseAllergies(Collection<String> allergies) throws ParseException {
        requireNonNull(allergies);
        final AllergyMap allergyMap = new AllergyMap();
        for (String allergy : allergies) {
            allergyMap.addAllergy(parseAllergies(allergy));
        }
        return allergyMap;
    }

    /**
     * Parses a {@code String bloodType} into an {@code BloodType}.
     * Leading and trailing whitespaces will be trimmed.
     * returns null if the given {@code bloodType} is an empty string
     * @throws ParseException if the given {@code bloodType} is invalid.
     */
    public static BloodType parseBloodType(String bloodType) throws ParseException {
        requireNonNull(bloodType);
        if (bloodType.isEmpty()) {
            return null;
        }
        String trimmedBloodType = bloodType.trim();
        if (!BloodType.isValidBloodType(trimmedBloodType)) {
            throw new ParseException(BloodType.MESSAGE_CONSTRAINTS);
        }
        return new BloodType(trimmedBloodType);
    }

    /**
     * Parses a {@code String existingCondition} into an {@code ExistingCondition}.
     * Leading and trailing whitespaces will be trimmed.
     * returns null if the given {@code existingCondition} is an empty string
     * @throws ParseException if the given {@code existingCondition} is invalid.
     */
    public static ExistingCondition parseExistingCondition(String existingCondition) throws ParseException {
        requireNonNull(existingCondition);
        if (existingCondition.isEmpty()) {
            return null;
        }
        String trimmedExistingCondition = existingCondition.trim();
        if (!ExistingCondition.isValidExistingCondition(trimmedExistingCondition)) {
            throw new ParseException(ExistingCondition.MESSAGE_CONSTRAINTS);
        }
        return new ExistingCondition(trimmedExistingCondition);
    }

    /**
     * Parses a {@code String note} into an {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     * returns null if the given {@code note} is an empty string
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        if (note.isEmpty()) {
            return null;
        }
        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String healthCondition} into an {@code HealthRisk}.
     * Leading and trailing whitespaces will be trimmed.
     * returns null if the given {@code healthCondition} is an empty string
     * @throws ParseException if the given {@code healthCondition} is invalid.
     */
    public static HealthRisk parseHealthRisk(String healthRisk) throws ParseException {
        requireNonNull(healthRisk);
        if (healthRisk.isEmpty()) {
            return null;
        }
        String trimmedHealthRisk = healthRisk.trim();
        if (!HealthRisk.isValidHealthRisk(trimmedHealthRisk)) {
            throw new ParseException(HealthRisk.MESSAGE_CONSTRAINTS);
        }
        return new HealthRisk(trimmedHealthRisk);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseNokName(String name) throws ParseException {
        requireNonNull(name);
        if (name.isEmpty()) {
            return null;
        }
        return parseName(name);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parseNokPhone(String phone) throws ParseException {
        requireNonNull(phone);
        if (phone.isEmpty()) {
            return null;
        }
        return parsePhone(phone);
    }

    /**
     * Parses a {@code String dateTime} into a {@code dateTime}.
     * @param dateTime
     * @return LocalDateTime
     * @throws ParseException
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!Appt.isValidDateTime(dateTime)) {
            throw new ParseException(Appt.DATETIME_MESSAGE_CONSTRAINTS);
        }
        return LocalDateTime.parse(trimmedDateTime, Appt.FORMATTER);
    }

    /**
     * Parses a {@code String healthService} into a {@code HealthService}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code healthService} is invalid.
     */
    public static HealthService parseHealthService(String healthService) throws ParseException {
        requireNonNull(healthService);
        String trimmedHealthService = healthService.trim();
        if (!HealthService.isValidHealthserviceName(trimmedHealthService)) {
            throw new ParseException(HealthService.MESSAGE_CONSTRAINTS);
        }
        return new HealthService(trimmedHealthService);
    }

    /**
     * Parses a {@code String dateTime} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static Appt parseSingleAppt(String dateTimeString, String healthServiceName) throws ParseException {
        requireAllNonNull(dateTimeString, healthServiceName);
        LocalDateTime dateTime = parseDateTime(dateTimeString);
        HealthService healthService = parseHealthService(healthServiceName);
        return new Appt(dateTime, healthService);
    }
}
