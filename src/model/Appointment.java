package model;

import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This class Appointments is used to make Diagnostic and Service Appointment objects, and includes getters/setters for id, custId, advisorId, techId, title, concerns, location, type,
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
     * Variable used to hold integer of advisor id.
     */
    private int advisorId;
    /**
     * Variable used to hold integer of tech id.
     */
    private int techId;
    /**
     * Variable used to hold string of type
     */
    private String type;
    /**
     * Variable used to hold the String title of an appointment.
     */
    private String concerns;
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
     * This is the Appointment constructor with id, custId, advisorId, techId, title, concerns, location, type,
     * startTimestamp, and endTimestamp.
     * @param id sets integer id
     * @param custId sets integer custId
     * @param advisorId sets integer advisorId
     * @param techId sets integer techId
     * @param type
     * @param concerns sets String concerns
     * @param startTimestamp sets Timestamp startTimestamp
     * @param endTimestamp sets Timestamp endTimestamp
     */
    public Appointment(int id, int custId, int advisorId, int techId, String type, String concerns, Timestamp startTimestamp, Timestamp endTimestamp) {
        this.id = id;
        this.custId = custId;
        this.advisorId = advisorId;
        this.techId = techId;
        this.type = type;
        this.concerns = concerns;
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
     * This method returns techId.
     * @return integer techId
     */
    public int getTechId() {
        return techId;
    }

    /**
     * This method sets techId variable to techId parameter.
     * @param techId sets integer techId
     */
    public void setTechId(int techId) {
        this.techId = techId;
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
     * This method returns advisorId
     * @return integer advisorId
     */
    public int getAdvisorId() {
        return advisorId;
    }

    /**
     * This method sets advisorId variable to advisorId parameter.
     * @param advisorId sets integer advisorId
     */
    public void setAdvisorId(int advisorId) {
        this.advisorId = advisorId;
    }

    /**
     * This mehtod returns type;
     * @return String type
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets type variable to type parameter;
     * @param type sets String type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns concerns.
     * @return String concerns.
     */
    public String getConcerns() {
        return concerns;
    }

    /**
     * This method sets concerns variable to concerns parameter.
     * @param concerns sets string concerns
     */
    public void setConcerns(String concerns) {
        this.concerns = concerns;
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
