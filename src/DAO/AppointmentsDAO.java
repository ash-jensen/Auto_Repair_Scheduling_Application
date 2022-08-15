package DAO;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Advisor;
import model.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This abstract class is a data access object that gets Appointment data from the database.
 *
 * @author Ashley Jensen
 */
public abstract class AppointmentsDAO {
    private static ObservableList<Appointment> allApptsList = observableArrayList();
    private static ObservableList<Appointment> currMonthList = observableArrayList();
    private static ObservableList<Appointment> currWeekList = observableArrayList();
    private static ObservableList<Appointment> loginApptList = observableArrayList();
    private static ObservableList<Appointment> techApptList = observableArrayList();

    /**
     * This method makes an ObservableList of appointments using data from the database. It gets Appointment_ID,
     * Customer_ID, Advisor_ID, Tech_ID, Concern, Start, and End, and makes an Appointment
     * object with the information. The Appointment is then put into ObservableList allApptsList which
     * is then returned.
     * @return ObservableList allApptsList
     */
    public static ObservableList<Appointment> getAllApptData() {
        try {
            // SQL statement to get all customers from customer table
            String sql = "SELECT * FROM appointments";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Clear apptList
            allApptsList.clear();

            // Set bind variables to create appt object, add appt to list
            while(rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                int custId = rs.getInt("Customer_ID");
                int advisorId = rs.getInt("Advisor_ID");
                int techId = rs.getInt("Tech_ID");
                String concerns = rs.getString("Concerns");
                Timestamp startTimestamp = rs.getTimestamp("Start");
                Timestamp endTimestamp = rs.getTimestamp("End");
                Appointment appt = new Appointment (apptId, custId, advisorId, techId, concerns, startTimestamp, endTimestamp);
                allApptsList.add(appt);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return apptList from db
        return allApptsList;
    }

    /**
     * This method makes an ObservableList of appointments based on the current month using data from the database.
     * It takes in Appointment_ID,Customer_ID, User_ID, Contact_ID, Title, Location, Description, Start, End, and Type
     * and makes an Appointment object with the information. The Appointment is then put into observableList currMonthList
     * which is then returned.
     * @return ObservableList currMonthList
     */
    public static ObservableList<Appointment> getCurrMonthApptData() {
        try {
            // SQL statement to get all customers from customer table
            String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(NOW())";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Clear apptList
            currMonthList.clear();

            // Set bind variables to create appt object, add appt to list
            while(rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                int custId = rs.getInt("Customer_ID");
                int advisorId = rs.getInt("Advisor_ID");
                int techId = rs.getInt("Tech_ID");
                String concerns = rs.getString("Concerns");
                Timestamp startTimestamp = rs.getTimestamp("Start");
                Timestamp endTimestamp = rs.getTimestamp("End");
                Appointment appt = new Appointment (apptId, custId, advisorId, techId, concerns, startTimestamp, endTimestamp);
                currMonthList.add(appt);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return currMonthList from db
        return currMonthList;
    }

    /**
     * This method makes an ObservableList of appointments based on the current week using data from the database.
     * It takes in Appointment_ID,Customer_ID, User_ID, Contact_ID, Title, Location, Description, Start, End, and Type
     * and makes an Appointment object with the information. The Appointment is then put into observableList currWeekList
     * which is then returned.
     * @return ObservableList currWeek
     */
    public static ObservableList<Appointment> getCurrWeekApptData() {
        try {
            // SQL statement to get all customers from customer table
            String sql = "SELECT * FROM appointments WHERE WEEK(start) = WEEK(NOW())";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            // ps.setTimestamp(1, timestampNow);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Clear apptList
            currWeekList.clear();

            // Set bind variables to create appt object, add appt to list
            while(rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                int custId = rs.getInt("Customer_ID");
                int advisorId = rs.getInt("Advisor_ID");
                int techId = rs.getInt("Tech_ID");
                String concerns = rs.getString("Concerns");
                Timestamp startTimestamp = rs.getTimestamp("Start");
                Timestamp endTimestamp = rs.getTimestamp("End");
                Appointment appt = new Appointment (apptId, custId, advisorId, techId, concerns, startTimestamp, endTimestamp);
                currWeekList.add(appt);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return currWeekList from db
        return currWeekList;
    }

    /**
     * This method makes an ObservableList of appointments per Contact_id using data from the database.
     * It takes in Appointment_ID,Customer_ID, User_ID, Contact_ID, Title, Location, Description, Start, End, and Type
     * and makes an Appointment object with the information. The Appointment is then put into observableList contactApptList
     * which is then returned.
     * @param techIdToFind integer of techId to find in database
     * @return ObservableList techApptList
     */
    public static ObservableList<Appointment> getTechApptData(int techIdToFind) {
        try {
            // SQL statement to get all customers from customer table
            String sql = "SELECT * FROM appointments WHERE Tech_ID = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, techIdToFind);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Clear apptList
            techApptList.clear();

            // Set bind variables to create appt object, add appt to list
            while(rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                int custId = rs.getInt("Customer_ID");
                int advisorId = rs.getInt("Advisor_ID");
                int techId = rs.getInt("Tech_ID");
                String concern = rs.getString("Concern");
                Timestamp startTimestamp = rs.getTimestamp("Start");
                Timestamp endTimestamp = rs.getTimestamp("End");
                Appointment appt = new Appointment (apptId, custId, advisorId, techId, concern, startTimestamp, endTimestamp);
                techApptList.add(appt);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return contactApptList from db
        return techApptList;
    }

    /**
     * This method adds an appointment to the database using information gotten from the Advisor and returns the new
     * appointments appointment id. It takes in custId, advisorId, techId, title, description, location, type,
     * startTimestamp, and endTimestamp.
     * @param custId integer custId to add to the appointment in the database
     * @param advisorId integer advisorId to add to the appointment in the database
     * @param techId integer techId to add to the appointment in the database
     * @param concerns String concern to add to the appointment in the database
     * @param startTimestamp Timestamp startTimestamp to add to the appointment in the database
     * @param endTimestamp Timestamp endTimestamp to add to the appointment in the database
     * @return integer apptId
     */
    public static int addAppt(int custId, int advisorId, int techId, String concerns, Timestamp startTimestamp, Timestamp endTimestamp) {
        int apptId = 0;
        int overlappingAppts = overlapCheck(custId, startTimestamp, endTimestamp);

        if (overlappingAppts == 0) {
            try {
                // SQL statement to insert customer in customers table
                String sql = "INSERT INTO appointments (Customer_ID, Advisor_ID, Tech_ID, Concerns, Start, End) VALUES (?, ?, ?, ?, ?, ?)";

                // Get connection to DB and send over the SQL
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                // Call prepared statement setter method to assign bind variables value
                ps.setInt(1, custId);
                ps.setInt(2, advisorId);
                ps.setInt(3, techId);
                ps.setString(4, concerns);
                ps.setTimestamp(5, startTimestamp);
                ps.setTimestamp(6, endTimestamp);

                // Execute the insert, get returned customer id
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                apptId = rs.getInt(1);

            } catch (SQLException throwables) {
                // Catch if errors with SQL
                throwables.printStackTrace();
            }
        }
        else {
            Alert alert;

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Error");
            alert.setContentText("Appointment could not be added because of overlapping appointment(s).");
            alert.showAndWait();
            return apptId;
        }

        // return rowsAffected;
        return apptId;
    }

    /**
     * This method updates an appointment in the database using information gotten form the Advisor and returns the number
     * of rows affected in the database. It takes in apptId to find in the database, then updates custId, advisorId,
     * techId, title, description, location, type, startTimestamp, and endTimestamp.
     * @param apptId intger apptId to find in the database
     * @param custId integer custId to update the appointment in the database
     * @param advisorId integer advisorId to update the appointment in the database
     * @param techId integer techId to update the appointment in the database
     * @param concerns String concerns to update the appointment in the database
     * @param startTimestamp Timestamp startTimestamp to update the appointment in the database
     * @param endTimestamp Timestamp endTimestamp to update the appointment in the database
     * @return integer rowsAffected
     */
    public static int updateAppt(int apptId, int custId, int advisorId, int techId, String concerns, Timestamp startTimestamp, Timestamp endTimestamp ) {
        Alert alert;
        int rowsAffected = 0;
        int overlappingAppts = modifyOverlapCheck(custId, apptId, startTimestamp, endTimestamp);

        if (overlappingAppts == 0) {
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to edit appointment #" + apptId + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // SQL statement to insert customer in customers table
                    String sql = "Update appointments SET Customer_ID = ?, Advisor_ID =?, Tech_ID =?, Concerns =?, Start =?, End =? WHERE Appointment_ID = ?";

                    // Get connection to DB and send over the SQL
                    PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

                    // Call prepared statement setter method to assign bind variables value
                    ps.setInt(1, custId);
                    ps.setInt(2, advisorId);
                    ps.setInt(3, techId);
                    ps.setString(4, concerns);
                    ps.setTimestamp(5, startTimestamp);
                    ps.setTimestamp(6, endTimestamp);
                    ps.setInt(7, apptId);

                    // Execute the update, assign number of rows affected and return
                    rowsAffected = ps.executeUpdate();
                    return rowsAffected;
                } catch (SQLException throwables) {
                    // Catch if errors with SQL
                    throwables.printStackTrace();
                }
            }
        }
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Error");
            alert.setContentText("Appointment could not be updated because of overlapping appointment(s).");
            alert.showAndWait();
            return rowsAffected;
        }

        // return number of rows affected
        return rowsAffected;
    }

    /**
     * This method deletes a row in the database, after confirmation, using a found appointment and returns the number of
     * rows affected. It takes in an appointment apptToDelete and gets the id, then finds that appointment
     * in the database and deletes the row.
     * @param apptToDelete Appointment to find and delete
     * @return integer rowsAffected
     */
    public static int deleteAppt(Appointment apptToDelete) {
        Alert alert;
        int rowsAffected = 0;
        int apptId = apptToDelete.getId();

        // Confirm user wants to delete customer & delete
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete appointment #" + apptId + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // SQL statement to run
                String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

                // Get a connection to DB and send over the SQL
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

                // Call prepared statement setter method to assign bind variables value
                ps.setInt(1, apptId);

                // Var of updated rows to return
                rowsAffected = ps.executeUpdate();
                return rowsAffected;

            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rowsAffected;
    }

    /**
     * This method checks for overlapping appointments for a new appointment in the database and returns the number of
     * overlapping rows. It takes in a customerId and start/end timestamps to check against other appointments the
     * customer has scheduled.
     * @param newCustId integer customerId to check appointments for
     * @param start Timestamp of start time of new appointment
     * @param end Timestamp of end time of new appointment
     * @return integer overlappingRows
     */
    public static int overlapCheck(int newCustId, Timestamp start, Timestamp end) {
        int overlappingRows = 0;

        try {
            // SQL statement to check for customer id with conflicting appointment times
            String sql = "SELECT * FROM appointments WHERE Customer_ID = ? AND (((? >= Start) AND (? < End)) OR " +
                    "((? > START) AND (? <= End)) OR ((? <= Start) AND (? >= End)))";

            // Get connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Call prepared statement setter method to assign bind variables value
            ps.setInt(1, newCustId);
            ps.setTimestamp(2, start);
            ps.setTimestamp(3, start);
            ps.setTimestamp(4, end);
            ps.setTimestamp(5, end);
            ps.setTimestamp(6, start);
            ps.setTimestamp(7, end);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Get and return number of rows that overlap
            while(rs.next()) {
                overlappingRows++;
            }
            return overlappingRows;
        }
        catch (SQLException throwables) {
            // Catch if errors with SQL
            throwables.printStackTrace();
        }

        // return number of rows overlapping
        return overlappingRows;
    }

    /**
     * This method checks for overlapping appointments for a customer in the database while leaving out the appointment
     * that is being modified so that it doesn't interfere with the check, and returns the number of overlapping
     * rows. It takes in a customerId, the existing appointment id to leave out of the check, and start/end timestamps
     * to check against other appointments the customer has scheduled.
     * @param existingCustId integer customerId to check appointments for
     * @param existingApptId integer apptId to leave out of the check
     * @param start Timestamp of start time of appointment
     * @param end Timestamp of end time of appointment
     * @return integer overlappingRows
     */
    public static int modifyOverlapCheck(int existingCustId, int existingApptId, Timestamp start, Timestamp end) {
        int overlappingRows = 0;

        try {
            // SQL statement to check if overlapping appointments
            String sql = "SELECT * FROM appointments WHERE Appointment_ID != ? AND (Customer_ID = ? AND (((? >= Start) AND (? < End)) " +
                    "OR ((? > START) AND (? <= End)) OR ((? <= Start) AND (? >= End))))";

            // Get connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Call prepared statement setter method to assign bind variables value
            ps.setInt(1, existingApptId);
            ps.setInt(2, existingCustId);
            ps.setTimestamp(3, start);
            ps.setTimestamp(4, start);
            ps.setTimestamp(5, end);
            ps.setTimestamp(6, end);
            ps.setTimestamp(7, start);
            ps.setTimestamp(8, end);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Get and return number of rows that overlap
            while(rs.next()) {
                overlappingRows++;
            }
            return overlappingRows;
        }
        catch (SQLException throwables) {
            // Catch if errors with SQL
            throwables.printStackTrace();
        }

        // return number of rows overlapping
        return overlappingRows;
    }

    /**
     * This method checks for appointments scheduled within 15 minutes of the current advisors's login time. It is called
     * once at login, if there is an appointment scheduled it will list the appointment's id, date, and time, if there is
     * not an appointment, it will also inform the user of this.
     */
    public static void apptLoginCheck() {
        Alert alert;
        LocalDateTime ldtNow = LocalDateTime.now();
        LocalDateTime ldtPlusMins = ldtNow.plusMinutes(15);
        Timestamp timestampNow = Timestamp.valueOf(ldtNow);
        Timestamp timestampPlusMins = Timestamp.valueOf(ldtPlusMins);
        Advisor currentAdvisor = AdvisorDAO.getCurrentAdvisor();
        int currentAdvisorId = currentAdvisor.getId();
        String apptsToPrint = "";

        try {
            // SQL statement to insert customer in customers table
            String sql = "SELECT * FROM Appointments WHERE Advisor_ID = ? AND ((Start >= ?) AND (Start <= ?))";


            // Get connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Call prepared statement setter method to assign bind variables value
            ps.setInt(1, currentAdvisorId);
            ps.setTimestamp(2, timestampNow);
            ps.setTimestamp(3, timestampPlusMins);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Clear apptList
            loginApptList.clear();

            // Set bind variables to create appt object, add appt to overlapping appt list
            while(rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                apptsToPrint = (apptsToPrint.concat(apptId + "                ") );
                int custId = rs.getInt("Customer_ID");
                int advisorId = rs.getInt("Adivosr_ID");
                int techId = rs.getInt("Tech_ID");
                String concerns = rs.getString("Concerns");
                Timestamp startTimestamp = rs.getTimestamp("Start");
                LocalDate startDate = (startTimestamp.toLocalDateTime()).toLocalDate();
                apptsToPrint = (apptsToPrint + startDate + "      ");
                LocalTime startTime = (startTimestamp.toLocalDateTime()).toLocalTime();
                apptsToPrint = (apptsToPrint + startTime + " - ");
                Timestamp endTimestamp = rs.getTimestamp("End");
                LocalTime endDateTime = (endTimestamp.toLocalDateTime()).toLocalTime();
                apptsToPrint = (apptsToPrint + endDateTime + "\n");
                Appointment appt = new Appointment (apptId, custId, advisorId, techId, concerns, startTimestamp, endTimestamp);
                loginApptList.add(appt);
            }

            // Check if there are appointments in login appointment list and alert user
            if (loginApptList.isEmpty()) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Reminder");
                alert.setContentText("You do not have any scheduled appointments in the next 15 minutes.");
                alert.showAndWait();
            }
            else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Reminder");
                alert.setContentText("You have the following appointment(s) in the next 15 minutes:\n" +
                        "Appt Id:       Date:                Time:\n" + apptsToPrint);
                alert.showAndWait();
            }
        }
        // Catch if errors with SQL
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method gets the number of appointments scheduled in the database based on the passed in month and appointment
     * type. It takes in a user chosen month and apptType and returns an integer numAppts.
     * @param month is the month the limit the search to
     * @param apptType is the appointment type to limit the search to
     * @return integer numAppts
     */
    public static int getNumAppts(String month, String apptType) {
        int numAppts = 0;
        try {
            // SQL statement to get appointments of month and apptType
            String sql = "SELECT * FROM appointments WHERE MONTHNAME(Start) = ? AND Type = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, month);
            ps.setString(2, apptType);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Count number of rows
            while(rs.next()) {
                numAppts++;
            }
            return numAppts;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return number of appointments
        return numAppts;
    }
}
