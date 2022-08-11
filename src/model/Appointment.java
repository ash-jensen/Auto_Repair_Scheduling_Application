package model;

import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This class makes Appointment objects and includes getters/setters for id, custId, userId, contactId, title, description, location, type,
 * startTimestamp, and endTimestamp.
 *
 * @author Ashley Jensen
 */
public class Appointment {
    /**
     * Variable used to hold integer of appointment id.
     */
    private int id;
    /**
     * Variable used to hold integer of customer id.
     */
    private int custId;
    /**
     * Variable used to hold integer of user id.
     */
    private int userId;
    /**
     * Variable used to hold integer of contact id.
     */
    private int contactId;
    /**
     * Variable used to hold the String title of an appointment.
     */
    private String title;
    /**
     * Variable used to hold the String of description of an appointment.
     */
    private String description;
    /**
     * Variable used to hold the String of location of an appointment.
     */
    private String location;
    /**
     * Variable used to hold the String of type of appointment.
     */
    private String type;
    /**
     * Variable used to hold the Timestamp of start of an appointment.
     */
    private Timestamp startTimestamp;
    /**
     * Variable used to hold the Timestamp of end of an appointment.
     */
    private Timestamp endTimestamp;
    /**
     * Variable used to hold start as a string
     */
    private String startString;
    /**
     *    Variable used to hold end as a string
     */
    private String endString;
    /**
     * ObservableList of appointment types.
     */
    private static ObservableList<String> apptTypes = observableArrayList();
    /**
     * ObservableList of months of the year.
     */
    private static ObservableList<String> monthsOfYear = observableArrayList();

    /**
     * This is the Appointment constructor with id, custId, userId, contactId, title, description, location, type,
     * startTimestamp, and endTimestamp.
     * @param id sets integer id
     * @param custId sets integer custId
     * @param userId sets integer userId
     * @param contactId sets integer contactId
     * @param title sets String title
     * @param description sets String description
     * @param location sets String location
     * @param type sets String type
     * @param startTimestamp sets Timestamp startTimestamp
     * @param endTimestamp sets Timestamp endTimestamp
     */
    public Appointment(int id, int custId, int userId, int contactId, String title, String description, String location, String type, Timestamp startTimestamp, Timestamp endTimestamp) {
        this.id = id;
        this. custId = custId;
        this.userId = userId;
        this.contactId = contactId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    /**
     * This method returns id.
     * @return integer id
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets id variable to id parameter.
     * @param id sets integer id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns contactId.
     * @return integer contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * This method sets contactId variable to contactId parameter.
     * @param contactId sets integer contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * This method returns custId.
     * @return integer custId
     */
    public int getCustId() {
        return custId;
    }

    /**
     * This method sets custId variable to custId parameter.
     * @param custId sets integer custId
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * This method returns description.
     * @return String description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets description variable to description parameter.
     * @param description sets string description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method returns endDateTime.
     * @return Timestamp endDateTime
     */
    public Timestamp getEndDateTime() {
        return endTimestamp;
    }

    /**
     * This method sets endTimestamp variable to endTimestamp parameter.
     * @param endTimestamp sets Timestamp endTimestamp
     */
    public void setEndDateTime(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    /**
     * This method turns end LocalDateTime into a string and returns it.
     * @return String endString
     */
    public String getEndString() {
        LocalDateTime endDateTime = endTimestamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        endString = endDateTime.format(formatter);
        return endString;
    }

    /**
     * This method returns location.
     * @return String location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method sets location variable to location parameter.
     * @param location sets string location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method returns startTimestamp
     * @return Timestamp startTimestamp
     */
    public Timestamp getStartDateTime() {
        return startTimestamp;
    }

    /**
     * This method sets startTimestamp variable to startTimestamp parameter.
     * @param startTimestamp sets Timestamp startTimestamp
     */
    public void setStartDateTime(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    /**
     * This method turns start LocalDateTime to string and returns it.
     * @return string startString
     */
    public String getStartString() {
        LocalDateTime startDateTime = startTimestamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        startString = startDateTime.format(formatter);
        return startString;
    }

    /**
     * This method returns title.
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method sets title variable to title parameter.
     * @param title sets String title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method returns type.
     * @return String type
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets type variable to type parameter.
     * @param type sets String type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns userId
     * @return integer userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * This method sets userId variable to userId parameter.
     * @param userId sets integer userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * This method takes in a LocalDateTime and changes it eastern time.
     * @param timeToChangeLDT is the LocalDateTime to change to eastern time
     * @return LocalDateTime updatedTime in eastern time
     */
    public static LocalDateTime updateDateTime(LocalDateTime timeToChangeLDT) {
        ZonedDateTime sysDefZDT = timeToChangeLDT.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime estZDT = sysDefZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime updatedTime = estZDT.toLocalDateTime();
        return updatedTime;
    }

    /**
     * This method returns an observableList of strings of appointment types
     * @return ObservableList of strings of appointment types
     */
    public static ObservableList<String> getAllApptTypes() {
        apptTypes.clear();
        apptTypes.add("Planning Session");
        apptTypes.add("De-Briefing");
        apptTypes.add("Meeting");
        apptTypes.add("Code Review");
        apptTypes.add("Other");
        return apptTypes;
    }

    /**
     * This method returns and observableList of strings of the months of the year
     * @return ObservableList of strings of months of the year
     */
    public static ObservableList<String> getMonthsOfYear() {
        monthsOfYear.clear();
        monthsOfYear.add("January");
        monthsOfYear.add("February");
        monthsOfYear.add("March");
        monthsOfYear.add("April");
        monthsOfYear.add("May");
        monthsOfYear.add("June");
        monthsOfYear.add("July");
        monthsOfYear.add("August");
        monthsOfYear.add("September");
        monthsOfYear.add("October");
        monthsOfYear.add("November");
        monthsOfYear.add("December");
        return monthsOfYear;
    }
}
